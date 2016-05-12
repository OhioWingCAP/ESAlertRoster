package gov.cap.ohwg.es.alertroster.view;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import gov.cap.ohwg.es.alertroster.model.pojo.GaeUser;
import gov.cap.ohwg.es.alertroster.model.pojo.UrlHolder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ckovacs on 5/11/16.
 */
@RestController
@RequestMapping("/view/api/auth")
public class AuthView {


    @RequestMapping("/current")
    public ResponseEntity<GaeUser> get() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        GaeUser user = null;
        if(principal instanceof GaeUser) {
            user = (GaeUser)principal;
        } else {
            user = new GaeUser(String.valueOf(principal), null, null);
        }
        return ResponseEntity.ok(user);
    }

    @RequestMapping(value = "/loginUrl")
    public ResponseEntity<UrlHolder> loginUrl(@RequestBody String continueUrl) {
        UserService userService = UserServiceFactory.getUserService();
        return ResponseEntity.ok(new UrlHolder(userService.createLoginURL(continueUrl)));
    }

    @RequestMapping(value = "/logoutUrl")
    public ResponseEntity<UrlHolder> logoutUrl(@RequestBody String continueUrl) {
        UserService userService = UserServiceFactory.getUserService();
        return ResponseEntity.ok(new UrlHolder(userService.createLogoutURL("/")));
    }

    @RequestMapping(value = "/isLoggedIn", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<Boolean> isLoggedIn() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(principal instanceof GaeUser);
    }
}
