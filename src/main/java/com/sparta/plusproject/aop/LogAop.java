package com.sparta.plusproject.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j(topic = "Log Aop")
public class LogAop {

	@Before("execution(* com.sparta.plusproject.controller..*(..))")
	public void logBefore(JoinPoint joinPoint) {
		// 현재 요청에 대한 ServletRequestAttributes를 가져옴
		ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		// Request URL과 HTTP Method를 가져와서 로그로 출력
		log.info("Request URL: {} - HTTP Method: {}", request.getRequestURL().toString(), request.getMethod());
	}
}