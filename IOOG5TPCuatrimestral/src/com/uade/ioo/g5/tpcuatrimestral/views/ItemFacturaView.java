package com.uade.ioo.g5.tpcuatrimestral.views;

public class ItemFacturaView {

	private PrendaView prenda;

	private int cantidad;

	/**
	 * @param prenda
	 * @param cantidad
	 */
	public ItemFacturaView(PrendaView prenda, int cantidad) {
		super();
		this.prenda = prenda;
		this.cantidad = cantidad;
	}

	public PrendaView getPrenda() {
		return prenda;
	}

	public int getCantidad() {
		return cantidad;
	}

	public int getCodigo() {
		return prenda.getCodigo();
	}

	@Override
	public String toString() {
		return prenda.toString() + "-" + cantidad;
	}

}
