package com.uade.ioo.g5.tpcuatrimestral.views.frames;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.uade.ioo.g5.tpcuatrimestral.controller.Controller;
import com.uade.ioo.g5.tpcuatrimestral.views.ProveedorView;
import com.uade.ioo.g5.tpcuatrimestral.views.builder.FormBuilder;

public class AgregarMaterialFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Fields
	private JTextField nombre;

	private JTextField stock;

	private JTextField costo;

	private JTextField puntoDePedido;

	private JComboBox<ProveedorView> proveedores;

	private DefaultComboBoxModel<ProveedorView> proveedoresModel;

	private JTextArea descripcion;

	// Buttons

	private JButton btnAceptar;

	private JButton btnCancelar;

	private Controller controller;

	public AgregarMaterialFrame() {
		setSize(380, 310);
		setResizable(false);
		setTitle("Agregar Material");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		nombre = new JTextField(20);
		stock = new JTextField(20);
		costo = new JTextField(20);
		puntoDePedido = new JTextField(20);
		proveedores = new JComboBox<ProveedorView>();
		proveedoresModel = new DefaultComboBoxModel<ProveedorView>();
		proveedores.setModel(proveedoresModel);
		descripcion = new JTextArea(5, 20);

		btnAceptar = new JButton("Aceptar");
		btnCancelar = new JButton("Cancelar");

		controller = Controller.getInstance();

		initValues();
		setLayouts();
		addEventListeners();
	}

	private void initValues() {
		ArrayList<ProveedorView> proveedoresView = controller.getProveedores();
		for (ProveedorView proveedorView : proveedoresView) {
			proveedoresModel.addElement(proveedorView);
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
		builder.addLabel("Costo");
		builder.addValue(costo);
		builder.addLabel("Stock");
		builder.addValue(stock);
		builder.addLabel("Punto de pedido");
		builder.addValue(puntoDePedido);
		builder.addLabel("Proveedor");
		builder.addValue(proveedores);
		builder.addLabel("Descripcion");
		builder.addValue(descripcion);

		// BUTTONS
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		add(btnPanel, BorderLayout.SOUTH);
		btnPanel.add(btnAceptar);
		btnPanel.add(btnCancelar);
	}

	private void addEventListeners() {
		btnAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (nombre.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Nombre no puede ser vacio!");
					return;
				}
				float cost = 0;
				try {
					cost = Float.parseFloat(costo.getText());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Costo debe ser un numero!");
					return;
				}
				if (stock.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Stock no puede ser vacio!");
					return;
				}
				int stockNum = 0;
				try {
					stockNum = Integer.parseInt(stock.getText());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Stock debe ser un numero!");
					return;
				}
				if (puntoDePedido.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Punto de Pedido no puede ser vacio!");
					return;
				}
				int puntoPedid = 0;
				try {
					puntoPedid = Integer.parseInt(puntoDePedido.getText());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Punto de Pedido debe ser un numero!");
					return;
				}
				if (proveedoresModel.getSize() <= 0 || proveedores.getSelectedItem().toString().isEmpty()) {
					JOptionPane.showMessageDialog(null, "No se puede crear una prenda sin Proveedor!");
					return;
				}

				if (descripcion.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Descripcion no puede ser vacio!");
					return;
				}
				int dni = 0;
				try {
					dni = Integer.parseInt(proveedores.getSelectedItem().toString().split("-")[0].trim());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "DNI inválido!");
					return;
				}
				if (controller.agregarMaterial(nombre.getText(), descripcion.getText(), cost, stockNum, puntoPedid, dni)) {
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "No se pudo agregar el material!");
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
