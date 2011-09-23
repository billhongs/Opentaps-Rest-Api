package org.opentaps.module.ws.rest.provider;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Created by IntelliJ IDEA.
 * User: alobrano
 * Date: 9/23/11
 * Time: 4:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class GeneralRuntimeErrorExceptionMapper  implements ExceptionMapper<Throwable>{

    public Response toResponse(Throwable throwable) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(throwable.getMessage()).build();
    }
}
