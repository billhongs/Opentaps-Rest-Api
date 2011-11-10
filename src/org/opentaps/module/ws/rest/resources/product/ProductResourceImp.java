package org.opentaps.module.ws.rest.resources.product;

import javolution.util.FastList;
import org.apache.wink.common.annotations.Workspace;
import org.ofbiz.base.util.*;
import org.ofbiz.entity.GenericEntityException;
import org.ofbiz.entity.GenericValue;
import org.ofbiz.entity.util.EntityUtil;
import org.opentaps.base.services.CreateGoodIdentificationService;
import org.opentaps.base.services.CreateProductPriceService;
import org.opentaps.base.services.CreateProductService;
import org.opentaps.domain.product.Product;
import org.opentaps.domain.product.ProductDomainInterface;
import org.opentaps.foundation.entity.EntityNotFoundException;
import org.opentaps.foundation.repository.RepositoryException;
import org.opentaps.foundation.service.ServiceException;
import org.opentaps.module.ws.rest.Context.OpentapsContext;
import org.opentaps.module.ws.rest.asset.product.ProductAsset;
import org.opentaps.module.ws.rest.asset.product.ProductsAsset;
import org.opentaps.module.ws.rest.domain.product.GoodIdentificationBean;
import org.opentaps.module.ws.rest.domain.product.ProductBean;
import org.opentaps.module.ws.rest.domain.product.ProductPriceBean;
import org.opentaps.module.ws.rest.domain.product.ProductsBean;
import org.opentaps.module.ws.rest.errors.NoPermissionException;
import org.opentaps.module.ws.rest.errors.RestApiException;
import org.opentaps.module.ws.rest.resources.ApiAbstractResource;

import javax.imageio.ImageIO;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: claudiomelis
 * Date: 10/25/11
 * Time: 12:07 PM
 * To change this template use File | Settings | File Templates.
 */
@Workspace(workspaceTitle = "REST API", collectionTitle = "Products")
public class ProductResourceImp extends ApiAbstractResource implements ProductResource {
    protected static String MODULE = ProductResourceImp.class.getName();

    public ProductsAsset getProducts(@Context OpentapsContext opentapsContext) throws RepositoryException, RestApiException {

        super.checkPermission(opentapsContext.getSecurityManager(), "PERMISSION", "products");

        ProductDomainInterface productDomain = opentapsContext.getDomainDirectory().getProductDomain();
        List<Product> products = productDomain.getProductRepository().findAll(Product.class);
        ProductsBean productsBean = new ProductsBean();
        for (Product product : products) {
            ProductBean productBean = getMapper().map(product, ProductBean.class);
            productsBean.getProduct().add(productBean);
        }
        return new ProductsAsset(productsBean);
    }

    public ProductAsset getProduct(@Context OpentapsContext opentapsContext,
                                   @PathParam("id") String productId) throws NoPermissionException, RepositoryException {
        //make sure we have the right permissions
        super.checkPermission(opentapsContext.getSecurityManager(), "PERMISSION", "products");

        return getProductById(opentapsContext, productId);
    }


    public ProductAsset createProduct(ProductAsset productAsset, @Context OpentapsContext opentapsContext) throws NoPermissionException, Exception {
        //make sure we have the right permissions
        super.checkPermission(opentapsContext.getSecurityManager(), "PERMISSION", "products");

        //get next seq id from product entity
        String productId = opentapsContext.getDomainDirectory().getProductDomain().getProductRepository().getNextSeqId("Product");
        productAsset.getProduct().setProductId(productId);

        //create image from base64/url and prepare image url
        String location = null;
        //small image type is empty only when there is not images to sync
        if (UtilValidate.isNotEmpty(productAsset.getProduct().getSmallImageType())) {
            Debug.logInfo("* * * Creating image...", MODULE);
            location = prepareAndCreateSmallImage(productAsset, productId, opentapsContext);
        }

        CreateProductService createProductService = getMapper().map(productAsset.getProduct(), CreateProductService.class);

        if (UtilValidate.isNotEmpty(productAsset.getProduct().getSmallImageType()) && UtilValidate.isNotEmpty(location)) {
            Debug.logInfo("* * * Creating product...", MODULE);
            createProductService.setUser(opentapsContext.getSecurityManager().getOpentapsUser());
            createProductService.setInSmallImageUrl(location);
            createProductService.runSync(opentapsContext.getInfrastructure());

            if (productAsset.getProduct().getGoodIdentifications().size() > 0) {
                // create good identification
                Debug.logInfo("* * * Creating good identifications...", MODULE);
                createGoodIdentification(productAsset, productId, opentapsContext);
            }


            if (productAsset.getProduct().getProductPrices().size() > 0) {
                //create product price
                Debug.logInfo("* * * Creating product prices...", MODULE);
                createProductPrices(productAsset, productId, opentapsContext);
            }

        }
        String outProductId = createProductService.getOutProductId();
        return getProductById(opentapsContext, outProductId);
    }


    private ProductAsset getProductById(OpentapsContext opentapsContext, String productId) {

        ProductDomainInterface productDomain = opentapsContext.getDomainDirectory().getProductDomain();
        Product productById = null;
        try {
            productById = productDomain.getProductRepository().getProductById(productId);
        } catch (EntityNotFoundException e) {
            //todo IW do something with this error
            e.printStackTrace();

        } catch (RepositoryException e) {
            //todo IW do something with this error
            e.printStackTrace();
        }
        ProductBean productBean = getMapper().map(productById, ProductBean.class);

        return new ProductAsset(productBean);

    }

