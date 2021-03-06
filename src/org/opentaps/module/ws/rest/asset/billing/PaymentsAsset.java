package org.opentaps.module.ws.rest.asset.billing;

import org.apache.wink.common.annotations.Asset;
import org.opentaps.module.ws.rest.domain.billing.PaymentsBean;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Integrating Web srl
 * User: alobrano
 * Date: 10/8/11
 * Time: 5:14 PM
 */
@Asset
public class PaymentsAsset {

    private PaymentsBean paymentsBean;

    public PaymentsAsset(PaymentsBean paymentsBean) {
        this.paymentsBean = paymentsBean;
    }

    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public PaymentsBean getPaymentsBean() {
        return paymentsBean;
    }
}

