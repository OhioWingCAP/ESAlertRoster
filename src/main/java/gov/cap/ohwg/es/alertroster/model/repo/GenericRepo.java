package gov.cap.ohwg.es.alertroster.model.repo;


import com.google.appengine.api.datastore.*;
import gov.cap.ohwg.es.alertroster.model.entity.Identifiable;
import org.apache.commons.lang.ArrayUtils;

import java.beans.Transient;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
        List<Key> keys = new ArrayList<Key>();
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
            if(ArrayUtils.contains(field.getDeclaredAnnotations(), Transient.class)) {
                continue;
            }
            entity.setProperty(field.getName(), field.get(pojo));
        }
        return entity;
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

    public List<T> getMatching(String name, String value) {
        List<T> allItems = new ArrayList<>();
        try {
            DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

            Query q = new Query(type.getSimpleName());
            q.setFilter(new Query.FilterPredicate(name, Query.FilterOperator.EQUAL, value));
            PreparedQuery pq = datastore.prepare(q);
            mapEntities(pq.asIterable(), allItems);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return allItems;
    }
}
