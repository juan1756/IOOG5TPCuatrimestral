package com.uade.ioo.g5.tpcuatrimestral.model;

public class ItemFactura {

	public ItemFactura(Prenda prenda, int cantidad) {
		super();
		this.prenda = prenda;
		this.cantidad = cantidad;
	}

	private Prenda prenda;

	private int cantidad;

	public boolean isPrenda(Prenda prenda) {
		return this.prenda == prenda;
	}

	public float getImporte() {
		return cantidad * prenda.getPrecioVenta();
	}

	public void restarStock() {
		prenda.restarStock(cantidad);
	}
}
