package org.opentaps.module.ws.rest.resources.billing;

import org.opentaps.foundation.repository.RepositoryException;
import org.opentaps.module.ws.rest.Context.OpentapsContext;
import org.opentaps.module.ws.rest.asset.billing.PaymentAsset;
import org.opentaps.module.ws.rest.asset.billing.PaymentsAsset;
import org.opentaps.module.ws.rest.errors.RestApiException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * Created by Integrating Web srl
 * User: alobrano
 * Date: 10/8/11
 * Time: 5:12 PM
 */
@Path(PaymentResource.PAYMENTS_PATH)
public interface PaymentResource {
    String PAYMENTS_PATH = "payments";
    String PAYMENT_ID = "id";
    String PAYMENT_ID_PATH = "{" + PAYMENT_ID + "}";

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_JSON})
    PaymentsAsset getPayments(@Context OpentapsContext opentapsContext) throws RepositoryException, RestApiException;



    @GET
    @Path(PaymentResource.PAYMENT_ID_PATH)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_JSON})
    PaymentAsset getPayment(@Context OpentapsContext opentapsContext,
                            @PathParam(PAYMENT_ID) String paymentId) throws RepositoryException, RestApiException;


}
