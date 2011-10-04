package org.opentaps.module.ws.rest.resources.common;

import javax.ws.rs.core.Response;


public class CommonException extends Exception {

    private Response.Status statusCode;

    public CommonException(Response.Status statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public Response.Status getStatus() {
        return statusCode;
    }

    public void setStatus(Response.Status statusCode) {
        this.statusCode = statusCode;
    }

}
