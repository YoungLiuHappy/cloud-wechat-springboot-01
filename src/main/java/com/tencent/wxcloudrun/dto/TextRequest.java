package com.tencent.wxcloudrun.dto;


import java.beans.Transient;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;
import lombok.NonNull;

@Data
public class TextRequest {

    /** ID of the model to use */
    @NonNull
    private String model = "text-davinci-003";
//    private String model = "text-davinci-002-render";
    

    /** the propmts to generate completions for */
    private String prompt;

    /** Control the Creativity, it can be 0~1.
     * It’s not recommended to use the temperature with the Top_p parameter
     * */
    private double temperature=1;

    /** the suffix that comes after a completion of inserted text */
    private String suffix;

    /** the maximum num of tokens to generate in the completion */
    @JSONField(name = "max_tokens")
    private int maxTokens = 2048;

    @JSONField(name = "top_p")
    private int topP;

    /**
     * how many completions to generate for each prompt
     * default to 1
     * */
    private int n = 1;

    @JSONField(name = "frequency_penalty")
    private double frequencyPenalty;

    @JSONField(name = "presence_penalty")

    private double presencePenalty;

    @JSONField(name = "best_of")
    private int bestOf = 1;

    /**
     * up to 4 sequences where the API stop generating more text.
     * */
//    private String stop;
    
    private transient String msgId ;
}
