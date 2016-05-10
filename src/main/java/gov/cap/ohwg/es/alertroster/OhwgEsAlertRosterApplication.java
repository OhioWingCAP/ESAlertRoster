package gov.cap.ohwg.es.alertroster;

import gov.cap.ohwg.es.alertroster.data.capwatch.GenericLoader;
import gov.cap.ohwg.es.alertroster.model.entity.Member;
import gov.cap.ohwg.es.alertroster.model.entity.Unit;
import gov.cap.ohwg.es.alertroster.model.repo.GenericRepo;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.legacy.context.web.MetricFilterAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
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
        new GenericLoader<Unit>(unitRepo, new String[]{"orgid", "region", "wing", "unit", "nextLevel", "name", "type",
                "dateChartered", "status", "scope", "usrID", "dateMod", "firstUsr", "dateCreated", "dateReceived",
                "orgNotes"}, "/data/Organization.txt", Unit.class).load();
        new GenericLoader<Member>(memberRepo, new String[]{"CAPID", "SSN", "NameLast", "NameFirst", "NameMiddle",
                "NameSuffix", "Gender", "DOB", "Profession", "EducationLevel", "Citizen", "ORGID", "Wing", "Unit", "Rank",
                "Joined", "Expiration", "OrgJoined", "UsrID", "DateMod", "LSCode", "Type", "RankDate", "Region",
                "MbrStatus", "PicStatus", "PicDate", "CdtWaiver"}, "/data/Member.txt", Member.class).load();
    }

}
