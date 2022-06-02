package com.sendriods.demo.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sendriods.demo.Domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private final Long id;

    private final String name;

    private final Integer age;

    private final String email;

    @JsonIgnore
    private final String passwd;

    private final Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String name, Integer age, String email, String passwd,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.passwd = passwd;
        this.authorities = authorities;

    }

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getDivisionSet().stream()
                .map(division -> new SimpleGrantedAuthority(division.getDivisionName()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getAge(),
                user.getEmail(),
                user.getPasswd(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return passwd;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }

    public Integer getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }
}
