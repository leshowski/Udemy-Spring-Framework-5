package com.bolsadeideas.springboot.di.app.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsadeideas.springboot.di.app.editors.NombreMayusculaEditor;
import com.bolsadeideas.springboot.di.app.editors.PaisPropertyeditor;
import com.bolsadeideas.springboot.di.app.editors.RolesEditor;
import com.bolsadeideas.springboot.di.app.models.domain.Pais;
import com.bolsadeideas.springboot.di.app.models.domain.Role;
import com.bolsadeideas.springboot.di.app.models.domain.Usuario;
import com.bolsadeideas.springboot.di.app.services.PaisService;
import com.bolsadeideas.springboot.di.app.services.RoleService;
import com.bolsadeideas.springboot.di.app.validation.UsuarioValidador;

@Controller
@SessionAttributes("usuario")
public class FormController {
	
	@Autowired
	UsuarioValidador usuarioValidador;
	
	@Autowired
	PaisService paisService;
	
	@Autowired
	RoleService rolesService;
	
	@Autowired
	private PaisPropertyeditor paisEditor;
	
	@Autowired
	private RolesEditor rolesEditor;
	

	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		binder.addValidators(usuarioValidador);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		//binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		binder.registerCustomEditor(Date.class, "fechaNacimiento",new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(String.class,new NombreMayusculaEditor());
		binder.registerCustomEditor(Pais.class,"pais",paisEditor);
		binder.registerCustomEditor(Role.class,"roles",rolesEditor);
	}
	
	@ModelAttribute("genero")
	public List<String> genero(){
		return Arrays.asList("Hombre","Mujer");
	}
	
	@ModelAttribute("listaRoles")
	public List<Role> listaRoles(){
		return this.rolesService.listar();
	}
	
	@ModelAttribute("listaPaises")
	public List<Pais> listaPaises(){
		return paisService.listar();
	}
	
	@ModelAttribute("listaRolesString")
	public List<String> listaRolesString(){
		List<String> roles = new ArrayList<String>();
		roles.add("ROLE_ADMIN");
		roles.add("ROLE_USER");
		roles.add("ROLE_MODERATOR");
		
		return roles;
	}
	
	@ModelAttribute("listaRolesMap")
	public Map<String,String> listaRolesMap  (){
		
		Map<String,String> roles = new HashMap<String,String>();
		roles.put("ROLE_ADMIN", "Administrador");
		roles.put("ROLE_USER", "Usuario");
		roles.put("ROLE_MODERATOR", "Moderador");
		
		return roles;
	}
	
	@ModelAttribute("paises")
	public List<String> paises(){
		return Arrays.asList("Chile","Argentina","Peru","Brasil","Ecuador","Bolivia");
	}
	
	@ModelAttribute("paisesMap")
	public Map<String,String> paisesMap(){
		
		Map<String,String> paises = new HashMap<String,String>();
		paises.put("CL", "Chile");
		paises.put("AR", "Argentina");
		paises.put("PE", "Peru");
		paises.put("BR", "Brasil");
		paises.put("EC", "Ecuador");
		paises.put("BL", "Bolivia");
		
		return paises;
	}
	
	@GetMapping("/form")
	public String form(Model model) {
		
		Usuario usuario = new Usuario();
		usuario.setNombre("Luis");
		usuario.setApellido("Ponce");
		usuario.setIdentificador("12.456.789-K");
		usuario.setValorSecreto("asdhsadsahdsa");
		usuario.setPais(new Pais(1,"CL","Chile"));
		usuario.setRoles(Arrays.asList(new Role(2, "Usuario", "ROLE_USER"),new Role(3, "Moderador", "ROLE_MODERATOR")));
		usuario.setHabilitar(true);
		
		model.addAttribute("titulo","Formulario usuarios");
		model.addAttribute("usuario",usuario);
		return "form";
	}
	
	@PostMapping("/form")
	public String procesar(@Valid Usuario usuario,
						   BindingResult result,
						   Model model
						   /*@RequestParam(name="username")
						   String username,
						   @RequestParam(name="password")
						   String password, 
						   @RequestParam(name="email")
						   String email*/) {
	
		//usuarioValidador.validate(usuario, result);
		
		if(result.hasErrors()) {
			/*Map<String,String> errores = new HashMap<>();
			result.getFieldErrors().forEach(err->{
				errores.put(err.getField(), "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
			});
			
			model.addAttribute("error",errores);*/
			model.addAttribute("titulo","Resultado form");
			
			return "form";
		}
			
		/*
		Usuario usuario = new Usuario();
		usuario.setUsername(username);
		usuario.setPassword(password);
		usuario.setEmail(email);
		*/
		
		model.addAttribute("usuario",usuario);
		
		return "redirect:/ver";
	}
	
	@GetMapping("/ver")
	public String ver(@SessionAttribute(name="usuario",required=false )Usuario usuario,
					  Model model,
			   		  SessionStatus status) {
		
		if(usuario == null) {
			return "redirect:/form";
		}
		
		
		model.addAttribute("titulo","Resultado form");
		
		status.setComplete();
		
		return "resultado";
	}
	
}
