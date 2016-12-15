<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	function display(){
		var sendFlag = '<%=(String)request.getSession().getAttribute("sendFlag") %>';
		'<%request.getSession().removeAttribute("sendFlag"); %>';
//		document.getElementById("dis").value = disString;
		if(sendFlag == "success"){
			alert("发送成功！！！");
		}else if(sendFlag == "error"){
			alert("发送失败！！！ 未选择目标地址或目标已下线");
		}
	}
	
	function fresh() {
//		document.mainForm.action = "/Web02/sendDataServlet?osid=refresh";
		mainForm.action = "/Web02/sendDataServlet?osid=refresh";
		mainForm.sumbit();
	}
</script>
</head>

<body onload="display()">

	<% 
	List<String> disnames = (List<String>)request.getSession().getAttribute("list");
	String dataLog = (String)request.getSession().getAttribute("dataLog");
	if(disnames == null){
		out.println("获取list失败");
	}else{
		out.println("获取list成功");
		out.println("list大小为："+disnames.size());
	 }
	 %>
	
	<form name="mainForm"  id="main" action="/Web02/sendDataServlet?osid=send" method="post">
	<table>
		<tr valign="top">
			<td>选择需要发送的终端地址：</td>
			<td><select name="Address" size=6  multiple="multiple">
						<option> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;请选择&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
				<%for(String name:disnames){%>
						<option><%=name %></option>
				 <%}%>
			</select></td>
		 </tr>
		  <tr valign="top">
						<td>发送信息:</td>
						<td><textarea rows=5 cols=30 name="data"></textarea></td>
		  </tr>
		<tr ><td align="center" colspan="2" ><input type="submit"  value="提交" >&nbsp;&nbsp; 
		<input type="submit"  value="刷新" id="refresh" onclick="fresh()"></td></tr>
	</table>
	</form>
	<div name="display">
		<tr><textarea name="dismessage" cols="60" rows="15" id="dis" readonly="true"><%=dataLog %></textarea></tr>
	</div>
	
	<form action="/Web02/sendDataServlet?osid=clear" method="post">
		<td align="center" colspan="2" ><input type="submit"  value="清空" ></td>
	</form>
	
	<%--  <table>
	 	<td>
	 	 <%for(String name:disnames){%>
				<input type="checkbox" value=<%=name %> name="Address"><%=name %><br/>
		 <%}%>
		</td>
	</table>--%>
			
	  
	
	
	
</body>
</html>