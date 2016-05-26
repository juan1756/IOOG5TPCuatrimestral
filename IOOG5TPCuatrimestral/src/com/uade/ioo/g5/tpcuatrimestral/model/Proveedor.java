package com.uade.ioo.g5.tpcuatrimestral.model;

import java.util.ArrayList;

import com.uade.ioo.g5.tpcuatrimestral.views.ProveedorView;

/**
 * 
 */
public class Proveedor {

	/**
     * 
     */
	private int dni;

	/**
     * 
     */
	private String nombre;

	/**
     * 
     */
	private ArrayList<Material> materiales;

	/**
	 * @param dni
	 * @param nombre
	 * @param materiales
	 */
	public Proveedor(int dni, String nombre, ArrayList<Material> materiales) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.materiales = materiales;
	}

	/**
	 * TODO
	 * 
	 * @return
	 */
	public ArrayList<Material> getMaterialesBajoPuntoPedido() {
		ArrayList<Material> mats = new ArrayList<Material>();
		for (Material material : materiales) {
			if (material.isBajoPuntoDePedido()) {
				mats.add(material);
			}
		}
		return mats;
	}

	public ArrayList<Material> getMateriales() {
		return materiales;
	}

	public boolean isDni(int dni) {
		return this.dni == dni;
	}

	public boolean agregarMaterial(Material m) {
		return materiales.add(m);
	}

	public ProveedorView getView() {
		return new ProveedorView(dni, nombre);
	}
}