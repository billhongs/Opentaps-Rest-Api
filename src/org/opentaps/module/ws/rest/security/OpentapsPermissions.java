package org.opentaps.module.ws.rest.security;

import org.opentaps.foundation.infrastructure.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: alobrano
 * Date: 9/23/11
 * Time: 7:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class OpentapsPermissions implements Permission {

    private OpentapsRestUserDetails opentapsRestUserDetails;

    public OpentapsPermissions(OpentapsRestUserDetails user) {
        this.opentapsRestUserDetails = user;
    }

    public String getUsername(){
        return opentapsRestUserDetails.getUsername();
    }

    public String getRoles(){
        Collection<GrantedAuthority> authorities = opentapsRestUserDetails.getAuthorities();
        StringBuilder result = new StringBuilder();
        for (GrantedAuthority authority : authorities) {
            result.append(authority).append(",");
        }
        return result.toString();
    }

    private User getOpentapsUser(){
        return opentapsRestUserDetails.getUser();
    }

}
