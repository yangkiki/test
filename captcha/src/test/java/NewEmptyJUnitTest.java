/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fenglianfinance.bill.cache.service.CacheService;
import com.fenglianfinance.bill.captcha.KaptchaDelegate;
import com.fenglianfinance.bill.model.ImgCaptchaResult;

/**
 *
 * @author hansy
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class NewEmptyJUnitTest {
    
    private static final Logger Log = LoggerFactory.getLogger(NewEmptyJUnitTest.class);
    
    private KaptchaDelegate delegate;
    
    public NewEmptyJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        delegate = new KaptchaDelegate(new CacheService() {
            Map<Object, Object> map = new HashMap<>();
            
            @Override
            public Object get(String key) {
                return map.get(key);
            }
            
            @Override
            public void set(Object key, Object value, int expire) {
                map.put(key, value);
            }
            
        });
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void hello() {
        ImgCaptchaResult result = this.delegate.generateCaptcha();
        
        if (Log.isDebugEnabled()) {
            Log.debug("result @" + result);
        }
    }
}
