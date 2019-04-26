package com.github.lucasjalves.projetoles.helper;

import com.github.lucasjalves.projetoles.entidade.Cliente;
import com.github.lucasjalves.projetoles.entidade.Pedido;
import com.github.lucasjalves.projetoles.util.CalculoUtil;

public final class ClienteHelper {

	private ClienteHelper() {
		
	}
	
	public static String atualizarCreditoUtilizado(Pedido pedido, Cliente cliente) {
		if(CalculoUtil.isValorZerado(pedido.getCreditoUtilizado())) {
			return "0,00";
		}
		
		Double creditoUtilizado = CalculoUtil.StringToDouble(pedido.getCreditoUtilizado());
		Double creditoCliente = CalculoUtil.StringToDouble(cliente.getCreditoDisponivel());

		creditoCliente = creditoCliente - creditoUtilizado;
	
	
		return String.format("%,.2f", creditoCliente);
	}
}
