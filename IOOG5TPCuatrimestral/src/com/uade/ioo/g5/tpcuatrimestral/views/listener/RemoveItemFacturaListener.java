package com.uade.ioo.g5.tpcuatrimestral.views.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JOptionPane;

import com.uade.ioo.g5.tpcuatrimestral.controller.Controller;
import com.uade.ioo.g5.tpcuatrimestral.views.ItemFacturaView;
import com.uade.ioo.g5.tpcuatrimestral.views.models.ItemFacturaModel;
import com.uade.ioo.g5.tpcuatrimestral.views.models.PrendasModel;

public class RemoveItemFacturaListener implements ActionListener {

	private ItemFacturaModel src;

	private PrendasModel dst;

	private JList<ItemFacturaView> viewSrc;

	private Controller controller;

	/**
	 * @param src
	 * @param dst
	 * @param prendasView
	 */
	public RemoveItemFacturaListener(ItemFacturaModel src, PrendasModel dst, JList<ItemFacturaView> prendasView) {
		super();
		this.src = src;
		this.dst = dst;
		this.viewSrc = prendasView;
		controller = Controller.getInstance();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int index = viewSrc.getSelectedIndex();
		if (index > -1) {
			try {
				if (controller.borrarItemFactura(src.getElementAt(index).getCodigo())) {
					ItemFacturaView view = src.remove(index);
					dst.add(view.getPrenda());
				} else {
					JOptionPane.showMessageDialog(null, "No se puede eliminar la prenda!");
				}
			} catch (Exception a) {

			}
		}
	}
}
