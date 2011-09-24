package org.opentaps.module.ws.rest.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: alobrano
 * Date: 9/23/11
 * Time: 7:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class OpentapsPermissions implements Permission {

    private User springUser;

    public OpentapsPermissions(User user) {
        this.springUser = user;
    }

    public String getUsername(){
        return springUser.getUsername();
    }

    public String getRoles(){
        Collection<GrantedAuthority> authorities = springUser.getAuthorities();
        StringBuilder result = new StringBuilder();
        for (GrantedAuthority authority : authorities) {
            result.append(authority).append(",");
        }
        return result.toString();
    }

}
