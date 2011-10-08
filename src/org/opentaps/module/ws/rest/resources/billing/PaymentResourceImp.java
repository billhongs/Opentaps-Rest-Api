package org.opentaps.module.ws.rest.resources.billing;

import org.opentaps.domain.billing.BillingDomainInterface;
import org.opentaps.domain.billing.payment.Payment;
import org.opentaps.foundation.entity.EntityNotFoundException;
import org.opentaps.foundation.repository.RepositoryException;
import org.opentaps.module.ws.rest.Context.OpentapsContext;
import org.opentaps.module.ws.rest.asset.billing.PaymentAsset;
import org.opentaps.module.ws.rest.asset.billing.PaymentsAsset;
import org.opentaps.module.ws.rest.domain.billing.PaymentBean;
import org.opentaps.module.ws.rest.domain.billing.PaymentsBean;
import org.opentaps.module.ws.rest.errors.RestApiException;
import org.opentaps.module.ws.rest.resources.ApiAbstractResource;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * Created by Integrating Web srl
 * User: alobrano
 * Date: 10/8/11
 * Time: 5:12 PM
 */
public class PaymentResourceImp  extends ApiAbstractResource implements PaymentResource {

    public PaymentsAsset getPayments(@Context OpentapsContext opentapsContext) throws RepositoryException, RestApiException {
         //make sure we have the right permissions
        super.checkPermission(opentapsContext.getSecurityManager(), "PERMISSION", "payments");

        BillingDomainInterface billingDomain = opentapsContext.getDomainDirectory().getBillingDomain();
        List<Payment> payments = billingDomain.getPaymentRepository().findAll(Payment.class);
        PaymentsBean paymentsBean = new PaymentsBean();
        for (Payment payment : payments) {
            paymentsBean.getPayment().add(getMapper().map(payment, PaymentBean.class));
        }
         return new PaymentsAsset(paymentsBean);
    }

    public PaymentAsset getPayment(@Context OpentapsContext opentapsContext, @PathParam(PAYMENT_ID) String paymentId) throws RepositoryException, RestApiException {
        //make sure we have the right permissions
        super.checkPermission(opentapsContext.getSecurityManager(), "PERMISSION", "payments");

        return getPaymentById(opentapsContext,paymentId);
    }


    private PaymentAsset getPaymentById(OpentapsContext opentapsContext, String paymentId){
        BillingDomainInterface billingDomain = opentapsContext.getDomainDirectory().getBillingDomain();
        Payment payment = null;

        try {
            payment = billingDomain.getPaymentRepository().getPaymentById(paymentId);
        } catch (RepositoryException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (EntityNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return new PaymentAsset(getMapper().map(payment,PaymentBean.class));
    }
}
