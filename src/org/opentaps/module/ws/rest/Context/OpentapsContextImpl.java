package org.opentaps.module.ws.rest.Context;

import org.ofbiz.entity.Delegator;
import org.ofbiz.entity.DelegatorFactory;
import org.ofbiz.service.GenericDispatcher;
import org.opentaps.domain.DomainsDirectory;
import org.opentaps.domain.DomainsLoader;
import org.opentaps.foundation.infrastructure.Infrastructure;
import org.opentaps.foundation.infrastructure.User;
import org.opentaps.module.ws.rest.security.*;
import org.opentaps.module.ws.rest.security.SecurityManager;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: alobrano
 * Date: 9/30/11
 * Time: 7:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class OpentapsContextImpl implements OpentapsContext{

    private final static String DELEGATOR_NAME = "default";
    private static String DISPATCHER_NAME = "default";
    private DomainsDirectory domains;

    private org.opentaps.module.ws.rest.security.SecurityManager securityManager;

    public OpentapsContextImpl(OpentapsRestUserDetails user) {
        securityManager = new SecurityManager(user);
    }

    public org.opentaps.module.ws.rest.security.SecurityManager getSecurityManager() {
        return securityManager;
    }

    public DomainsDirectory getDomainDirectory(){
        if(domains==null){
            Delegator delegator = DelegatorFactory.getDelegator(DELEGATOR_NAME);
            Infrastructure infrastructure = new Infrastructure(GenericDispatcher.getLocalDispatcher(DISPATCHER_NAME, delegator));
            //todo if the security manager is null or there is not user we should not process this!! check it at a later stage
            DomainsLoader domainsLoader = new DomainsLoader(infrastructure,securityManager.getOpentapsUser());
            domains = domainsLoader.getDomainsDirectory();
        }
        return domains;
    }

}
