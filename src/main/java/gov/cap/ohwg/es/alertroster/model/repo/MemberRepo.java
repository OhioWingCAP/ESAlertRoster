package gov.cap.ohwg.es.alertroster.model.repo;

import gov.cap.ohwg.es.alertroster.model.entity.Member;
import org.springframework.stereotype.Component;

/**
 * Created by ckovacs on 5/11/16.
 */
@Component
public class MemberRepo extends GenericRepo<Member> {
    public MemberRepo() {
        super(Member.class);
    }
}
