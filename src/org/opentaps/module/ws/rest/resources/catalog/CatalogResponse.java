package org.opentaps.module.ws.rest.resources.catalog;

import org.opentaps.module.ws.rest.resources.common.CommonResponse;
import org.opentaps.module.ws.rest.resources.product.ProductBean;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "response")
public class CatalogResponse extends CommonResponse {

    @XmlElementWrapper(name = "data")
    @XmlElementRef
    private ArrayList<CategoryBean> data;


    public CatalogResponse() {
        super();
    }

    public CatalogResponse(String status,
                           String message) {
        super(status, message);
    }

    public CatalogResponse(String status,
                           String message,
                           ArrayList<CategoryBean> categories) {
        super(status, message);

        data = categories;
    }

    public ArrayList<CategoryBean> getData() {
        return data;
    }

    public void setData(ArrayList<CategoryBean> categories) {
        data = categories;
    }

}
