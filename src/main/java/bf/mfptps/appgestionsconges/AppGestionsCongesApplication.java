package bf.mfptps.appgestionsconges;

import bf.mfptps.appgestionsconges.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class, MailProperties.class})
public class AppGestionsCongesApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppGestionsCongesApplication.class, args);
    }

}
