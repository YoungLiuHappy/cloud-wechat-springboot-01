package com.tencent.wxcloudrun.dto;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

@Data
public class CompletionUsage {
    @JSONField(name = "prompt_tokens")
    private int promptTokens;

    @JSONField(name = "completion_tokens")
    private int completionTokens;

    @JSONField(name = "total_tokens")
    private int totalTokens;
}
