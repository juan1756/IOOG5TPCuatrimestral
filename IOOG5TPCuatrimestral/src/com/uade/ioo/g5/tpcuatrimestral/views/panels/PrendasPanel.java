package com.uade.ioo.g5.tpcuatrimestral.views.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.uade.ioo.g5.tpcuatrimestral.controller.Controller;
import com.uade.ioo.g5.tpcuatrimestral.views.PrendaView;
import com.uade.ioo.g5.tpcuatrimestral.views.frames.AgregarPrendaFrame;
import com.uade.ioo.g5.tpcuatrimestral.views.frames.AgregarPrendaTemporadaFrame;
import com.uade.ioo.g5.tpcuatrimestral.views.frames.ModificarPrendaFrame;
import com.uade.ioo.g5.tpcuatrimestral.views.frames.ModificarPrendaTemporadaFrame;
import com.uade.ioo.g5.tpcuatrimestral.views.listener.TableMouseListener;
import com.uade.ioo.g5.tpcuatrimestral.views.models.PrendasTableModel;

public class PrendasPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel lblTitulo;

	private JTable tblItems;

	private JButton btnAgregar;

	private JButton btnAgregarTemporada;

	private PrendasTableModel tableModel;

	private Controller controller;

	private JPopupMenu popupMenu;

	private JMenuItem menuItemBorrar;

	private JMenuItem menuItemModificar;

	public PrendasPanel() {
		// Instanciacion
		lblTitulo = new JLabel("<HTML><U>Prendas</U></HTML>");

		// Agregado al panel
		btnAgregar = new JButton("Agregar prenda");
		btnAgregarTemporada = new JButton("Agregar prenda de Temporada");
		tblItems = new JTable();
		menuItemBorrar = new JMenuItem("Borrar");
		menuItemModificar = new JMenuItem("Modificar");
		popupMenu = new JPopupMenu();
		popupMenu.add(menuItemModificar);
		popupMenu.add(menuItemBorrar);

		ArrayList<String> columns = new ArrayList<String>();
		columns.add("Codigo");
		columns.add("Nombre");
		columns.add("Stock");
		columns.add("Estacion");
		columns.add("Descripción");
		tableModel = new PrendasTableModel(columns);
		tblItems.setModel(tableModel);

		controller = Controller.getInstance();

		setLayouts();
		addEventListeners();
	}

	private void setLayouts() {
		JPanel title = new JPanel(new FlowLayout(FlowLayout.LEFT));
		title.add(lblTitulo);
		JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		botones.add(btnAgregar);
		botones.add(btnAgregarTemporada);

		this.setLayout(new BorderLayout());
		this.add(title, BorderLayout.NORTH);
		this.add(new JScrollPane(tblItems), BorderLayout.CENTER);
		this.add(botones, BorderLayout.SOUTH);

		tblItems.getTableHeader().setReorderingAllowed(false);
		tblItems.setComponentPopupMenu(popupMenu);
	}

	private void addEventListeners() {
		tblItems.addMouseListener(new TableMouseListener(tblItems));
		menuItemBorrar.addActionListener(this);
		menuItemModificar.addActionListener(this);

		btnAgregar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AgregarPrendaFrame().setVisible(true);
			}
		});

		btnAgregarTemporada.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AgregarPrendaTemporadaFrame().setVisible(true);
			}
		});
	}

	public void updateModel() {
		ArrayList<PrendaView> prendas = controller.getPrendas();
		for (int i = 0; i < prendas.size(); i++) {
			if (i < tableModel.getRowCount()) {
				tableModel.setRow(prendas.get(i), i);
			} else {
				tableModel.addRow(prendas.get(i));
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == menuItemBorrar) {
			int selectedRow = tblItems.getSelectedRow();
			PrendaView prenda = tableModel.getItemRow(selectedRow);
			if (controller.borrarPrenda(prenda.getCodigo())) {
				tableModel.removeRow(selectedRow);
			}
		} else if (e.getSource() == menuItemModificar) {
			int selectedRow = tblItems.getSelectedRow();
			PrendaView prenda = tableModel.getItemRow(selectedRow);
			if (prenda.getEstacion().isEmpty()) {
				new ModificarPrendaFrame(prenda.getCodigo()).setVisible(true);
			} else {
				new ModificarPrendaTemporadaFrame(prenda.getCodigo()).setVisible(true);
			}
		}
	}
}