import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.fenglianfinance.bill.notifier.sms.api.SmsConfiguration;


@Configuration
@PropertySource("classpath:smsconfig.properties")
public class TestConfig {

	@Autowired
	Environment env;

	@Bean
	public SmsConfiguration config() {

		SmsConfiguration config = new SmsConfiguration();
		config.setAccount(env.getProperty("account"));
		config.setPassword(env.getProperty("password"));
		config.setApiUrl(env.getProperty("apiurl"));

		return config;
	}

}