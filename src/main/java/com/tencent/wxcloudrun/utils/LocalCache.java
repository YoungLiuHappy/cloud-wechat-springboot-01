package com.tencent.wxcloudrun.utils;

import java.util.HashMap;
import java.util.Map;

public class LocalCache {
	
	//包含access_token String/expires_in Integer/expires_time Long
	public static Map<String, Object> WX_ACCESS_TOKEN = new HashMap<String, Object>();
	
	public static Map<String, String> WX_MESSAGE_ID_REPLY = new HashMap<String, String>();
}
