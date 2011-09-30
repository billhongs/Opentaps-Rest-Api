package org.opentaps.module.ws.rest.provider;

import org.opentaps.module.ws.rest.Context.OpentapsContext;
import org.opentaps.module.ws.rest.Context.OpentapsContextImpl;
import org.opentaps.module.ws.rest.security.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * Created by IntelliJ IDEA.
 * User: alobrano
 * Date: 9/30/11
 * Time: 7:30 PM
 * To change this template use File | Settings | File Templates.
 */
@Provider
public class OpentapsContextResolver implements ContextResolver<OpentapsContext> {

     @Context
     SecurityContext securityContext;

    public OpentapsContext getContext(Class<?> aClass) {

        UsernamePasswordAuthenticationToken userPrincipal = (UsernamePasswordAuthenticationToken) securityContext.getUserPrincipal();
        OpentapsRestUserDetails principal = (OpentapsRestUserDetails) userPrincipal.getPrincipal();
        OpentapsContext opentapsContext = new OpentapsContextImpl(principal);

        return opentapsContext;
    }
}
