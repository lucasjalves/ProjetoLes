package com.github.lucasjalves.projetoles.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
class ControllerBase {
	
	
	@Autowired
	protected HttpSession httpSession;
	
	@Autowired
	protected ObjectMapper mapper;
}
