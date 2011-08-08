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
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Providers;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Asset
public class ProductsAsset {

    private static final String MODULE = ProductsAsset.class.getName();

    private List<ProductBean> products;


    public ProductsAsset(){
        this.products = FastList.newInstance();
    }


    public ProductsAsset(Collection<Product> products){
        this();
        for (Product product : products) {
            this.products.add(new ProductBean(product));
        }

    }


    public List<ProductBean> getProducts() {
        return products;
    }


    @Produces
    public SyndFeed getSyndFeed (@Context Providers providers, @Context LinkBuilders linkBuilders, @Context UriInfo uriInfo) throws IOException {
        SyndFeed syndFeed = new SyndFeed();
        syndFeed.setId("urn:com:hp:qaproducts:products");
        syndFeed.setTitle(new SyndText("Products"));
        syndFeed.addAuthor(new SyndPerson("Alberto"));
        syndFeed.setUpdated(new Date());

        //set the entries
        for (ProductBean product : products) {
            ProductAsset productAsset = new ProductAsset(product, true);
            SyndEntry syndEntry = productAsset.getSyndEntry(providers, uriInfo, linkBuilders);
            syndFeed.addEntry(syndEntry);
        }

        syndFeed.setBase(uriInfo.getAbsolutePath().toString());
        linkBuilders.createSystemLinksBuilder().build(syndFeed.getLinks());
        return syndFeed;
    }

}
