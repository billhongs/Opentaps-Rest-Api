package org.opentaps.module.ws.rest.resources.product_old;

import org.opentaps.domain.product.Product;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.opentaps.module.ws.rest.resources.common.DateAdapter;

import java.sql.Timestamp;
import java.math.BigDecimal;
import java.lang.Long;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "product")
//@XmlType(name = "ProductType", propOrder = {"productId", "productName", "productTypeId", "createdStamp", "createdTxStamp", "isActive"})
public class ProductBean {

    @XmlElement(required = true)
    private String productId;

    @XmlElement(required = true)
    private String productTypeId;

    @XmlElement(required = true)
    private String primaryProductCategoryId;

    @XmlElement(required = true)
    private String manufacturerPartyId;

    @XmlElement(required = true)
    private String facilityId;

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Timestamp introductionDate;

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Timestamp supportDiscontinuationDate;

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Timestamp salesDiscontinuationDate;

    @XmlElement(required = true)
    private String salesDiscWhenNotAvail;

    @XmlElement(required = true)
    private String internalName;

    @XmlElement(required = true)
    private String brandName;

    @XmlElement(required = true)
    private String comments;

    @XmlElement(required = true)
    private String productName;

    @XmlElement(required = true)
    private String description;

    @XmlElement(required = true)
    private String longDescription;

    @XmlElement(required = true)
    private String priceDetailText;

    @XmlElement(required = true)
    private String smallImageUrl;

    @XmlElement(required = true)
    private String mediumImageUrl;

    @XmlElement(required = true)
    private String largeImageUrl;

    @XmlElement(required = true)
    private String detailImageUrl;

    @XmlElement(required = true)
    private String originalImageUrl;

    @XmlElement(required = true)
    private String detailScreen;

    @XmlElement(required = true)
    private String inventoryMessage;

    @XmlElement(required = true)
    private String requireInventory;

    @XmlElement(required = true)
    private String quantityUomId;

    @XmlElement(required = true)
    private BigDecimal quantityIncluded;

    @XmlElement(required = true)
    private Long piecesIncluded;

    @XmlElement(required = true)
    private String requireAmount;

    @XmlElement(required = true)
    private BigDecimal fixedAmount;

    @XmlElement(required = true)
    private String amountUomTypeId;

    @XmlElement(required = true)
    private String weightUomId;

    @XmlElement(required = true)
    private BigDecimal weight;

    @XmlElement(required = true)
    private String heightUomId;

    @XmlElement(required = true)
    private BigDecimal productHeight;

    @XmlElement(required = true)
    private BigDecimal shippingHeight;

    @XmlElement(required = true)
    private String widthUomId;

    @XmlElement(required = true)
    private BigDecimal productWidth;

    @XmlElement(required = true)
    private BigDecimal shippingWidth;

    @XmlElement(required = true)
    private String depthUomId;

    @XmlElement(required = true)
    private BigDecimal productDepth;

    @XmlElement(required = true)
    private BigDecimal shippingDepth;

    @XmlElement(required = true)
    private BigDecimal productRating;

    @XmlElement(required = true)
    private String ratingTypeEnum;

    @XmlElement(required = true)
    private String returnable;

    @XmlElement(required = true)
    private String taxable;

    @XmlElement(required = true)
    private String chargeShipping;

    @XmlElement(required = true)
    private String autoCreateKeywords;

    @XmlElement(required = true)
    private String includeInPromotions;

    @XmlElement(required = true)
    private String isVirtual;

    @XmlElement(required = true)
    private String isVariant;

    @XmlElement(required = true)
    private String virtualVariantMethodEnum;

    @XmlElement(required = true)
    private String originGeoId;

    @XmlElement(required = true)
    private String requirementMethodEnumId;

    @XmlElement(required = true)
    private Long billOfMaterialLevel;

    @XmlElement(required = true)
    private BigDecimal reservMaxPersons;

    @XmlElement(required = true)
    private BigDecimal reserv2ndPPPerc;

    @XmlElement(required = true)
    private BigDecimal reservNthPPPerc;

    @XmlElement(required = true)
    private String configId;

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Timestamp createdDate;

