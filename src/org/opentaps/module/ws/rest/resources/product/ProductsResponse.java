package org.opentaps.module.ws.rest.resources.product;

import org.opentaps.module.ws.rest.resources.common.CommonResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "response")
public class ProductsResponse extends CommonResponse {

    @XmlElementWrapper(name = "products")
    @XmlElementRef
    private ArrayList<ProductBean> data;


    public ProductsResponse() {
        super();
    }


    public ProductsResponse(String status,
                          String message) {
        super(status, message);
    }


    public ProductsResponse(String status,
                          String message,
                          ArrayList<ProductBean> products) {
        super(status, message);

        this.data = products;
    }


    public void setData(ArrayList<ProductBean> products) {
        this.data = products;
    }

}
