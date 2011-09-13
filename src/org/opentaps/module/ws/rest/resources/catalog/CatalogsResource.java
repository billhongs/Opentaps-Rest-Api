package org.opentaps.module.ws.rest.resources.catalog;

import org.apache.wink.common.annotations.Workspace;
import org.ofbiz.base.util.Debug;
import org.opentaps.base.entities.ProdCatalog;
import org.opentaps.foundation.repository.RepositoryException;
import org.opentaps.module.ws.rest.resources.common.CommonResource;
import org.opentaps.module.ws.rest.resources.common.CommonResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.opentaps.domain.order.ProductStore;
import org.ofbiz.product.catalog.CatalogWorker;
import org.ofbiz.entity.GenericValue;

import java.util.List;


@Path(CatalogsResource.MAIN_URL)
@Workspace(workspaceTitle = "REST API", collectionTitle = "Stores")
public class CatalogsResource extends CommonResource {

    private static String MODULE = CatalogsResource.class.getName();
    public static final String MAIN_URL = "/stores";

    @Context HttpHeaders requestHeaders;

    public CatalogsResource() {
        super();
    }


    @GET
    @Consumes({MediaType.WILDCARD})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_XML})
    public Response getProductStores() {
        Debug.logInfo("Stores ++++++++++", MODULE);

        CommonResponse response = new CommonResponse("success", "Everything is OK, you'll get Stores soon.");
        return Response.status(Response.Status.OK).entity(response).build();
    }


    @Path("{storeId}")
    @GET
    @Consumes({MediaType.WILDCARD})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_XML})
    public Response getProductStore(@PathParam("storeId") String storeId) {
        Debug.logInfo("Store ID: " + storeId + " ++++++++++", MODULE);

        CommonResponse response = new CommonResponse("success", "Everything is OK, you'll get Store soon.");
        return Response.status(Response.Status.OK).entity(response).build();
    }


    @Path("{storeId}/catalogs")
    @GET
    @Consumes({MediaType.WILDCARD})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_XML})
    public Response getProductCatalogs(@PathParam("storeId") String storeId) {
        Debug.logInfo("Store ID: " + storeId + " Catalogs ++++++++++", MODULE);

        CommonResponse response = new CommonResponse("success", "Everything is OK, you'll get Catalogs soon.");
        return Response.status(Response.Status.OK).entity(response).build();
    }


    @Path("{storeId}/catalogs/{catalogId}")
    @GET
    @Consumes({MediaType.WILDCARD})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_XML})
    public Response getProductCatalog(@PathParam("storeId") String storeId,
                                      @PathParam("catalogId") String catalogId) {
        Debug.logInfo("Store ID: " + storeId + " Catalog ID: " + catalogId + " ++++++++++", MODULE);

        CommonResponse response = new CommonResponse("success", "Everything is OK, you'll get Catalog soon.");
        return Response.status(Response.Status.OK).entity(response).build();
    }


    @Path("{storeId}/catalogs/{catalogId}/categories")
    @GET
    @Consumes({MediaType.WILDCARD})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_XML})
    public Response getProductCategories(@PathParam("storeId") String storeId,
                                         @PathParam("catalogId") String catalogId) {
        Debug.logInfo("Store ID: " + storeId + " Catalog ID: " + catalogId + " Categories ++++++++++", MODULE);

        CommonResponse response = new CommonResponse("success", "Everything is OK, you'll get Categories soon.");
        return Response.status(Response.Status.OK).entity(response).build();
    }


    @Path("{storeId}/catalogs/{catalogId}/categories/{categoryId}")
    @GET
    @Consumes({MediaType.WILDCARD})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_XML})
    public Response getProductCategory(@PathParam("storeId") String storeId,
                                       @PathParam("catalogId") String catalogId,
                                       @PathParam("categoryId") String categoryId) {
        Debug.logInfo("Store ID: " + storeId + " Catalog ID: " + catalogId + " Category ID: " + categoryId + " ++++++++++", MODULE);

        CommonResponse response = new CommonResponse("success", "Everything is OK, you'll get Category soon.");
        return Response.status(Response.Status.OK).entity(response).build();
    }

}
