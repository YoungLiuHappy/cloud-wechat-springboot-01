package com.tencent.wxcloudrun.service.impl;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

import com.alibaba.fastjson.JSONObject;

public class ChatGPTExample {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter your question: ");
        String question = reader.readLine();
        
        // Replace the API key with your own key
        String apiKey = "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";
        String prompt = question;
        String endpoint = "https://api.openai.com/v1/engines/davinci/jobs";

        URL url = new URL(endpoint);
//        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy_host", proxy_port));
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();//proxy
        con.setRequestMethod("POST");
        con.setRequestProperty("Authorization", "Bearer " + apiKey);
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);
        
        JSONObject json = new JSONObject();
        json.put("prompt", prompt);
        json.put("max_tokens", 2048);
        json.put("temperature", 2);
        json.put("top_p", 1);
        
        String jsonInputString = json.toJSONString();
        
        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);           
        }

        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println("Response: " + response.toString());
        } else {
            System.out.println("Response code: " + responseCode);
        }
    }
}
*/
import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.dto.TextRequest;
import com.tencent.wxcloudrun.dto.TextResponse;
import com.tencent.wxcloudrun.service.ChatGPTExampleService;
import com.tencent.wxcloudrun.utils.HttpClientUtil;
import com.tencent.wxcloudrun.utils.LocalCache;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhangjh
 * @date 3:21 PM 2022/12/15
 * @Description
 */
@Slf4j
@Service
public class ChatGPTExampleServiceImpl implements ChatGPTExampleService {
    @Value("${openai.apikey}")
    private String apiKey;

    private final Map<String, String> header = new HashMap<>();

    private static final String TEXT_COMPLETION_URL = "https://api.openai.com/v1/completions";
//    private static final String TEXT_COMPLETION_URL = "https://chat.openai.com/backend-api/moderations";
    

    private static final String IMAGE_GENERATE_URL = "https://api.openai.com/v1/images/generations";

    @PostConstruct
    public void init() {
        if(StringUtils.isEmpty(apiKey)) {
            apiKey = System.getenv("openai.apikey");
        }
        Assert.isTrue(StringUtils.isNotEmpty(apiKey), "openai apiKey not exist");
        header.put("Authorization", "Bearer " + apiKey);
    }

    @Override
    public TextResponse createTextCompletion(TextRequest textRequest) {
        TextResponse response = new TextResponse();
        try {
        	String msgId = textRequest.getMsgId();
        	if(LocalCache.WX_MESSAGE_ID_REPLY.containsKey(msgId)) {
        		return response;
        	}
        	LocalCache.WX_MESSAGE_ID_REPLY.put(msgId, null);
        	
            JSONObject jsonObject = HttpClientUtil.sendHttp(TEXT_COMPLETION_URL, JSONObject.toJSONString(textRequest), header);
            response = JSONObject.toJavaObject(jsonObject, TextResponse.class);
            if(null!=response && !CollectionUtils.isEmpty(response.getChoices()) && response.getChoices().stream().findFirst().isPresent() ) {
            	LocalCache.WX_MESSAGE_ID_REPLY.put(msgId, response.getChoices().stream().findFirst().get().getText());
            }
        } catch (Throwable t) {
            log.error("createCompletion failed, data: {}, t: ",
                    JSONObject.toJSONString(textRequest), t);
            throw new RuntimeException(t);
        }
        return response;
    }
   
}