    @XmlElement(required = true)
    private String createdByUserLogin;

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Timestamp lastModifiedDate;

    @XmlElement(required = true)
    private String lastModifiedByUserLogin;

    @XmlElement(required = true)
    private String inShippingBox;

    @XmlElement(required = true)
    private String defaultShipmentBoxTypeId;

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

    @XmlElement(required = true)
    private String isActive;

//    @XmlElement(required = true)
//    private String fields;


    public ProductBean() {
    }


    public ProductBean(Product product) {
        productId = product.getProductId();
        productTypeId = product.getProductTypeId();
        primaryProductCategoryId = product.getPrimaryProductCategoryId();
        manufacturerPartyId = product.getManufacturerPartyId();
        facilityId = product.getFacilityId();
        introductionDate = product.getIntroductionDate();
        supportDiscontinuationDate = product.getSupportDiscontinuationDate();
        salesDiscontinuationDate = product.getSalesDiscontinuationDate();
        salesDiscWhenNotAvail = product.getSalesDiscWhenNotAvail();
        internalName = product.getInternalName();
        brandName = product.getBrandName();
        comments = product.getComments();
        productName = product.getProductName();
        description = product.getDescription();
        longDescription = product.getLongDescription();
        priceDetailText = product.getPriceDetailText();
        smallImageUrl = product.getSmallImageUrl();
        mediumImageUrl = product.getMediumImageUrl();
        largeImageUrl = product.getLargeImageUrl();
        detailImageUrl = product.getDetailImageUrl();
        originalImageUrl = product.getOriginalImageUrl();
        detailScreen = product.getDetailScreen();
        inventoryMessage = product.getInventoryMessage();
        requireInventory = product.getRequireInventory();
        quantityUomId = product.getQuantityUomId();
        quantityIncluded = product.getQuantityIncluded();
        piecesIncluded = product.getPiecesIncluded();
        requireAmount = product.getRequireAmount();
        fixedAmount = product.getFixedAmount();
        amountUomTypeId = product.getAmountUomTypeId();
        weightUomId = product.getWeightUomId();
        weight = product.getWeight();
        heightUomId = product.getHeightUomId();
        productHeight = product.getProductHeight();
        shippingHeight = product.getShippingHeight();
        widthUomId = product.getWidthUomId();
        productWidth = product.getProductWidth();
        shippingWidth = product.getShippingWidth();
        depthUomId = product.getDepthUomId();
        productDepth = product.getProductDepth();
        shippingDepth = product.getShippingDepth();
        productRating = product.getProductRating();
        ratingTypeEnum = product.getRatingTypeEnum();
        returnable = product.getReturnable();
        taxable = product.getTaxable();
        chargeShipping = product.getChargeShipping();
        autoCreateKeywords = product.getAutoCreateKeywords();
        includeInPromotions = product.getIncludeInPromotions();
        isVirtual = product.getIsVirtual();
        isVariant = product.getIsVariant();
        virtualVariantMethodEnum = product.getVirtualVariantMethodEnum();
        originGeoId = product.getOriginGeoId();
        requirementMethodEnumId = product.getRequirementMethodEnumId();
        billOfMaterialLevel = product.getBillOfMaterialLevel();
        reservMaxPersons = product.getReservMaxPersons();
        reserv2ndPPPerc = product.getReserv2ndPPPerc();
        reservNthPPPerc = product.getReservNthPPPerc();
        configId = product.getConfigId();
        createdDate = product.getCreatedDate();
        createdByUserLogin = product.getCreatedByUserLogin();
        lastModifiedDate = product.getLastModifiedDate();
        lastModifiedByUserLogin = product.getLastModifiedByUserLogin();
        inShippingBox = product.getInShippingBox();
        defaultShipmentBoxTypeId = product.getDefaultShipmentBoxTypeId();
        lastUpdatedStamp = product.getLastUpdatedStamp();
        lastUpdatedTxStamp = product.getLastUpdatedTxStamp();
        createdStamp = product.getCreatedStamp();
        createdTxStamp = product.getCreatedTxStamp();
        isActive = product.getIsActive();

//        fields = product.getAllFieldsNames().toString();
    }


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
