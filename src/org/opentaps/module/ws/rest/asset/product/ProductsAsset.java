package org.opentaps.module.ws.rest.asset.product;

import org.apache.wink.common.annotations.Asset;
import org.opentaps.module.ws.rest.domain.product.ProductsBean;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by IntelliJ IDEA.
 * User: claudiomelis
 * Date: 10/25/11
 * Time: 12:41 PM
 * To change this template use File | Settings | File Templates.
 */
@Asset
public class ProductsAsset {

    private ProductsBean productsBean;

    public ProductsAsset(ProductsBean productsBean){
        this.productsBean = productsBean;
    }


    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ProductsBean getProducts(){
        return productsBean;
    }
}
