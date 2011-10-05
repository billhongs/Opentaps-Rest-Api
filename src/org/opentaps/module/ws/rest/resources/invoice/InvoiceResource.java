package org.opentaps.module.ws.rest.resources.invoice;

import org.apache.wink.common.annotations.Workspace;
import org.opentaps.domain.billing.BillingDomainInterface;
import org.opentaps.domain.billing.invoice.Invoice;
import org.opentaps.foundation.repository.RepositoryException;
import org.opentaps.module.ws.rest.Context.OpentapsContext;
import org.opentaps.module.ws.rest.errors.NoPermissionException;
import org.opentaps.module.ws.rest.errors.RestApiException;
import org.opentaps.module.ws.rest.resources.ApiAbstractResource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: alobrano
 * Date: 22/09/2011
 * Time: 11:13
 * To change this template use File | Settings | File Templates.
 */
@Path("/invoices")
@Workspace(workspaceTitle = "REST API", collectionTitle = "Invoices")
public class InvoiceResource extends ApiAbstractResource {
    protected static String MODULE = InvoiceResource.class.getName();


    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_JSON})
    public InvoicesAsset getInvoices(@Context OpentapsContext opentapsContext) throws RepositoryException, RestApiException {

        //make sure we have the right permissions
        super.checkPermission(opentapsContext.getSecurityManager(), "PERMISSION", "invoices");

        BillingDomainInterface billingDomain = opentapsContext.getDomainDirectory().getBillingDomain();
        List<Invoice> invoices = billingDomain.getInvoiceRepository().findAll(Invoice.class);
        return new InvoicesAsset(invoices, getMapper());
    }
}
