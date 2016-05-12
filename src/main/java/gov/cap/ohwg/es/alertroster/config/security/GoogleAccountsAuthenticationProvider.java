package gov.cap.ohwg.es.alertroster.config.security;

import com.google.appengine.api.users.User;
import gov.cap.ohwg.es.alertroster.model.entity.DutyPosition;
import gov.cap.ohwg.es.alertroster.model.entity.Member;
import gov.cap.ohwg.es.alertroster.model.pojo.GaeUser;
import gov.cap.ohwg.es.alertroster.model.repo.DutyPositionRepo;
import gov.cap.ohwg.es.alertroster.model.repo.MemberRepo;
import gov.cap.ohwg.es.alertroster.service.user.GaeDatastoreUserRegistry;
import gov.cap.ohwg.es.alertroster.service.user.UserRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ckovacs on 1/17/16.
 */
@Component
public class GoogleAccountsAuthenticationProvider extends PreAuthenticatedAuthenticationProvider {

    private final GaeDatastoreUserRegistry userRegistry;

    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private DutyPositionRepo dutyPositionRepo;

    @Autowired
    public GoogleAccountsAuthenticationProvider(GaeDatastoreUserRegistry userRegistry) {
        this.userRegistry = userRegistry;
        setPreAuthenticatedUserDetailsService(userRegistry);
    }

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Object principal = authentication.getPrincipal();
        User googleUser = null;
        GaeUser user = null;
        if (principal instanceof User) {
            googleUser = (User) authentication.getPrincipal();
            if (googleUser != null) {
                userRegistry.findUser(googleUser.getUserId());
            }
        } else if (principal instanceof GaeUser) {
            user = (GaeUser) principal;
        }

        if (googleUser != null) {

            if (user == null) {
                // User not in registry. Needs to register
                user = new GaeUser(googleUser.getUserId(), googleUser.getNickname(), googleUser.getEmail());
            }

            Member member = memberRepo.getOneByEmail(googleUser.getEmail());
            if (member == null) {
                return null;
            }
            String charter = member.getUnit();
            List<DutyPosition> dutyPositions = dutyPositionRepo.getMatching("capid", member.getCapid());
            for(DutyPosition position : filterESOpsCommand(dutyPositions)) {
                user.getUnitAccess().add(position.getOrgid());
            }
        }

        if (!user.isEnabled()) {
            throw new DisabledException("Account is disabled");
        }

        return new GaeUserAuthentication(user, authentication.getDetails());
    }

    private List<DutyPosition> filterESOpsCommand(List<DutyPosition> dutyPositions) {
        List<DutyPosition> esOpsCommand = new ArrayList<>();
        for (DutyPosition position : dutyPositions) {
            //DO, DOS, or EX
            String functArea = position.getFunctArea();
            switch (position.getFunctArea()) {
                case "DO":
                case "DOS":
                case "EX":
                    esOpsCommand.add(position);
                    break;
            }
        }
        return esOpsCommand;
    }

}

