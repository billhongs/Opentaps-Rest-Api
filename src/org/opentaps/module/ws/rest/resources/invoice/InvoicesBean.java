package org.opentaps.module.ws.rest.resources.invoice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: alobrano
 * Date: 9/30/11
 * Time: 7:00 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "invoices")
public class InvoicesBean {

    @XmlElementRef
    private Collection<InvoiceBean> invoiceBeans;


}
