package com.eventview.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.eventview.demo.dao.EvenRepo;
import com.eventview.demo.model.Evens;

@Controller
public class EController {

	@Autowired
	EvenRepo repo;

	@RequestMapping("/")
	public String home() {

		return "home.jsp";
	}

	@RequestMapping("/addEven")
	public String addEven(Evens eve) {
		repo.save(eve);
		return "home.jsp";
	}

	@RequestMapping("/getEven")
	public ModelAndView getEven(@RequestParam int eid) {
		ModelAndView mv = new ModelAndView("evenview.jsp");
		Evens eve = repo.findById(eid).orElse(new Evens());
		mv.addObject(eve);
		return mv;
	}
}
