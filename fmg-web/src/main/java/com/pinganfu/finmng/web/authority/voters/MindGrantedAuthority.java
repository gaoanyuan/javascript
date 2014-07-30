package com.pinganfu.finmng.web.authority.voters;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * Created by anygao on 14-7-22.
 */
public class MindGrantedAuthority implements GrantedAuthority {
    private List<String> groups;
    private String user;
    private List<String> roles;
    private List<String> authorities;
    @Override
    public String getAuthority() {
        return null;
    }
}
