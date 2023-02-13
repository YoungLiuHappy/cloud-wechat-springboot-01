package com.tencent.wxcloudrun.dto;

import java.io.Serializable;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName  = "xml")
@ToString
public class WxMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JacksonXmlProperty(localName  = "ToUserName")
    private String ToUserName;
    @JacksonXmlProperty(localName  = "FromUserName")
    private String FromUserName;
    @JacksonXmlProperty(localName  = "CreateTime")
    private long  CreateTime;
    @JacksonXmlProperty(localName  = "MsgType")
    private String MsgType;
    @JacksonXmlProperty(localName  = "Event")
    private String Event;
    @JacksonXmlProperty(localName  = "PicUrl")
    private String PicUrl;
    @JacksonXmlProperty(localName  = "MediaId")
    private String MediaId;
    @JacksonXmlProperty(localName  = "MsgId")
    private long  MsgId;
    @JacksonXmlProperty(localName  = "Content")
    private String Content;
    
    public static WxMessage xmlToBean(String xmlStr) {
    	WxMessage wm = null;
    	try {
    		wm = new XmlMapper().readValue(xmlStr, WxMessage.class);
		} catch (Exception e) {
		}
    	return wm;
    }
}
