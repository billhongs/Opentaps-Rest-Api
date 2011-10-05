package org.opentaps.module.ws.rest.resources;

import org.dozer.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: alobrano
 * Date: 10/5/11
 * Time: 12:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class ApiAbstractResource {
    protected Mapper mapper;


    protected Object mapBean(Object source,Class destination){
        return mapper.map(source,destination);
    }


//    GETTERS AND SETTERS

    public Mapper getMapper() {
        return mapper;
    }

    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }
}
