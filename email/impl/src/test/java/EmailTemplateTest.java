
import com.fenglianfinance.bill.notifier.email.api.EmailConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fenglianfinance.bill.notifier.email.api.EmailOperations;

import com.fenglianfinance.bill.notifier.email.impl.EmailTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class EmailTemplateTest {
    private static final Logger log=LoggerFactory.getLogger(EmailTemplateTest.class);

    EmailOperations tempalte = null;

    @Autowired
    EmailConfiguration config;

    @Before
    public void setup() {
        tempalte = new EmailTemplate(config);
    }

    @Test
    public void test() {
        
        log.debug("call test in EmailTemplateTest!");
        try {
            //tempalte.send("hantsy@tom.com", "test email subject", "test email content");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
