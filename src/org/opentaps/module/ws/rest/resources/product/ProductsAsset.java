package org.opentaps.module.ws.rest.resources.product;

import org.apache.wink.common.annotations.Asset;
import org.opentaps.domain.product.Product;

import javax.ws.rs.Produces;
import java.util.List;
import java.util.ArrayList;


@Asset
public class ProductsAsset {

    public ArrayList<ProductBean> products;


    public ProductsAsset(){
        products = new ArrayList<ProductBean>();
    }

    public ProductsAsset(Product product){
        this();
        add(product);
    }

    public ProductsAsset(List<Product> products){
        this();

        for(Product product : products) {
            add(product);
        }
    }
/*
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_XML})
    public ProductsAsset getProducts() {
//        return new ProductsResponse("success", "Some success message", products);
        return this;
    }
*/

    public void add(Product product) {
        products.add(new ProductBean(product));
    }

}
