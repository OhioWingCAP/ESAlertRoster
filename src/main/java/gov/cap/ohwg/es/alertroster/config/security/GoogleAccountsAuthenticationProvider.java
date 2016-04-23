package gov.cap.ohwg.es.alertroster.config.security;

import com.google.appengine.api.users.User;
import gov.cap.ohwg.es.alertroster.model.pojo.GaeUser;
import gov.cap.ohwg.es.alertroster.service.user.GaeDatastoreUserRegistry;
import gov.cap.ohwg.es.alertroster.service.user.UserRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

/**
 * Created by ckovacs on 1/17/16.
 */
@Component
public class GoogleAccountsAuthenticationProvider extends PreAuthenticatedAuthenticationProvider {

    private final GaeDatastoreUserRegistry userRegistry;

    @Autowired
    public GoogleAccountsAuthenticationProvider(GaeDatastoreUserRegistry userRegistry) {
        this.userRegistry = userRegistry;
        setPreAuthenticatedUserDetailsService(userRegistry);
    }

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User googleUser = (User) authentication.getPrincipal();

        GaeUser user = userRegistry.findUser(googleUser.getUserId());

        if (user == null) {
            // User not in registry. Needs to register
            user = new GaeUser(googleUser.getUserId(), googleUser.getNickname(), googleUser.getEmail());
            user.getAuthorities().add(AppRole.NEW_USER);
        }

        if (!user.isEnabled()) {
            throw new DisabledException("Account is disabled");
        }

        return new GaeUserAuthentication(user, authentication.getDetails());
    }

}

