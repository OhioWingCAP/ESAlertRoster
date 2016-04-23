package gov.cap.ohwg.es.alertroster.model.pojo;

import gov.cap.ohwg.es.alertroster.config.security.AppRole;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ckovacs on 1/17/16.
 */
public class GaeUser implements Serializable, UserDetails {
    private final String userId;
    private final String email;
    private final String nickname;
    private final String forename;
    private final String surname;
    private Set<AppRole> authorities = new HashSet<>();
    private final boolean enabled;

    public GaeUser(String userId, String email, String nickname, String forename, String surname, boolean enabled) {
        this.userId = userId;
        this.email = email;
        this.nickname = nickname;
        this.forename = forename;
        this.surname = surname;
        this.enabled = enabled;
    }

    public GaeUser(String userId, String nickname, String email) {
        this(userId, email,nickname,"","",true);
    }

    public GaeUser(String name, String nickname, String email, String forename, String surname, Set<AppRole> roles, Boolean enabled) {
        this.userId = name;
        this.nickname = nickname;
        this.email = email;
        this.forename = forename;
        this.surname = surname;
        this.authorities = roles;
        this.enabled = enabled;
    }

    public Set<AppRole> getAuthorities() {
        return authorities;
    }

    public String getEmail() {
        return email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getForename() {
        return forename;
    }

    public String getNickname() {
        return nickname;
    }

    public String getSurname() {
        return surname;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return getUserId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }
}