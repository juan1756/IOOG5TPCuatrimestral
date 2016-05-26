package com.uade.ioo.g5.tpcuatrimestral.views;

import java.util.ArrayList;

public class OrdenCompraView {

	private int codigo;

	private ProveedorView proveedor;

	private ArrayList<ItemOrdenDeCompraView> materiales;

	/**
	 * @param codigo
	 * @param proveedor
	 * @param materiales
	 */
	public OrdenCompraView(int codigo, ProveedorView proveedor, ArrayList<ItemOrdenDeCompraView> materiales) {
		super();
		this.codigo = codigo;
		this.proveedor = proveedor;
		this.materiales = materiales;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public ProveedorView getProveedor() {
		return proveedor;
	}

	public void setProveedor(ProveedorView proveedor) {
		this.proveedor = proveedor;
	}

	public ArrayList<ItemOrdenDeCompraView> getMateriales() {
		return materiales;
	}

	public void setMateriales(ArrayList<ItemOrdenDeCompraView> materiales) {
		this.materiales = materiales;
	}
}
