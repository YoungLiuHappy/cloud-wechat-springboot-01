package com.tencent.wxcloudrun.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tencent.wxcloudrun.config.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@RestController("/api/message")
@Slf4j
public class MessageController {
	
	@GetMapping
	public ApiResponse getMsg(String msg) {
		log.info("=收到消息Get={}",msg);
		return ApiResponse.ok();
	}
	@PostMapping
	public ApiResponse revMsg(String msg) {
		log.info("=收到消息POST={}",msg);
		return ApiResponse.ok();
	}
}
