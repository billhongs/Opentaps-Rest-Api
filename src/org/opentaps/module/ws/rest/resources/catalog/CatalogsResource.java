package org.opentaps.module.ws.rest.resources.catalog;

import org.apache.wink.common.annotations.Workspace;
import org.ofbiz.base.util.Debug;
import org.ofbiz.entity.GenericEntityConfException;
import org.ofbiz.entity.GenericEntityException;
import org.opentaps.base.entities.ProdCatalog;
import org.opentaps.foundation.repository.RepositoryException;
import org.opentaps.module.ws.rest.resources.common.CommonResource;
import org.opentaps.module.ws.rest.resources.common.CommonResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.opentaps.domain.order.ProductStore;
import org.ofbiz.product.catalog.CatalogWorker;
import org.ofbiz.entity.GenericValue;

import javolution.util.FastList;
import java.util.ArrayList;
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
    public Response getStores() {
        Debug.logInfo("Stores ++++++++++", MODULE);

/*
        // TODO change to hasAccess(getCredentials(requestHeaders))
        if (!checkAccess(requestHeaders)) {
            CommonResponse response = new CommonResponse("error", "403 Forbidden.");
            return Response.status(Response.Status.FORBIDDEN).entity(response).build();
        }
*/

        List<ProductStore> prodStores;

        try {
            prodStores = repository.findAll(ProductStore.class);
            // TODO add ability to get paginated list of products for huge catalogs,
            //  based on request parameters? with a max limit i.e. 500 per page
//            products = repository.findPage(Product.class, EntityCondition.makeCondition(), 1, 20);
            Debug.logInfo("Found product stores: " + prodStores.size(), "getProductStores");
        } catch (RepositoryException e) {
            Debug.logError("Cannot find product stores: " + e.getMessage(), MODULE);

            CommonResponse response = new CommonResponse("error", "Cannot find product stores: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
        }

        for(ProductStore prodStore : prodStores) {
            Debug.logInfo("getProductStores ID: " + prodStore.getProductStoreId(), "getProductStores");
        }
/*
        ProductStore productStore = new ProductStore();

        List<ProdCatalog> prodCatalogs;

        try {
            prodCatalogs = productStore.getProdCatalogs();
//            repository.getRel
        } catch (RepositoryException e) {
            CommonResponse response = new CommonResponse("error", "Can't find product catalogs: " + e.getMessage());
            return Response.status(Response.Status.OK).entity(response).build();
        }

        Debug.logInfo(prodCatalogs.toString(), "getCatalogs");
*/
/*
        User assignedTo = user;

        try {
            LeadsServices leadsServices = new LeadsServices(infrastructure, assignedTo, Locale.getDefault());
            leadsServices.findLeads();
        } catch (ServiceException e) {
            // TODO catch here
        }
*/

        CommonResponse response = new CommonResponse("success", "Everything is OK, you'll get Stores soon.");
        return Response.status(Response.Status.OK).entity(response).build();
    }


    @Path("{storeId}")
    @GET
    @Consumes({MediaType.WILDCARD})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_XML})
    public Response getStore(@PathParam("storeId") String storeId) {
        Debug.logInfo("Store ID: " + storeId + " ++++++++++", MODULE);

        CommonResponse response = new CommonResponse("success", "Everything is OK, you'll get Store soon.");
        return Response.status(Response.Status.OK).entity(response).build();
    }


    @Path("{storeId}/catalogs")
    @GET
    @Consumes({MediaType.WILDCARD})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_XML})
    public Response getStoreCatalogs(@PathParam("storeId") String storeId) {
        Debug.logInfo("Store ID: " + storeId + " Catalogs ++++++++++", MODULE);

        List<GenericValue> storeCatalogs = CatalogWorker.getStoreCatalogs(delegator, storeId);
        Debug.logInfo("Store ID: " + storeId + " Catalogs found: " + storeCatalogs.size() + " ++++++++++", MODULE);

        for(GenericValue storeCatalog : storeCatalogs) {
            Debug.logInfo("getProductCatalogs ID: " + storeCatalog.get("prodCatalogId"), "getProductCatalogs");
        }

        CommonResponse response = new CommonResponse("success", "Everything is OK, you'll get Catalogs soon.");
        return Response.status(Response.Status.OK).entity(response).build();
    }


    @Path("{storeId}/catalogs/{catalogId}")
    @GET
    @Consumes({MediaType.WILDCARD})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_XML})
    public Response getStoreCatalog(@PathParam("storeId") String storeId,
                                      @PathParam("catalogId") String catalogId) {
        Debug.logInfo("Store ID: " + storeId + " Catalog ID: " + catalogId + " ++++++++++", MODULE);

        CommonResponse response = new CommonResponse("success", "Everything is OK, you'll get Catalog soon.");
        return Response.status(Response.Status.OK).entity(response).build();
    }


    @Path("{storeId}/catalogs/{catalogId}/categories")
    @GET
    @Consumes({MediaType.WILDCARD})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_XML})
    public Response getStoreCatalogCategories(@PathParam("storeId") String storeId,
                                         @PathParam("catalogId") String catalogId) {
        Debug.logInfo("Store ID: " + storeId + " Catalog ID: " + catalogId + " Categories ++++++++++", MODULE);

        List<GenericValue> productCatalogCategories = new FastList<GenericValue>();
        GenericValue topCategory = CatalogServices.getCatalogTopCategory(delegator, catalogId);

        productCatalogCategories.add(topCategory);
        Debug.logInfo("ROOT category ID: " + topCategory.get("productCategoryId"), MODULE);

        String categoryId = topCategory.get("productCategoryId").toString();

//        List<GenericValue> productCatalogCategories = CatalogServices.getRelatedCategoriesRet(delegator, "subCategories", categoryId, false, false, true); // PCCT_BROWSE_ROOT, PCCT_VIEW_ALLW,
        productCatalogCategories.addAll(CatalogServices.getRelatedCategoriesRet(delegator, "subCategories", categoryId, false, false, true));
        Debug.logInfo("Catalog ID: " + catalogId + ", Categories found: " + productCatalogCategories.size() + " ++++++++++ 1 ", MODULE);

        ArrayList<CategoryBean> categories = new ArrayList<CategoryBean>();

        for(GenericValue productCatalogCategory : productCatalogCategories) {
//            Debug.logInfo("getProductCategories ID: " + productCatalogCategory.toString() + "getProductCategories"); // get("productCategoryId")
            Debug.logInfo("getProductCategories ID: " + productCatalogCategory.getClass().getName(), "getProductCategories ======"); // get("productCategoryId")
            categories.add(new CategoryBean(productCatalogCategory));
        }

        CatalogResponse response = new CatalogResponse("success", "List of product catalog categories");
        response.setData(categories);

        return Response.status(Response.Status.OK).entity(response).build();
    }


    @Path("{storeId}/catalogs/{catalogId}/categories/{categoryId}")
    @GET
    @Consumes({MediaType.WILDCARD})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_XML})
    public Response getStoreCatalogCategory(@PathParam("storeId") String storeId,
                                       @PathParam("catalogId") String catalogId,
                                       @PathParam("categoryId") String categoryId) {
        Debug.logInfo("Store ID: " + storeId + " Catalog ID: " + catalogId + " Category ID: " + categoryId + " ++++++++++", MODULE);

        CommonResponse response = new CommonResponse("success", "Everything is OK, you'll get Category soon.");
        return Response.status(Response.Status.OK).entity(response).build();
    }

}
