package org.opentaps.module.ws.rest.resources.lead;

import org.ofbiz.base.util.Debug;
//import org.ofbiz.base.util.UtilMisc;
//import org.ofbiz.base.util.UtilValidate;

//import org.ofbiz.entity.GenericDelegator;
//import org.ofbiz.entity.GenericValue;
//import org.ofbiz.service.GenericDispatcher;
//import org.ofbiz.service.LocalDispatcher;

//import org.opentaps.base.entities.UserLogin;
//import org.opentaps.base.services.CreateProductService;
//import org.opentaps.base.services.UserLoginService;
//import org.opentaps.domain.product.Product;
//import org.opentaps.foundation.entity.EntityNotFoundException;
//import org.opentaps.foundation.infrastructure.Infrastructure;
//import org.opentaps.foundation.infrastructure.User;
//import org.opentaps.foundation.repository.RepositoryException;
//import org.opentaps.foundation.repository.ofbiz.Repository;
//import org.opentaps.foundation.service.ServiceException;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
//import java.util.List;

import org.apache.wink.common.annotations.Workspace;

import org.opentaps.module.ws.rest.resources.common.CommonResource;


@Path(LeadsResource.MAIN_URL)
@Workspace(workspaceTitle = "REST API", collectionTitle = "Leads")
public class LeadsResource extends CommonResource {

    private static String MODULE = LeadsResource.class.getName();
    public static final String MAIN_URL = "/leads";


    public LeadsResource() {
        super();
    }


    @GET
    @Produces({MediaType.TEXT_PLAIN}) // MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_XML
    public String getLeads() {
        Debug.logInfo("AK47 - getLeads called", MODULE);
        return "getLeads - Test response";
    }


    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_XML})
    public Response createLead(LeadAsset asset, @Context UriInfo uriInfo) {
        LeadBean lead = asset.getLead();

        if (lead == null) {
            Debug.logError("AK47 - The content of the lead is missing", MODULE);
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        lead.setId("LEAD-123");

        URI location = uriInfo.getAbsolutePathBuilder().segment(lead.getId()).build();

//        return Response.status(Response.Status.CREATED).entity(asset).location(location)
//            .tag(new EntityTag(String.valueOf(lead.hashCode()))).build();

        return Response.status(Response.Status.CREATED).location(location)
            .tag(new EntityTag(String.valueOf(lead.hashCode()))).build();
    }

}
