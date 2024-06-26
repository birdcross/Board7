package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.board.menus.domain.MenuVo;
import com.board.menus.mapper.MenuMapper;
import com.board.user.domain.UserVo;
import com.board.user.mapper.UserMapper;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private MenuMapper menuMapper;
	// http://localhost:9090
	@RequestMapping("/")
	public  String   home() {
		return "home";
	}
	// login
	@RequestMapping("/loginForm")
	public ModelAndView loginForm() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/users/login");
		return mv;
	}
	
	// login(userid=test&passwd=1234)
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request) {
		
		String userid = request.getParameter("userid");
		String passwd = request.getParameter("passwd");
		
		UserVo userVo = userMapper.login(userid, passwd);
		MenuVo menuVo = menuMapper.getMenu("menuid");
		String loc = "";
		if (userVo != null) { // 아이디와 비밀번호가 일치하면
		HttpSession session = request.getSession();
		session.setAttribute("login", userVo);
		session.setAttribute("menu", menuVo);
		session.setMaxInactiveInterval(30*60); // 30분동안 유지
		loc = "redirect:/";
		} else {
			loc = "redirect:/loginForm";
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName(loc);
		return mv;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/loginForm";
		
	}
	
}









