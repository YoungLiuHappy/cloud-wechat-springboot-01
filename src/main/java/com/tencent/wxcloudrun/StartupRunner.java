package com.tencent.wxcloudrun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.tencent.wxcloudrun.service.CommonService;


@Component
public class StartupRunner implements ApplicationRunner{
	@Autowired
	private CommonService commonService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
//		commonService.holdWxAccessToken();
	}

}
