package com.uade.ioo.g5.tpcuatrimestral.model;

import com.uade.ioo.g5.tpcuatrimestral.views.ItemMaterialView;

/**
 * 
 */
public class ItemMaterial {

	/**
     * 
     */
	private Material material;

	/**
     * 
     */
	private int cantidad;

	/**
	 * 
	 * @param material
	 * @param cantidad
	 */
	public ItemMaterial(Material material, int cantidad) {
		super();
		this.material = material;
		this.cantidad = cantidad;
	}

	/**
	 * @return the material
	 */
	public boolean isMaterial(Material material) {
		return this.material == material;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getCosto() {
		return cantidad * material.getCosto();
	}

	public ItemMaterialView getView() {
		return new ItemMaterialView(cantidad, material.getView());
	}
}