package com.uade.ioo.g5.tpcuatrimestral.views.models;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

import com.uade.ioo.g5.tpcuatrimestral.views.ItemFacturaView;

public class ItemFacturaModel extends AbstractListModel<ItemFacturaView> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<ItemFacturaView> prendas;

	public ItemFacturaModel() {
		prendas = new ArrayList<ItemFacturaView>();
	}

	/**
	 * @param prendas
	 */
	public ItemFacturaModel(ArrayList<ItemFacturaView> prendas) {
		super();
		this.prendas = prendas;
	}

	public void add(ItemFacturaView prenda) {
		prendas.add(prenda);
		fireContentsChanged(this, prendas.size() - 1, prendas.size() - 1);
	}

	@Override
	public ItemFacturaView getElementAt(int index) {
		return prendas.get(index);
	}

	public ItemFacturaView remove(int index) {
		ItemFacturaView remove = prendas.remove(index);
		fireContentsChanged(this, index, index);
		return remove;
	}

	@Override
	public int getSize() {
		return prendas.size();
	}

}
