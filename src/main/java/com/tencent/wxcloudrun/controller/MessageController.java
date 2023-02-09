package com.tencent.wxcloudrun.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tencent.wxcloudrun.config.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/message")
@Slf4j
public class MessageController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ApiResponse getMsg(String signature,String timestamp,String nonce,String echostr) {
		log.info("=收到消息Get={}={}={}={}",signature,timestamp,nonce,echostr);
		return ApiResponse.ok();
	}
	@RequestMapping(method = RequestMethod.POST)
	public ApiResponse revMsg(String signature,String timestamp,String nonce,String echostr) {
		log.info("=收到消息POST={}={}={}={}",signature,timestamp,nonce,echostr);
		return ApiResponse.ok();
	}
}
