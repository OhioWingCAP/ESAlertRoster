package gov.cap.ohwg.es.alertroster.config.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by ckovacs on 1/17/16.
 */
public enum AppRole implements GrantedAuthority {
    ADMIN (0),
    NEW_USER (1),
    USER (2);

    private int bit;

    AppRole(int bit) {
        this.bit = bit;
    }

    public String getAuthority() {
        return toString();
    }

    public int getBit() { return bit; }
}