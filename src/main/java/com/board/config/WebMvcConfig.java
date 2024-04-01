package com.board.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.board.interceptor.LoginCheckInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	//폴더 위치 중요 : 반드시 main( Board6Application)함수가 있는  클래스의
	//  (com.board)아래의 .config 을 생성해서 저장
	// com.board.config 
	// 각종 설정정보를 저장하는곳
	// 용도 : Spring legacy project 는 설정을 .xml 에서 
	// Springboot Project는 설정을 WebMvcConfig.java에 저장
	
	
	@Autowired
	 private  LoginCheckInterceptor   loginCheckInterceptor;
	   
	 @Override
	 public void addInterceptors(InterceptorRegistry registry) {

		 //1System.out.println("okokok");
		 registry.addInterceptor( loginCheckInterceptor ).addPathPatterns("/**")      // http://localhost:9090/
	     .addPathPatterns("/**/*")    
	     .excludePathPatterns("/log*","/css/**", "/img/**", "/js/**");
	        
	    }
}
