package com.ie.gestorColegio;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class GestorColegio extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GestorColegio() {

		this.setTitle("Gestion del colegio");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JTabbedPane pestanias = new JTabbedPane();

		pestanias.add("Alta Alumnos", new altaAlumnos());
		pestanias.add("Alta Profesor", new altaProfesor());
		this.add(pestanias);
	}

}
