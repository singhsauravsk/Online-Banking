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

import com.zycus.dto.AccountDTO;
import com.zycus.entity.accounts.Account;
import com.zycus.services.Services;

@Controller
public class AccountController {

	@Autowired
	Services <Account, Long> service;
	
	@RequestMapping(value = "/account/add", method = RequestMethod.GET)
    public String addAccountGet(HttpServletRequest request) {
		request.getSession().invalidate();
    
        return "login";
    }
	
	@RequestMapping(value = "/account/add", method = RequestMethod.POST, consumes = "application/json", produces = "plain/text")
    public @ResponseBody String addAccountPost(@RequestBody AccountDTO accountDetails, HttpServletRequest request, Model madel) {
		System.out.println(request.getSession().getAttribute("adminName"));
		if(isSessionExpired(request)) {
			return "fail";
		}
		else {
			Account account = new Account();
			account.setHolderName(accountDetails.getHolderName());
			account.setAccountType(accountDetails.getAccountType());
			
			service.addNew(account);
			
			return "success";
		}
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
