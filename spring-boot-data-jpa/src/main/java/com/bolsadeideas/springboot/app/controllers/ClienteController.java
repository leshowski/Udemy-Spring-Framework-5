package com.bolsadeideas.springboot.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.service.IClienteService;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	private IClienteService iClienteService;
	
	@RequestMapping(value="/listar",method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo","Listado de clientes");
		model.addAttribute("clientes",iClienteService.findAll());
		return "listar";
	}
	
	@RequestMapping("/form")
	public String crear(Map<String,Object> model) {
		
		Cliente cliente = new Cliente();
		
		model.put("titulo", "Formulario de Cliente");
		model.put("cliente", cliente);
		
		return "form";
		
	}
	
	@RequestMapping("/form/{id}")
	public String editar(@PathVariable(value = "id")Long id,Map<String,Object> model,RedirectAttributes flash) {
		
		Cliente cliente = new Cliente();
		
		if(id > 0) {
			cliente = iClienteService.findOne(id);
			if(cliente == null) {
				flash.addFlashAttribute("error", "El cliente no existe en la Base de Datos");
				return "redirect:/listar";
			}
				
		}else {
			flash.addFlashAttribute("error", "El ID de cliente no puede ser cero!");
			return "redirect:/listar";
		}
		
		model.put("titulo", "Editar cliente");
		model.put("cliente", cliente);
		
		return "form";
		
	}
	
	@RequestMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id")Long id,RedirectAttributes flash) {
		
		if(id > 0) {
			iClienteService.delete(id);
			flash.addFlashAttribute("success", "Cliente eliminado con éxito");
		}
		return "redirect:/listar";
		
	}
	
	@RequestMapping(value="/form",method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model,SessionStatus status,RedirectAttributes flash) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Cliente");
			return "form";
		}else {
			iClienteService.save(cliente);
			status.setComplete();
			
			String mensajeFlash = (cliente.getId()!= null?"Cliente editado con éxito":"Cliente creado con Éxito");
			flash.addFlashAttribute("success", mensajeFlash);
			
			return "redirect:listar";
		}
	}
	
}
