package com.bolsadeideas.springboot.web.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bolsadeideas.springboot.web.app.models.Usuario;

@Controller
@RequestMapping("/app")
public class IndexController {
	
	@Value("${texto.indexcontroller.index.titulo}")
	private String textoIndex;
	
	@Value("${texto.indexcontroller.perfil.titulo}")
	private String textoPerfil;
	

	@Value("${texto.indexcontroller.listar.titulo}")
	private String textoListar;
	
	
	@GetMapping({"/index","/","","/home"})
	public String index(Model model) {
	//public String index(ModelMap model) {
		model.addAttribute("titulo", textoIndex);
		return "index";
	}
	
	@RequestMapping("/perfil")
	public String perfil(Model model) {
		
		Usuario usuario = new Usuario();
		usuario.setNombre("Luis");
		usuario.setApellido("Ponce");
		usuario.setEmail("lponcecabello@gmail.com");
		
		model.addAttribute("titulo", textoPerfil + usuario.getNombre());
		model.addAttribute("usuario",usuario);
		
		return "perfil";
	}
	
	@RequestMapping("/listar")
	public String listar(Model model) {
		
		model.addAttribute("titulo", textoListar);
		
		return "listar";
	}
	
	@ModelAttribute("usuarios")
	public List<Usuario> listarUsuarios(){
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		usuarios.add(new Usuario("Luis","Ponce","lpc@gmail.com"));
		usuarios.add(new Usuario("Aysha","Zuñiga","azg@gmail.com"));
		usuarios.add(new Usuario("Trinidad","Ponce","tpz@gmail.com"));
		usuarios.add(new Usuario("Dario","Ponce","dpz@gmail.com"));
		
		return usuarios;
		
	}
	
	/*
	public String index(Map<String, Object> map) {
		map.put("titulo", "Hola Spring Framework!");
		return "index";
	}
	public ModelAndView index(ModelAndView mv) {
		mv.addObject("titulo", "Hola Spring Framework!");
		mv.setViewName("index");
		return mv;
	}*/

}
