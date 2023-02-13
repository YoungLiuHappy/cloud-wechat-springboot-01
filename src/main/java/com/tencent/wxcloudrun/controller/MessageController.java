package com.tencent.wxcloudrun.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.dto.TextRequest;
import com.tencent.wxcloudrun.dto.TextResponse;
import com.tencent.wxcloudrun.dto.WxMessage;
import com.tencent.wxcloudrun.service.ChatGPTExampleService;
import com.tencent.wxcloudrun.utils.HttpClientResult;
import com.tencent.wxcloudrun.utils.HttpClientUtil;
import com.tencent.wxcloudrun.utils.LocalCache;
import com.tencent.wxcloudrun.utils.aes.AesException;
import com.tencent.wxcloudrun.utils.aes.SHA1;
import com.tencent.wxcloudrun.utils.aes.XMLParse;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api/message")
@Slf4j
public class MessageController {
	@Autowired
	private ChatGPTExampleService chatGPTExampleService;
	
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
	public String revMsg(String signature,String timestamp,String nonce,String echostr,String openid,@RequestBody String postData) {
//		CompletableFuture.runAsync(()-> sendMsg(postData));
//		return "success";
		Long st = System.currentTimeMillis();
		WxMessage wm = WxMessage.xmlToBean(postData);
		log.info("=收到消息POST={}",wm);
		String msgId = ""+wm.getMsgId();
		TextRequest tre = new TextRequest();
		tre.setMsgId(msgId);
		tre.setPrompt(wm.getContent());
		String result = null;
		
		do {
			if(LocalCache.WX_MESSAGE_ID_REPLY.containsKey(msgId)) {
				result = LocalCache.WX_MESSAGE_ID_REPLY.get(msgId);
			}else {
				TextResponse trp = chatGPTExampleService.createTextCompletion(tre);
				
				if(null!=trp && !CollectionUtils.isEmpty(trp.getChoices()) && trp.getChoices().stream().findFirst().isPresent() ) {
					result = XMLParse.generateOvertTextXml(wm.getFromUserName(), wm.getToUserName(), Long.toString(System.currentTimeMillis()), trp.getChoices().get(0).getText().trim());
				}
			}
			if(StringUtils.isNotBlank(result) || System.currentTimeMillis()-st >= 5000) {
				break;
			}
		} while (true);
		
		log.info("=返回消息POST=用时:{}={}",System.currentTimeMillis()-st,result);
		return result;
	}
	
	public void sendMsg(String postData) {
		
		WxMessage wm = WxMessage.xmlToBean(postData);
		
//		TextRequest tre = new TextRequest();
//		tre.setPrompt(wm.getContent());
//		
//		TextResponse trp = chatGPTExampleService.createTextCompletion(tre);
		String TEXT_COMPLETION_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+LocalCache.WX_ACCESS_TOKEN.get("access_token");
		
		Map<String,String> params = new HashMap<String, String>();
		params.put("touser", wm.getFromUserName());
		params.put("msgtype", "text");
		params.put("text",new HashMap<String, String>().put("content", "Hello word"));//trp.getChoices().get(0).getText().trim()
		
		try {
			HttpClientResult result=  HttpClientUtil.doPost(TEXT_COMPLETION_URL, params);
			log.info("=客服消息返回={}",result);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
