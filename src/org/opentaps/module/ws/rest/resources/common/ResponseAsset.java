package org.opentaps.module.ws.rest.resources.common;

import org.apache.wink.common.annotations.Asset;
import org.ofbiz.base.util.Debug;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Asset
public class ResponseAsset {

    private ResponseBean response;


    public ResponseAsset() {
        this(null);
    }


    public ResponseAsset(ResponseBean response) {
        this.response = response;
    }


    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_XML})
    public ResponseBean getResponse() {
        return response;
    }


    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_XML})
    public void setResponse(ResponseBean response) {
        this.response = response;
    }

}
