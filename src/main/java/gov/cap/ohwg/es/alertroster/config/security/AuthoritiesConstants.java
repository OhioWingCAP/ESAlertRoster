package gov.cap.ohwg.es.alertroster.config.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    private AuthoritiesConstants() {
    }

    public static final String ADMIN = "ROLE_eDiscountAdmin";

    public static final String CREATOR = "ROLE_eDiscountRequestCreators";

    public static final String USER = "ROLE_eDiscountTeam";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";
}
