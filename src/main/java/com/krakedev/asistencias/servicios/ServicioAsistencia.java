package com.krakedev.asistencias.servicios;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.krakedev.asistencias.entidades.Asistencia;
import com.krakedev.asistencias.entidades.Estudiante;
import com.krakedev.asistencias.entidades.RegistroAsistencia;

@Service
public class ServicioAsistencia {

	private ArrayList<RegistroAsistencia> registros = new ArrayList<>();

	private final ServicioEstudiantes servicioEstudiantes;

	// Inyeccion:
	public ServicioAsistencia(ServicioEstudiantes servicioEstudiantes) {
		this.servicioEstudiantes = servicioEstudiantes;
	}

	public RegistroAsistencia registrarAsistencia(String cedula) {
		Estudiante estudiante = servicioEstudiantes.buscarPorCedula(cedula);
		if (estudiante == null) {
			return null;
		}
		Asistencia asistencia = new Asistencia(LocalDate.now(), LocalDateTime.now(), "P");
		RegistroAsistencia registro = new RegistroAsistencia(estudiante, asistencia);
		registros.add(registro);
		return registro;
	}

	public ArrayList<Asistencia> consultarAsistencia(String cedula) {

		ArrayList<Asistencia> asistencias = new ArrayList<>();
		for (RegistroAsistencia r : registros) {
			if (r.getEstudiante().getCedula().equals(cedula)) {
				asistencias.add(r.getAsistencia());
			}
		}

		return asistencias;
	}
}