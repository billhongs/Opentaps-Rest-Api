package org.opentaps.module.ws.rest.asset.billing;

import org.apache.wink.common.annotations.Asset;
import org.opentaps.module.ws.rest.domain.billing.PaymentBean;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Integrating Web srl
 * User: alobrano
 * Date: 10/8/11
 * Time: 5:15 PM
 */
@Asset
public class PaymentAsset {
    private PaymentBean paymentBean;

    public PaymentAsset(PaymentBean paymentBean) {
        this.paymentBean = paymentBean;
    }

    @Produces({MediaType.APPLICATION_XML})
    public PaymentBean getPaymentBean() {
        return paymentBean;
    }


}
