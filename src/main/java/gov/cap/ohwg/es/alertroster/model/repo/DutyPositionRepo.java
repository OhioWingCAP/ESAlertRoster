package gov.cap.ohwg.es.alertroster.model.repo;

import gov.cap.ohwg.es.alertroster.model.entity.DutyPosition;
import org.springframework.stereotype.Component;

/**
 * Created by ckovacs on 5/12/16.
 */
@Component
public class DutyPositionRepo extends GenericRepo<DutyPosition> {
    public DutyPositionRepo() {
        super(DutyPosition.class);
    }

}
