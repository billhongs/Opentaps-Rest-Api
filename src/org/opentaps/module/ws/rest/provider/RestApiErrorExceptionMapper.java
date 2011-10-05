package org.opentaps.module.ws.rest.provider;

import org.opentaps.module.ws.rest.errors.RestApiException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by IntelliJ IDEA.
 * User: alobrano
 * Date: 10/5/11
 * Time: 7:40 PM
 * To change this template use File | Settings | File Templates.
 */
@Provider
public class RestApiErrorExceptionMapper implements ExceptionMapper<RestApiException>{

    public Response toResponse(RestApiException e) {
        return Response.status(e.getErrorCode()).entity(e.getErrorMessage()).build();
    }
}
