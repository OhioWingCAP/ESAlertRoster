package gov.cap.ohwg.es.alertroster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"gov.cap.ohwg.es.alertroster"})
public class OhwgEsAlertRosterApplication {

	public static void main(String[] args) {
		SpringApplication.run(OhwgEsAlertRosterApplication.class, args);
	}
}
