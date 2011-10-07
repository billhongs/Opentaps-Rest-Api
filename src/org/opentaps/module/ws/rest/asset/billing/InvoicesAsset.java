package org.opentaps.module.ws.rest.asset.billing;

import org.apache.wink.common.annotations.Asset;
import org.opentaps.module.ws.rest.domain.billing.InvoicesBean;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by IntelliJ IDEA.
 * User: alobrano
 * Date: 10/5/11
 * Time: 3:35 PM
 * To change this template use File | Settings | File Templates.
 */
@Asset
public class InvoicesAsset {

    private InvoicesBean invoicesBean;

    public InvoicesAsset(InvoicesBean invoicesBean){
        this.invoicesBean = invoicesBean;
    }


    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public InvoicesBean getInvoices(){
        return invoicesBean;
    }


}
