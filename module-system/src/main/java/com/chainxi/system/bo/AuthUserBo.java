package com.chainxi.system.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserBo implements UserDetails {
    @Serial
    private static final long serialVersionUID = -1L;
    private Long userId;
    private Long clientId;
    @JsonIgnore
    private String userName;
    @JsonIgnore
    private String password;
    private Collection<Long> deptIds;
    private Collection<AuthRoleBo> roles;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles != null ?
                roles
                        .stream()
                        .map(role -> new SimpleGrantedAuthority(String.valueOf(role.getId())))
                        .collect(Collectors.toList()) :
                null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
