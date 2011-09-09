package org.opentaps.module.ws.rest.resources.product;

import org.opentaps.module.ws.rest.resources.common.CommonResponse;
import org.opentaps.domain.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.*;


@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsList {

    @XmlElementRef
    ArrayList<ProductBean> products;


    public ProductsList() {
        products = new ArrayList<ProductBean>();
    }


    public ProductsList(Product product) {
        this();

        add(product);
    }


    public ProductsList(List<Product> products) {
        this();

        for(Product product : products) {
            add(product);
        }
    }


    public void add(Product product) {
        products.add(new ProductBean(product));
    }

}
