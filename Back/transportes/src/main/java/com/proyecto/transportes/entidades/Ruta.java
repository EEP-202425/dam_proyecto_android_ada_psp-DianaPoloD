package com.proyecto.transportes.entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;

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
	private LocalTime horarioSalida;
	@Column
	private LocalTime horarioLlegada;
	@Column
	private LocalDate fechaViaje;


	@JsonGetter("fechaViaje")//para que pueda funcionar el local 
	public LocalDate getFechaViaje() {
	    return LocalDate.now(); // <-- simula que siempre es hoy
	}


	public void setFechaViaje(LocalDate fechaViaje) {
		this.fechaViaje = fechaViaje;
	}

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

	public LocalTime getHorarioSalida() {
		return horarioSalida;
	}

	public void setHorarioSalida(LocalTime horarioSalida) {
		this.horarioSalida = horarioSalida;
	}

	public LocalTime getHorarioLlegada() {
		return horarioLlegada;
	}

	public void setHorarioLlegada(LocalTime horarioLlegada) {
		this.horarioLlegada = horarioLlegada;
	}

}
