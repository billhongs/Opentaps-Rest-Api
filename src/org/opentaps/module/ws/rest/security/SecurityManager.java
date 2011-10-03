package org.opentaps.module.ws.rest.security;


import org.opentaps.foundation.infrastructure.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: alobrano
 * Date: 9/23/11
 * Time: 7:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class SecurityManager {

    OpentapsRestUserDetails opentapsRestUser;

    public SecurityManager(OpentapsRestUserDetails user) {
        this.opentapsRestUser = user;
    }

    public String getRoles(){
          Collection<GrantedAuthority> authorities = opentapsRestUser.getAuthorities();
          StringBuilder result = new StringBuilder();
          for (GrantedAuthority authority : authorities) {
              result.append(authority).append(",");
          }
          return result.toString();
      }

    public String getUsername(){
        return opentapsRestUser.getUsername();
    }

    public User getOpentapsUser() {
        return opentapsRestUser.getUser();
    }

}
