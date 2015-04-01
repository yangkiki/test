
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.moxian.ng.notifier.email.api.EmailConfiguration;

@Configuration
@PropertySource("classpath:emailconfig.properties")
public class TestConfig {

    @Autowired
    Environment env;

    @Bean
    public EmailConfiguration config() {

        EmailConfiguration config = new EmailConfiguration();
        config.setUsername(env.getProperty("mail.username"));
        config.setPassword(env.getProperty("mail.password"));
        config.setHost(env.getProperty("mail.host"));
        config.setPort(env.getProperty("mail.port", Integer.class));
        config.setFrom(env.getProperty("mail.from"));
        config.setDebug(env.getProperty("mail.debug", Boolean.class));

        return config;
    }

}
