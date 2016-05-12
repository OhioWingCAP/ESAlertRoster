package gov.cap.ohwg.es.alertroster.model.pojo;

import com.google.appengine.api.datastore.Query;

/**
 * Created by ckovacs on 5/12/16.
 */
public class Sort {
    private String field;
    private Query.SortDirection direction;

    public Sort() {
    }

    public Sort(String field, Query.SortDirection direction) {
        this.direction = direction;
        this.field = field;
    }

    public Query.SortDirection getDirection() {
        return direction;
    }

    public void setDirection(Query.SortDirection direction) {
        this.direction = direction;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
