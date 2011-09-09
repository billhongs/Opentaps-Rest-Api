package org.opentaps.module.ws.rest.resources.product;

import org.apache.wink.common.annotations.Asset;
import org.apache.wink.common.model.synd.*;
import org.apache.wink.common.utils.ProviderUtils;
import org.apache.wink.server.utils.LinkBuilders;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Providers;
import java.io.IOException;


@Asset
public class ProductAsset {

    private ProductBean product;
    private boolean child;


    public ProductAsset() {
        this(null);
    }

    public ProductAsset(ProductBean product) {
        this(product, false);
    }

    public ProductAsset(ProductBean product, boolean child) {
        this.product = product;
        this.child = child;
    }

    @Produces({MediaType.APPLICATION_XML})
    public ProductBean getProduct() {
        return product;
    }

    @Produces({MediaType.WILDCARD, MediaType.APPLICATION_JSON})
    public SyndEntry getSyndEntry(@Context Providers providers, @Context UriInfo uriInfo, @Context LinkBuilders linkBuilders) throws IOException {
        SyndEntry entry = new SyndEntry();
        entry.setId("urn:com:hp:qaproducts:product:" + product.getProductId());
        entry.setTitle(new SyndText(product.getProductId()));
        entry.setSummary(new SyndText("product summary"));
        entry.addAuthor(new SyndPerson("Alberto"));

        // serialize the product xml
        String contentString =
                ProviderUtils.writeToString(providers, product, MediaType.APPLICATION_XML_TYPE);
        entry.setContent(new SyndContent(contentString, MediaType.APPLICATION_XML, false));

        // set base uri if this is a standalone entry
        if (!child) {
            entry.setBase(uriInfo.getAbsolutePath().toString());
        }

        // generate system links
        linkBuilders.createSystemLinksBuilder().subResource(product.getProductId()).build(entry.getLinks());
        return entry;
    }

    @Consumes(MediaType.APPLICATION_XML)
    public void setProduct(ProductBean product) {
        this.product = product;
    }

    @Consumes
    public void setSyndEntry(SyndEntry entry, @Context Providers providers) throws IOException {
        product = null;
        SyndContent content = entry.getContent();
        if (content == null) {
            return;
        }
        String value = content.getValue();
        String type = content.getType();
        if (value == null || !MediaType.APPLICATION_XML.equalsIgnoreCase(type)) {
            return;
        }
        // deserialize the product xml
        product =
            ProviderUtils.readFromString(providers,
                                         value,
                                         ProductBean.class,
                                         MediaType.APPLICATION_XML_TYPE);
    }

}
