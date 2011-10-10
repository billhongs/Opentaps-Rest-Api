package org.opentaps.module.ws.rest.resources;

import org.apache.wink.common.annotations.Workspace;
import org.apache.wink.common.model.wadl.WADLGenerator;
import org.apache.wink.spring.Registrar;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: alobrano
 * Date: 10/4/11
 * Time: 7:52 PM
 * To change this template use File | Settings | File Templates.
 */
@Path("/wadl")
@Workspace(workspaceTitle = "REST API", collectionTitle = "Wadl")

public class Wadl  implements ApplicationContextAware{

    private static final String REGISTRAR_BEAN = "registrar";
    @Context
    UriInfo uriInfo;

    private ApplicationContext applicationContext;

    @GET
    @Produces("application/xml")
    public org.apache.wink.common.model.wadl.Application getWadlDoc(){
        Registrar registrar = (Registrar)applicationContext.getBean(REGISTRAR_BEAN);
        Set<Object> instances = registrar.getInstances();
        Set<Class<?>> classes = new HashSet<Class<?>>();
        for (Object instance : instances) {
            classes.add(instance.getClass());
        }


        String baseUri = uriInfo.getBaseUri().toString();
        return new WADLGenerator().generate(baseUri,classes);
    }



    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
