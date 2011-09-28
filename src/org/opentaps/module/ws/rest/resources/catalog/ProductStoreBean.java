package org.opentaps.module.ws.rest.resources.catalog;

import org.opentaps.module.ws.rest.resources.common.DateAdapter;
import org.opentaps.base.entities.ProductStore;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.sql.Timestamp;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "store")
public class ProductStoreBean {

    @XmlElement(required = true)
    private String productStoreId;
    @XmlElement(required = true)
    private String primaryStoreGroupId;
    @XmlElement(required = true)
    private String storeName;
    @XmlElement(required = true)
    private String companyName;
    @XmlElement(required = true)
    private String title;
    @XmlElement(required = true)
    private String subtitle;
    @XmlElement(required = true)
    private String payToPartyId;
    @XmlElement(required = true)
    private Long daysToCancelNonPay;
    @XmlElement(required = true)
    private String manualAuthIsCapture;
    @XmlElement(required = true)
    private String prorateShipping;
    @XmlElement(required = true)
    private String prorateTaxes;
    @XmlElement(required = true)
    private String viewCartOnAdd;
    @XmlElement(required = true)
    private String autoSaveCart;
    @XmlElement(required = true)
    private String autoApproveReviews;
    @XmlElement(required = true)
    private String isDemoStore;
    @XmlElement(required = true)
    private String isImmediatelyFulfilled;
    @XmlElement(required = true)
    private String inventoryFacilityId;
    @XmlElement(required = true)
    private String oneInventoryFacility;
    @XmlElement(required = true)
    private String checkInventory;
    @XmlElement(required = true)
    private String reserveInventory;
    @XmlElement(required = true)
    private String reserveOrderEnumId;
    @XmlElement(required = true)
    private String requireInventory;
    @XmlElement(required = true)
    private String balanceResOnOrderCreation;
    @XmlElement(required = true)
    private String requirementMethodEnumId;
    @XmlElement(required = true)
    private String orderNumberPrefix;
    @XmlElement(required = true)
    private String defaultLocaleString;
    @XmlElement(required = true)
    private String defaultCurrencyUomId;
    @XmlElement(required = true)
    private String defaultSalesChannelEnumId;
    @XmlElement(required = true)
    private String allowPassword;
    @XmlElement(required = true)
    private String defaultPassword;
    @XmlElement(required = true)
    private String explodeOrderItems;
    @XmlElement(required = true)
    private String checkGcBalance;
    @XmlElement(required = true)
    private String retryFailedAuths;
    @XmlElement(required = true)
    private String headerApprovedStatus;
    @XmlElement(required = true)
    private String itemApprovedStatus;
    @XmlElement(required = true)
    private String digitalItemApprovedStatus;
    @XmlElement(required = true)
    private String headerDeclinedStatus;
    @XmlElement(required = true)
    private String itemDeclinedStatus;
    @XmlElement(required = true)
    private String headerCancelStatus;
    @XmlElement(required = true)
    private String itemCancelStatus;
    @XmlElement(required = true)
    private String authDeclinedMessage;
    @XmlElement(required = true)
    private String authFraudMessage;
    @XmlElement(required = true)
    private String authErrorMessage;
    @XmlElement(required = true)
    private String visualThemeId;
    @XmlElement(required = true)
    private String oldStyleSheet;
    @XmlElement(required = true)
    private String oldHeaderLogo;
    @XmlElement(required = true)
    private String oldHeaderMiddleBackground;
    @XmlElement(required = true)
    private String oldHeaderRightBackground;
    @XmlElement(required = true)
    private String usePrimaryEmailUsername;
    @XmlElement(required = true)
    private String requireCustomerRole;
    @XmlElement(required = true)
    private String autoInvoiceDigitalItems;
    @XmlElement(required = true)
    private String reqShipAddrForDigItems;
    @XmlElement(required = true)
    private String showCheckoutGiftOptions;
    @XmlElement(required = true)
    private String selectPaymentTypePerItem;
    @XmlElement(required = true)
    private String showPricesWithVatTax;
    @XmlElement(required = true)
    private String showTaxIsExempt;
    @XmlElement(required = true)
    private String vatTaxAuthGeoId;
    @XmlElement(required = true)
    private String vatTaxAuthPartyId;
    @XmlElement(required = true)
    private String enableAutoSuggestionList;
    @XmlElement(required = true)
    private String enableDigProdUpload;
    @XmlElement(required = true)
    private String prodSearchExcludeVariants;
    @XmlElement(required = true)
    private String digProdUploadCategoryId;
    @XmlElement(required = true)
    private String autoOrderCcTryExp;
    @XmlElement(required = true)
    private String autoOrderCcTryOtherCards;
    @XmlElement(required = true)
    private String autoOrderCcTryLaterNsf;
    @XmlElement(required = true)
    private Long autoOrderCcTryLaterMax;
    @XmlElement(required = true)
    private Long storeCreditValidDays;
    @XmlElement(required = true)
    private String autoApproveInvoice;
    @XmlElement(required = true)
    private String autoApproveOrder;
    @XmlElement(required = true)
    private String shipIfCaptureFails;
    @XmlElement(required = true)
    private String setOwnerUponIssuance;
    @XmlElement(required = true)
    private String reqReturnInventoryReceive;
    @XmlElement(required = true)
    private String addToCartRemoveIncompat;
    @XmlElement(required = true)
    private String addToCartReplaceUpsell;
    @XmlElement(required = true)
    private String splitPayPrefPerShpGrp;
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
    private String noShipOnDropShipGroups;
    @XmlElement(required = true)
    private String allowDoNotShipOrders;
    @XmlElement(required = true)
    private String defaultShipmentMethodTypeId;
    @XmlElement(required = true)
    private String defaultShippingCarrierPartyId;
    @XmlElement(required = true)
    private String billToThirdPartyId;


