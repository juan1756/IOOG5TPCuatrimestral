package com.uade.ioo.g5.tpcuatrimestral.views;

import java.util.ArrayList;

public class FacturaView {

	private float importe;

	private ArrayList<ItemFacturaView> items;

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	public ArrayList<ItemFacturaView> getItems() {
		return items;
	}

	public void setItems(ArrayList<ItemFacturaView> items) {
		this.items = items;
	}
}
