package org.opentaps.module.ws.rest.resources.lead;

import org.opentaps.domain.DomainService;

import org.opentaps.base.constants.ContactMechPurposeTypeConstants;
import org.opentaps.base.services.CrmsfaCreateLeadService;
import org.opentaps.base.services.CreatePartyEmailAddressService;
import org.opentaps.base.services.CreatePartyNoteService;
import org.opentaps.foundation.service.ServiceException;
import org.opentaps.foundation.infrastructure.Infrastructure;
import org.opentaps.foundation.infrastructure.User;

import org.ofbiz.base.util.Debug;
import org.ofbiz.base.util.UtilMisc;
import org.ofbiz.base.util.UtilValidate;

import java.util.Locale;


/**
 * Service to upload leads from an Excel spreadsheet.
 */
public class LeadsServices extends DomainService {

    private static final String MODULE = LeadsServices.class.getName();


    /**
     * Default constructor.
     */
    public LeadsServices() {
        super();
    }

    /**
     * Creates a new <code>UploadLeadsServices</code> instance.
     *
     * @param infrastructure an <code>Infrastructure</code> value
     * @param user an <code>User</code> value
     * @param locale a <code>Locale</code> value
     * @exception ServiceException if an error occurs
     */
    public LeadsServices(Infrastructure infrastructure, User user, Locale locale) throws ServiceException {
        super(infrastructure, user, locale);
    }


