package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity

public class Exame {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	private String nome_paciente;
	private String nome_exame;
	private String resultado;
	private String data_exame;
	
	public Exame() {}
	
	public Exame(String nome_paciente, String nome_exame, String resultado, String data_exame) {
		this.nome_paciente = nome_paciente;
		this.nome_exame = nome_exame;
		this.resultado = resultado;
		this.data_exame = data_exame;
	}


	public String getData_exame() {
		return data_exame;
	}

	public void setData_exame(String data_exame) {
		this.data_exame = data_exame;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getNome_paciente() {
		return nome_paciente;
	}
	
	public void setNome_paciente(String nome_paciente) {
		this.nome_paciente = nome_paciente;
	}
	
	public String getNome_exame() {
		return nome_exame;
	}
	
	public void setNome_exame(String nome_exame) {
		this.nome_exame = nome_exame;
	}
	
	public String getResultado() {
		return resultado;
	}
	
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	
	
	@Override
	public String toString() {
		return "Exame {id=" + id + ", nome_paciente=" + nome_paciente + ", nome_exame=" + nome_exame + ", resultado="
				+ resultado + ", data_exame=" + data_exame + "}";
	}

	
}
