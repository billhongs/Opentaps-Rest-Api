package org.opentaps.module.ws.rest.resources.common;

import org.apache.wink.common.annotations.Workspace;
import org.ofbiz.base.util.Debug;
import org.ofbiz.base.util.UtilMisc;
import org.ofbiz.base.util.UtilValidate;
import org.ofbiz.entity.GenericDelegator;
import org.ofbiz.entity.GenericValue;
import org.ofbiz.service.GenericDispatcher;
import org.ofbiz.service.LocalDispatcher;

import org.opentaps.base.entities.UserLogin;
import org.opentaps.base.services.CreateProductService;
import org.opentaps.base.services.UserLoginService;
import org.opentaps.domain.product.Product;
import org.opentaps.foundation.entity.EntityNotFoundException;
import org.opentaps.foundation.infrastructure.Infrastructure;
import org.opentaps.foundation.infrastructure.User;
import org.opentaps.foundation.repository.RepositoryException;
import org.opentaps.foundation.repository.ofbiz.Repository;
import org.opentaps.foundation.service.ServiceException;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;


public class CommonResource {

    protected static String MODULE = CommonResource.class.getName();

    private static String DELEGATOR = "default";
    private static String DISPATCHER = "default";

    protected Infrastructure infrastructure = null;
    protected Repository repository = null;


    public CommonResource() {
        infrastructure = getInfrastructure();
        repository = getRepository(infrastructure);
    }


    protected Boolean login(String username, String password, Infrastructure infrastructure) {
        UserLoginService userLoginService = new UserLoginService();
        userLoginService.setInLoginUsername(username);
        userLoginService.setInLoginPassword(password);

        try {
            userLoginService.runSync(infrastructure);
        } catch (ServiceException e) {
            Debug.logError(e, MODULE);
        }

        if (!userLoginService.getOutResponseMessage().equals("success")) {
            Debug.logError("User cannot be authenticated", MODULE);
            //todo we should return an error message
            throw new WebApplicationException(Response.Status.FORBIDDEN);
        }

        return Boolean.TRUE;
    }


    protected User getUser(String username) {
        UserLogin userLogin = null;
        User user = null;

        try {
            userLogin = repository.findOneNotNull(UserLogin.class, repository.map(UserLogin.Fields.userLoginId, username));

        } catch (RepositoryException e) {
            Debug.logError("Error retrieving the repository", MODULE);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);

        } catch (EntityNotFoundException e) {
            Debug.logError("User cannot be found", MODULE);
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }


        try {
            user = new User(Repository.genericValueFromEntity(userLogin));
        } catch (RepositoryException e) {
            Debug.logError("User cannot be found", MODULE);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }

        user.setDelegator((GenericDelegator) GenericDelegator.getGenericDelegator(DELEGATOR));

        return user;

    }


    protected Infrastructure getInfrastructure() {
        GenericDelegator delegator = (GenericDelegator) GenericDelegator.getGenericDelegator(DELEGATOR);
        LocalDispatcher dispatcher = GenericDispatcher.getLocalDispatcher(DISPATCHER, delegator);
        Infrastructure infrastructure = new Infrastructure(dispatcher);
        return infrastructure;
    }


    protected Repository getRepository(Infrastructure infrastructure) {
        Repository repository = new Repository();
        repository.setInfrastructure(infrastructure);
        return repository;

    }

}
