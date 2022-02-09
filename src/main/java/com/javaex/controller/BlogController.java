package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "{id}", method = { RequestMethod.GET, RequestMethod.POST })
public class BlogController {

//블로그-메인	
	@RequestMapping("")
	public String blogMain() {
		
		return "blog/blog-main";
	}

}
