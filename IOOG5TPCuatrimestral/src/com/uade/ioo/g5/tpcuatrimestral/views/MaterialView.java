package com.uade.ioo.g5.tpcuatrimestral.views;

public class MaterialView {

	private int codigo;

	private String nombre;

	private int stock;

	private float costo;

	private String descripcion;

	/**
	 * @param codigo
	 * @param nombre
	 * @param stock
	 * @param costo
	 * @param descripcion
	 */
	public MaterialView(int codigo, String nombre, int stock, float costo, String descripcion) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.stock = stock;
		this.costo = costo;
		this.descripcion = descripcion;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public int getStock() {
		return stock;
	}

	public float getCosto() {
		return costo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	@Override
	public String toString() {
		return this.nombre;
	}
}
