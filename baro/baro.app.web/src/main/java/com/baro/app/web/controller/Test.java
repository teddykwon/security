package com.baro.app.web.controller;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class Test {
	
	@RequestMapping(value="/test")
	@ResponseBody
	public String test(ModelMap map, HttpServletRequest request){
		return "test";
	}

}
