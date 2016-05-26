package com.uade.ioo.g5.tpcuatrimestral.views.frames;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.uade.ioo.g5.tpcuatrimestral.controller.Controller;
import com.uade.ioo.g5.tpcuatrimestral.views.ItemFacturaView;
import com.uade.ioo.g5.tpcuatrimestral.views.PrendaView;
import com.uade.ioo.g5.tpcuatrimestral.views.listener.AddPrendaListener;
import com.uade.ioo.g5.tpcuatrimestral.views.listener.RemoveItemFacturaListener;
import com.uade.ioo.g5.tpcuatrimestral.views.models.ItemFacturaModel;
import com.uade.ioo.g5.tpcuatrimestral.views.models.PrendasModel;

public class NuevaVentaFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton btnAddPrenda;

	private JButton btnDelPrenda;

	private JButton btnCancelar;

	private JButton btnAceptar;

	private JList<PrendaView> prendas;

	private PrendasModel prendasModel;

	private JList<ItemFacturaView> seleccionados;

	private ItemFacturaModel seleccionadosModel;

	private Controller controller;

	public NuevaVentaFrame() {
		setTitle("Nueva Venta");
		setSize(600, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		// MODEL
		prendas = new JList<PrendaView>();
		prendasModel = new PrendasModel();
		prendas.setModel(prendasModel);
		seleccionados = new JList<ItemFacturaView>();
		seleccionadosModel = new ItemFacturaModel();
		seleccionados.setModel(seleccionadosModel);

		// BUTTONS
		btnAddPrenda = new JButton("<<");
		btnDelPrenda = new JButton(">>");
		btnAceptar = new JButton("Aceptar");
		btnCancelar = new JButton("Cancelar");

		controller = Controller.getInstance();

		initValues();
		setLayouts();
		addEventListeners();
	}

	private void initValues() {
		ArrayList<PrendaView> prendasViews = controller.getPrendas();
		for (PrendaView prendaView : prendasViews) {
			prendasModel.add(prendaView);
		}
		controller.iniciarVenta();
	}

	private void setLayouts() {
		setLayout(new BorderLayout());

		// Prendas Lists
		JPanel prendasButtons = new JPanel(new GridBagLayout());
		GridBagConstraints cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 0;
		prendasButtons.add(btnAddPrenda, cons);
		cons.gridx = 0;
		cons.gridy = 1;
		prendasButtons.add(btnDelPrenda, cons);

		// TITULOS
		JPanel titlePanel = new JPanel(new BorderLayout());
		titlePanel.add(new Label("Seleccionadas"), BorderLayout.WEST);
		titlePanel.add(new Label("Prendas"), BorderLayout.EAST);

		JPanel prendasPanel = new JPanel(new BorderLayout());
		prendasPanel.add(titlePanel, BorderLayout.NORTH);
		prendasPanel.add(new JScrollPane(seleccionados), BorderLayout.WEST);
		prendasPanel.add(prendasButtons, BorderLayout.CENTER);
		prendasPanel.add(new JScrollPane(prendas), BorderLayout.EAST);

		add(prendasPanel, BorderLayout.CENTER);

		// BUTTONS
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		add(btnPanel, BorderLayout.SOUTH);
		btnPanel.add(btnAceptar);
		btnPanel.add(btnCancelar);
	}

	private void addEventListeners() {
		btnAddPrenda.addActionListener(new AddPrendaListener(prendasModel, seleccionadosModel, prendas));
		btnDelPrenda.addActionListener(new RemoveItemFacturaListener(seleccionadosModel, prendasModel, seleccionados));

		btnAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (seleccionadosModel.getSize() <= 0) {
					JOptionPane.showMessageDialog(null, "No se puede realizar una venta sin prendas!");
					return;
				}
				if (controller.cerrarVenta()) {
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Error al crear la Venta!");
				}
			}
		});

		btnCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
