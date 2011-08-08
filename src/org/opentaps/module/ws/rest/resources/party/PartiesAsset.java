package org.opentaps.module.ws.rest.resources.lead;

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
public class PartiesAsset {

    private static final String MODULE = PartiesAsset.class.getName();

}
