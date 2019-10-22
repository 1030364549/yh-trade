package com.yh.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SuppressWarnings("all")
@Scope("prototype")
@Controller
@RequestMapping("Index")
public class IndexController extends BaseController {

	/**
	 * ************************************
	 * index
	 * @param 
	 * @return String
	 * ************************************
	 */
	@RequestMapping("index")
	public String index(){
		
		return this.display();
	}
}
