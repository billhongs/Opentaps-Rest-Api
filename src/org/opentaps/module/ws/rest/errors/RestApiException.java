package org.opentaps.module.ws.rest.errors;

import javax.ws.rs.core.Response;

/**
 * Created by IntelliJ IDEA.
 * User: alobrano
 * Date: 10/5/11
 * Time: 7:30 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class RestApiException extends Exception{

    public RestApiException() {
        super();
    }


    public abstract Response.Status getErrorCode();

    public abstract String getErrorMessage();

}
