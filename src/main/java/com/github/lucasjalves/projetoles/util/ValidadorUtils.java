package com.github.lucasjalves.projetoles.util;

import java.util.InputMismatchException;
import java.util.regex.Pattern;

public final class ValidadorUtils {
	public static final Pattern VALID_EMAIL_ADDRESS = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	private ValidadorUtils() {
		
	}
	
	public static boolean emailValido(String email) {
		if(email.trim().length() == 0) {
			return false;
		}else {
			return VALID_EMAIL_ADDRESS.matcher(email).matches();
		}
	}

	public static boolean cpfValido(String cpf) {
		cpf = cpf.replaceAll("\\D", "");
		System.out.println(cpf);
        if (cpf.equals("00000000000") ||
            cpf.equals("11111111111") ||
            cpf.equals("22222222222") || cpf.equals("33333333333") ||
            cpf.equals("44444444444") || cpf.equals("55555555555") ||
            cpf.equals("66666666666") || cpf.equals("77777777777") ||
            cpf.equals("88888888888") || cpf.equals("99999999999") ||
            (cpf.length() != 11))
            return(false);
          
        char dig10, dig11;
        int sm, i, r, num, peso;
          
        try {
  
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {              
       
            num = (int)(cpf.charAt(i) - 48); 
            sm = sm + (num * peso);
            peso = peso - 1;
            }
          
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48); 
          

            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
            num = (int)(cpf.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
            }
          
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                 dig11 = '0';
            else dig11 = (char)(r + 48);
          
            if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10)))
                 return(true);
            else return(false);
                } catch (InputMismatchException erro) {
                return(false);
            }
      }
	
	public static boolean senhaForte(String senha) {

		if(senha.length() <= 8) {
			return false;
		}
		return senha.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
	}
	
}

