package com.bolsadeideas.springboot.error.app.controllers;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bolsadeideas.springboot.error.app.errors.UsuarioNoEncontradoException;

@ControllerAdvice
public class ErrorHandlerController {
	
	@ExceptionHandler({ArithmeticException.class})
	public String aritmeticaError(ArithmeticException e,Model model) {
		
		model.addAttribute("error","Error de aritmética");
		model.addAttribute("message",e.getMessage());
		model.addAttribute("status",HttpStatus.INTERNAL_SERVER_ERROR.value());
		model.addAttribute("timestamp",new Date());
		
		return "error/generica";
	}
	
	@ExceptionHandler({NumberFormatException.class})
	public String numeroFormatoError(NumberFormatException e,Model model) {
		
		model.addAttribute("error","Error: Formato de número inválido!");
		model.addAttribute("message",e.getMessage());
		model.addAttribute("status",HttpStatus.INTERNAL_SERVER_ERROR.value());
		model.addAttribute("timestamp",new Date());
		
		return "error/numero-formato";
	}
	
	@ExceptionHandler({UsuarioNoEncontradoException.class})
	public String usuarioNoEncontado(UsuarioNoEncontradoException e,Model model) {
		
		model.addAttribute("error","Error: usuario no encontrado!!");
		model.addAttribute("message",e.getMessage());
		model.addAttribute("status",HttpStatus.INTERNAL_SERVER_ERROR.value());
		model.addAttribute("timestamp",new Date());
		
		return "error/generica";
	}

}
