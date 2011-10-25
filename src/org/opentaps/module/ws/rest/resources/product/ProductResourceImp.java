package org.opentaps.module.ws.rest.resources.product;

import org.apache.wink.common.annotations.Workspace;
import org.opentaps.base.services.CreateProductService;
import org.opentaps.domain.product.Product;
import org.opentaps.domain.product.ProductDomainInterface;
import org.opentaps.foundation.entity.EntityNotFoundException;
import org.opentaps.foundation.repository.RepositoryException;
import org.opentaps.module.ws.rest.Context.OpentapsContext;
import org.opentaps.module.ws.rest.asset.product.ProductAsset;
import org.opentaps.module.ws.rest.asset.product.ProductsAsset;
import org.opentaps.module.ws.rest.domain.product.ProductBean;
import org.opentaps.module.ws.rest.domain.product.ProductsBean;
import org.opentaps.module.ws.rest.errors.NoPermissionException;
import org.opentaps.module.ws.rest.errors.RestApiException;
import org.opentaps.module.ws.rest.resources.ApiAbstractResource;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: claudiomelis
 * Date: 10/25/11
 * Time: 12:07 PM
 * To change this template use File | Settings | File Templates.
 */
@Workspace(workspaceTitle = "REST API", collectionTitle = "Products")
public class ProductResourceImp extends ApiAbstractResource implements ProductResource {
    protected static String MODULE = ProductResourceImp.class.getName();

    public ProductsAsset getProducts(@Context OpentapsContext opentapsContext) throws RepositoryException, RestApiException {

        super.checkPermission(opentapsContext.getSecurityManager(), "PERMISSION", "products");

        ProductDomainInterface productDomain = opentapsContext.getDomainDirectory().getProductDomain();
        List<Product> products = productDomain.getProductRepository().findAll(Product.class);
        ProductsBean productsBean = new ProductsBean();
        for (Product product : products) {
            ProductBean productBean = getMapper().map(product, ProductBean.class);
            productsBean.getProduct().add(productBean);
        }
        return new ProductsAsset(productsBean);
    }

    public ProductAsset getProduct(@Context OpentapsContext opentapsContext,
                                   @PathParam("id") String productId) throws NoPermissionException, RepositoryException {
        //make sure we have the right permissions
        super.checkPermission(opentapsContext.getSecurityManager(), "PERMISSION", "products");

        //todo IW check that the invoice id is populated

        return getProductById(opentapsContext, productId);
    }


    public ProductAsset createProduct(ProductAsset productAsset, @Context OpentapsContext opentapsContext) throws NoPermissionException, Exception {
        //make sure we have the right permissions
        super.checkPermission(opentapsContext.getSecurityManager(), "PERMISSION", "products");

        CreateProductService createProductService = getMapper().map(productAsset.getProduct(), CreateProductService.class);

        createProductService.setUser(opentapsContext.getSecurityManager().getOpentapsUser());
        createProductService.runSync(opentapsContext.getInfrastructure());

        String outProductId = createProductService.getOutProductId();

        return getProductById(opentapsContext, outProductId);

    }


    private ProductAsset getProductById(OpentapsContext opentapsContext, String productId) {

        ProductDomainInterface productDomain = opentapsContext.getDomainDirectory().getProductDomain();
        Product productById = null;
        try {
            productById = productDomain.getProductRepository().getProductById(productId);
        } catch (EntityNotFoundException e) {
            //todo IW do something with this error
            e.printStackTrace();

        } catch (RepositoryException e) {
            //todo IW do something with this error
            e.printStackTrace();
        }
        ProductBean productBean = getMapper().map(productById, ProductBean.class);

        return new ProductAsset(productBean);

    }


}
