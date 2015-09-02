<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="java.io.DataInputStream"%>
<%@page import="java.io.FileOutputStream"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="javax.servlet.http.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>


	<body>
		<center>
			<%
				String fileName = "";
				String saveFile="";
				//定义上载文件的最大字节
				int MAX_SIZE = 102400 * 102400;
				//创建根路径的保存变量
				String rootPath = "";
				//声明文件读入类
				DataInputStream in = null;
				FileOutputStream fos = null;
				//取得客户端网络地址
				String remoteAddr = request.getRemoteAddr();
				//获得服务器的名字
				String serverName = request.getServerName();
				//取得互联网程序的绝对地址
				String realPath = request.getRealPath(serverName);
				realPath = realPath.substring(0, realPath.lastIndexOf("\\"));
				//创建文件的保存目录
				rootPath = rootPath + "\\upload\\";
				rootPath = "D:\\Program Files\\MyEclipse8.5\\WorkSpace\\WechatPrint\\WebRoot\\upload\\";
				//取得客户端上传的数据类型
				String contentType = request.getContentType();
				try {
					if (contentType.indexOf("multipart/form-data") >= 0) {
						//读入上传数据
						in = new DataInputStream(request.getInputStream());
						int formDataLength = request.getContentLength();
						if (formDataLength > MAX_SIZE) {
							out.println("<p>上传文件的字节数不可超过" + MAX_SIZE + "</p>");
							return;
						}
						//保存上传文件的数据	
						byte dataBytes[] = new byte[formDataLength];
						int byteRead = 0;
						int totalBytesRead = 0;
						//上传的数据保存在byte数组
						while (totalBytesRead < formDataLength) {
							byteRead = in.read(dataBytes, totalBytesRead,
									formDataLength);
							totalBytesRead += byteRead;
						}
						//根据byte数组创建字符串
						String file = new String(dataBytes);
						//取得上传的数据的文件名
						saveFile = file.substring(file
								.indexOf("filename=\"") + 10);
						saveFile = saveFile.substring(0, saveFile.indexOf("\n"));
						saveFile = saveFile.substring(
								saveFile.lastIndexOf("\\") + 1, saveFile
										.indexOf("\""));
						int lastIndex = contentType.lastIndexOf("=");
						//取得数据的分隔字符串
						String boundary = contentType.substring(lastIndex + 1,
								contentType.length());
						//创建保存路径的文件名
						fileName = rootPath + saveFile;
						int pos;
						pos = file.indexOf("filename=\"");
						pos = file.indexOf("\n", pos) + 1;
						pos = file.indexOf("\n", pos) + 1;
						pos = file.indexOf("\n", pos) + 1;
						int boundaryLocation = file.indexOf(boundary, pos) - 4;
						//取得文件数ju的开始位置
						int startPos = ((file.substring(0, pos)).getBytes()).length;
						//取得文件数据的结束位置
						int endPos = ((file.substring(0, boundaryLocation))
								.getBytes()).length;
						//检查上载文件是否存在
						File checkFile = new File(fileName);
						if (checkFile.exists()) {
							out.println("<p>" + saveFile + "文件已经存在.</p>");
						} else {
							//检查上载文件的目录是否存在
							File fileDir = new File(rootPath);
							if (!fileDir.exists()) {
								fileDir.mkdirs();
							}
							//创建文件的写出类
							FileOutputStream fileOut = new FileOutputStream(
									fileName);
							//保存文件的数据
							fileOut.write(dataBytes, startPos, (endPos - startPos));
							fileOut.close();
							out.println(saveFile + "文件成功上载.</p>");
						}
					} else {
						String content = request.getContentType();
						out.println("<p>上传的数据类型不是multipart/form-data</p>");
					}

				} catch (Exception e) {
					throw new ServletException(e.getMessage());
				}
			%>
			<img  src="upload/<%=saveFile%>" width="229" height="200">
			
	
		</center>
	</body>
</html>
