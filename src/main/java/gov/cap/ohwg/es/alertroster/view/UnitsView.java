package gov.cap.ohwg.es.alertroster.view;

import gov.cap.ohwg.es.alertroster.model.entity.Unit;
import gov.cap.ohwg.es.alertroster.model.repo.GenericRepo;
import gov.cap.ohwg.es.alertroster.model.repo.UnitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<Unit>> getAll() {
        return ResponseEntity.ok(unitRepo.getMatching("wing", "OH"));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Unit> get(@PathVariable("id") int id) {
        return ResponseEntity.ok(unitRepo.get(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<Void> save(@PathVariable("id") int id, @RequestBody Unit unit) {
        unitRepo.save(unit);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
