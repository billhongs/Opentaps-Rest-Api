package org.opentaps.module.ws.rest.resources;

import org.dozer.Mapper;
import org.ofbiz.base.util.Debug;
import org.opentaps.module.ws.rest.errors.NoPermissionException;
import org.opentaps.module.ws.rest.security.*;

/**
 * Created by IntelliJ IDEA.
 * User: alobrano
 * Date: 10/5/11
 * Time: 12:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class ApiAbstractResource {
    private static final String MODULE = ApiAbstractResource.class.getName();
    protected Mapper mapper;


    public void checkPermission(org.opentaps.module.ws.rest.security.SecurityManager securityManager, String minimumPermission,String resource) throws NoPermissionException {
        Debug.logInfo("USERNAME [" + securityManager.getUsername() + "] ROLES [" + securityManager.getRoles() + "] ", MODULE);
        //todo throw exception if the user does not have the right permission
        if(false){
            throw new NoPermissionException(resource);
        }
    }

//    GETTERS AND SETTERS

    public Mapper getMapper() {
        return mapper;
    }

    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }
}
