package gov.cap.ohwg.es.alertroster.model.entity;

/**
 * Created by ckovacs on 4/23/16.
 */
public class CrewTeam {
    private CrewKind kind;
    private String charter;

    public String getCharter() {
        return charter;
    }

    public void setCharter(String charter) {
        this.charter = charter;
    }

    public CrewKind getKind() {
        return kind;
    }

    public void setKind(CrewKind kind) {
        this.kind = kind;
    }
}
