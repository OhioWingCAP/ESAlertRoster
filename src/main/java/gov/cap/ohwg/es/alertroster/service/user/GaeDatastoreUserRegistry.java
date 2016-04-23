package gov.cap.ohwg.es.alertroster.service.user;

import com.google.appengine.api.datastore.*;
import gov.cap.ohwg.es.alertroster.config.security.AppRole;
import gov.cap.ohwg.es.alertroster.model.pojo.GaeUser;
import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

@Component
public class GaeDatastoreUserRegistry implements UserRegistry, UserDetailsService, AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {
    private static final String USER_TYPE = "GaeUser";
    private static final String USER_FORENAME = "forename";
    private static final String USER_SURNAME = "surname";
    private static final String USER_NICKNAME = "nickname";
    private static final String USER_EMAIL = "email";
    private static final String USER_ENABLED = "enabled";
    private static final String USER_AUTHORITIES = "authorities";

    private static Logger logger = Logger.getLogger(GaeDatastoreUserRegistry.class);

    public GaeUser findUser(String userId) {
        Key key = KeyFactory.createKey(USER_TYPE, userId);
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        try {
            Entity user = datastore.get(key);

            long binaryAuthorities = (Long) user.getProperty(USER_AUTHORITIES);
            Set<AppRole> roles = EnumSet.noneOf(AppRole.class);

            for (AppRole r : AppRole.values()) {
                if ((binaryAuthorities & (1 << r.getBit())) != 0) {
                    roles.add(r);
                }
            }

            GaeUser gaeUser =
                new GaeUser(
                    user.getKey().getName(),
                    (String) user.getProperty(USER_NICKNAME),
                    (String) user.getProperty(USER_EMAIL),
                    (String) user.getProperty(USER_FORENAME),
                    (String) user.getProperty(USER_SURNAME),
                    roles,
                    (Boolean) user.getProperty(USER_ENABLED));


            return gaeUser;

        } catch (EntityNotFoundException e) {
            logger.debug(userId + " not found in datastore");
            return null;
        }
    }

    public void registerUser(GaeUser newUser) {
        Key key = KeyFactory.createKey(USER_TYPE, newUser.getUserId());
        Entity user = new Entity(key);
        user.setProperty(USER_EMAIL, newUser.getEmail());
        user.setProperty(USER_NICKNAME, newUser.getNickname());
        user.setProperty(USER_FORENAME, newUser.getForename());
        user.setProperty(USER_SURNAME, newUser.getSurname());
        user.setUnindexedProperty(USER_ENABLED, newUser.isEnabled());

        Collection<? extends GrantedAuthority> roles = newUser.getAuthorities();

        long binaryAuthorities = 0;

        for (GrantedAuthority r : roles) {
            binaryAuthorities |= 1 << ((AppRole) r).getBit();
        }

        user.setUnindexedProperty(USER_AUTHORITIES, binaryAuthorities);

        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        datastore.put(user);
    }

    public void removeUser(String userId) {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Key key = KeyFactory.createKey(USER_TYPE, userId);

        datastore.delete(key);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        GaeUser user = findUser(username);
        return user;
    }

    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {
        GaeUser user = findUser(token.getName());
        return user;
    }
}