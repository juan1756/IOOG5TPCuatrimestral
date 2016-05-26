package com.uade.ioo.g5.tpcuatrimestral.views.frames;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.uade.ioo.g5.tpcuatrimestral.controller.Controller;
import com.uade.ioo.g5.tpcuatrimestral.views.ItemMaterialView;
import com.uade.ioo.g5.tpcuatrimestral.views.MaterialView;
import com.uade.ioo.g5.tpcuatrimestral.views.builder.FormBuilder;
import com.uade.ioo.g5.tpcuatrimestral.views.listener.AddItemMaterialListener;
import com.uade.ioo.g5.tpcuatrimestral.views.listener.RemoveItemMaterialListener;
import com.uade.ioo.g5.tpcuatrimestral.views.models.ItemMaterialesModel;
import com.uade.ioo.g5.tpcuatrimestral.views.models.MaterialesModel;

public class AgregarPrendaTemporadaFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Fields
	private JTextField nombre;

	private JTextField stock;

	private JTextArea descripcion;

	// Buttons

	private JButton btnAceptar;

	private JButton btnCancelar;

	private JButton btnAddMaterial;

	private JButton btnDelMaterial;

	// Materiales

	private JList<MaterialView> materiales;

	private MaterialesModel materialesModel;

	private JList<ItemMaterialView> seleccionados;

	private ItemMaterialesModel seleccionadosModel;

	private JComboBox<String> estaciones;

	private DefaultComboBoxModel<String> estacionesModel;

	private Controller controller;

	public AgregarPrendaTemporadaFrame() {
		setSize(740, 300);
		setResizable(false);
		setTitle("Agregar prenda de temporada!");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		nombre = new JTextField(20);
		stock = new JTextField(20);
		descripcion = new JTextArea(5, 20);

		btnAceptar = new JButton("Agregar");
		btnCancelar = new JButton("Cancelar");

		btnAddMaterial = new JButton("<<");
		btnDelMaterial = new JButton(">>");

		// Lists
		materiales = new JList<MaterialView>();
		materialesModel = new MaterialesModel();
		materiales.setModel(materialesModel);
		seleccionados = new JList<ItemMaterialView>();
		seleccionadosModel = new ItemMaterialesModel();
		seleccionados.setModel(seleccionadosModel);

		// Combo
		estaciones = new JComboBox<String>();
		estacionesModel = new DefaultComboBoxModel<String>();
		estaciones.setModel(estacionesModel);

		controller = Controller.getInstance();

		initValues();
		setLayouts();
		addEventListeners();
	}

	private void initValues() {
		controller.iniciarCreacionPrendaTemporada();
		estacionesModel.addElement("VERANO");
		estacionesModel.addElement("OTOÑO");
		estacionesModel.addElement("INVIERNO");
		estacionesModel.addElement("PRIMAVERA");
		ArrayList<MaterialView> materialesView = controller.getMateriales();
		for (MaterialView materialView : materialesView) {
			materialesModel.add(materialView);
		}
	}

	private void setLayouts() {
		// ELEMENTS
		JPanel form = new JPanel(new GridBagLayout());
		setLayout(new BorderLayout());
		add(form, BorderLayout.CENTER);

		FormBuilder builder = new FormBuilder(form);
		builder.addLabel("Nombre");
		builder.addValue(nombre);
		builder.addLabel("Stock");
		builder.addValue(stock);
		builder.addLabel("Estacion");
		builder.addValue(estaciones);
		builder.addLabel("Descripcion");
		builder.addValue(descripcion);

		// Materiales Lists
		JPanel materialesButtons = new JPanel(new GridBagLayout());
		GridBagConstraints cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 0;
		materialesButtons.add(btnAddMaterial, cons);
		cons.gridx = 0;
		cons.gridy = 1;
		materialesButtons.add(btnDelMaterial, cons);

		// TITULOS
		JPanel titlePanel = new JPanel(new BorderLayout());
		titlePanel.add(new Label("Seleccionados"), BorderLayout.WEST);
		titlePanel.add(new Label("Materiales"), BorderLayout.EAST);

		JPanel materialesPanel = new JPanel(new BorderLayout());
		materialesPanel.add(titlePanel, BorderLayout.NORTH);
		materialesPanel.add(new JScrollPane(seleccionados), BorderLayout.WEST);
		materialesPanel.add(materialesButtons, BorderLayout.CENTER);
		materialesPanel.add(new JScrollPane(materiales), BorderLayout.EAST);

		add(materialesPanel, BorderLayout.EAST);

		// BUTTONS
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		add(btnPanel, BorderLayout.SOUTH);
		btnPanel.add(btnAceptar);
		btnPanel.add(btnCancelar);
	}

	private void addEventListeners() {
		btnAddMaterial.addActionListener(new AddItemMaterialListener(materialesModel, seleccionadosModel, materiales, true));
		btnDelMaterial.addActionListener(new RemoveItemMaterialListener(seleccionadosModel, materialesModel, seleccionados, true));
		btnAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (nombre.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Nombre no puede ser vacio!");
					return;
				}
				if (stock.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Stock no puede ser vacio!");
					return;
				}
				try {
					Integer.parseInt(stock.getText());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Stock debe ser un numero!");
					return;
				}
				if (descripcion.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Descripcion no puede ser vacio!");
					return;
				}
				if (seleccionadosModel.getSize() <= 0) {
					JOptionPane.showMessageDialog(null, "No se puede crear una prenda sin materiales!");
					return;
				}
				if (controller.agregarPrendaTemporada(nombre.getText(), descripcion.getText(), Integer.parseInt(stock.getText()), estaciones.getSelectedItem()
						.toString())) {
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "No se pudo agregar la prenda!");
				}

			}
		});

		btnCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("CANCELAR");
				dispose();
			}
		});
	}
}
