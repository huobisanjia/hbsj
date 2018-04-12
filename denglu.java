package com.sh.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import com.sh.entity.User;
import com.sh.dao.user.UserDaoImpl;

@Controller
@RequestMapping("/loginuser")
public class denglu {
	@Resource
	private UserDaoImpl userDaoImpl;

	@RequestMapping("/login")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model,HttpServletRequest request,
			HttpSession session) {
		//User u = this.userDaoImpl.findByQqNumAndPassword(username);此处的username是ID
		//session.setAttribute("username", username);
		
		User u = this.userDaoImpl.findByemail(email);
		
		if (u != null && password.equals(u.getPassword())) {
			int username=u.getQqnum();
			String nickname=u.getNickName();
			String introduce=u.getIntroduce();
			session.setAttribute("username", username);
			session.setAttribute("nickname", nickname);
			session.setAttribute("introduce", introduce);
			int statusnum=u.getEmailstatus();
			
			
			request.setAttribute("statusnum",statusnum);
			return "redirect:/loginuser/ifjihuoeamillogin?statusnum="+statusnum;
			
		} else {
			model.addAttribute("errorinfo", "您的账号密码不正确！");
			return "login";
		}

	}
	@RequestMapping("/ifjihuoeamillogin")
	public String ifjihuoeamillogin(Model model,@RequestParam("statusnum") String statusnum) {
		int statusnumemail=Integer.parseInt(statusnum.toString());
		System.out.println(statusnumemail);
	
		if ( statusnumemail==1) {
			return "userzhuye";
		} else {
			model.addAttribute("errorjihuo", "邮箱尚未激活");
			return "login";
		}
		
	}
	@RequestMapping("/emailonlyone")
	public void emailonlyone(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String regix = "[a-zA-Z_0-9]+@[a-zA-Z_0-9]{2,6}(\\.[a-zA-Z_0-9]{2,3})+";
		String email =  request.getParameter("email");
	
		System.out.println(email);
		int num = this.userDaoImpl.findcountByemail(email);
		System.out.println(num);
		  
			if(num!=0){ //此邮箱已经被注册
				   
		        try {
					response.getWriter().print(false);
					System.out.println("此邮箱已经被注册");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{  //此邮箱未被注册但格式不正确
				if(!email.matches(regix)){
					try {
						response.getWriter().print(false);
						System.out.println("此邮箱未被注册但格式不正确");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{   //此邮箱未被注册且格式正确
					try {
						response.getWriter().print(true);
						System.out.println("此邮箱未被注册且格式正确");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
	}
	@RequestMapping("/isthepassword")
	public void isthepassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String password =  request.getParameter("password");
		String configpassword =  request.getParameter("configpassword");
		System.out.println("password: "+password);
		System.out.println("configpassword: "+configpassword);
		if(configpassword==null||configpassword==""){ //未填
			   
	        try {
				response.getWriter().print(false);
				System.out.println("确认密码未填");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{  
			if(!password.equals(configpassword)){
				try {
					response.getWriter().print(false);
					System.out.println("确认密码填写了但不一致");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{  
				try {
					response.getWriter().print(true);
					System.out.println("确定密码与原密码一致");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
    }
	@RequestMapping("/yanzhenname")
	public void yanzhenname(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		if(username==null||username==""){
			try {
				response.getWriter().print(false);
				System.out.println("用户名不能为空");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{  
			try {
				response.getWriter().print(true);
				System.out.println("用户名填写正确");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@RequestMapping("/yanzhengmima")
	public void yanzhengmima(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//String regix = "^[a-zA-Z]w{5,17}$";
		String password =  request.getParameter("password");
		
		System.out.println("密码"+password);
		
		  
			if(password==null||password==""){ 
				   
		        try {
					response.getWriter().print(false);
					System.out.println("此密码为空");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{  
				if(password.length()<6){
					try {
						response.getWriter().print(false);
						System.out.println("此密码不为空但格式不正确");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{  
					try {
						response.getWriter().print(true);
						System.out.println("此密码格式正确");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
	}
  
}