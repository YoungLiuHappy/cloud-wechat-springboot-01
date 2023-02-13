package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.dto.TextRequest;
import com.tencent.wxcloudrun.dto.TextResponse;

public interface ChatGPTExampleService {
    /**
     * text completion
     * @param data
     * @return chatResponse
     */
    TextResponse createTextCompletion(TextRequest data);

}
