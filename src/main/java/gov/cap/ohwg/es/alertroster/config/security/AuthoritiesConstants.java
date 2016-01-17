package gov.cap.ohwg.es.alertroster.config.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    public static final GrantedAuthority NEW_USER = new SimpleGrantedAuthority("NEW_USER");

    private AuthoritiesConstants() {
    }

    public static final String ADMIN = "ROLE_eDiscountAdmin";

    public static final String CREATOR = "ROLE_eDiscountRequestCreators";

    public static final String USER = "ROLE_eDiscountTeam";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";
}
