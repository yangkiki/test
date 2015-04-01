package com.fenglianfinance.bill.job.processor;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.data.redis.core.RedisTemplate;

import com.fenglianfinance.bill.domain.BackLogAmount;
import com.fenglianfinance.bill.domain.BackLogAmountTotal;
import com.fenglianfinance.bill.domain.BackLogItem;

public class ExpirationBillProcessor implements ItemProcessor<BackLogItem, BackLogItem> {

    private static final String BACKLOGTOTAL_SN_CACHE = "BL:SN:BACKLOGTOTAL";

    private static final Logger log = LoggerFactory.getLogger(ExpirationBillProcessor.class);

    private RedisTemplate<Object, Object> redisTemplate;

    public ExpirationBillProcessor(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public ExpirationBillProcessor() {}

    @Override
    public BackLogItem process(BackLogItem backLogItem) throws Exception {

        backLogItem.setValid(BackLogItem.Valid.EXPIRATION);
        
        BackLogAmountTotal backLogAmountTotalRedis= getAmountFromRedis();
        setAmountToRedis(backLogAmountTotalRedis, backLogItem);

        return backLogItem;
    }

    private BackLogAmountTotal getAmountFromRedis() {
        BackLogAmountTotal backLogAmountTotalRedis =
                (BackLogAmountTotal) redisTemplate.opsForValue().get(BACKLOGTOTAL_SN_CACHE);

        if (backLogAmountTotalRedis == null) {
            backLogAmountTotalRedis = new BackLogAmountTotal();
        }

        return backLogAmountTotalRedis;
    }

    private void setAmountToRedis(BackLogAmountTotal backLogAmountTotalRedis, BackLogItem backLogItem) {

        HashMap<String, BackLogAmount> backLogAmountMap = backLogAmountTotalRedis.getBackLogAmountMap();

        BackLogAmount currentBacklogAmount = backLogAmountMap.get(backLogItem.getType().toString());

        if (currentBacklogAmount == null) {
            currentBacklogAmount = new BackLogAmount();
        }

        currentBacklogAmount.setBillAmount(currentBacklogAmount.getBillAmount().subtract(
                backLogItem.getBill().getDenomination()));
        currentBacklogAmount.setType(backLogItem.getType());

        backLogAmountMap.put(backLogItem.getType().toString(), currentBacklogAmount);

        backLogAmountTotalRedis.setBackLogAmountMap(backLogAmountMap);

        redisTemplate.opsForValue().set(BACKLOGTOTAL_SN_CACHE, backLogAmountTotalRedis);
    }

}
