package bf.mfptps.appgestionsconges.config;

import bf.mfptps.appgestionsconges.aop.LoggingAspect;
import bf.mfptps.appgestionsconges.utils.AppProfile;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

@Configuration
@EnableAspectJAutoProxy
public class LoggingAspectConfiguration {

    @Bean
    @Profile(AppProfile.DEVELOPMENT)
    public LoggingAspect loggingAspect(Environment env) {
        return new LoggingAspect(env);
    }
}
