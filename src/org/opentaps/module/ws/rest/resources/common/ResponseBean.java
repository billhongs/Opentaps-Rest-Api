package org.opentaps.module.ws.rest.resources.common;

import javax.xml.bind.annotation.*;
import java.util.Date;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "response", propOrder = {"status", "message"})
@XmlRootElement(name = "response")
public class ResponseBean {

    @XmlElement(required = true)
    private String         status;
    @XmlElement(required = true)
    private String         message;


    /**
     * Constructor.
     */
    public ResponseBean() {
    }

    /**
     * Constructor.
     *
     * @param status Response status
     * @param message First name
     */
    public ResponseBean(String status,
                        String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * <code>status</code> getter.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * <code>status</code> setter.
     *
     * @param status the <code>status</code> to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * <code>message</code> getter.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * <code>message</code> setter.
     *
     * @param message the <code>message</code> to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
