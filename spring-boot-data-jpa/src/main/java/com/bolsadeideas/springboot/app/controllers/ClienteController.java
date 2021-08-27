package com.bolsadeideas.springboot.app.controllers;



import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.service.IClienteService;
import com.bolsadeideas.springboot.app.service.IUploadService;
import com.bolsadeideas.springboot.app.util.paginator.PageRender;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

	private final static String UPLOADS_FOLDER="uploads";
	
	@Autowired
	private IClienteService iClienteService;
	
	@Autowired 
	IUploadService uploadService;
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	@GetMapping(value="/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename){
		Resource recurso=null;
		try {
			recurso = uploadService.load(filename);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+recurso.getFilename()+"\"").body(recurso);
	}
	
	@GetMapping(value="/ver/{id}")
	public String ver(@PathVariable(value="id") Long id,Model model,RedirectAttributes flash) {
		
		Cliente cliente = iClienteService.findOne(id);
		if(cliente == null) {
			flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
			return "redirect:/listar";
		}
		
		model.addAttribute("cliente",cliente);
		model.addAttribute("titulo","Detalle cliente: "+ cliente.getNombre());
		
		return "ver";
	}
	
	
	@RequestMapping(value="/listar",method = RequestMethod.GET)
	public String listar(@RequestParam(name="page",defaultValue="0")int page,Model model) {
		
		Pageable pageRequest = PageRequest.of(page, 4);
		
		Page<Cliente> clientes = iClienteService.findAll(pageRequest);
		
		PageRender<Cliente> pageRender = new PageRender<>("/listar",clientes);
		
		model.addAttribute("titulo","Listado de clientes");
		model.addAttribute("clientes",clientes);
		model.addAttribute("page",pageRender);
		
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
			
			Cliente cliente = iClienteService.findOne(id);
			
			iClienteService.delete(id);
			flash.addFlashAttribute("success", "Cliente eliminado con éxito");
			
			if(uploadService.delete(cliente.getFoto())) {
				flash.addFlashAttribute("info", "Foto ".concat(cliente.getFoto()).concat(" eliminada con éxito!"));
				}
		}
		return "redirect:/listar";
		
	}
	
	@RequestMapping(value="/form",method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, 
						  BindingResult result, 
						  @RequestParam("file") MultipartFile foto,
						  Model model,
						  SessionStatus status,
						  RedirectAttributes flash) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Cliente");
			return "form";
		}
			
		if(!foto.isEmpty()) {
			
			if(cliente.getId() != null 
					&& cliente.getId()>0
					&& cliente.getFoto()!=null
					&& cliente.getFoto().length()>0) {
				
				uploadService.delete(cliente.getFoto());
			}
			
			
			String uniqueFileName=null;
			try {
				uniqueFileName = uploadService.copy(foto);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			flash.addFlashAttribute("info","Has subido correctamente '"+foto.getOriginalFilename()+"'");
				
			cliente.setFoto(uniqueFileName);
				
			
		}
		
		iClienteService.save(cliente);
		status.setComplete();
			
		String mensajeFlash = (cliente.getId()!= null?"Cliente editado con éxito":"Cliente creado con Éxito");
		flash.addFlashAttribute("success", mensajeFlash);
			
		return "redirect:listar";
		
	}
	
}
