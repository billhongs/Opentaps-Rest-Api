package org.opentaps.module.ws.rest.provider;

import org.opentaps.module.ws.rest.security.*;
import org.opentaps.module.ws.rest.security.SecurityManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * Created by IntelliJ IDEA.
 * User: alobrano
 * Date: 9/23/11
 * Time: 7:00 PM
 * To change this template use File | Settings | File Templates.
 */
@Provider
public class SecurityContextResolver implements ContextResolver<Permission>{

     @Context
    SecurityContext securityContext;

    public Permission getContext(Class<?> aClass) {
         //todo potentially we could receive an opentaps object here which encapsulate the ofbiz user and the spring user
        Permission permission = SecurityManager.getSecurityManager().getPermission(
               (User) ((UsernamePasswordAuthenticationToken)securityContext.getUserPrincipal()).getPrincipal());

        return permission;
    }
}
