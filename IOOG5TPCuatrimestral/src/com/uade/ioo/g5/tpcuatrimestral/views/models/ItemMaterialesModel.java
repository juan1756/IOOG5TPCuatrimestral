package com.uade.ioo.g5.tpcuatrimestral.views.models;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

import com.uade.ioo.g5.tpcuatrimestral.views.ItemMaterialView;

public class ItemMaterialesModel extends AbstractListModel<ItemMaterialView> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<ItemMaterialView> materiales;

	public ItemMaterialesModel() {
		materiales = new ArrayList<ItemMaterialView>();
	}

	/**
	 * @param materiales
	 */
	public ItemMaterialesModel(ArrayList<ItemMaterialView> materiales) {
		super();
		this.materiales = materiales;
	}

	public void add(ItemMaterialView material) {
		materiales.add(material);
		fireContentsChanged(this, materiales.size() - 1, materiales.size() - 1);
	}

	@Override
	public ItemMaterialView getElementAt(int index) {
		return materiales.get(index);
	}

	public ItemMaterialView remove(int index) {
		ItemMaterialView remove = materiales.remove(index);
		fireContentsChanged(this, index, index);
		return remove;
	}

	@Override
	public int getSize() {
		return materiales.size();
	}

	public ArrayList<ItemMaterialView> getMateriales() {
		return materiales;
	}
}
