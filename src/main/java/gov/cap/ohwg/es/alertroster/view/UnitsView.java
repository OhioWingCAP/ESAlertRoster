package gov.cap.ohwg.es.alertroster.view;

import gov.cap.ohwg.es.alertroster.model.entity.Unit;
import gov.cap.ohwg.es.alertroster.model.repo.GenericRepo;
import gov.cap.ohwg.es.alertroster.model.repo.UnitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ckovacs on 4/23/16.
 */
@RestController
@RequestMapping("/view/api/units")
public class UnitsView {

    @Autowired
    private UnitRepo unitRepo;

    @RequestMapping("")
    public ResponseEntity<List<Unit>> getAll(){
        return ResponseEntity.ok(unitRepo.getMatching("wing", "OH"));
    }

    @RequestMapping("/{id}")
    public ResponseEntity<Unit> get(@PathVariable("id") int id) {
        return ResponseEntity.ok(unitRepo.get(id));
    }
}
