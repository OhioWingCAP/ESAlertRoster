package gov.cap.ohwg.es.alertroster.model.repo;

import gov.cap.ohwg.es.alertroster.model.entity.DutyPosition;
import gov.cap.ohwg.es.alertroster.model.entity.Member;
import gov.cap.ohwg.es.alertroster.model.entity.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ckovacs on 5/10/16.
 */
@Component
public class UnitRepo extends GenericRepo<Unit> {

    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private DutyPositionRepo dutyPositionRepo;

    @Autowired
    private AuthUtils authUtils;

    public UnitRepo() {
        super(Unit.class);
    }

    @Override
    public Unit get(long id) {
        Unit unit = parent(super.get(id));
        return authUtils.checkAuth(unit.getOrgid(), unit);
    }

    @Override
    public List<Unit> getAll() {
        return parent(filterAuthorized(super.getAll()));
    }

    private List<Unit> filterAuthorized(List<Unit> units) {
        List<Unit> authorizedUnits = new ArrayList<>();
        for(Unit unit : units) {
            if(authUtils.checkAuth(unit.getOrgid(), unit) != null) {
                authorizedUnits.add(unit);
            }
        }
        return authorizedUnits;
    }

    @Override
    public List<Unit> getMatching(String name, Object value) {
        return parent(filterAuthorized(super.getMatching(name, value)));
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
