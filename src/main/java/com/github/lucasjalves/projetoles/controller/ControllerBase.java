package com.github.lucasjalves.projetoles.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
class ControllerBase {
	
	@Autowired
	protected HttpSession httpSession;
	
}
