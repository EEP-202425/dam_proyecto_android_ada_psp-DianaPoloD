package com.proyecto.transportes.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Autobus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private String tipo;
	@Column
	private int capacidad;
	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ruta_id")
	private Ruta ruta;
	
	@OneToMany(mappedBy = "autobus", cascade = CascadeType.ALL, fetch = FetchType.LAZY) //para poder asociar billetes
	@JsonIgnore
	private java.util.List<Billete> billetes;

	public java.util.List<Billete> getBilletes() {
	    return billetes;
	}

	public void setBilletes(java.util.List<Billete> billetes) {
	    this.billetes = billetes;
	}

	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	
	public Ruta getRuta() {
	    return ruta;
	}

	public void setRuta(Ruta ruta) {
	    this.ruta = ruta;
	}


}
