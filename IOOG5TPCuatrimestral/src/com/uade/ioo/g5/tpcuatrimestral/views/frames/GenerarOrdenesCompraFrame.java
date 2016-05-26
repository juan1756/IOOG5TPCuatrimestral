package com.uade.ioo.g5.tpcuatrimestral.views.frames;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.uade.ioo.g5.tpcuatrimestral.controller.Controller;
import com.uade.ioo.g5.tpcuatrimestral.views.ItemOrdenDeCompraView;
import com.uade.ioo.g5.tpcuatrimestral.views.OrdenCompraView;
import com.uade.ioo.g5.tpcuatrimestral.views.ProveedorView;

public class GenerarOrdenesCompraFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String content;

	private Controller controller;

	public GenerarOrdenesCompraFrame() {
		setSize(350, 300);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		controller = Controller.getInstance();
		initValues();
		setLayouts();
	}

	private void initValues() {
		ArrayList<OrdenCompraView> ordenesDeCompra = controller.generarOrdenesDeCompra();

		for (OrdenCompraView orden : ordenesDeCompra) {
			ProveedorView proveedor = orden.getProveedor();
			ArrayList<ItemOrdenDeCompraView> items = orden.getMateriales();

			this.content = "<html><h3><u>Ordene de Compra:" + orden.getCodigo() + "</u></h3>";
			this.content += "Proveedor: " + proveedor + "<br>";

			for (ItemOrdenDeCompraView item : items) {
				this.content += "Material: " + item.getMaterial() + "<br>";
				this.content += "Cantidad: " + item.getCantidad() + "<br>";
			}
		}
		this.content += "</html>";
	}

	private void setLayouts() {
		JPanel title = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblTitulo = new JLabel(this.content);
		title.add(lblTitulo);
		this.setLayout(new BorderLayout());
		this.add(title, BorderLayout.NORTH);
	}
}
