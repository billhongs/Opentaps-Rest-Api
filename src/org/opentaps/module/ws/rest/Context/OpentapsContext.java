package org.opentaps.module.ws.rest.Context;


import org.opentaps.domain.DomainsDirectory;

/**
 * Created by IntelliJ IDEA.
 * User: alobrano
 * Date: 9/30/11
 * Time: 7:27 PM
 * To change this template use File | Settings | File Templates.
 */
public interface OpentapsContext {

    org.opentaps.module.ws.rest.security.SecurityManager getSecurityManager();
    DomainsDirectory getDomainDirectory();

}
