package com.zycus.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zycus.entity.users.User;
import com.zycus.services.Services;

@Controller
public class LoginController {

	@Autowired
	Services <User, String> service;
	
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage(HttpServletRequest request) {
        return "login";
    }

    @RequestMapping(value="/login", method = RequestMethod.POST, consumes = "application/json", produces = "plain/text")
    public @ResponseBody String showHomePage(@RequestBody User user, HttpServletRequest request, Model model) {
    	
    	if(service.validateUser(user, request) && !isSessionExpired(request)) {
    		return "success";
    	}
    	else {
    		return "Fail";
    	}
    }
    
    @RequestMapping(value="/home", method = RequestMethod.GET)
    public String showHomePage(HttpServletRequest request, Model model) {
    	model.addAttribute("adminName", request.getSession().getAttribute("adminName"));
    	
    	if(isSessionExpired(request)) {
    		return "loginPage";
    	}
    	else {
    		return "homePage";
    	}
    }
    
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, Model model) {
    	HttpSession session = request.getSession();
    	session.invalidate();
    	
        return "loginPage";
    }
    
    public boolean isSessionExpired(HttpServletRequest request) {
    	HttpSession session = request.getSession();
    	
    	if(session == null) {
    		return true;
    	}
    	else if(session.getAttribute("adminName") == null) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
}
