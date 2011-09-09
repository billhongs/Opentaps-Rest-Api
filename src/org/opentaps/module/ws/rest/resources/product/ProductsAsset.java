package org.opentaps.module.ws.rest.resources.product;

import javolution.util.FastList;
import org.apache.wink.common.annotations.Asset;
import org.apache.wink.common.model.synd.SyndEntry;
import org.apache.wink.common.model.synd.SyndFeed;
import org.apache.wink.common.model.synd.SyndPerson;
import org.apache.wink.common.model.synd.SyndText;
import org.apache.wink.server.utils.LinkBuilders;
import org.opentaps.domain.product.Product;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Providers;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;


@Asset
public class ProductsAsset {

    private static final String MODULE = ProductsAsset.class.getName();

    public ProductsList products;


    public ProductsAsset(){
        products = new ProductsList();
    }


    public ProductsAsset(Product product){
        products = new ProductsList(product);
    }


    public ProductsAsset(List<Product> products){
        this.products = new ProductsList(products);
    }


    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_XML})
    public ProductsAsset getProducts() {
//        return new ProductsResponse("success", "Some success message", products);
        return this;
    }


/*
    @Produces
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_XML})
    public SyndFeed getSyndFeed (@Context Providers providers, @Context LinkBuilders linkBuilders, @Context UriInfo uriInfo) throws IOException {
        SyndFeed syndFeed = new SyndFeed();
//        syndFeed.setId("urn:com:hp:qaproducts:products");
//        syndFeed.setTitle(new SyndText("Products"));
//        syndFeed.addAuthor(new SyndPerson("Alberto"));
        syndFeed.setUpdated(new Date());

        //set the entries
        for (ProductBean product : products) {
            ProductAsset productAsset = new ProductAsset(product, true);
            SyndEntry syndEntry = productAsset.getSyndEntry(providers, uriInfo, linkBuilders);
//            syndFeed.addEntry(syndEntry);
        }

        syndFeed.setBase(uriInfo.getAbsolutePath().toString());
        linkBuilders.createSystemLinksBuilder().build(syndFeed.getLinks());

        return syndFeed;
    }
*/

}
