package com.tencent.wxcloudrun.dto;

import lombok.Data;

@Data
public class TextRet {
    private String text;

    private int index;

    private String finishReason;
}
