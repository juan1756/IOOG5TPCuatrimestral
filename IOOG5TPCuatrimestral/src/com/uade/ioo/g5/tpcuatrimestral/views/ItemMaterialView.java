package com.uade.ioo.g5.tpcuatrimestral.views;

public class ItemMaterialView {

	/**
	 * 
	 */
	private int cantidad;

	private MaterialView material;

	/**
	 * @param cantidad
	 * @param material
	 */
	public ItemMaterialView(int cantidad, MaterialView material) {
		super();
		this.cantidad = cantidad;
		this.material = material;
	}

	public int getCodigo() {
		return material.getCodigo();
	}

	public String getNombre() {
		return String.valueOf(material.getCodigo());
	}

	/**
	 * @return the cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}

	public String getDescripcion() {
		return material.getDescripcion();
	}

	public MaterialView getMaterial() {
		return material;
	}

	@Override
	public String toString() {
		return material.toString() + "-" + cantidad;
	}
}
