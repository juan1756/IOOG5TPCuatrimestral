package com.uade.ioo.g5.tpcuatrimestral.model;

import java.util.ArrayList;

/**
 * 
 */
public class Factura {

	private float importe;

	/**
	 * 
	 */
	private ArrayList<ItemFactura> productos;

	public Factura() {
		productos = new ArrayList<ItemFactura>();
	}

	public boolean existe(Prenda p) {
		// TODO
		return false;
	}

	public boolean agregarItem(Prenda p, int cantidad) {
		return productos.add(new ItemFactura(p, cantidad));
	}

	public boolean borrarItem(Prenda p) {
		ItemFactura item = obtenerItem(p);
		if (item != null) {
			return productos.remove(item);
		}
		return false;
	}

	public void calcularImporte() {
		importe = 0;
		for (int i = 0; i < productos.size(); i++) {
			importe += productos.get(i).getImporte();
		}
	}

	private ItemFactura obtenerItem(Prenda p) {
		ItemFactura item = null;
		int pos = 0;
		while (pos < productos.size() && item == null) {
			if (productos.get(pos).isPrenda(p)) {
				item = productos.get(pos);
			}
			pos++;
		}

		return item;
	}

	public void restarStock() {
		for (ItemFactura item : productos) {
			item.restarStock();
		}
	}
}