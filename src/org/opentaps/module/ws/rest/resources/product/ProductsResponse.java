package org.opentaps.module.ws.rest.resources.product;

import org.opentaps.module.ws.rest.resources.common.CommonResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.*;


@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsResponse extends CommonResponse {

    @XmlElementRef
    ProductsList data;


    public ProductsResponse() {
        super();
    }


    public ProductsResponse(String status,
                          String message) {
        super(status, message);
    }


    public ProductsResponse(String status,
                          String message,
                          ProductsList products) {
        super(status, message);

        data = products;
    }


    public void setData(ProductsList products) {
        data = products;
    }

}
