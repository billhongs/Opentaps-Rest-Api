package org.opentaps.module.ws.rest.resources.invoice;

import org.apache.wink.common.annotations.Asset;
import org.dozer.Mapper;
import org.opentaps.domain.billing.invoice.Invoice;
import org.opentaps.module.ws.rest.domain.billing.InvoiceXml;
import org.opentaps.module.ws.rest.domain.billing.InvoicesXml;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: alobrano
 * Date: 10/5/11
 * Time: 3:35 PM
 * To change this template use File | Settings | File Templates.
 */
@Asset
public class InvoicesAsset {

    private List<InvoiceXml> invoiceXmlList;

    public InvoicesAsset(List<Invoice> invoiceList,Mapper mapper) {
        invoiceXmlList = new ArrayList<InvoiceXml>(invoiceList.size());
        for (Invoice invoice : invoiceList) {
            invoiceXmlList.add(mapper.map(invoice, InvoiceXml.class));
        }
    }

    @Produces( {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public InvoicesXml getInvoices(){
        return new InvoicesXml(invoiceXmlList);
    }


}
