package org.opentaps.module.ws.rest.resources.lead;

import org.ofbiz.base.util.Debug;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Locale;
import java.util.Random;
//import java.util.List;

import org.apache.wink.common.annotations.Workspace;

import org.opentaps.foundation.infrastructure.Infrastructure;
import org.opentaps.foundation.infrastructure.InfrastructureException;
import org.opentaps.foundation.infrastructure.User;
import org.opentaps.foundation.service.ServiceException;
import org.opentaps.module.ws.rest.resources.common.CommonResource;
import org.opentaps.module.ws.rest.resources.common.ResponseAsset;
import org.opentaps.module.ws.rest.resources.common.ResponseBean;


@Path(LeadsResource.MAIN_URL)
@Workspace(workspaceTitle = "REST API", collectionTitle = "Leads")
public class LeadsResource extends CommonResource {

    private static String MODULE = LeadsResource.class.getName();
    public static final String MAIN_URL = "/leads";

    @Context HttpHeaders requestHeaders;

    public LeadsResource() {
        super();
    }


    @GET
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_XML})
    public Response getLeads() {
        if (!checkAccess(requestHeaders)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }

        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(100000);

        String result = "getLeads - Test response. Authorized: " + randomInt;
        ResponseBean response = new ResponseBean();
        response.setMessage("Everything is OK.");

        ResponseAsset responseAsset = new ResponseAsset(response);

//        return Response.status(Response.Status.OK).entity(result).build();
        return Response.status(Response.Status.OK).entity(responseAsset).build();
    }


    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_XML})
    public Response createLead(LeadAsset asset, @Context UriInfo uriInfo) {
        Debug.logInfo("AK47 - createLead called: " + requestHeaders, MODULE);

        if (!checkAccess(requestHeaders)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }

        LeadBean lead = asset.getLead();

        if (lead == null) {
            Debug.logError("AK47 - The content of the lead is missing", MODULE);
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

//        Boolean login = login("APIF8C702B8", "33a41e802f428972a03107d392644713");
//        Debug.logInfo("User login: " + login, MODULE);
//        user = getUser("APIF8C702B8");

        try {
            LeadsServices leadsServices = new LeadsServices(infrastructure, user, Locale.getDefault());
            leadsServices.putLead(lead);
        } catch (ServiceException e) {
            Debug.logError("Creating lead failed: " + e.getMessage(), "LeadsResource");
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        Debug.logInfo("AK47 - lead ID: " + lead.getId(), MODULE);

//        URI location = uriInfo.getAbsolutePathBuilder().segment(lead.getId()).build();

        return Response.status(Response.Status.CREATED).entity(asset) // .location(location)
            .tag(new EntityTag(String.valueOf(lead.hashCode()))).build();
    }

}
