/*package com.eventview.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eventview.demo.service.EventService;

@Controller
@RequestMapping("users")
public class EvenViewController {

	@Autowired
	private EventService eventService;

	@RequestMapping(method = RequestMethod.GET)
	public String home(ModelMap modelMap) {
		modelMap.put("users", eventService.listAll());
		return "product/index";
	}

}
*/
