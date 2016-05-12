package gov.cap.ohwg.es.alertroster.view;

import gov.cap.ohwg.es.alertroster.model.entity.Member;
import gov.cap.ohwg.es.alertroster.model.entity.Unit;
import gov.cap.ohwg.es.alertroster.model.repo.GenericRepo;
import gov.cap.ohwg.es.alertroster.model.repo.MemberRepo;
import gov.cap.ohwg.es.alertroster.model.repo.UnitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ckovacs on 4/23/16.
 */
@RestController
@RequestMapping("/view/api/members")
public class MembersView {

    @Autowired
    private MemberRepo memberRepo ;

    @RequestMapping("")
    public ResponseEntity<List<Member>> getAll(@RequestParam(value="query", required=true) String query){
        String[] terms = query.split("\\s");
        return ResponseEntity.ok(memberRepo.searchFor(terms));
    }

    @RequestMapping("/{id}")
    public ResponseEntity<Member> get(@PathVariable("id") int id) {
        return ResponseEntity.ok(memberRepo.get(id));
    }

}
