package gov.cap.ohwg.es.alertroster;

import gov.cap.ohwg.es.alertroster.model.entity.Unit;
import gov.cap.ohwg.es.alertroster.model.repo.GenericRepo;
import gov.cap.ohwg.es.alertroster.model.repo.UnitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.legacy.context.web.MetricFilterAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

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
        unitRepo.save(new Unit("58","Headquarters, Group IV","1"));
        unitRepo.save(new Unit("0","OHIO RESERVE SQUADRON","1"));
        unitRepo.save(new Unit("156","WARREN COUNTY CADET SQDN","44"));
        unitRepo.save(new Unit("288","PATHFINDER COMPOSITE SQUADRON","44"));
        unitRepo.save(new Unit("279","CLERMONT COUNTY COMPOSITE SQUADRON","44"));
        unitRepo.save(new Unit("197","DAYTON AERO CADET SQDN 706","43"));
        unitRepo.save(new Unit("1","OHIO WING HQ - CIVIL AIR PATROL","1"));
        unitRepo.save(new Unit("64","Headquarters, Group VI","1"));
        unitRepo.save(new Unit("131","CUYAHOGA COUNTY CADET SQUADRON","58"));
        unitRepo.save(new Unit("210","RICKENBACKER ANGB COMP SQUADRON","291"));
        unitRepo.save(new Unit("275","AKRON-CANTON SENIOR FLYING SQDN","254"));
        unitRepo.save(new Unit("277","TUSCO COMPOSITE SQUADON","254"));
        unitRepo.save(new Unit("115","CAPT EDDIE RICKENBACKER COMPOSITE SQDN","291"));
        unitRepo.save(new Unit("229","HARRISON CADET SQUADRON","44"));
        unitRepo.save(new Unit("285","DAYTON SENIOR SQDN","43"));
        unitRepo.save(new Unit("282","WRIGHT BROTHERS COMPOSITE SQDN","43"));
        unitRepo.save(new Unit("139","COLUMBUS COMPOSITE SQDN","291"));
        unitRepo.save(new Unit("236","LAKEFRONT T-BIRDS COMPOSITE SQDN","58"));
        unitRepo.save(new Unit("3","LORAIN COUNTY COMPOSITE SQDN","58"));
        unitRepo.save(new Unit("44","Headquarters, Group I","1"));
        unitRepo.save(new Unit("43","Headquarters, Group VII","1"));
        unitRepo.save(new Unit("85","COLUMBUS SENIOR SQDN","291"));
        unitRepo.save(new Unit("70","ROSS P. BARRETT CADET SQDN 702","43"));
        unitRepo.save(new Unit("278","AKRON CANTON COMPOSITE SQUADRON ","254"));
        unitRepo.save(new Unit("177","MANSFIELD 177TH SQUADRON","254"));
        unitRepo.save(new Unit("114","DON GENTILE COMPOSITE SQUADRON 709","43"));
        unitRepo.save(new Unit("37","WRIGHT-PATTERSON COMPOSITE SQUADRON","43"));
        unitRepo.save(new Unit("252","FRANK H. KETTLEWOOD COMPOSITE SQUADRON","58"));
        unitRepo.save(new Unit("254","Headquarters, Group III","1"));
        unitRepo.save(new Unit("261","DEFIANCE AREA FLIGHT","64"));
        unitRepo.save(new Unit("219","MEDINA COUNTY SKYHAWKS COMPOSITE SQDN","254"));
        unitRepo.save(new Unit("231","GRAND LAKE FLIGHT","64"));
        unitRepo.save(new Unit("51","YOUNGSTOWN ARS COMPOSITE SQUADRON","254"));
        unitRepo.save(new Unit("96","96TH COMPOSITE SQDN","254"));
        unitRepo.save(new Unit("16","TOLEDO ANGB COMPOSITE SQDN","64"));
        unitRepo.save(new Unit("78","LUNKEN CADET SQDN ","44"));
        unitRepo.save(new Unit("32","BLUE ASH CADET SQDN ","44"));
        unitRepo.save(new Unit("244","LT COL JAMES R. SANDERS SENIOR SQDN","44"));
        unitRepo.save(new Unit("243","ROSS COUNTY SENIOR SQUADRON ","291"));
        unitRepo.save(new Unit("291","HEADQUARTERS GROUP VIII","1"));
        unitRepo.save(new Unit("999","OHIO WING LEGISLATIVE SQDN","1"));
        unitRepo.save(new Unit("234","VICTOR A HAMMOND COMPOSITE SQDN 234","291"));
        unitRepo.save(new Unit("296","VAN WERT COMPOSITE SQUADRON","64"));
        unitRepo.save(new Unit("157","LICKING COUNTY COMPOSITE SQUADRON","291"));
        unitRepo.save(new Unit("284","MIAMI VALLEY COMPOSITE SQUADRON","43"));
        unitRepo.save(new Unit("298","HARDIN COUNTY FLIGHT","64"));
        unitRepo.save(new Unit("18","PERRYSBURG SENIOR FLIGHT","64"));
    }
}
