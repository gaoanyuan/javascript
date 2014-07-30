package com.pinganfu.finmng.web.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by anygao on 14-7-17.
 */
public class UserDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new MyUserDetails(username);
    }

    static class MyUserDetails implements UserDetails {
        private String userName;
        private static List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();
        static {
            authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        MyUserDetails(String userName) {
            this.userName = userName;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorityList;
        }

        @Override
        public String getPassword() {
            return "123";
        }

        @Override
        public String getUsername() {
            return userName;
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
    }
}
