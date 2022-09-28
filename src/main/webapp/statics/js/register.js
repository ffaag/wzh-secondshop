function sendCode(){
	
	var email = $.trim($("#cemail").val());
	
	axios.post("code",qs.stringify({receiveEmail:email})).then(rt =>{
	})
	var time=180;
	var timerTask = setInterval(()=> {
		if(time>0){
			time --;
			$("#getCode").val(time+"S");	
		} else{
			$("#getCode").removeAttr("disabled").val("重新发送");
			$("#email").removeAtrr("readonly");
			clearInterval(timerTask);	
		}
		
	},1000);
}



/*function checkcode(){
	
	document.getElementById("passError").innerHTML = "";
	
	var password = document.getElementById("ccode").value;
	var passwordConfirm = document.getElementById("cemail").value;

	if ( password === "" || passwordConfirm === "") {
		document.getElementById("passError").innerHTML = "对不起，不可以为空！";
	} else {
		document.getElementById("passError").innerHTML = "";
		axios.post("changepwd1",qs.stringify({pwd:password})).then(rt =>{
			alert("修改密码成功！跳转到登录页面，您可以使用修改好的密码登录！");
			location.href="login";
			
		})
		
	}
	
	
	
	
	if(code!=""){
		axios.post("reg",qs.stringify({code:code})).then(rt =>{
		})
	}
	else{
		alert("请输入验证码！");
	}
	
}*/


function submitPass() {
	document.getElementById("passError").innerHTML = "";
	
	var password = document.getElementById("newPass1").value;
	var passwordConfirm = document.getElementById("newPass2").value;

	if ( password === "" || passwordConfirm === "") {
		document.getElementById("passError").innerHTML = "对不起，不可以为空！";
	} else if (password!== passwordConfirm) {
		document.getElementById("passError").innerHTML = "对不起，您2次输入的密码不一致";
	} else {
		document.getElementById("passError").innerHTML = "";
		axios.post("changepwd1",qs.stringify({pwd:password})).then(rt =>{
			alert("修改密码成功！跳转到登录页面，您可以使用修改好的密码登录！");
			location.href="login";
			
		})
		
	}
}




	

	