package com.yh.controller;

import com.yh.service.AccountsService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@SuppressWarnings("all")
@Scope("prototype")
@Controller
@RequestMapping("Accounts")
public class AccountsController extends BaseController{
	@Resource
	private AccountsService accountsService;

}