    private String prepareAndCreateSmallImage(ProductAsset productAsset, String productId, OpentapsContext opentapsContext) {

        try {
            BufferedImage bImageFromConvert = null;
            ByteBuffer bb = null;
            String imageType = productAsset.getProduct().getSmallImageType();
            Debug.logInfo("* * * Creating image: Image Type is [" + imageType + "]", MODULE);
            if (UtilValidate.isNotEmpty(productAsset.getProduct().getSmallImageData())) {
                byte[] smallImagedata = productAsset.getProduct().getSmallImageData();
                InputStream in = new ByteArrayInputStream(smallImagedata);
                bImageFromConvert = ImageIO.read(in);

            } else {
                URL url = new URL(productAsset.getProduct().getSmallImageUrl());
                Debug.logInfo("* * * Creating image: linking url ", MODULE);
                return url.toString();
            }

            File installationDir = new File(".");

            String dbImgLocation = UtilProperties.getPropertyValue("rest-api", "image.final.path");
            String imgDir = UtilProperties.getPropertyValue("rest-api", "image.directory");

            String path = installationDir.getCanonicalPath() + imgDir + dbImgLocation;
            Debug.logInfo("* * * Creating image: Opentaps installation directory is [" + installationDir.getCanonicalPath() + "]", MODULE);
            Debug.logInfo("* * * Creating image: Image will be installed to [" + path + "]", MODULE);

            String filenameToUse = productId + "_small";
            List<GenericValue> fileExtension = FastList.newInstance();
            fileExtension = opentapsContext.getInfrastructure().getDelegator().findByAnd("FileExtension", UtilMisc.toMap("mimeTypeId", imageType));
            GenericValue extension = EntityUtil.getFirst(fileExtension);
            if (extension != null) {
                filenameToUse += "." + extension.getString("fileExtensionId");
            }
            Debug.logInfo("* * * Creating image: Image filename is [" + filenameToUse + "]", MODULE);

            ImageIO.write(bImageFromConvert, extension.getString("fileExtensionId"), new File(path + "/" + filenameToUse));
            Debug.logInfo("* * * Creating image: Image successfully created", MODULE);
            return dbImgLocation + "/" + filenameToUse;
        } catch (IOException e) {
            //todo IW do something with this error
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (GenericEntityException e) {
            //todo IW do something with this error
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return null;
    }

    private void createGoodIdentification(ProductAsset productAsset, String productId, OpentapsContext opentapsContext) {
        Debug.logInfo("* * * Creating good identification: Number of Good Identification to create [" + productAsset.getProduct().getGoodIdentifications().size() + "]", MODULE);

        try {
            int i = 1;
            for (GoodIdentificationBean goodIdentificationBean : productAsset.getProduct().getGoodIdentifications()) {
                Debug.logInfo("* * * Creating good identification: Creating Good Identification [" + i + "] ProductId [" + productId + "]", MODULE);
                CreateGoodIdentificationService createGoodIdentificationService = new CreateGoodIdentificationService();

                createGoodIdentificationService.setUser(opentapsContext.getSecurityManager().getOpentapsUser());
                createGoodIdentificationService.setInProductId(productId);
                Debug.logInfo("* * * Creating good identification: Good Identification Type is [" + goodIdentificationBean.getGoodIdentificationTypeId() + "]", MODULE);
                createGoodIdentificationService.setInGoodIdentificationTypeId(goodIdentificationBean.getGoodIdentificationTypeId());
                Debug.logInfo("* * * Creating good identification: Good Identification value is  [" + goodIdentificationBean.getIdValue() + "]", MODULE);
                createGoodIdentificationService.setInIdValue(goodIdentificationBean.getIdValue());
                createGoodIdentificationService.runSync(opentapsContext.getInfrastructure());
                Debug.logInfo("* * * Creating good identification: Good Identification successfully created", MODULE);
                i++;
            }
        } catch (ServiceException e) {
            //todo IW do something with this error
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private void createProductPrices(ProductAsset productAsset, String productId, OpentapsContext opentapsContext) {
        try {
            int i = 1;
            for (ProductPriceBean productPrice : productAsset.getProduct().getProductPrices()) {
                Debug.logInfo("* * * Creating product prices: Creating Product Price [" + i + "] ProductId [" + productId + "]", MODULE);

                CreateProductPriceService createProductPriceService = new CreateProductPriceService();
                createProductPriceService.setUser(opentapsContext.getSecurityManager().getOpentapsUser());
                createProductPriceService.setInCurrencyUomId(productPrice.getCurrencyUomId());
                Debug.logInfo("* * * Creating product prices: Product Price Currency is [" + productPrice.getCurrencyUomId() + "]", MODULE);
                createProductPriceService.setInProductId(productId);
                createProductPriceService.setInProductPricePurposeId(productPrice.getProductPricePurposeId());
                createProductPriceService.setInProductPriceTypeId(productPrice.getProductPriceTypeId());
                Debug.logInfo("* * * Creating product prices: Product Price Type Id is [" + productPrice.getProductPriceTypeId() + "]", MODULE);
                createProductPriceService.setInProductStoreGroupId(productPrice.getProductStoreGroupId());
                Debug.logInfo("* * * Creating product prices: Product Price Product Store Group Id is [" + productPrice.getProductStoreGroupId() + "]", MODULE);
                createProductPriceService.setInPrice(new BigDecimal(productPrice.getPrice()));
                Debug.logInfo("* * * Creating product prices: Product Price  Id is [" + productPrice.getPrice() + "]", MODULE);
                createProductPriceService.setInFromDate(UtilDateTime.nowTimestamp());
                createProductPriceService.runSync(opentapsContext.getInfrastructure());
                Debug.logInfo("* * * Creating product prices: Product Price  successfully created", MODULE);
                i++;
            }
        } catch (ServiceException e) {
            //todo IW do something with this error
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


}
