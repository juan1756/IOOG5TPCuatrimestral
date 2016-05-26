package com.uade.ioo.g5.tpcuatrimestral.views.models;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

import com.uade.ioo.g5.tpcuatrimestral.views.PrendaView;

public class PrendasModel extends AbstractListModel<PrendaView> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<PrendaView> prendas;

	public PrendasModel() {
		prendas = new ArrayList<PrendaView>();
	}

	/**
	 * @param prendas
	 */
	public PrendasModel(ArrayList<PrendaView> prendas) {
		super();
		this.prendas = prendas;
	}

	public void add(PrendaView prenda) {
		prendas.add(prenda);
		fireContentsChanged(this, prendas.size() - 1, prendas.size() - 1);
	}

	@Override
	public PrendaView getElementAt(int index) {
		return prendas.get(index);
	}

	public PrendaView remove(int index) {
		PrendaView remove = prendas.remove(index);
		fireContentsChanged(this, index, index);
		return remove;
	}

	@Override
	public int getSize() {
		return prendas.size();
	}

}
