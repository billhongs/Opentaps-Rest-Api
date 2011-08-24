package org.opentaps.module.ws.rest.resources.common;

import org.ofbiz.base.util.Debug;
import org.ofbiz.base.util.UtilMisc;
import org.ofbiz.base.util.UtilValidate;
import org.ofbiz.entity.GenericDelegator;
import org.ofbiz.entity.GenericValue;
import org.ofbiz.service.GenericDispatcher;
import org.ofbiz.service.LocalDispatcher;

import org.opentaps.base.entities.UserLogin;
import org.opentaps.base.services.UserLoginService;
import org.opentaps.foundation.entity.EntityNotFoundException;
import org.opentaps.foundation.infrastructure.Infrastructure;
import org.opentaps.foundation.infrastructure.InfrastructureException;
import org.opentaps.foundation.infrastructure.User;
import org.opentaps.foundation.repository.RepositoryException;
import org.opentaps.foundation.repository.ofbiz.Repository;
import org.opentaps.foundation.service.ServiceException;

import javax.ws.rs.*;
import javax.ws.rs.core.*;


public class CommonResource {

    protected static String MODULE = CommonResource.class.getName();

    private static String DELEGATOR = "default";
    private static String DISPATCHER = "default";

    protected GenericDelegator delegator;
    protected LocalDispatcher dispatcher;
    protected Infrastructure infrastructure = null;
    protected Repository repository = null;

    protected User user = null;


    public CommonResource() {
        delegator = GenericDelegator.getGenericDelegator(DELEGATOR);
        dispatcher = GenericDispatcher.getLocalDispatcher(DISPATCHER, delegator);
        infrastructure = new Infrastructure(dispatcher);

        repository = new Repository();
        repository.setInfrastructure(infrastructure);
    }


    protected Boolean login(String username, String password) {
        UserLoginService userLoginService = new UserLoginService();
        userLoginService.setInLoginUsername(username);
        userLoginService.setInLoginPassword(password);

        try {
            userLoginService.runSync(infrastructure);
        } catch (ServiceException e) {
//            Debug.logError(e, MODULE);
            Debug.logError("runSync: " + e.getMessage(), MODULE);
            return false;
        }

        if (!userLoginService.getOutResponseMessage().equals("success")) {
            Debug.logError("User cannot be authenticated: " + username, MODULE);
            // TODO we should return an error message
//            throw new WebApplicationException(Response.Status.FORBIDDEN);
            return false;
        }

        try {
            user = getUser("APIF8C702B8"); // 33a41e802f428972a03107d392644713
//            Debug.logInfo("User ID: " + user.getUserId(), MODULE);
            Debug.logInfo("User Permissions: " + user.getPermissions(), MODULE);
        } catch (InfrastructureException e) {
            Debug.logError("Error retrieving User Permissions", MODULE);
        }

        return true;
//        return userLoginService.getUser();
    }


    protected User getUser(String username) {
        UserLogin userLogin = null;
        User user = null;

        try {
            userLogin = repository.findOneNotNull(UserLogin.class, repository.map(UserLogin.Fields.userLoginId, username));
/*
            try {
                String userId = user.getUserId();
                Debug.logInfo("User found, ID: " + userId, MODULE);
            } catch (InfrastructureException e) {
                Debug.logError("Error retrieving User ID", MODULE);
            }
*/
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
        return infrastructure;
    }


    protected Repository getRepository() {
        return repository;
    }


    protected Boolean checkAccess(HttpHeaders requestHeaders) {
        String username = getRequestHeader(requestHeaders, "username");
        String password = getRequestHeader(requestHeaders, "password");
        String token = getRequestHeader(requestHeaders, "token");

        Debug.logInfo("AK47 - checkAccess: username: " + username + ", password: " + password + ", token: " + token, MODULE);

        if (token != null) {
            //todo use token to provide access, for now assume user is not authorized
            Debug.logInfo("AK47 - checkAccess: token used", MODULE);
//            return false;
        } else if (username != null && password != null && login(username, password)) {
            Debug.logInfo("AK47 - checkAccess: username/password used", MODULE);
            return true;
        }

        Debug.logInfo("AK47 - checkAccess: login failed", MODULE);
        return false;
    }


    private String getRequestHeader(HttpHeaders requestHeaders, String header) {
        String value;

        try {
            value = requestHeaders.getRequestHeader(header).get(0);
        } catch (NullPointerException e) {
            return null;
        } catch (IllegalStateException e) {
            return null;
        }

        return value;
    }

}
