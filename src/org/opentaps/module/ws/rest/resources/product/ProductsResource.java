package org.opentaps.module.ws.rest.resources.product;

import org.opentaps.module.ws.rest.resources.common.CommonResource;

import org.apache.wink.common.annotations.Workspace;
import org.ofbiz.base.util.Debug;
import org.ofbiz.base.util.UtilMisc;
import org.ofbiz.base.util.UtilValidate;
import org.ofbiz.entity.GenericDelegator;
import org.ofbiz.entity.GenericValue;
import org.ofbiz.service.GenericDispatcher;
import org.ofbiz.service.LocalDispatcher;

import org.opentaps.base.entities.UserLogin;
import org.opentaps.base.services.CreateProductService;
import org.opentaps.base.services.UserLoginService;
import org.opentaps.domain.product.Product;
import org.opentaps.foundation.entity.EntityNotFoundException;
import org.opentaps.foundation.infrastructure.Infrastructure;
import org.opentaps.foundation.infrastructure.User;
import org.opentaps.foundation.repository.RepositoryException;
import org.opentaps.foundation.repository.ofbiz.Repository;
import org.opentaps.foundation.service.ServiceException;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;


@Path(ProductsResource.PRODUCTS_URL)
@Workspace(workspaceTitle = "REST API", collectionTitle = "Products")
public class ProductsResource extends CommonResource {

    private static String MODULE = ProductsResource.class.getName();
    public static final String PRODUCTS_URL = "/products";
    public static final String PRODUCT = "product";
    public static final String PRODUCT_URL = "{" + PRODUCT + "}";


    public ProductsResource() {
        super();
    }


    @GET
    @Produces({MediaType.APPLICATION_JSON}) // , MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_XML
    public ProductsAsset getProducts() {
        List<Product> products = null;
        try {
            products = repository.findAll(Product.class);
        } catch (RepositoryException e) {
            Debug.logError(e, MODULE);
        }

        ProductsAsset productsAsset = new ProductsAsset(products);

        return productsAsset;
    }


    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_XML})
    public Response createProduct(ProductAsset asset, @Context UriInfo uriInfo, @HeaderParam("username") String username, @HeaderParam("password") String password) {
        Debug.logInfo("Creating product", MODULE);
        Debug.logInfo("Username [" + username + "]", MODULE);
        Debug.logInfo("Password [" + password + "]", MODULE);

        if (UtilValidate.isEmpty(username)) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if (UtilValidate.isEmpty(password)) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        ProductBean product = asset.getProduct();
        if (product == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        login(username, password, infrastructure);

        CreateProductService createProductService = new CreateProductService();
        createProductService.setInProductId(repository.getNextSeqId(new Product()));
        createProductService.setInInternalName(product.getInternalName());
        createProductService.setInProductTypeId(product.getProductType());
        createProductService.setUser(getUser(username));

        try {
            createProductService.runAsync(infrastructure);
        } catch (ServiceException e) {
            Debug.logError(e, MODULE);
        }


        URI location = uriInfo.getAbsolutePathBuilder().segment(createProductService.getInProductId()).build();
        return Response.status(Response.Status.CREATED).entity(asset).location(location)
                .tag(new EntityTag(String.valueOf(product.hashCode()))).build();
    }


    @Path(PRODUCT_URL)
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_XML})
    public ProductAsset getDefect(@PathParam(PRODUCT) String productId) {
        if (UtilValidate.isEmpty(productId)) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        Product product = null;

        try {
            product = repository.findOneNotNull(Product.class, repository.map(Product.Fields.productId, productId));
        } catch (RepositoryException e) {
            Debug.logError("Error retrieving the repository", MODULE);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        } catch (EntityNotFoundException e) {
            Debug.logError("product [" + productId + "] cannot be found", MODULE);
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        ProductAsset productAsset = new ProductAsset(new ProductBean(product));

        return productAsset;
    }

}
