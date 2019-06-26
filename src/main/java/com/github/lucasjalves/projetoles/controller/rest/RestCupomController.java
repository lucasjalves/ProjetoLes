package com.github.lucasjalves.projetoles.controller.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.lucasjalves.projetoles.controller.ControllerBase;
import com.github.lucasjalves.projetoles.entidade.Cupom;
import com.github.lucasjalves.projetoles.rns.Resultado;

@RestController
@RequestMapping("/rest/cupom")
@CrossOrigin
public class RestCupomController extends ControllerBase{

	@GetMapping("/{codigo}")
	public Resultado getByCodigo(@PathVariable String codigo) {
		Cupom cupom = new Cupom();
		cupom.setCodigo(codigo);
	
		Resultado res = facade.consultar(cupom);
		
		if(res.getEntidades().isEmpty()) {
			return new Resultado("Cupom não encotrado");
		} else {
			cupom = (Cupom) res.getEntidades().get(0);
			cupom.setValidar(true);
			return facade.consultar(cupom);
		}
	}
}
