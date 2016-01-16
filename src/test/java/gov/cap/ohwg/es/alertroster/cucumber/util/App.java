package gov.cap.ohwg.es.alertroster.cucumber.util;

/**
 * Created by ckovacs on 1/16/16.
 */
public class App {
    public String getUrlFor(String path) {
        return "http://localhost:8080/" + path;
    }
}
