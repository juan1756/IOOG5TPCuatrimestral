package com.uade.ioo.g5.tpcuatrimestral.model;

import com.uade.ioo.g5.tpcuatrimestral.views.ItemOrdenDeCompraView;

/**
 * 
 */
public class ItemOrdenDeCompra {

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
     */
	public ItemOrdenDeCompra(Material material, int cantidad) {
		super();
		this.material = material;
		this.cantidad = cantidad;
	}

	public ItemOrdenDeCompraView getView() {
		return new ItemOrdenDeCompraView(material.getView(), cantidad);
	}
}