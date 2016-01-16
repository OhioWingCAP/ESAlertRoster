package gov.cap.ohwg.es.alertroster.config.security.identity;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by ckovacs on 7/6/15.
 */
public class WeblogicAuthority implements GrantedAuthority {
    private final String name;

    public WeblogicAuthority(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + name;
    }
}
