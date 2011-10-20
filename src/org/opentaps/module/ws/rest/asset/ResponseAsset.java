package org.opentaps.module.ws.rest.asset;

import org.apache.wink.common.annotations.Asset;
import org.opentaps.module.ws.rest.domain.response.ResponseBean;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Integrating Web srl
 * User: alobrano
 * Date: 10/19/11
 * Time: 1:29 PM
 */
@Asset
public class ResponseAsset {
    private ResponseBean responseBean;

    public ResponseAsset() {
    }

    public ResponseAsset(ResponseBean responseBean) {
        this.responseBean = responseBean;
    }
    @Produces({MediaType.APPLICATION_XML})
    public ResponseBean getResponseBean() {
        return responseBean;
    }

    public void setResponseBean(ResponseBean responseBean) {
        this.responseBean = responseBean;
    }
}
