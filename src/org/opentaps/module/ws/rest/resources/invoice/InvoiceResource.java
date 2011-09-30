package org.opentaps.module.ws.rest.resources.invoice;

import org.apache.wink.common.annotations.Workspace;
import org.ofbiz.base.util.Debug;
import org.opentaps.base.entities.Invoice;
import org.opentaps.domain.billing.BillingDomainInterface;
import org.opentaps.foundation.repository.RepositoryException;
import org.opentaps.module.ws.rest.Context.OpentapsContext;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
public class InvoiceResource {
    protected static String MODULE = InvoiceResource.class.getName();


    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_XML})
    public Response getProducts(@Context OpentapsContext opentapsContext) {

        Debug.logInfo("USERNAME ["+opentapsContext.getSecurityManager().getUsername()+"] ROLES ["+opentapsContext.getSecurityManager().getRoles()+"] ",MODULE);
        BillingDomainInterface billingDomain = opentapsContext.getDomainDirectory().getBillingDomain();
        try {
            List<Invoice> invoices = billingDomain.getInvoiceRepository().findAll(Invoice.class);

             return Response.ok(invoices).build();


        } catch (RepositoryException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        throw new NullPointerException("MISSING IMPLEMENTATION");

    }
}
