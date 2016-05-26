package com.uade.ioo.g5.tpcuatrimestral.views.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JOptionPane;

import com.uade.ioo.g5.tpcuatrimestral.controller.Controller;
import com.uade.ioo.g5.tpcuatrimestral.views.ItemFacturaView;
import com.uade.ioo.g5.tpcuatrimestral.views.PrendaView;
import com.uade.ioo.g5.tpcuatrimestral.views.models.ItemFacturaModel;
import com.uade.ioo.g5.tpcuatrimestral.views.models.PrendasModel;

public class AddPrendaListener implements ActionListener {

	private PrendasModel src;

	private ItemFacturaModel dst;

	private JList<PrendaView> viewSrc;

	private Controller controller;

	/**
	 * @param src
	 * @param dst
	 * @param prendasView
	 */
	public AddPrendaListener(PrendasModel src, ItemFacturaModel dst, JList<PrendaView> prendasView) {
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
				String s = (String) JOptionPane.showInputDialog(null, "Ingrese una cantidad", "");
				try {
					int cantidad = Integer.parseInt(s);
					PrendaView view = src.remove(index);
					if (controller.agregarItemFactura(view.getCodigo(), cantidad)) {
						dst.add(new ItemFacturaView(view, cantidad));
					} else {
						JOptionPane.showMessageDialog(null, "No se puede agregar la prenda!");
					}
				} catch (Exception e2) {
				}
			} catch (Exception a) {

			}
		}
	}
}
