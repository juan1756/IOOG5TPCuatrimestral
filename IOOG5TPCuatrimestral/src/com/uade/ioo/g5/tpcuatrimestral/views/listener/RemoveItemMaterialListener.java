package com.uade.ioo.g5.tpcuatrimestral.views.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JOptionPane;

import com.uade.ioo.g5.tpcuatrimestral.controller.Controller;
import com.uade.ioo.g5.tpcuatrimestral.views.ItemMaterialView;
import com.uade.ioo.g5.tpcuatrimestral.views.models.ItemMaterialesModel;
import com.uade.ioo.g5.tpcuatrimestral.views.models.MaterialesModel;

public class RemoveItemMaterialListener implements ActionListener {

	private ItemMaterialesModel src;

	private MaterialesModel dst;

	private JList<ItemMaterialView> viewSrc;

	private Controller controller;

	private boolean impactar;

	/**
	 * @param src
	 * @param dst
	 * @param materialesView
	 */
	public RemoveItemMaterialListener(ItemMaterialesModel src, MaterialesModel dst, JList<ItemMaterialView> materialesView, boolean impactar) {
		super();
		this.src = src;
		this.dst = dst;
		this.viewSrc = materialesView;
		this.impactar = impactar;
		controller = Controller.getInstance();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int index = viewSrc.getSelectedIndex();
		if (index > -1) {
			try {
				ItemMaterialView view = src.getElementAt(index);
				if (impactar) {
					if (controller.borrarItemMaterial(src.getElementAt(index).getCodigo())) {
						src.remove(index);
						dst.add(view.getMaterial());
					} else {
						JOptionPane.showMessageDialog(null, "No se puede eliminar el material!");
					}
				} else {
					src.remove(index);
					dst.add(view.getMaterial());
				}
			} catch (Exception a) {

			}
		}
	}
}
