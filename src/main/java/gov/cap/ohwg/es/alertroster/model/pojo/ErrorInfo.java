package gov.cap.ohwg.es.alertroster.model.pojo;

/**
 * Created by ckovacs on 5/12/16.
 */
public class ErrorInfo {
    public final String url;
    public final String ex;

    public ErrorInfo(String url, Exception ex) {
        this.url = url;
        this.ex = ex.getLocalizedMessage();
    }

    public String getEx() {
        return ex;
    }

    public String getUrl() {
        return url;
    }
}
