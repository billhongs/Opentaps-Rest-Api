package org.opentaps.module.ws.rest.errors;

import javax.ws.rs.core.Response;

/**
 * Created by IntelliJ IDEA.
 * User: alobrano
 * Date: 10/5/11
 * Time: 7:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class NoPermissionException extends RestApiException{

    private static final Response.Status ERROR_CODE = Response.Status.FORBIDDEN;;
    private static final String ERROR_MESSAGE = "You don't have the right permissions to access the resource ";
    private String resource;

    public NoPermissionException(String resource) {
        super();
        this.resource = resource;
    }



    @Override
    public Response.Status getErrorCode() {
        return ERROR_CODE;
    }

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE + resource;
    }
}
