package gov.cap.ohwg.es.alertroster.model.repo;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.CompositeFilter;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.urlfetch.FetchOptions;
import gov.cap.ohwg.es.alertroster.model.entity.Unit;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ckovacs on 4/23/16.
 */
@Component
public class UnitRepo {


    public List<Unit> getUnits() {
        List<Unit> units = new ArrayList<>();
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Query q = new Query("Unit");
        PreparedQuery pq = datastore.prepare(q);
        for (Entity entity : pq.asIterable()) {
            units.add(new Unit(entity));
        }
        return units;
    }

    public void deleteAll() {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Query q = new Query("Unit").setKeysOnly();
        PreparedQuery pq = datastore.prepare(q);
        List<Key> keys = new ArrayList<Key>();
        Iterator<Entity> iterator = pq.asIterator();
        while (iterator.hasNext()) {
            keys.add(iterator.next().getKey());
        }
        datastore.delete(keys);
    }

    public void save(Unit unit) {
        Entity unitEntity = toEntity(unit);
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        datastore.put(unitEntity);
    }

    private Entity toEntity(Unit unit) {
        Entity entity = new Entity("Unit");
        entity.setProperty("charter", unit.getCharter());
        entity.setProperty("name", unit.getName());
        entity.setProperty("parentCharter", unit.getParentCharter());
        return entity;
    }

}
