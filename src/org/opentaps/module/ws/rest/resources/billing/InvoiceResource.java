package org.opentaps.module.ws.rest.resources.billing;

import org.apache.wink.common.annotations.Workspace;
import org.opentaps.foundation.repository.RepositoryException;
import org.opentaps.module.ws.rest.Context.OpentapsContext;
import org.opentaps.module.ws.rest.asset.billing.InvoiceAsset;
import org.opentaps.module.ws.rest.asset.billing.InvoicesAsset;
import org.opentaps.module.ws.rest.errors.NoPermissionException;
import org.opentaps.module.ws.rest.errors.RestApiException;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * Created by Integrating Web srl
 * User: alobrano
 * Date: 10/7/11
 * Time: 4:28 PM
 */
@Path(InvoiceResource.INVOICES_PATH)
@Workspace(workspaceTitle = "REST API", collectionTitle = "Invoices")
public interface InvoiceResource {
    String INVOICES_PATH = "invoices";
    String INVOICE_ID = "id";
    String INVOICE_ID_PATH = "{"+ INVOICE_ID +"}";

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_JSON})
    InvoicesAsset getInvoices(@Context OpentapsContext opentapsContext) throws RepositoryException, RestApiException;

    @Path(InvoiceResource.INVOICE_ID_PATH)
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_JSON})
    InvoiceAsset getInvoice(@Context OpentapsContext opentapsContext,
                            @PathParam("id") String invoiceId) throws NoPermissionException, RepositoryException;

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_JSON})
    InvoiceAsset createInvoice(InvoiceAsset invoiceAsset, @Context OpentapsContext opentapsContext) throws NoPermissionException, Exception;
}
