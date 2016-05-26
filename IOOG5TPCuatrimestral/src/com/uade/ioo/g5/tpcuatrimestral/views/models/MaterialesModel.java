package com.uade.ioo.g5.tpcuatrimestral.views.models;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

import com.uade.ioo.g5.tpcuatrimestral.views.MaterialView;

public class MaterialesModel extends AbstractListModel<MaterialView> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<MaterialView> materiales;

	public MaterialesModel() {
		materiales = new ArrayList<MaterialView>();
	}

	/**
	 * @param materiales
	 */
	public MaterialesModel(ArrayList<MaterialView> materiales) {
		super();
		this.materiales = materiales;
	}

	public void add(MaterialView material) {
		materiales.add(material);
		fireContentsChanged(this, materiales.size() - 1, materiales.size() - 1);
	}

	@Override
	public MaterialView getElementAt(int index) {
		return materiales.get(index);
	}

	public MaterialView remove(int index) {
		MaterialView remove = materiales.remove(index);
		fireContentsChanged(this, index, index);
		return remove;
	}

	@Override
	public int getSize() {
		return materiales.size();
	}

}
