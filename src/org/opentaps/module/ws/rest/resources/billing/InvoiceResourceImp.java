package org.opentaps.module.ws.rest.resources.billing;

import org.apache.wink.common.annotations.Workspace;
import org.opentaps.base.entities.InvoiceItem;
import org.opentaps.domain.billing.BillingDomainInterface;
import org.opentaps.domain.billing.invoice.Invoice;
import org.opentaps.foundation.repository.RepositoryException;
import org.opentaps.module.ws.rest.Context.OpentapsContext;
import org.opentaps.module.ws.rest.asset.billing.InvoiceAsset;
import org.opentaps.module.ws.rest.asset.billing.InvoicesAsset;
import org.opentaps.module.ws.rest.domain.billing.InvoiceBean;
import org.opentaps.module.ws.rest.domain.billing.InvoiceItemBean;
import org.opentaps.module.ws.rest.domain.billing.InvoicesBean;
import org.opentaps.module.ws.rest.errors.NoPermissionException;
import org.opentaps.module.ws.rest.errors.RestApiException;
import org.opentaps.module.ws.rest.resources.ApiAbstractResource;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: alobrano
 * Date: 22/09/2011
 * Time: 11:13
 * To change this template use File | Settings | File Templates.
 */

public class InvoiceResourceImp extends ApiAbstractResource implements InvoiceResource {
    protected static String MODULE = InvoiceResourceImp.class.getName();



    public InvoicesAsset getInvoices(@Context OpentapsContext opentapsContext) throws RepositoryException, RestApiException {

        //make sure we have the right permissions
        super.checkPermission(opentapsContext.getSecurityManager(), "PERMISSION", "invoices");

        BillingDomainInterface billingDomain = opentapsContext.getDomainDirectory().getBillingDomain();
        List<Invoice> invoices = billingDomain.getInvoiceRepository().findAll(Invoice.class);
        InvoicesBean invoicesBean = new InvoicesBean();
        for (Invoice invoice : invoices) {
            InvoiceBean invoiceBean = getMapper().map(invoice,InvoiceBean.class);
            invoicesBean.getInvoices().add(invoiceBean);
        }
        return new InvoicesAsset(invoicesBean);
    }


    public InvoiceAsset getInvoice(@Context OpentapsContext opentapsContext,
                                   @PathParam("id") String invoiceId) throws NoPermissionException, RepositoryException {
        //make sure we have the right permissions
        super.checkPermission(opentapsContext.getSecurityManager(), "PERMISSION", "invoices");
//
//        BillingDomainInterface billingDomain = opentapsContext.getDomainDirectory().getBillingDomain();
//        Invoice invoiceById = null;
//        try {
//            invoiceById = billingDomain.getInvoiceRepository().getInvoiceById(invoiceId);
//        } catch (EntityNotFoundException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
//
//        return new InvoiceAsset(getMapper().map(invoiceById,InvoiceXml.class));
        return null;
    }


    public InvoiceAsset createInvoice(InvoiceAsset invoiceAsset, @Context OpentapsContext opentapsContext) throws NoPermissionException, Exception {
        //make sure we have the right permissions
//        super.checkPermission(opentapsContext.getSecurityManager(), "PERMISSION", "invoices");
//
//        CreateInvoiceService createInvoiceService = new CreateInvoiceService();
//        InvoiceXml invoiceXml = invoiceAsset.getInvoice();
//        createInvoiceService.setInInvoiceTypeId(invoiceXml.getInvoiceTypeId());
//
//        createInvoiceService.setUser(opentapsContext.getSecurityManager().getOpentapsUser());
//
//        createInvoiceService.runSync(opentapsContext.getInfrastructure());
//
//        String outInvoiceId = createInvoiceService.getOutInvoiceId();
//
//        Invoice invoice = opentapsContext.getDomainDirectory().getBillingDomain().getInvoiceRepository().getInvoiceById(outInvoiceId);
//
//
//        return new InvoiceAsset(getMapper().map(invoice,InvoiceXml.class));
        return null;
    }
}
