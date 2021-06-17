package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ExameController {

	private final ExameRepository exameRepository;

	@Autowired
	public ExameController(ExameRepository exameRepository) {
		super();
		this.exameRepository = exameRepository;
	}

	@GetMapping("/index")
	public String mostrarExames(Model model) {
		model.addAttribute("exames", exameRepository.findAll());
		return ("index");
	}

	@GetMapping("/novoExame")
	public String mostrarFormularioCadastro(Exame exame) {
		// template com o formulario
		return "cadastrar-exame";
	}

	@PostMapping("/cadastarExame")
	public String cadastrarExame(Exame exame, BindingResult result, Model model) {

		exameRepository.save(exame);
		return "redirect:/index";
	}

	@GetMapping("/editar/{id}")
	public String mostrarFormularioAtualisacao(@PathVariable("id") long id, Model model) {
		Exame exame = exameRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		model.addAttribute("exame", exame);

		return "atualizar-exame";
	}

	@PostMapping("/atualisar/{id}")
	public String updateUser(@PathVariable("id") long id, Exame exame, BindingResult result, Model model) {

		exameRepository.save(exame);

		return "redirect:/index";
	}

	@GetMapping("/deletar/{id}")
	public String apagarExame(@PathVariable("id") long id, Model model) {
		Exame exame = exameRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		exameRepository.delete(exame);

		return "redirect:/index";
	}

}
