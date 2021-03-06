package gov.cap.ohwg.es.alertroster.model.repo;


import com.google.appengine.api.datastore.*;
import com.google.appengine.repackaged.com.google.common.collect.Lists;
import gov.cap.ohwg.es.alertroster.model.entity.Identifiable;
import gov.cap.ohwg.es.alertroster.model.pojo.Sort;
import org.apache.commons.lang.ArrayUtils;

import java.beans.Transient;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by ckovacs on 4/23/16.
 */
public class GenericRepo<T extends Identifiable> {

    private final Class<T> type;

    public GenericRepo(Class<T> type) {
        this.type = type;
    }

    public List<T> getAll() {
        List<T> allItems = new ArrayList<>();
        try {
            DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

            Query q = new Query(type.getSimpleName());
            PreparedQuery pq = datastore.prepare(q);
            mapEntities(pq.asIterable(), allItems);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return allItems;
    }

    protected List<T> getAll(Query.Filter filter, Sort... sort) {
        List<T> allItems = new ArrayList<>();
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        Query q = new Query(type.getSimpleName());
        q.setFilter(filter);
        for(Sort s : sort) {
            q.addSort(s.getField(), s.getDirection());
        }
        PreparedQuery pq = datastore.prepare(q);
        try {
            mapEntities(pq.asIterable(), allItems);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return allItems;
    }

    private void mapEntities(Iterable<Entity> entities, List<T> allItems) throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        for (Entity entity : entities) {
            T resultObject = mapProperties(entity);
            allItems.add(resultObject);
        }
    }

    private T mapProperties(Entity entity) throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        T result = type.newInstance();
        for (Map.Entry<String, Object> entry : entity.getProperties().entrySet()) {
            Field f = type.getDeclaredField(entry.getKey());
            f.setAccessible(true);
            f.set(result, entry.getValue());
        }
        return result;
    }

    public void deleteAll() {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Query q = new Query(type.getSimpleName()).setKeysOnly();
        PreparedQuery pq = datastore.prepare(q);
        List<Key> keys = new ArrayList<>();
        Iterator<Entity> iterator = pq.asIterator();
        while (iterator.hasNext()) {
            keys.add(iterator.next().getKey());
        }
        datastore.delete(keys);
    }

    public void save(T pojo) {
        Entity entity = null;
        try {
            entity = mapEntity(pojo);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        datastore.put(entity);
    }

    private Entity mapEntity(T pojo) throws IllegalAccessException {
        Entity entity = new Entity(type.getSimpleName(), pojo.getId());
        for(Field field : type.getDeclaredFields()) {
            field.setAccessible(true);
            if(isTransient(field)) {
                continue;
            }
            entity.setProperty(field.getName(), field.get(pojo));
        }
        return entity;
    }

    private boolean isTransient(Field field) {
        String fieldName = field.getName();
        try {
            Method method = type.getMethod("get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1));
            for(Annotation annotation : method.getDeclaredAnnotations()) {
                if(annotation instanceof Transient) {
                    return ((Transient)annotation).value();
                }
            }
        } catch (NoSuchMethodException e) {
            return false;
        }
        return false;
    }

    public T get(long id) {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        try {
            Entity entity = datastore.get(KeyFactory.createKey(type.getSimpleName(), id));
            return mapProperties(entity);
        } catch (Exception e) {
            return null;
        }
    }

    public List<T> getMatching(String name, Object value) {
        return getMatching(name, value, Query.FilterOperator.EQUAL);
    }

    public List<T> getMatching(String name, Object value, Query.FilterOperator operator) {
        List<T> allItems = new ArrayList<>();
        try {
            DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

            Query q = new Query(type.getSimpleName());
            q.setFilter(new Query.FilterPredicate(name, operator, value));
            PreparedQuery pq = datastore.prepare(q);
            mapEntities(pq.asIterable(), allItems);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return allItems;
    }



}
