package com.sanzhong.score.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sanzhong.score.pojo.User;

@Controller
@RequestMapping("/topic")
public class TopicController {

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String helloWorld(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
		return "topic";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String test(HttpServletRequest request, HttpServletResponse response,User user) {
		System.out.println("Hello www.JavaBloger.com ");
		request.setAttribute("message", "Hello JavaBloger ! ,@RequestMapping(value='/add')");
		return "topic";
	}
}
