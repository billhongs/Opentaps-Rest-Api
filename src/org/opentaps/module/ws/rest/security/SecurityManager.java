package org.opentaps.module.ws.rest.security;

import org.springframework.security.core.userdetails.User;

/**
 * Created by IntelliJ IDEA.
 * User: alobrano
 * Date: 9/23/11
 * Time: 7:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class SecurityManager {

    private static SecurityManager securityManager;

    private SecurityManager(){

    }

    public static SecurityManager getSecurityManager(){
          if(securityManager == null){
              securityManager = new SecurityManager();
          }
        return securityManager;
    }

    public Permission getPermission(OpentapsRestUserDetails user){
        return new OpentapsPermissions(user);
    }
}
