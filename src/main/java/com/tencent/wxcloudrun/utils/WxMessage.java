package com.tencent.wxcloudrun.utils;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@JacksonXmlRootElement(localName  = "xml")
public class WxMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	@JacksonXmlProperty(localName  = "ToUserName")
    private String ToUserName;
//    @JacksonXmlProperty(localName  = "FromUserName")
    private String FromUserName;
//    @JacksonXmlProperty(localName  = "CreateTime")
    private long  CreateTime;
//    @JacksonXmlProperty(localName  = "MsgType")
    private String MsgType;
//    @JacksonXmlProperty(localName  = "Event")
    private String Event;
//    @JacksonXmlProperty(localName  = "PicUrl")
    private String PicUrl;
//    @JacksonXmlProperty(localName  = "MediaId")
    private String MediaId;
//    @JacksonXmlProperty(localName  = "MsgId")
    private long  MsgId;
//    @JacksonXmlProperty(localName  = "Content")
    private String Content;
}
