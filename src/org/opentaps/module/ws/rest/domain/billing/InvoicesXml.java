package org.opentaps.module.ws.rest.domain.billing;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: alobrano
 * Date: 10/5/11
 * Time: 3:42 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "invoices")
public class InvoicesXml {

    private Collection<InvoiceXml>invoiceXml;

    public InvoicesXml() {
    }

    public InvoicesXml(List<InvoiceXml> invoicesXml) {
        this.invoiceXml = invoicesXml;
    }

    public Collection<InvoiceXml> getInvoiceXml() {
        return invoiceXml;
    }
}
