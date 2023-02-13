package com.tencent.wxcloudrun.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.service.CommonService;
import com.tencent.wxcloudrun.utils.HttpClientResult;
import com.tencent.wxcloudrun.utils.HttpClientUtil;
import com.tencent.wxcloudrun.utils.LocalCache;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class CommonServiceImpl implements CommonService {

	@Override
	public void holdWxAccessToken() {
		try {
 			if(null!=LocalCache.WX_ACCESS_TOKEN && null != LocalCache.WX_ACCESS_TOKEN.get("expires_time")) {
				Long expiresTime = (Long)LocalCache.WX_ACCESS_TOKEN.get("expires_time");
				
				if(expiresTime - System.currentTimeMillis() > 1800000) {
					return;
				}
			}
			
			log.info("=获取微信access_token=start");
			String TEXT_COMPLETION_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx51876427b60a70ac&secret=6aadb3f53d7d78b05c726d55df50c6f0";
			HttpClientResult result = HttpClientUtil.doGet(TEXT_COMPLETION_URL, null);
			
			if(null==result || StringUtils.isBlank(result.getContent()) || !JSONObject.isValid(result.getContent())) {
				return ;
			}
			JSONObject accTokenJson =  JSONObject.parseObject(result.getContent());
			
			String accessToken = accTokenJson.getString("access_token");
			Integer expiresIn = accTokenJson.getInteger("expires_in");
			Long expiresTime = System.currentTimeMillis()+(expiresIn*1000);
			
			LocalCache.WX_ACCESS_TOKEN.put("access_token", accessToken);
			LocalCache.WX_ACCESS_TOKEN.put("expires_in", expiresIn);
			LocalCache.WX_ACCESS_TOKEN.put("expires_time", expiresTime);
			
			log.info("{},,,{}",LocalCache.WX_ACCESS_TOKEN,result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
    }

}
