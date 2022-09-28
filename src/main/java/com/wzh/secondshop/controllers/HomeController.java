package com.wzh.secondshop.controllers;

import com.wzh.secondshop.models.User;
import com.wzh.secondshop.services.UserService;
import com.wzh.secondshop.utils.InfoCheck;
import com.wzh.secondshop.utils.RandomString;
import com.wzh.secondshop.utils.try1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class HomeController {
    private final UserService userService;
    private try1 t=new try1();

    
    
    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(ModelMap mode, HttpServletRequest request) {
        String preURL = request.getHeader("Referer");
        mode.addAttribute("preURL", preURL);
        return "home/login";
    }
    
    //这个可以向前端传一个值过去，变量fuck的值为fuck,前端也可以用${fuck}===>fuck取得fuck的值:fuck
    @RequestMapping(value = "/changepwd", method = RequestMethod.GET)
    public String changepwd(ModelMap mode, HttpServletRequest request) {
    	//System.out.print('\n'+"-------------change!----------------------"+'\n');
    	mode.addAttribute("fuck", "fuck");//传参 若重定向了则把这个参数一起带过去以字符串拼接的形式
        return "home/emailchangepwd";//定向到这个路径下的页面
    }
    
    @RequestMapping(value = "/code", method = RequestMethod.POST)
	public String sendcode(String receiveEmail,
			HttpSession session
			) throws Exception {
		System.out.print('\n'+"-------------邮箱----------------------"+receiveEmail+'\n');
    	
    	

		//1.生成验证码
		String code="";
		Random rd= new Random();
		
		while(code.length()<6){
			code +=rd.nextInt(10);
		}
		
		System.out.print('\n'+"验证码--------------------"+code+"--------------------------");//成功
		
		System.out.print('\n'+"收件人--------------------"+receiveEmail+"--------------------------");//?
		
		
		//2.发送邮件
		
		if(t.sendHtmlMailoo(receiveEmail,code)) {
			//如果邮件发送成功，则将验证码存到session中并启动一个定时器，3反正后清理session中的验证码
			session.removeAttribute("vCode");
			session.setAttribute("vCode", code);
			//创建一个定时任务
			TimerTask task =new TimerTask() {
			@Override
				public void run() {
					session.removeAttribute("vCode");
				}
			};
		
			//启动一个定时器，去执行上面的定时任务
			Timer timer =new Timer();
			timer.schedule(task, 180000);
			return "home/emailchangepwd";
		}
		
		
		
		//4.给客户端一个响应
		return "home/emailchangepwd";//失败回到主页
	}
    
    @RequestMapping(value = "/reg", method = RequestMethod.POST)
	public String checkcode(String code,HttpSession session) {
    	Object obj= session.getAttribute("vCode");
    	String icode = String.valueOf(obj);
		if(code.equals(icode)) {
			System.out.print("成功！！！！！");
			return "home/emailchangepwd1";
		}//后端穿过来的值等于前端输入，则成功
	   
		return "home/emailchangepwd";
	}

    
    @RequestMapping(value = "/changepwd1", method = RequestMethod.POST)
   	public String change(String pwd,HttpSession session) {
       System.out.print("!!!!!!!!!!!!!-----------------------"+pwd);
       Object obj= session.getAttribute("email");
   	   String email= String.valueOf(obj);
       userService.updatePasswordByemail(pwd,email);
       return "home/emailchangepwd1";
   	}

       
    
    
    
    
    
    @RequestMapping(value = "/changepwd", method = RequestMethod.POST)
    public String changepwdSubmit(ModelMap model,
    		@RequestParam(value = "cemail", required = false, defaultValue = "") String cemail,
            @RequestParam(value = "ccode", required = false) String ccode,
            HttpSession session
    		) {
    	session.removeAttribute("email");
    	session.setAttribute("email", cemail); 
    	
    	
    	Object objem= session.getAttribute("email");
    	String em = String.valueOf(objem);
    	Object obj= session.getAttribute("vCode");
    	String icode = String.valueOf(obj);
    	String msg="";
    	if(em.equals("")||icode.equals("")) {
    		msg="邮箱和验证码均不能为空！";
    		model.addAttribute("msg", msg);
    		return "home/emailchangepwd";
    	}else if(ccode.equals(icode)) {
    		model.addAttribute("msg", msg);//正确则跳到下个页面，有空或验证码不正确则跳回原来界面！
			return "home/emailchangepwd1";
		}else {
			msg="验证码不正确，请检查是否输入错误！";
			model.addAttribute("msg",msg);
			return "home/emailchangepwd";
		}
  
	}
    
 
    @RequestMapping(value = "/changepwd1", method = RequestMethod.GET)
    public String changepwd1(ModelMap mode, HttpServletRequest request) {
    	System.out.print('\n'+"-------------change1!----------------------"+'\n');
        return "home/emailchangepwd1";//定向到这个路径下的页面
    }
    
    
    

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginSubmit(ModelMap model,//下面的@RequestParam都是取login？preURL= &email= &password=的值 
    						  @RequestParam(value = "preURL", required = false, defaultValue = "") String preURL,
                              @RequestParam(value = "email", required = false) String email,
                              @RequestParam(value = "password", required = false) String password,
                              HttpSession session) {
    	
    	
    	System.out.print('\n'+"------------"+email+"--------------"+'\n');
    	System.out.print('\n'+"------------"+password+"--------------"+'\n');
    	System.out.print('\n'+"------------"+preURL+"--------------"+'\n');
    	
    	
    	
    	
        User user = userService.getUserByEmail(email);
        String message;
        if (user != null){//+ user.getCode()).getBytes()
            /*String mdsPass = DigestUtils.md5DigestAsHex(password.getBytes());*/
        	String mdsPass = password;
            if (!mdsPass .equals(user.getPassword())){
                message = "用户密码错误！";
            } else {
                if (user.getStatusId() == 4){
                    session.setAttribute("user",user);
                    if (preURL.equals("")){
                        return "redirect:/";
                    } else {
                        return "redirect:" + preURL;
                    }
                } else {
                    message = "用户已失效！";
                }
            }
        } else {
            message = "用户不存在！";
        }
        model.addAttribute("message", message);
        return "/home/login";//回到这个路径下
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(@RequestParam(required = false, defaultValue = "false" )String logout, HttpSession session){
        if (logout.equals("true")){
            session.removeAttribute("user");
        }
        return "redirect:/";
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        return "home/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerSuccess(ModelMap model,
                                  @Valid User user) {
        String status;
        Boolean insertSuccess;
        InfoCheck infoCheck = new InfoCheck();
        if (!infoCheck.isMobile(user.getMobile())){
            status = "请输入正确的手机号！";
        } else if (!infoCheck.isEmail(user.getEmail())){
            status = "请输入正确的邮箱！";
        } else if (userService.getUserByMobile(user.getMobile()) != null) {
            status = "此手机号码已使用！";
        } else if (userService.getUserByEmail(user.getEmail()) != null) {
            status = "此邮箱已使用！";
        } else if (user.getPassword2() == null){
            status = "请确认密码！";
        } else {
            RandomString randomString = new RandomString();
            user.setCode(randomString.getRandomString(5));
            String md5Pass = user.getPassword();//DigestUtils.md5DigestAsHex((user.getPassword() + user.getCode()).getBytes());
            user.setPassword(md5Pass);
            insertSuccess = userService.registerUser(user);
            if (insertSuccess){
                return "home/login";
            } else {
                status = "注册失败！";
                model.addAttribute("user", user);
                model.addAttribute("status", status);
                return "home/register";
            }
        }
        System.out.println(user.getMobile());
        System.out.println(status);
        model.addAttribute("user", user);
        model.addAttribute("status", status);
        return "home/register";
    }
}