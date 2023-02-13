package com.tencent.wxcloudrun.dto;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TextResponse {
    private String id;
    @JSONField(name = "object")
    private String object;
    @JSONField(name = "created")
    private Long created;
    @JSONField(name = "model")
    private String model;
    private String errorMsg;
    @JSONField(name = "choices")
    private List<TextRet> choices;
    
    private CompletionUsage usage;
}
