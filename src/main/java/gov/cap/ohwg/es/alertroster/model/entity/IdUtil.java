package gov.cap.ohwg.es.alertroster.model.entity;

import java.lang.reflect.Field;

/**
 * Created by ckovacs on 5/12/16.
 */
public class IdUtil {
    public static Long generateHash(Object entity) {
        if(entity == null) {
            return null;
        }
        long hashCode = 1;
        for(Field f : entity.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            Object obj = null;
            try {
                obj = f.get(entity);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode());
        }
        return hashCode;
    }
}
