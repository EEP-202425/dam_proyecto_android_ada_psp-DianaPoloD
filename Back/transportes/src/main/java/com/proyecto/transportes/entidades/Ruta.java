package com.proyecto.transportes.entidades;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Ruta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private String destino;
	@Column
	private String origen;
	@Column
	private LocalDate horarioSalida;
	@Column
	private LocalDate horarioLlegada;
	
	@OneToMany(mappedBy = "ruta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Autobus> autobuses;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public LocalDate getHorarioSalida() {
		return horarioSalida;
	}

	public void setHorarioSalida(LocalDate horarioSalida) {
		this.horarioSalida = horarioSalida;
	}

	public LocalDate getHorarioLlegada() {
		return horarioLlegada;
	}

	public void setHorarioLlegada(LocalDate horarioLlegada) {
		this.horarioLlegada = horarioLlegada;
	}

}
