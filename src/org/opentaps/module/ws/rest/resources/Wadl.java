package org.opentaps.module.ws.rest.resources;

import org.apache.wink.common.annotations.Workspace;
import org.apache.wink.common.model.wadl.WADLGenerator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * Created by IntelliJ IDEA.
 * User: alobrano
 * Date: 10/4/11
 * Time: 7:52 PM
 * To change this template use File | Settings | File Templates.
 */
@Path("/wadl")
@Workspace(workspaceTitle = "REST API", collectionTitle = "Wadl")

public class Wadl  {
    @Context
    Application app;
    @Context
    UriInfo uriInfo;

    @GET
    @Produces("application/xml")
    public org.apache.wink.common.model.wadl.Application getWadlDoc(){
        String baseUri = uriInfo.getBaseUri().toString();
        return new WADLGenerator().generate(baseUri,app.getClasses());
    }


}
