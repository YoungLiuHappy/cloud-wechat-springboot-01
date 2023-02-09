package com.tencent.wxcloudrun.controller;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
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
	public ApiResponse getMsg(@RequestBody JSONObject jsonObject) {
		log.info("=收到消息Get={}",jsonObject);
		return ApiResponse.ok();
	}
	@RequestMapping(method = RequestMethod.POST)
	public ApiResponse revMsg(@RequestBody JSONObject jsonObject) {
		log.info("=收到消息POST={}",jsonObject);
		return ApiResponse.ok();
	}
}
