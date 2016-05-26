package com.uade.ioo.g5.tpcuatrimestral.views;

import java.util.ArrayList;

public class PrendaView {

	private int codigo;

	private String nombre;

	private String descripcion;

	private int stock;

	private ArrayList<ItemMaterialView> materiales;

	private String estacion;

	/**
	 * @param codigo
	 * @param nombre
	 * @param descripcion
	 * @param stock
	 * @param materiales
	 * @param estacion
	 */
	public PrendaView(int codigo, String nombre, String descripcion, int stock, ArrayList<ItemMaterialView> materiales, String estacion) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.stock = stock;
		this.materiales = materiales;
		this.estacion = estacion;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public int getStock() {
		return stock;
	}

	public ArrayList<ItemMaterialView> getMateriales() {
		return materiales;
	}

	public String getEstacion() {
		if (estacion == null) {
			return "";
		}
		return estacion;
	}

	@Override
	public String toString() {
		return this.nombre;
	}

}
