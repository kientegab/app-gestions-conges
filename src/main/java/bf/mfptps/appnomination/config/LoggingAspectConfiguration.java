package bf.mfptps.appnomination.config;

import bf.mfptps.appnomination.aop.LoggingAspect;
import bf.mfptps.appnomination.utils.AppProfile;
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
