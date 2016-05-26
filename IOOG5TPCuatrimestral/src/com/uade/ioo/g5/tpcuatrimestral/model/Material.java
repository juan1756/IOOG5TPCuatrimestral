package com.uade.ioo.g5.tpcuatrimestral.model;

import com.uade.ioo.g5.tpcuatrimestral.views.MaterialView;

/**
 * 
 */
public class Material {

	public Material(int cod, String n, String d, int s, int p, float c) {
		super();
		this.codigo = cod;
		this.nombre = n;
		this.stock = s;
		this.puntoDePedido = p;
		this.costo = c;
		this.descripcion = d;
	}

	/**
     * 
     */
	private int codigo;

	/**
     * 
     */
	private String nombre;

	/**
     * 
     */
	private int stock;

	/**
     * 
     */
	private int puntoDePedido;

	/**
     * 
     */
	private float costo;

	/**
	 * 
	 */
	private String descripcion;

	public int getCodigo() {
		return codigo;
	}

	public boolean isCodigo(int codigo) {
		return this.codigo == codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public int getPuntoDePedido() {
		return puntoDePedido;
	}

	public float getCosto() {
		return costo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public boolean isBajoPuntoDePedido() {
		return (stock <= puntoDePedido);
	}

	public MaterialView getView() {
		return new MaterialView(codigo, nombre, stock, costo, descripcion);
	}
}