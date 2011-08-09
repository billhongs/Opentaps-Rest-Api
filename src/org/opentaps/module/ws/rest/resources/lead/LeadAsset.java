package org.opentaps.module.ws.rest.resources.lead;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.wink.common.annotations.Asset;


@Asset
public class LeadAsset {

    private LeadBean lead;


    public LeadAsset() {
        lead = null;
    }


    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_XML})
    public LeadBean getLead() {
        return lead;
    }


    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_XML})
    public void setLead(LeadBean lead) {
        this.lead = lead;
    }

}
