package gov.cap.ohwg.es.alertroster.model.repo;

import gov.cap.ohwg.es.alertroster.model.entity.Unit;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by ckovacs on 5/10/16.
 */
@Component
public class UnitRepo extends GenericRepo<Unit> {

    public UnitRepo() {
        super(Unit.class);
    }

    @Override
    public Unit get(long id) {
        return parent(super.get(id));
    }

    @Override
    public List<Unit> getAll() {
        return parent(super.getAll());
    }

    @Override
    public List<Unit> getMatching(String name, String value) {
        return parent(super.getMatching(name, value));
    }

    private Unit parent(Unit unit) {
        if(unit == null || unit.getNextLevel() == null) {
            return unit;
        }
        unit.setParent(super.get(unit.getNextLevel()));
        return unit;
    }

    private List<Unit> parent(List<Unit> units) {
        for(Unit unit : units) {
            parent(unit);
        }
        return units;
    }

}
