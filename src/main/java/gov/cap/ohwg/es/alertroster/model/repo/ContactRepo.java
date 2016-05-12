package gov.cap.ohwg.es.alertroster.model.repo;

import gov.cap.ohwg.es.alertroster.model.entity.Contact;
import org.springframework.stereotype.Component;

/**
 * Created by ckovacs on 5/12/16.
 */
@Component
public class ContactRepo extends GenericRepo<Contact> {
    public ContactRepo() {
        super(Contact.class);
    }
}
