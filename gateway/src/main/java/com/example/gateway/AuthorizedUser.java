package com.example.gateway;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * <pre>
 * <div><strong>Project name:</strong> DemoOAuth2 </div>
 * <div><strong>Creation date:</strong> 2025-09-23 </div>
 * </pre>
 *
 * @author Ivannikov Alexey
 * @since 1.0.0
 */
public class AuthorizedUser extends User {

    public AuthorizedUser(
        String username, String password,
        Collection<? extends GrantedAuthority> authorities
    ) {
        super(username, password, authorities);
    }

    public AuthorizedUser(
        String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
        boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities
    ) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
