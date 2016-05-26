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
import com.uade.ioo.g5.tpcuatrimestral.views.PrendaView;
import com.uade.ioo.g5.tpcuatrimestral.views.builder.FormBuilder;
import com.uade.ioo.g5.tpcuatrimestral.views.listener.AddItemMaterialListener;
import com.uade.ioo.g5.tpcuatrimestral.views.listener.RemoveItemMaterialListener;
import com.uade.ioo.g5.tpcuatrimestral.views.models.ItemMaterialesModel;
import com.uade.ioo.g5.tpcuatrimestral.views.models.MaterialesModel;

public class ModificarPrendaTemporadaFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int codigo;

	// Fields
	private JTextField nombre;

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

	public ModificarPrendaTemporadaFrame(int codigo) {
		this.codigo = codigo;

		setSize(600, 250);
		// setResizable(false);
		setTitle("Modificar prenda!");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		nombre = new JTextField(20);
		descripcion = new JTextArea(5, 20);

		btnAceptar = new JButton("Guardar");
		btnCancelar = new JButton("Cancelar");

		btnAddMaterial = new JButton("<<");
		btnDelMaterial = new JButton(">>");

		// Lists
		materiales = new JList<MaterialView>();
		materialesModel = new MaterialesModel();
		materiales.setModel(materialesModel);
		seleccionados = new JList<ItemMaterialView>();

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
		PrendaView prendaView = controller.iniciarModificacionPrenda(codigo);
		ArrayList<ItemMaterialView> matsSeleccionados = prendaView.getMateriales();
		ArrayList<MaterialView> materialesView = controller.getMateriales();

		// Agregar Materiales que no pertenecen a la prenda
		for (MaterialView materialView : materialesView) {
			boolean existe = false;
			int pos = 0;
			while (pos < matsSeleccionados.size() && !existe) {
				if (materialView.getCodigo() == matsSeleccionados.get(pos).getCodigo()) {
					existe = true;
				}
				pos++;
			}
			if (!existe) {
				materialesModel.add(materialView);
			}
		}

		// Agregar Materiales que pertenecen a la prenda
		seleccionadosModel = new ItemMaterialesModel(matsSeleccionados);
		seleccionados.setModel(seleccionadosModel);

		// Set Fields
		this.nombre.setText(prendaView.getNombre());
		this.descripcion.setText(prendaView.getDescripcion());

		// Estaciones
		controller.iniciarCreacionPrendaTemporada();
		estacionesModel.addElement("VERANO");
		estacionesModel.addElement("OTOÑO");
		estacionesModel.addElement("INVIERNO");
		estacionesModel.addElement("PRIMAVERA");
		estaciones.setSelectedItem(prendaView.getEstacion());
	}

	private void setLayouts() {
		// ELEMENTS
		JPanel form = new JPanel(new GridBagLayout());
		setLayout(new BorderLayout());
		add(form, BorderLayout.CENTER);

		FormBuilder builder = new FormBuilder(form);
		builder.addLabel("Nombre");
		builder.addValue(nombre);
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
		titlePanel.add(new Label("Prendas"), BorderLayout.EAST);

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
		btnAddMaterial.addActionListener(new AddItemMaterialListener(materialesModel, seleccionadosModel, materiales, false));
		btnDelMaterial.addActionListener(new RemoveItemMaterialListener(seleccionadosModel, materialesModel, seleccionados, false));
		btnAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (nombre.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Nombre no puede ser vacio!");
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
				if (controller.modificarPrendaTemporada(codigo, nombre.getText(), descripcion.getText(), seleccionadosModel.getMateriales(), estaciones
						.getSelectedItem().toString())) {
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "No se pudo agregar la prenda!");
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
