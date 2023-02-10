package com.tencent.wxcloudrun.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tencent.wxcloudrun.utils.aes.AesException;
import com.tencent.wxcloudrun.utils.aes.SHA1;
import com.tencent.wxcloudrun.utils.aes.WXBizMsgCrypt;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api/message")
@Slf4j
public class MessageController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String getMsg(String signature,String timestamp,String nonce,String echostr) {
		log.info("=收到消息Get={}={}={}={}",signature,timestamp,nonce,echostr);
		try {
			String data = SHA1.getSHA1("6aadb3f53d7d78b05c726d55df50c6f0", timestamp, nonce, "");
			log.info("=收到消息Get1={}",data);
		} catch (AesException e) {
			e.printStackTrace();
		}
		
		return echostr;
	}
	@RequestMapping(method = RequestMethod.POST)
	public Object revMsg(String signature,String timestamp,String nonce,String echostr,String openid,@RequestBody String postData) {
		Long st = System.currentTimeMillis();
		log.info("=收到消息POST={}={}={}={}={}",signature,timestamp,nonce,echostr,postData);
		String result = String.format("<xml><ToUserName><![CDATA[oNMDRjjVlGp4UNVTOGDUhbwbWciM]]></ToUserName><FromUserName><![CDATA[gh_7adaa0104ee1]]></FromUserName><CreateTime>%s</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[你好，这是一条自动回复]]></Content></xml>",Long.toString(System.currentTimeMillis()));
		/*
		try {
			WXBizMsgCrypt wxbmc = new WXBizMsgCrypt("6aadb3f53d7d78b05c726d55df50c6f0", "I5lQU2SfxxbObowGiQXsVdQ637l5eXqZQ4mx3qyS5CN", "wx51876427b60a70ac");
			
			result = wxbmc.encryptMsg(String.format("<xml><ToUserName><![CDATA[oNMDRjjVlGp4UNVTOGDUhbwbWciM]]></ToUserName><FromUserName><![CDATA[gh_7adaa0104ee1]]></FromUserName><CreateTime>$s</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[你好，这是一条自动回复]]></Content></xml>",Long.toString(System.currentTimeMillis())), Long.toString(System.currentTimeMillis()), nonce);
		} catch (AesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		log.info("=返回消息POST=用时:{}={}",System.currentTimeMillis()-st,result);
		return result;
	}
}
