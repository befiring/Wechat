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
 * ���ķ�����
 */
public class CoreService {
	/**
	 * ����΢�ŷ���������
	 * 
	 * @param request
	 * @return
	 */
	public static String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// Ĭ�Ϸ��ص��ı���Ϣ����
			String respContent = "�������쳣�����Ժ��ԣ�";

			// xml�������
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// ���ͷ��ʺţ�open_id��
			String fromUserName = requestMap.get("FromUserName");
			// �����ʺ�
			String toUserName = requestMap.get("ToUserName");
			// ��Ϣ����
			String msgType = requestMap.get("MsgType");
			// ��Ϣ����
			String mContent = requestMap.get("Content");
	

			// �ظ��ı���Ϣ
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);

			// �ı���Ϣ
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				if ("���".equals(mContent)) {
					respContent = mContent;
				} else if ("������".equals(mContent)) {
					respContent = "��ӭ����<a href=\"http://blog.csdn.net/lyq8479\">�ҵĲ���</a>!";
				} else if ("����".equals(mContent)) {
					respContent = "<a href=\"http://mp.weixin.qq.com/mp/redirect?url=http://interest.libaclub.com/facade.php?act=download\">�������</a>";
				} else if ("1".equals(mContent)) {
					respContent = "<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx9cb2e1d04727b99d&redirect_uri= http://javabefiring.sinaapp.com&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect\">��Ȩ</a>";
				} else if("2".equals(mContent)){
					respContent="<a href=\"http://61.143.61.20:8989/WechatPrint/WebRoot/PrinterList.jsp\">�ҵĴ�ӡ��</a>";
				}
				else {
					respContent = "�����͵����ı���Ϣ��";
				}
			}
			// ͼƬ��Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "�����͵���ͼƬ��Ϣ��";
			}
			// ����λ����Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "�����͵��ǵ���λ����Ϣ��";
			}
			// ������Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "�����͵���������Ϣ��";
			}
			// ��Ƶ��Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "�����͵�����Ƶ��Ϣ��";
			}
			// �¼�����
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// �¼�����
				String eventType = requestMap.get("Event");
				// ����
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "лл���Ĺ�ע��";
				}
				// ȡ������
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO ȡ�����ĺ��û����ղ������ںŷ��͵���Ϣ����˲���Ҫ�ظ���Ϣ
				}
				// �Զ���˵�����¼�
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// TODO �Զ���˵�Ȩû�п��ţ��ݲ����������Ϣ
					String eventKey = requestMap.get("EventKey");
					if (eventKey.equals("V1001_TODAY_MUSIC")) {
						respContent = "�����ո������������";
					}
					if (eventKey.equals("V1001_TODAY_SINGER")) {
						respContent = "�����ּ�鱻�������";
					}
					if (eventKey.equals("V1001_GOOD")) {
						respContent = "����һ�����Ǳ��������";
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
		// �������ɹ�
		if (null != jsonObject) {
			try {
			    accessToken=jsonObject.getString("access_token");
//				openId=jsonObject.getString("openid");
//				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
//				result="accessToken="+accessToken+"  openId="+openId;
			} catch (JSONException e) {
				accessToken ="failed";
				// ��ȡtokenʧ��
//				log.error("��ȡtokenʧ�� errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
		return accessToken;
	}
	*/
}
