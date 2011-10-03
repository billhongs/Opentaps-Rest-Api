package org.opentaps.module.ws.rest.resources.common;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class CommonExceptionMapper implements ExceptionMapper<CommonException> {

    public Response toResponse(CommonException e) {
        CommonResponse response = new CommonResponse("error", e.getMessage());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response).build();
    }

}
