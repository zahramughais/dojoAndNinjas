package com.zahra.dojoninja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zahra.dojoninja.models.Dojo;
import com.zahra.dojoninja.models.Ninja;
import com.zahra.dojoninja.services.DojoService;
import com.zahra.dojoninja.services.NinjaService;

@Controller
public class MainController {

	@Autowired
	DojoService dojoService;
	
	@Autowired
	NinjaService ninjaService;
	
	@RequestMapping("/dojos/{id}")
	public String showDojo(@PathVariable("id") Long id, Model model) {
		Dojo dojo = dojoService.findDojo(id);
		List<Ninja> ninjas = dojo.getNinjas();
		model.addAttribute("dojo", dojo);
		model.addAttribute("ninjas", ninjas);
		return "dojoshow.jsp";
	}
	
	@RequestMapping("/dojos/new")
	public String newDojo(@ModelAttribute("dojo") Dojo dojo) {
		return "newdojo.jsp";
	}
	
	@RequestMapping(value="/dojos/new", method=RequestMethod.POST)
	public String createDojo(@Valid @ModelAttribute("dojo") Dojo dojo, BindingResult result) {
		if(result.hasErrors()) {
			return "newDojo.jsp";
		} else {
			Dojo newDojo = dojoService.createDojo(dojo);
			Long id = newDojo.getId();
			return "redirect:/dojos/"+id;
		}
	}
	
	@RequestMapping("/ninjas/new")
	public String newNinja(@ModelAttribute("ninja") Ninja ninja, Model model) {
		List<Dojo> dojos = dojoService.allDojos();
		model.addAttribute("dojos", dojos);
		return "newninja.jsp";
	}
	
	@RequestMapping(value="/ninjas/new", method=RequestMethod.POST)
	public String createNinja(@Valid @ModelAttribute("ninja") Ninja ninja, BindingResult result) {
		if(result.hasErrors()) {
			return "newninja.jsp";
		} else {
			Ninja newNinja = ninjaService.createNinja(ninja);
			Long id = newNinja.getDojo().getId();
			return "redirect:/dojos/"+id;
		}
	}
}
