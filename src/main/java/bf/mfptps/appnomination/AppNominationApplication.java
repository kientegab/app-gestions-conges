package bf.mfptps.appnomination;

import bf.mfptps.appnomination.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class, MailProperties.class})
public class AppNominationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppNominationApplication.class, args);
    }

}
