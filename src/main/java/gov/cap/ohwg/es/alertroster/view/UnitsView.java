package gov.cap.ohwg.es.alertroster.view;

import gov.cap.ohwg.es.alertroster.model.entity.Unit;
import gov.cap.ohwg.es.alertroster.model.repo.UnitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
        return ResponseEntity.ok(unitRepo.getUnits());
    }
}
