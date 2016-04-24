
import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails {
    private String username;
    private String password;
    private Collection<GrantedAuthority> authorities;

    public User(String username, String password, String roles) {
        super();
        this.username = username;
        this.password = password;
        setRoles(roles);
    }

    public void setRoles(final String roles) {
        authorities = new HashSet<GrantedAuthority>();


        for (final String role : roles.split(",")) {
            int i=(role.trim()).length();
            if (i==0){continue;}
            SimpleGrantedAuthority grandAuthority = new SimpleGrantedAuthority(role.trim());
            grandAuthority.getAuthority();
            authorities.add(grandAuthority);
                }

        }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

}