    public ProductStoreBean() {
    }


    public ProductStoreBean(ProductStore productStore) {
        productStoreId = productStore.getProductStoreId();
        primaryStoreGroupId = productStore.getPrimaryStoreGroupId();
        storeName = productStore.getStoreName();
        companyName = productStore.getCompanyName();
        title = productStore.getTitle();
        subtitle = productStore.getSubtitle();
        payToPartyId = productStore.getPayToPartyId();
        daysToCancelNonPay = productStore.getDaysToCancelNonPay();
        manualAuthIsCapture = productStore.getManualAuthIsCapture();
        prorateShipping = productStore.getProrateShipping();
        prorateTaxes = productStore.getProrateTaxes();
        viewCartOnAdd = productStore.getViewCartOnAdd();
        autoSaveCart = productStore.getAutoSaveCart();
        autoApproveReviews = productStore.getAutoApproveReviews();
        isDemoStore = productStore.getIsDemoStore();
        isImmediatelyFulfilled = productStore.getIsImmediatelyFulfilled();
        inventoryFacilityId = productStore.getInventoryFacilityId();
        oneInventoryFacility = productStore.getOneInventoryFacility();
        checkInventory = productStore.getCheckInventory();
        reserveInventory = productStore.getReserveInventory();
        reserveOrderEnumId = productStore.getReserveOrderEnumId();
        requireInventory = productStore.getRequireInventory();
        balanceResOnOrderCreation = productStore.getBalanceResOnOrderCreation();
        requirementMethodEnumId = productStore.getRequirementMethodEnumId();
        orderNumberPrefix = productStore.getOrderNumberPrefix();
        defaultLocaleString = productStore.getDefaultLocaleString();
        defaultCurrencyUomId = productStore.getDefaultCurrencyUomId();
        defaultSalesChannelEnumId = productStore.getDefaultSalesChannelEnumId();
        allowPassword = productStore.getAllowPassword();
        defaultPassword = productStore.getDefaultPassword();
        explodeOrderItems = productStore.getExplodeOrderItems();
        checkGcBalance = productStore.getCheckGcBalance();
        retryFailedAuths = productStore.getRetryFailedAuths();
        headerApprovedStatus = productStore.getHeaderApprovedStatus();
        itemApprovedStatus = productStore.getItemApprovedStatus();
        digitalItemApprovedStatus = productStore.getDigitalItemApprovedStatus();
        headerDeclinedStatus = productStore.getHeaderDeclinedStatus();
        itemDeclinedStatus = productStore.getItemDeclinedStatus();
        headerCancelStatus = productStore.getHeaderCancelStatus();
        itemCancelStatus = productStore.getItemCancelStatus();
        authDeclinedMessage = productStore.getAuthDeclinedMessage();
        authFraudMessage = productStore.getAuthFraudMessage();
        authErrorMessage = productStore.getAuthErrorMessage();
        visualThemeId = productStore.getVisualThemeId();
        oldStyleSheet = productStore.getOldStyleSheet();
        oldHeaderLogo = productStore.getOldHeaderLogo();
        oldHeaderMiddleBackground = productStore.getOldHeaderMiddleBackground();
        oldHeaderRightBackground = productStore.getOldHeaderRightBackground();
        usePrimaryEmailUsername = productStore.getUsePrimaryEmailUsername();
        requireCustomerRole = productStore.getRequireCustomerRole();
        autoInvoiceDigitalItems = productStore.getAutoInvoiceDigitalItems();
        reqShipAddrForDigItems = productStore.getReqShipAddrForDigItems();
        showCheckoutGiftOptions = productStore.getShowCheckoutGiftOptions();
        selectPaymentTypePerItem = productStore.getSelectPaymentTypePerItem();
        showPricesWithVatTax = productStore.getShowPricesWithVatTax();
        showTaxIsExempt = productStore.getShowTaxIsExempt();
        vatTaxAuthGeoId = productStore.getVatTaxAuthGeoId();
        vatTaxAuthPartyId = productStore.getVatTaxAuthPartyId();
        enableAutoSuggestionList = productStore.getEnableAutoSuggestionList();
        enableDigProdUpload = productStore.getEnableDigProdUpload();
        prodSearchExcludeVariants = productStore.getProdSearchExcludeVariants();
        digProdUploadCategoryId = productStore.getDigProdUploadCategoryId();
        autoOrderCcTryExp = productStore.getAutoOrderCcTryExp();
        autoOrderCcTryOtherCards = productStore.getAutoOrderCcTryOtherCards();
        autoOrderCcTryLaterNsf = productStore.getAutoOrderCcTryLaterNsf();
        autoOrderCcTryLaterMax = productStore.getAutoOrderCcTryLaterMax();
        storeCreditValidDays = productStore.getStoreCreditValidDays();
        autoApproveInvoice = productStore.getAutoApproveInvoice();
        autoApproveOrder = productStore.getAutoApproveOrder();
        shipIfCaptureFails = productStore.getShipIfCaptureFails();
        setOwnerUponIssuance = productStore.getSetOwnerUponIssuance();
        reqReturnInventoryReceive = productStore.getReqReturnInventoryReceive();
        addToCartRemoveIncompat = productStore.getAddToCartRemoveIncompat();
        addToCartReplaceUpsell = productStore.getAddToCartReplaceUpsell();
        splitPayPrefPerShpGrp = productStore.getSplitPayPrefPerShpGrp();
        lastUpdatedStamp = productStore.getLastUpdatedStamp();
        lastUpdatedTxStamp = productStore.getLastUpdatedTxStamp();
        createdStamp = productStore.getCreatedStamp();
        createdTxStamp = productStore.getCreatedTxStamp();
        noShipOnDropShipGroups = productStore.getNoShipOnDropShipGroups();
        allowDoNotShipOrders = productStore.getAllowDoNotShipOrders();
        defaultShipmentMethodTypeId = productStore.getDefaultShipmentMethodTypeId();
        defaultShippingCarrierPartyId = productStore.getDefaultShippingCarrierPartyId();
        billToThirdPartyId = productStore.getBillToThirdPartyId();
    }


/*
    public String getProductId() {
        return productId;
    }


    public void setProductId(String productId) {
        this.productId = productId;
    }
*/
}
