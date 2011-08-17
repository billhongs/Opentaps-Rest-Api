package org.opentaps.module.ws.rest.resources.lead;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Providers;

import org.apache.wink.common.annotations.Asset;

import org.ofbiz.base.util.Debug;


@Asset
public class LeadAsset {

    private LeadBean lead;


    public LeadAsset() {
        this(null);
        Debug.logInfo("AK47 - LeadAsset 1 called", "MODULE");
    }


    public LeadAsset(LeadBean lead) {
        this.lead = lead;
        Debug.logInfo("AK47 - LeadAsset 2 called", "MODULE");
    }


    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_XML})
    public LeadBean getLead() {
        Debug.logInfo("AK47 - getLead called", "MODULE");

        return lead;
    }


    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_XML})
    public void setLead(LeadBean lead) {
        Debug.logInfo("AK47 - setLead called", "LeadAsset");
        this.lead = lead;
    }

}
