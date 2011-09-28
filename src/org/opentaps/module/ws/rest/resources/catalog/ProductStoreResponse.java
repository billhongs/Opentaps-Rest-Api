package org.opentaps.module.ws.rest.resources.catalog;

import org.opentaps.module.ws.rest.resources.common.CommonResponse;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "response")
public class ProductStoreResponse extends CommonResponse {

    @XmlElementWrapper(name = "data")
    @XmlElementRef
    private ArrayList<ProductStoreBean> data;


    public ProductStoreResponse() {
        super();
    }

    public ProductStoreResponse(String status,
                                String message) {
        super(status, message);
    }

    public ProductStoreResponse(String status,
                                String message,
                                ArrayList<ProductStoreBean> categories) {
        super(status, message);

        data = categories;
    }

    public ArrayList<ProductStoreBean> getData() {
        return data;
    }

    public void setData(ArrayList<ProductStoreBean> categories) {
        data = categories;
    }

}
