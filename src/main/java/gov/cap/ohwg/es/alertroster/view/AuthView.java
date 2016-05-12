package gov.cap.ohwg.es.alertroster.view;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.repackaged.com.google.api.client.http.HttpRequest;
import com.google.appengine.repackaged.com.google.api.client.http.HttpResponse;
import gov.cap.ohwg.es.alertroster.model.pojo.GaeUser;
import gov.cap.ohwg.es.alertroster.model.pojo.UrlHolder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Map;

/**
 * Created by ckovacs on 5/11/16.
 */
@RestController
@RequestMapping("/view/api/auth")
public class AuthView {


    private SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

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

    @RequestMapping("/logout")
    public ResponseEntity<UrlHolder> logout(HttpServletRequest request) {
        logoutHandler.logout(request, null, null);
        UserService userService = UserServiceFactory.getUserService();
        return ResponseEntity.ok(new UrlHolder(userService.createLogoutURL("http://www.ohwg.cap.gov")));
    }
    @RequestMapping(value = "/loginUrl")
    public ResponseEntity<UrlHolder> loginUrl(@RequestBody String continueUrl) {
        UserService userService = UserServiceFactory.getUserService();
        return ResponseEntity.ok(new UrlHolder(userService.createLoginURL(continueUrl)));
    }

    @RequestMapping(value = "/isLoggedIn")
    public ResponseEntity<Map<String, Boolean>> isLoggedIn() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(Collections.singletonMap("loggedIn", principal instanceof GaeUser));
    }
}
