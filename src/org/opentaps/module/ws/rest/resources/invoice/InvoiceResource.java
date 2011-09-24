package org.opentaps.module.ws.rest.resources.invoice;

import org.apache.wink.common.annotations.Workspace;
import org.ofbiz.base.util.Debug;
import org.opentaps.module.ws.rest.security.Permission;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by IntelliJ IDEA.
 * User: alobrano
 * Date: 22/09/2011
 * Time: 11:13
 * To change this template use File | Settings | File Templates.
 */
@Path("/invoices")
@Workspace(workspaceTitle = "REST API", collectionTitle = "Invoices")
public class InvoiceResource {
    protected static String MODULE = InvoiceResource.class.getName();


    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_XML})
    public Response getProducts(@Context Permission permission) {

        Debug.logInfo("USERNAME ["+permission.getUsername()+"] ROLES ["+permission.getRoles()+"] ",MODULE);

        throw new NullPointerException("MISSING IMPLEMENTATION");

    }
}
