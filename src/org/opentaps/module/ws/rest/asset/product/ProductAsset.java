package org.opentaps.module.ws.rest.asset.product;

import org.apache.wink.common.annotations.Asset;
import org.opentaps.module.ws.rest.domain.product.ProductBean;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by IntelliJ IDEA.
 * User: claudiomelis
 * Date: 10/25/11
 * Time: 12:44 PM
 * To change this template use File | Settings | File Templates.
 */

@Asset
public class ProductAsset {


    private ProductBean productBean;


    public ProductAsset() {
    }

    public ProductAsset(ProductBean productBean) {
        this.productBean = productBean;
    }

    @Produces({MediaType.APPLICATION_XML})
    public ProductBean getProduct(){
         return productBean;
    }

    @Consumes(MediaType.APPLICATION_XML)
    public void setProduct(ProductBean productBean){
        this.productBean = productBean;
    }
}
