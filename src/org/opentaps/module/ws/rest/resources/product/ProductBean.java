package org.opentaps.module.ws.rest.resources.product;

import org.opentaps.domain.product.Product;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "product")
public class ProductBean {

    @XmlElement(required = true)
    private String productId;
    @XmlElement(required = true)
    private String internalName;
    @XmlElement(required = true)
    private String productType;


    public ProductBean() {

    }


    public ProductBean(Product product){
        this.productId = product.getProductId();
        this.internalName = product.getInternalName();
        this.productType = product.getProductTypeId();
    }


    public String getProductId() {
        return productId;
    }


    public void setProductId(String productId) {
        this.productId = productId;
    }


    public String getInternalName() {
        return internalName;
    }


    public void setInternalName(String internalName) {
        this.internalName = internalName;
    }


    public String getProductType() {
        return productType;
    }


    public void setProductType(String productType) {
        this.productType = productType;
    }

}
