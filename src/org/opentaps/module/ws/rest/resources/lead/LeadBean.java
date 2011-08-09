package org.opentaps.module.ws.rest.resources.lead;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DefectType", propOrder = {"id", "companyName", "firstName", "lastName", "description", "created", "author",
                                           "status", "severity", "assignedTo"})
@XmlRootElement(name = "lead")
public class LeadBean {

    @XmlElement(required = true)
    private String         id;
    @XmlElement(required = true)
    private String         companyName;
    @XmlElement(required = true)
    private String         firstName;
    @XmlElement(required = true)
    private String         lastName;
    @XmlElement(required = true)
    private String         description;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    private Date           created;
    @XmlElement(required = true)
    private String         status;
    @XmlElement(required = true)
    private String         assignedTo;


    /**
     * Constructor.
     */
    public LeadBean() {
    }


    /**
     * Constructor.
     * 
     * @param id Lead ID
     * @param companyName Company name
     * @param firstName First name
     * @param lastName Last name
     * @param description Lead description
     * @param created Creation date
     * @param status Status
     * @param assignedTo Assigned to
     */
    public LeadBean(String id,
                      String companyName,
                      String firstName,
                      String lastName,
                      String description,
                      Date created,
                      String status,
                      String assignedTo) {
        this.id = id;
        this.companyName = companyName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.created = created;
        this.status = status;
        this.assignedTo = assignedTo;
    }

    /**
     * <code>companyName</code> getter.
     * 
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * <code>companyName</code> setter.
     * 
     * @param companyName the <code>companyName</code> to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * <code>firstName</code> getter.
     *
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * <code>firstName</code> setter.
     *
     * @param firstName the <code>firstName</code> to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * <code>lastName</code> getter.
     *
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * <code>lastName</code> setter.
     *
     * @param lastName the <code>lastName</code> to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * <code>assignedTo</code> getter.
     *
     * @return the assignedTo
     */
    public String getAssignedTo() {
        return assignedTo;
    }

    /**
     * <code>assignedTo</code> setter.
     *
     * @param assignedTo the <code>assignedTo</code> to set
     */
    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    /**
     * <code>created</code> getter.
     * 
     * @return the created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * <code>created</code> setter.
     * 
     * @param created the <code>created</code> to set
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * <code>description</code> getter.
     * 
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * <code>description</code> setter.
     * 
     * @param description the <code>description</code> to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * <code>id</code> getter.
     * 
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * <code>id</code> setter.
     * 
     * @param id the <code>id</code> to set
     */
    public void setId(String id) {
        this.id = id;
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

}
