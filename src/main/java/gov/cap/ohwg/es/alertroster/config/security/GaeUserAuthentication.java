package gov.cap.ohwg.es.alertroster.config.security;

import edu.emory.mathcs.backport.java.util.Collections;
import gov.cap.ohwg.es.alertroster.model.pojo.GaeUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by ckovacs on 1/17/16.
 */
@Component
public class GaeUserAuthentication implements Authentication {

    private Collection<? extends GrantedAuthority> authorities;
    private Object credentials;
    private Object details;
    private Object principal;
    private boolean authenticated;
    private String name;

    public GaeUserAuthentication() {
    }

    public GaeUserAuthentication(GaeUser user, Object details) {
        this.principal = user;
        this.details = details;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities == null ? Collections.emptySet() : this.authorities;
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getDetails() {
        return this.details;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated=isAuthenticated;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
