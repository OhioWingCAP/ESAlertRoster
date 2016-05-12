package gov.cap.ohwg.es.alertroster;

import gov.cap.ohwg.es.alertroster.data.capwatch.GenericLoader;
import gov.cap.ohwg.es.alertroster.model.entity.Contact;
import gov.cap.ohwg.es.alertroster.model.entity.DutyPosition;
import gov.cap.ohwg.es.alertroster.model.entity.Member;
import gov.cap.ohwg.es.alertroster.model.entity.Unit;
import gov.cap.ohwg.es.alertroster.model.repo.GenericRepo;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.legacy.context.web.MetricFilterAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.annotation.PostConstruct;

@SpringBootApplication(
        exclude = {
                JmxAutoConfiguration.class,
                MetricFilterAutoConfiguration.class,
                DataSourceAutoConfiguration.class
        }
)
@ComponentScan(basePackages = {"gov.cap.ohwg.es.alertroster"})
public class OhwgEsAlertRosterApplication {

    private GenericRepo<Unit> unitRepo = new GenericRepo<>(Unit.class);

    private GenericRepo<Member> memberRepo = new GenericRepo<>(Member.class);

    private GenericRepo<Contact> contactRepo = new GenericRepo<>(Contact.class);

    private GenericRepo<DutyPosition> dutyPositionRepo = new GenericRepo<>(DutyPosition.class);


    @Bean(name = "multipartResolver")
    public StandardServletMultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @PostConstruct
    public void onApplicationEvent() {
//        new GenericLoader<>(unitRepo, new String[]{"orgid", "region", "wing", "unit", "nextLevel", "name", "type",
//                "dateChartered", "status", "scope", "usrID", "dateMod", "firstUsr", "dateCreated", "dateReceived",
//                "orgNotes"}, "/data/Organization.txt", Unit.class).load();
//        new GenericLoader<>(memberRepo, new String[]{"CAPID", "SSN", "NameLast", "NameFirst", "NameMiddle",
//                "NameSuffix", "Gender", "DOB", "Profession", "EducationLevel", "Citizen", "ORGID", "Wing", "Unit", "Rank",
//                "Joined", "Expiration", "OrgJoined", "UsrID", "DateMod", "LSCode", "Type", "RankDate", "Region",
//                "MbrStatus", "PicStatus", "PicDate", "CdtWaiver"}, "/data/Member.txt", Member.class).load();
//        new GenericLoader<>(contactRepo, new String[]{"CAPID", "Type", "Priority", "Contact", "UsrID", "DateMod",
//                "DoNotContact"}, "/data/MbrContact.txt", Contact.class).load();
//"capid","Duty","FunctArea","Lvl","Asst","UsrID","DateMod","orgid"
        new GenericLoader<>(dutyPositionRepo, new String[]{"capid","Duty","FunctArea","Lvl","Asst","UsrID","DateMod",
                "orgid"}, "/data/DutyPosition.txt", DutyPosition.class).load();
    }

}
