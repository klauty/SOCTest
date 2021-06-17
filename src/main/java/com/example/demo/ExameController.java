package com.example.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

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

		// validando se o nome do paciente ja esta cadastrado no repositorio
		Iterable<Exame> lista = exameRepository.findAll();
		for (Exame item : lista) {
			if (exame.getNome_paciente().equals(item.getNome_paciente())) {
				System.out.println(item.getNome_paciente());
				System.out.println("paciente ja cadastrado");
				return "cadastrar-exame";
			}
		}

		// verificando se a data do exame é valida
		LocalDate date = LocalDate.now();
		System.out.println(date);
		System.out.println(exame.getData_exame());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		try {
			Date agora = sdf.parse(LocalDate.now().toString());
			Date date_exame = sdf.parse(exame.getData_exame());

			if (date_exame.compareTo(agora) < 0) {
				System.out.println("data invalida !");
				return "cadastrar-exame";
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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


		// verificando se a data do exame é valida
		LocalDate date = LocalDate.now();
		System.out.println(date);
		System.out.println(exame.getData_exame());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		try {
			Date agora = sdf.parse(LocalDate.now().toString());
			Date date_exame = sdf.parse(exame.getData_exame());

			if (date_exame.compareTo(agora) < 0) {
				System.out.println("data invalida !");
				return "atualizar-exame";
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
