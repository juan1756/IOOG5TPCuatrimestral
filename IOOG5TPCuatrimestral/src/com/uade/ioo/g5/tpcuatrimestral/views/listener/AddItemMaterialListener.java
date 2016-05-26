package com.uade.ioo.g5.tpcuatrimestral.views.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JOptionPane;

import com.uade.ioo.g5.tpcuatrimestral.controller.Controller;
import com.uade.ioo.g5.tpcuatrimestral.views.ItemMaterialView;
import com.uade.ioo.g5.tpcuatrimestral.views.MaterialView;
import com.uade.ioo.g5.tpcuatrimestral.views.models.ItemMaterialesModel;
import com.uade.ioo.g5.tpcuatrimestral.views.models.MaterialesModel;

public class AddItemMaterialListener implements ActionListener {

	private MaterialesModel src;

	private ItemMaterialesModel dst;

	private JList<MaterialView> viewSrc;

	private Controller controller;

	private boolean impactar;

	/**
	 * @param src
	 * @param dst
	 * @param materialesView
	 */
	public AddItemMaterialListener(MaterialesModel src, ItemMaterialesModel dst, JList<MaterialView> materialesView, boolean impactar) {
		super();
		this.src = src;
		this.dst = dst;
		this.viewSrc = materialesView;
		controller = Controller.getInstance();
		this.impactar = impactar;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int index = viewSrc.getSelectedIndex();
		if (index > -1) {
			try {
				String s = (String) JOptionPane.showInputDialog(null, "Ingrese una cantidad", "");
				try {
					int cantidad = Integer.parseInt(s);
					MaterialView view = src.getElementAt(index);
					if (impactar) {
						if (controller.agregarItemMaterial(view.getCodigo(), cantidad)) {
							src.remove(index);
							dst.add(new ItemMaterialView(cantidad, view));
						} else {
							JOptionPane.showMessageDialog(null, "No se puede agregar el material!");
						}
					} else {
						src.remove(index);
						dst.add(new ItemMaterialView(cantidad, view));
					}
				} catch (Exception e2) {
				}
			} catch (Exception a) {

			}
		}
	}
}
