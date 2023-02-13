package com.tencent.wxcloudrun.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tencent.wxcloudrun.service.CommonService;

@Component
public class CommonJob {
	@Autowired
	private CommonService commonService;
	
	
//	@Scheduled(cron="0 0/20 * * * ? ",zone = "GMT+8")
//    public void holdWxAccessToken() {
//		commonService.holdWxAccessToken();
//	}
}
