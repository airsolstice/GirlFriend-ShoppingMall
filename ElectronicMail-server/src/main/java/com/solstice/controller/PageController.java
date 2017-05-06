package com.solstice.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

	@RequestMapping("loginPage")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}

	@RequestMapping("registPage")
	public ModelAndView regist() {
		ModelAndView mav = new ModelAndView("regist");
		return mav;
	}

	@RequestMapping("forgetpwdPage")
	public ModelAndView forgetpwd() {
		ModelAndView mav = new ModelAndView("forgetpwd");
		return mav;
	}

	@RequestMapping("")


	public ModelAndView index(HttpServletResponse response,HttpServletRequest  request) throws IOException {
		ModelAndView mav = null;
		try {
			response.sendRedirect(request.getContextPath()+"/app/index.html");
		} catch (Exception e) {
			mav = new ModelAndView("index");
			e.printStackTrace();
		}
		return mav;
	}

	@RequestMapping("addGoods")
	public ModelAndView addGoods() {
		ModelAndView mav = new ModelAndView("addGoods");
		return mav;
	}

}
