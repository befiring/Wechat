<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>

<html>
	<title><%=application.getServerInfo()%></title>
	<script>
	function setImagePreview() {
		var docObj = document.getElementById("upfile");
		var imgObjPreview = document.getElementById("preview");
		if (docObj.files && docObj.files[0]) {
			//����£�ֱ����img���� 
			imgObjPreview.style.display = 'block';
			imgObjPreview.style.width = '300px';
			imgObjPreview.style.height = '120px';
			imgObjPreview.src = docObj.files[0].getAsDataURL();
			//���7���ϰ汾�����������getAsDataURL()��ʽ��ȡ����Ҫһ�·�ʽ 
			imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
		} else {
			//IE�£�ʹ���˾� 
			docObj.select();
			var imgSrc = document.selection.createRange().text;
			var localImagId = document.getElementById("localImag");
			//�������ó�ʼ��С 
			localImagId.style.width = "250px";
			localImagId.style.height = "200px";
			//ͼƬ�쳣�Ĳ�׽����ֹ�û��޸ĺ�׺��α��ͼƬ 
			try {
				localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
				localImagId.filters
						.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
			} catch (e) {
				alert("���ϴ���ͼƬ��ʽ����ȷ��������ѡ��!");
				return false;
			}
			imgObjPreview.style.display = 'none';
			document.selection.empty();
		}
		return true;
	}
</script>
	<body>
		<center>
			<form action="doUpload.jsp" method="post"
				enctype="multipart/form-data">
				��ѡ��Ҫ�ϴ����ļ�
				<input type="file" name="upfile" id="upfile" size="50"
					onchange="javascript:setImagePreview();">
				<input type="submit" value="�ύ">
				<div id="localImag">
					<img id="preview" width=-1 height=-1 style="diplay: none" />
				</div>
			</form>
		</center>
	</body>
</html>
