package com.uade.ioo.g5.tpcuatrimestral.views.frames;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.uade.ioo.g5.tpcuatrimestral.views.panels.MaterialesPanel;
import com.uade.ioo.g5.tpcuatrimestral.views.panels.PrendasPanel;

public class SistemaFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MaterialesPanel materialesPanel;

	private PrendasPanel prendasPanel;

	private JMenuItem generarOrdenes;

	private JMenuItem nuevaVenta;

	public SistemaFrame() {
		setSize(950, 550);
		setResizable(false);
		materialesPanel = new MaterialesPanel();
		prendasPanel = new PrendasPanel();
		generarOrdenes = new JMenuItem("Generar Ordenes de Compra");
		nuevaVenta = new JMenuItem("Nueva Venta");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		setLayout();
		addEventListeners();
	}

	private void setLayout() {
		setLayout(new FlowLayout());
		add(materialesPanel);
		add(prendasPanel);

		JMenu menu = new JMenu("Acciones");
		menu.add(generarOrdenes);
		menu.add(nuevaVenta);
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(menu);
		setJMenuBar(menuBar);
	}

	private void addEventListeners() {
		generarOrdenes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new GenerarOrdenesCompraFrame().setVisible(true);
			}
		});

		nuevaVenta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new NuevaVentaFrame().setVisible(true);
			}
		});
		this.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				System.out.println("Window Opened Event");
			}

			public void windowClosing(WindowEvent e) {
				System.out.println("Window Closing Event");
			}

			public void windowClosed(WindowEvent e) {
				System.out.println("Window Close Event");
			}

			public void windowIconified(WindowEvent e) {
				System.out.println("Window Iconified Event");
			}

			public void windowDeiconified(WindowEvent e) {
				System.out.println("Window Deiconified Event");
			}

			public void windowActivated(WindowEvent e) {
				prendasPanel.updateModel();
				materialesPanel.updateModel();
			}

			public void windowDeactivated(WindowEvent e) {
				System.out.println("Window Deactivated Event");
			}

			public void windowStateChanged(WindowEvent e) {
				System.out.println("Window State Changed Event");
			}

			public void windowGainedFocus(WindowEvent e) {
				System.out.println("Window Gained Focus Event");
			}

			public void windowLostFocus(WindowEvent e) {
				System.out.println("Window Lost Focus Event");
			}
		});
	}
}
