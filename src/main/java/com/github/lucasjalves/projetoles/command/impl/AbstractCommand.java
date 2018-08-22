package com.github.lucasjalves.projetoles.command.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.lucasjalves.projetoles.command.Command;
import com.github.lucasjalves.projetoles.facade.impl.FacadeImpl;

@Component
public abstract class AbstractCommand implements Command{

	@Autowired
	FacadeImpl facade;
}
