package org.opentaps.module.ws.rest.resources.party;

import org.opentaps.module.ws.rest.resources.common.CommonResource;

import org.apache.wink.common.annotations.Workspace;
import org.ofbiz.base.util.Debug;
import org.ofbiz.base.util.UtilMisc;
import org.ofbiz.base.util.UtilValidate;
import org.ofbiz.entity.GenericDelegator;
import org.ofbiz.entity.GenericValue;
import org.ofbiz.service.GenericDispatcher;
import org.ofbiz.service.LocalDispatcher;

import org.opentaps.base.entities.UserLogin;
import org.opentaps.base.services.CreateProductService;
import org.opentaps.base.services.UserLoginService;
import org.opentaps.domain.product.Product;
import org.opentaps.foundation.entity.EntityNotFoundException;
import org.opentaps.foundation.infrastructure.Infrastructure;
import org.opentaps.foundation.infrastructure.User;
import org.opentaps.foundation.repository.RepositoryException;
import org.opentaps.foundation.repository.ofbiz.Repository;
import org.opentaps.foundation.service.ServiceException;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;


@Path(PartiesResource.MAIN_URL)
@Workspace(workspaceTitle = "REST API", collectionTitle = "Parties")
public class PartiesResource extends CommonResource {

    private static String MODULE = PartiesResource.class.getName();
    public static final String MAIN_URL = "/parties";


    public PartiesResource() {
        super();
    }


    @GET
    @Produces({MediaType.TEXT_PLAIN}) // MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_XML
    public String getParties() {
        Debug.logInfo("AK47 - Getting list of parties", MODULE);
        return "Test response";
    }

}
