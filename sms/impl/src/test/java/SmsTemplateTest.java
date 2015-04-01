import com.moxian.ng.notifier.sms.api.SmsConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.moxian.ng.notifier.sms.api.SmsOperations;

import com.moxian.ng.notifier.sms.impl.SmsTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ TestConfig.class })
public class SmsTemplateTest {

	SmsOperations tempalte = null;

	@Autowired
	SmsConfiguration config;

	@Before
	public void setup() {
		tempalte = new SmsTemplate(config);
	}

	@Test
	public void test() {
		try {
		//	tempalte.send("18520122224", "tes@@message【丰联金融】");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
