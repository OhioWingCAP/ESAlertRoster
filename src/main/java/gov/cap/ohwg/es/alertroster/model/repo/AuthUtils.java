package gov.cap.ohwg.es.alertroster.model.repo;

import gov.cap.ohwg.es.alertroster.model.pojo.GaeUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by ckovacs on 5/12/16.
 */
@Service
public class AuthUtils {
    public GaeUser getLoggedInUser() {
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(obj instanceof GaeUser) {
            return (GaeUser)obj;
        }
        return null;
    }

    public <T> T checkAuth(Long orgId, T object) {
        GaeUser user = getLoggedInUser();
        if(user == null) {
            return null;
        }
        if(user.getUnitAccess().contains(orgId)) {
            return object;
        }
        return null;
    }
}
