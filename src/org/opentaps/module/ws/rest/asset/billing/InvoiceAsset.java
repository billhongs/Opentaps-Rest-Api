package org.opentaps.module.ws.rest.asset.billing;


import org.apache.wink.common.annotations.Asset;
import org.opentaps.module.ws.rest.domain.billing.InvoiceBean;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by IntelliJ IDEA.
 * User: alobrano
 * Date: 10/6/11
 * Time: 1:27 PM
 * To change this template use File | Settings | File Templates.
 */
@Asset
public class InvoiceAsset {

    private InvoiceBean invoiceBean;


    public InvoiceAsset() {
    }

    public InvoiceAsset(InvoiceBean invoiceBean) {
        this.invoiceBean = invoiceBean;
    }

    @Produces({MediaType.APPLICATION_XML})
    public InvoiceBean getInvoice(){
         return invoiceBean;
    }

    @Consumes(MediaType.APPLICATION_XML)
    public void setInvoice(InvoiceBean invoiceBean){
        this.invoiceBean = invoiceBean;
    }

}
