package com.uade.ioo.g5.tpcuatrimestral.views;

public class ProveedorView {

	private int dni;

	private String nombre;

	/**
	 * @param dni
	 * @param nombre
	 */
	public ProveedorView(int dni, String nombre) {
		super();
		this.dni = dni;
		this.nombre = nombre;
	}

	public int getDni() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public String toString() {
		return dni + " - " + nombre;
	}
}
