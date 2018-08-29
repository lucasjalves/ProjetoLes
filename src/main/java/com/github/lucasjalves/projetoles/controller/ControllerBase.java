package com.github.lucasjalves.projetoles.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lucasjalves.projetoles.facade.Facade;

@Controller
public class ControllerBase {

	@Autowired
	protected ObjectMapper mapper;
	
	@Autowired
	protected HttpSession httpSession;
	
}
