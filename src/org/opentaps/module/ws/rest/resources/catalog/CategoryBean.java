package org.opentaps.module.ws.rest.resources.catalog;

import org.opentaps.domain.product.Product;
import org.opentaps.module.ws.rest.resources.common.DateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.ofbiz.entity.GenericValue;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "category")
public class CategoryBean {

    @XmlElement(required = true)
    private String productCategoryId;

    @XmlElement(required = true)
    private String productCategoryTypeId;

    @XmlElement(required = true)
    private String primaryParentCategoryId;

    @XmlElement(required = true)
    private String categoryName;

    @XmlElement(required = true)
    private String description;

    @XmlElement(required = true)
    private String longDescription;

    @XmlElement(required = true)
    private String categoryImageUrl;

    @XmlElement(required = true)
    private String linkOneImageUrl;

    @XmlElement(required = true)
    private String linkTwoImageUrl;

    @XmlElement(required = true)
    private String detailScreen;

    @XmlElement(required = true)
    private String showInSelect;

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Timestamp lastUpdatedStamp;

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Timestamp lastUpdatedTxStamp;

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Timestamp createdStamp;

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Timestamp createdTxStamp;


    public CategoryBean() {
    }


    public CategoryBean(GenericValue category) {
        productCategoryId = category.get("productCategoryId") != null ? category.get("productCategoryId").toString() : null;
        productCategoryId = category.get("productCategoryId") != null ? category.get("productCategoryId").toString() : null;
        productCategoryTypeId = category.get("productCategoryTypeId") != null ? category.get("productCategoryTypeId").toString() : null;
        primaryParentCategoryId = category.get("primaryParentCategoryId") != null ? category.get("primaryParentCategoryId").toString() : null;
        categoryName = category.get("categoryName") != null ? category.get("categoryName").toString() : null;
        description = category.get("description") != null ? category.get("description").toString() : null;
        longDescription = category.get("longDescription") != null ? category.get("longDescription").toString() : null;
        categoryImageUrl = category.get("categoryImageUrl") != null ? category.get("categoryImageUrl").toString() : null;
        linkOneImageUrl = category.get("linkOneImageUrl") != null ? category.get("linkOneImageUrl").toString() : null;
        linkTwoImageUrl = category.get("linkTwoImageUrl") != null ? category.get("linkTwoImageUrl").toString() : null;
        detailScreen = category.get("detailScreen") != null ? category.get("detailScreen").toString() : null;
        showInSelect = category.get("showInSelect") != null ? category.get("showInSelect").toString() : null;
        lastUpdatedStamp = category.get("lastUpdatedStamp") != null ? (Timestamp) category.get("lastUpdatedStamp") : null;
        lastUpdatedTxStamp = category.get("lastUpdatedTxStamp") != null ? (Timestamp) category.get("lastUpdatedTxStamp") : null;
        createdStamp = category.get("createdStamp") != null ? (Timestamp) category.get("createdStamp") : null;
        createdTxStamp = category.get("createdTxStamp") != null ? (Timestamp) category.get("createdTxStamp") : null;
    }


/*
    public String getProductId() {
        return productId;
    }


    public void setProductId(String productId) {
        this.productId = productId;
    }


    public String getProductTypeId() {
        return productTypeId;
    }


    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
    }


    public String getProductName() {
        return productName;
    }


    public void setProductName(String productName) {
        this.productName = productName;
    }
*/


/*
    public String getIsActive() {
        return isActive;
    }


    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
*/


//    public Timestamp getCreatedStamp() {
//        return createdStamp;
//    }


//    public void setCreatedStamp(Timestamp createdStamp) {
//        this.createdStamp = createdStamp;
//    }

/*
    public String getFields() {
        return fields;
    }
*/

}
