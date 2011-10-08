package org.opentaps.module.ws.rest.asset;

import org.apache.wink.common.annotations.Asset;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Integrating Web srl
 * User: alobrano
 * Date: 10/8/11
 * Time: 3:36 PM
 */
@Asset
public class BeanAsset<T> {
    private T bean;

    public BeanAsset(T bean) {
        this.bean = bean;
    }

    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public T getBean(){
        return  bean;
    }

}
