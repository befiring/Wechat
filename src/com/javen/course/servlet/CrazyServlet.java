package com.javen.course.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javen.course.service.CoreService;
import com.javen.course.util.SignUtil;

/**
 * ������������ 
 * @author ��΢��
 * @Email zyw205@gmail.com
 * 
 */
public class CrazyServlet extends HttpServlet {

    private static final long serialVersionUID = -5021188348833856475L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        // ΢�ż���ǩ��  
        String signature = request.getParameter("signature");  
        // ʱ���  
        String timestamp = request.getParameter("timestamp");  
        // �����  
        String nonce = request.getParameter("nonce");  
        // ����ַ���  
        String echostr = request.getParameter("echostr");  
        PrintWriter out = response.getWriter();  
        // ͨ������signature���������У�飬��У��ɹ���ԭ������echostr����ʾ����ɹ����������ʧ��  
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {  
            out.print(echostr);  
        }  
        out.close();
        out = null;
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO ��Ϣ�Ľ��ա�������Ӧ  
    	request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8");  
  
        // ���ú���ҵ���������Ϣ��������Ϣ  
        String respMessage = CoreService.processRequest(request);  
          
        // ��Ӧ��Ϣ  
        PrintWriter out = response.getWriter();  
        out.print(respMessage);  
        out.close();  
    }
}