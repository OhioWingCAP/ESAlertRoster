package gov.cap.ohwg.es.alertroster.service.user;

import gov.cap.ohwg.es.alertroster.model.pojo.GaeUser;

/**
 * Created by ckovacs on 1/17/16.
 */
public interface UserRegistry {
    GaeUser findUser(String userId);
    void registerUser(GaeUser newUser);
    void removeUser(String userId);
}