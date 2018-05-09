package com.navarro.xptosystems.dto;

import java.io.Serializable;

import com.navarro.xptosystems.domain.Cidade;
import com.navarro.xptosystems.domain.Estado;

public class CidadeDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer ibgeId;
	private String name;
	private Estado estado;
	private Boolean capital;
	private Double lon;
	private Double lat;
	private String noAccents;
	private String alternativeNames;
	private String microregion;
	private String mesoregion;

	public CidadeDTO() {
	}

	public CidadeDTO(Cidade obj) {
		id = obj.getId();
		ibgeId = obj.getIbgeId();
		name = obj.getName();
		estado = obj.getEstado();
		capital = obj.getCapital();
		lon = obj.getLon();
		lat = obj.getLat();
		noAccents = obj.getNoAccents();
		alternativeNames = obj.getAlternativeNames();
		microregion = obj.getMicroregion();
		mesoregion = obj.getMesoregion();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Integer getIbgeId() {
		return ibgeId;
	}

	public void setIbgeId(Integer ibgeId) {
		this.ibgeId = ibgeId;
	}

	public Boolean getCapital() {
		return capital;
	}

	public void setCapital(Boolean capital) {
		this.capital = capital;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public String getNoAccents() {
		return noAccents;
	}

	public void setNoAccents(String noAccents) {
		this.noAccents = noAccents;
	}

	public String getAlternativeNames() {
		return alternativeNames;
	}

	public void setAlternativeNames(String alternativeNames) {
		this.alternativeNames = alternativeNames;
	}

	public String getMicroregion() {
		return microregion;
	}

	public void setMicroregion(String microregion) {
		this.microregion = microregion;
	}

	public String getMesoregion() {
		return mesoregion;
	}

	public void setMesoregion(String mesoregion) {
		this.mesoregion = mesoregion;
	}

}
