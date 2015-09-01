package com.javen.course.service;

import java.awt.image.RescaleOp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.javen.course.menu.AccessToken;
import com.javen.course.message.resp.Article;
import com.javen.course.message.resp.NewsMessage;
import com.javen.course.message.resp.TextMessage;
import com.javen.course.util.MenuUtil;
import com.javen.course.util.MessageUtil;
import com.javen.course.util.SaveLog;

/**
 * 核心服务类
 */
public class CoreService {
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	public static String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";

			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			// 消息内容
			String mContent = requestMap.get("Content");
	

			// 回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				if ("你好".equals(mContent)) {
					respContent = mContent;
				} else if ("超链接".equals(mContent)) {
					respContent = "欢迎访问<a href=\"http://blog.csdn.net/lyq8479\">我的博客</a>!";
				} else if ("下载".equals(mContent)) {
					respContent = "<a href=\"http://mp.weixin.qq.com/mp/redirect?url=http://interest.libaclub.com/facade.php?act=download\">点击下载</a>";
				} else if ("1".equals(mContent)) {
					respContent = "<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx9cb2e1d04727b99d&redirect_uri= http://javabefiring.sinaapp.com&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect\">授权</a>";
				} else if("2".equals(mContent)){
					respContent="<a href=\"http://61.143.61.20:8989/WechatPrint/WebRoot/PrinterList.jsp\">我的打印机</a>";
				}
				else {
					respContent = "您发送的是文本消息！";
				}
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息！";
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
			}
			// 音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是音频消息！";
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "谢谢您的关注！";
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// TODO 自定义菜单权没有开放，暂不处理该类消息
					String eventKey = requestMap.get("EventKey");
					if (eventKey.equals("V1001_TODAY_MUSIC")) {
						respContent = "“今日歌曲被点击！”";
					}
					if (eventKey.equals("V1001_TODAY_SINGER")) {
						respContent = "“歌手简介被点击！”";
					}
					if (eventKey.equals("V1001_GOOD")) {
						respContent = "“赞一下我们被点击！”";
					}
				}
			}

			textMessage.setContent(respContent);
			respMessage = MessageUtil.textMessageToXml(textMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respMessage;
	}
	/*
	public static String getAccessToken() {
		String accessToken = null;

//		String requestUrl ="<a href=\"https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx9cb2e1d04727b99d&secret=ea7120a226a4fd59ebb1e0e64a646845&code="+code+"&grant_type=authorization_code";
		String requestUrl ="";
		JSONObject jsonObject = MenuUtil.httpRequest(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
			    accessToken=jsonObject.getString("access_token");
//				openId=jsonObject.getString("openid");
//				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
//				result="accessToken="+accessToken+"  openId="+openId;
			} catch (JSONException e) {
				accessToken ="failed";
				// 获取token失败
//				log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
		return accessToken;
	}
	*/
}
