package com.uade.ioo.g5.tpcuatrimestral.views.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.uade.ioo.g5.tpcuatrimestral.controller.Controller;
import com.uade.ioo.g5.tpcuatrimestral.views.MaterialView;
import com.uade.ioo.g5.tpcuatrimestral.views.frames.AgregarMaterialFrame;
import com.uade.ioo.g5.tpcuatrimestral.views.listener.TableMouseListener;
import com.uade.ioo.g5.tpcuatrimestral.views.models.MaterialesTableModel;

public class MaterialesPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel lblTitulo;

	private JTable tblItems;

	private JButton btnAgregar;

	// MODELS

	private MaterialesTableModel tableModel;

	private Controller controller;

	public MaterialesPanel() {
		// Instanciacion
		lblTitulo = new JLabel("<HTML><U>Materiales</U></HTML>");
		tblItems = new JTable();

		// Column Names
		ArrayList<String> columns = new ArrayList<String>();
		columns.add("Codigo");
		columns.add("Nombre");
		columns.add("Stock");
		columns.add("Costo");
		columns.add("Descripción");

		tableModel = new MaterialesTableModel(columns);
		tblItems.setModel(tableModel);
		btnAgregar = new JButton("Agregar material");

		controller = Controller.getInstance();
		// Agregado al panel
		setLayouts();
		addEventListeners();
	}

	private void setLayouts() {
		JPanel title = new JPanel(new FlowLayout(FlowLayout.LEFT));
		title.add(lblTitulo);
		JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		botones.add(btnAgregar);

		this.setLayout(new BorderLayout());
		this.add(title, BorderLayout.NORTH);
		this.add(new JScrollPane(tblItems), BorderLayout.CENTER);
		this.add(botones, BorderLayout.SOUTH);

		tblItems.getTableHeader().setReorderingAllowed(false);
	}

	public void updateModel() {
		ArrayList<MaterialView> materiales = controller.getMateriales();
		for (int i = 0; i < materiales.size(); i++) {
			if (i < tableModel.getRowCount()) {
				tableModel.setRow(materiales.get(i), i);
			} else {
				tableModel.addRow(materiales.get(i));
			}
		}
	}

	private void addEventListeners() {
		tblItems.addMouseListener(new TableMouseListener(tblItems));
		btnAgregar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AgregarMaterialFrame().setVisible(true);
			}
		});
	}
}