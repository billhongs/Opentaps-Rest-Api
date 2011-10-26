package org.opentaps.module.ws.rest.resources.product;

import org.opentaps.foundation.repository.RepositoryException;
import org.opentaps.module.ws.rest.Context.OpentapsContext;
import org.opentaps.module.ws.rest.asset.product.ProductAsset;
import org.opentaps.module.ws.rest.asset.product.ProductsAsset;
import org.opentaps.module.ws.rest.errors.NoPermissionException;
import org.opentaps.module.ws.rest.errors.RestApiException;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * Created by IntelliJ IDEA.
 * User: claudiomelis
 * Date: 10/25/11
 * Time: 11:51 AM
 * To change this template use File | Settings | File Templates.
 */
@Path(ProductResource.PRODUCTS_PATH)
public interface ProductResource {
    String PRODUCTS_PATH = "products";
    String PRODUCT_ID = "id";
    String PRODUCT_ID_PATH = "{"+ PRODUCT_ID +"}";

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_JSON})
    ProductsAsset getProducts(@Context OpentapsContext opentapsContext) throws RepositoryException, RestApiException;

    @Path(ProductResource.PRODUCT_ID_PATH)
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_JSON})
    ProductAsset getProduct(@Context OpentapsContext opentapsContext,
                            @PathParam("id") String productId) throws NoPermissionException, RepositoryException;

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_JSON})
    ProductAsset createProduct(ProductAsset productAsset, @Context OpentapsContext opentapsContext) throws Exception;



}
