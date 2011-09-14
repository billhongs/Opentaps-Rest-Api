package org.opentaps.module.ws.rest.resources.product;

import org.opentaps.module.ws.rest.resources.common.CommonResponse;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "response")
public class ProductsResponse extends CommonResponse {

    @XmlElementWrapper(name = "data")
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

        data = products;
    }

    public ArrayList<ProductBean> getData() {
        return data;
    }

    public void setData(ArrayList<ProductBean> products) {
        data = products;
    }

}
