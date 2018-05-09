package com.navarro.xptosystems.dto;

import java.io.Serializable;

import com.navarro.xptosystems.domain.Estado;

public class EstadoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String uf;
	private String name;
	private Integer qtdCidades;

	public EstadoDTO() {
	}

	public EstadoDTO(Estado obj) {
		id = obj.getId();
		uf = obj.getUf();
		name = obj.getName();
		qtdCidades = obj.getQtdCidades();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getQtdCidades() {
		return qtdCidades;
	}

	public void setQtdCidades(Integer qtdCidades) {
		this.qtdCidades = qtdCidades;
	}

}