    public void createLead(LeadBean lead) throws ServiceException {
        Debug.logInfo("Creating lead [" + lead.getCompanyName() + " " + lead.getFirstName() + " " + lead.getLastName() + " " + lead.getEmailAddress() + " " + lead.getEmailAddress() + "]", "LeadsServices");

        // Create the lead, this must be filled
        CrmsfaCreateLeadService createLeadService = new CrmsfaCreateLeadService();
//        createLeadService.setInUserLogin();
        createLeadService.setInCompanyName(lead.getCompanyName());
        createLeadService.setInFirstName(lead.getFirstName());
        createLeadService.setInLastName(lead.getLastName());

        // TODO Mandatory fields are missing, test how that works.

//        if (UtilValidate.isNotEmpty(lead.getEmailAddress())) {
            createLeadService.setInPrimaryEmail(lead.getEmailAddress());
//        }

//        if (UtilValidate.isNotEmpty(lead.getDescription())) {
            createLeadService.setInDescription(lead.getDescription());
//        }

        runSync(createLeadService);

        String leadId = createLeadService.getOutPartyId();

        Debug.logInfo("createLead - Creating lead leadId: " + leadId, "LeadsServices");
/*
        // Create the postal address if given
        if (UtilValidate.isNotEmpty(row.get("address1"))) {
            CreatePartyPostalAddressService addressSer = new CreatePartyPostalAddressService();
            addressSer.setInPartyId(leadId);
            addressSer.setInAttnName(row.get("attnName"));
            addressSer.setInAddress1(row.get("address1"));
            addressSer.setInAddress2(row.get("address2"));
            addressSer.setInCity(row.get("city"));
            addressSer.setInStateProvinceGeoId(row.get("stateProvinceGeoId"));
            addressSer.setInPostalCode(row.get("postalCode"));
            addressSer.setInPostalCodeExt(row.get("postalCodeExt"));
            addressSer.setInCountryGeoId(row.get("countryGeoId"));
            // set the purpose as General Correspondence Address
            addressSer.setInContactMechPurposeTypeId(ContactMechPurposeTypeConstants.GENERAL_LOCATION);
            // set the toName to the lead names
            addressSer.setInToName(row.get("firstName") + " " + row.get("lastName"));
            runSync(addressSer);
        }

        // Create the primary phone number if given
        if (UtilValidate.isNotEmpty(row.get("primaryPhoneNumber"))) {
            CreatePartyTelecomNumberService phoneSer = new CreatePartyTelecomNumberService();
            phoneSer.setInPartyId(leadId);
            phoneSer.setInCountryCode(row.get("primaryPhoneCountryCode"));
            phoneSer.setInAreaCode(row.get("primaryPhoneAreaCode"));
            phoneSer.setInContactNumber(row.get("primaryPhoneNumber"));
            phoneSer.setInExtension(row.get("primaryPhoneExtension"));
            // set the purpose as Primary Phone Number
            phoneSer.setInContactMechPurposeTypeId(ContactMechPurposeTypeConstants.PRIMARY_PHONE);
            runSync(phoneSer);
        }

        // Create the secondary phone number if given
        if (UtilValidate.isNotEmpty(row.get("secondaryPhoneNumber"))) {
            CreatePartyTelecomNumberService phoneSer = new CreatePartyTelecomNumberService();
            phoneSer.setInPartyId(leadId);
            phoneSer.setInCountryCode(row.get("secondaryPhoneCountryCode"));
            phoneSer.setInAreaCode(row.get("secondaryPhoneAreaCode"));
            phoneSer.setInContactNumber(row.get("secondaryPhoneNumber"));
            phoneSer.setInExtension(row.get("secondaryPhoneExtension"));
            // set the purpose as Primary Phone Number (there is no secondary type)
            phoneSer.setInContactMechPurposeTypeId(ContactMechPurposeTypeConstants.PRIMARY_PHONE);
            runSync(phoneSer);
        }

        // Create the fax number if given
        if (UtilValidate.isNotEmpty(row.get("faxNumber"))) {
            CreatePartyTelecomNumberService faxSer = new CreatePartyTelecomNumberService();
            faxSer.setInPartyId(leadId);
            faxSer.setInCountryCode(row.get("faxCountryCode"));
            faxSer.setInAreaCode(row.get("faxAreaCode"));
            faxSer.setInContactNumber(row.get("faxNumber"));
            // set the purpose as Fax Number
            faxSer.setInContactMechPurposeTypeId(ContactMechPurposeTypeConstants.FAX_NUMBER);
            runSync(faxSer);
        }
*/
/*
        // Create the email address if given
        if (UtilValidate.isNotEmpty(lead.getEmailAddress())) {
            CreatePartyEmailAddressService emailSer = new CreatePartyEmailAddressService();
            emailSer.setInPartyId(leadId);
            emailSer.setInEmailAddress(lead.getEmailAddress());
            // set the purpose as Primary Email Address
            emailSer.setInContactMechPurposeTypeId(ContactMechPurposeTypeConstants.PRIMARY_EMAIL);

            try {
                runSync(emailSer);
            } catch (ServiceException e) {
                Debug.logError("createLead - Adding Email to a lead failed: " + e.getMessage(), "LeadsServices");
            }
        }
*/
/*
        // Create the web address if given
        if (UtilValidate.isNotEmpty(row.get("webAddress"))) {
            CreatePartyContactMechService webSer = new CreatePartyContactMechService();
            webSer.setInPartyId(leadId);
            webSer.setInContactMechTypeId(ContactMechTypeConstants.ElectronicAddress.WEB_ADDRESS);
            webSer.setInInfoString(row.get("webAddress"));
            // set the purpose as Primary Email Address
            webSer.setInContactMechPurposeTypeId(ContactMechPurposeTypeConstants.PRIMARY_WEB_URL);
            runSync(webSer);
        }
*/
/*
        // Create the note if given
        if (UtilValidate.isNotEmpty(lead.getDescription())) {
            CreatePartyNoteService noteSer = new CreatePartyNoteService();
            noteSer.setInPartyId(leadId);
            noteSer.setInNote(lead.getDescription());

            try {
                runSync(noteSer);
            } catch (ServiceException e) {
                Debug.logError("createLead - Adding Note to a lead failed: " + e.getMessage(), "LeadsServices");
            }
        }
*/

        // add to the list of successfully imported leads
//        createdLeadIds.add(leadId);

        lead.setId(leadId);
    }

}
