package com.sanzhong.score.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/dock")
public class DockController {

	@RequestMapping(value = "/docks",method=RequestMethod.POST)
	public @ResponseBody Map<String,String> dock(HttpServletRequest request, HttpServletResponse response) {
		
		return null;
	}
}
