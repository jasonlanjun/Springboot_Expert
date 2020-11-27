package com.junlan.service.prehandle;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SplitService {

    private static final Logger logger = LoggerFactory.getLogger(SplitService.class);

    public void saveAndSplitLog(String message) {
        JSONObject jsonObject = JSONObject.parseObject(message);
        String content = jsonObject.getString("message");
    }

}
