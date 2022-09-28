<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>邮箱找回</title>
</head>
<link rel="stylesheet"
	href="<c:url value="/statics/bootstrap-3.3.0/css/bootstrap.css"/>">
<link rel="stylesheet"
	href="<c:url value="/statics/jquery-ui-1.12.1/jquery-ui.css"/>">
<link rel="stylesheet"
	href="<c:url value="/statics/jquery-ui-1.12.1/jquery-ui.theme.css"/>">
<link rel="stylesheet" href="<c:url value="/statics/css/style.css"/>">
<body
	style="background-image: url(<c:url value="/statics/image/background/background1.jpg"/>); background-size: cover">
	<div class="container" style="margin-top: 180px;">
		<div class="row">
			<div style="margin: 20px auto; color: #ffffff">
				<h1 align="center" ; style="color: #292b33">邮箱验证找回密码</h1>
			</div>

			<div class="col-xs-5 r"
				style="position: absolute; left: 50%; transform: translateX(-50%); padding: 30px; background-color: #ffffff">
				<form id="app"  action="/wzh-secondshop/changepwd" method="post">
				<div class="form-group">
					 <label for="cemail">邮箱</label>
					 <input class="form-control" id="cemail" name="cemail" type="cemail"  />
				</div>
				<div class="form-group" align="center">
					 <label for="ccode" style="float:left" >验证码</label>
					 <input class="form-control" id="ccode" name="ccode"/>
					 <!-- 
					 <input type="button" id="getCode" onclick="sendCode()"  value="获取验证码"/>
					   <button id="getCode" onclick="sendCode()"  style="margin-left: 0px; width: 20%;float:left" type="submit" class="btn btn-primary">获取验证码</button>
					  -->
					 <input style="float:left"  type="button" id="getCode" onclick="sendCode()"  value="获取验证码"/>
				</div>
				
				<div align="center">	<output id="passError" style="color: red">${msg}</output></div>
				
				
				<div class="form-group" >
				
				<button style="float:left;margin-left: 10px; width: 30%; margin: 20px" type="submit"
					class="btn btn-primary" onclick="">下一步</button> <!-- checkcode() -->
				<button style="float:right;margin-left: 10px; width: 30%; margin: 20px" type="button"
					class="btn btn-primary" onclick="location.href='login'">返回首页</button>
				</div>
				</form>
			</div>
		</div>
	</div>
	</div>
	
		
	<script src="<c:url value="/statics/js/qs.js"/>"></script>
	<script src="<c:url value="/statics/js/vue.js"/>"></script>
	<script src="<c:url value="/statics/js/axios.js"/>"></script>
	
	
	<script src="<c:url value="/statics/js/register.js"/>"></script>
	<script src="<c:url value="/statics/jquery-1.12.4/jquery-1.12.4.js"/>"></script>
	<script src="<c:url value="/statics/bootstrap-3.3.0/js/bootstrap.js"/>"></script>
	<script src="<c:url value="/statics/jquery-ui-1.12.1/jquery-ui.js"/>"></script>
	<script
		src="<c:url value="/statics/jquery-ui-1.12.1/datepicker-zh-CN.js"/>"></script>
</body>
</html>