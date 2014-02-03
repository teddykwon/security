package com.baro.app.web.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class ModelInterceptor implements HandlerInterceptor {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ModelInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		  log.debug("========= postHandle Interceptor ========");
		  Map<String, Object> model = modelAndView.getModel();
		  for(String name : model.keySet()){
			  log.debug("name : {}, value : {}", name, model.get(name));
		  }
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
