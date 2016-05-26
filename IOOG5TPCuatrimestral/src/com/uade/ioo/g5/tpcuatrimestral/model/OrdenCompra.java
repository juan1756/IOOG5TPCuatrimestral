package com.uade.ioo.g5.tpcuatrimestral.model;

import java.util.ArrayList;

import com.uade.ioo.g5.tpcuatrimestral.views.ItemOrdenDeCompraView;
import com.uade.ioo.g5.tpcuatrimestral.views.OrdenCompraView;

/**
 * 
 */
public class OrdenCompra {

	/**
     * 
     */
	private int codigo;

	/**
     * 
     */
	private Proveedor proveedor;

	/**
     * 
     */
	private ArrayList<ItemOrdenDeCompra> materiales;

	public OrdenCompra(int codigo, Proveedor proveedor) {
		this.codigo = codigo;
		this.proveedor = proveedor;
		materiales = new ArrayList<ItemOrdenDeCompra>();
		for (Material material : this.proveedor.getMateriales()) {
			if (material.isBajoPuntoDePedido()) {
				int cant = material.getPuntoDePedido() * 2;
				ItemOrdenDeCompra item = new ItemOrdenDeCompra(material, cant);
				materiales.add(item);
			}
		}
	}

	public boolean haveItems() {
		return (materiales.size() > 0);
	}

	public OrdenCompraView getOrdenCompraView() {
		ArrayList<ItemOrdenDeCompraView> itemsView = new ArrayList<ItemOrdenDeCompraView>();
		for (ItemOrdenDeCompra item : materiales) {
			itemsView.add(item.getView());
		}
		OrdenCompraView view = new OrdenCompraView(this.codigo, this.proveedor.getView(), itemsView);
		return view;

	}
}