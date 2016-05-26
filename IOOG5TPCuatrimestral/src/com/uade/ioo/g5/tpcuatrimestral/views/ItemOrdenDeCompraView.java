package com.uade.ioo.g5.tpcuatrimestral.views;

public class ItemOrdenDeCompraView {

	private MaterialView material;

	private int cantidad;

	/**
	 * @param material
	 * @param cantidad
	 */
	public ItemOrdenDeCompraView(MaterialView material, int cantidad) {
		super();
		this.material = material;
		this.cantidad = cantidad;
	}

	public MaterialView getMaterial() {
		return material;
	}

	public int getCantidad() {
		return cantidad;
	}
}
