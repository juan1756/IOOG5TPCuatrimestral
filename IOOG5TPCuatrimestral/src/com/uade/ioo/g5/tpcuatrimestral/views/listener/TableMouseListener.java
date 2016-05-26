package com.uade.ioo.g5.tpcuatrimestral.views.listener;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

/**
 * Listener para seleccionar un elemento al hacer click
 */
public class TableMouseListener extends MouseAdapter {

	private JTable table;

	public TableMouseListener(JTable table) {
		this.table = table;
	}

	@Override
	public void mousePressed(MouseEvent event) {
		Point point = event.getPoint();
		int currentRow = table.rowAtPoint(point);
		if (currentRow != -1)
			table.setRowSelectionInterval(currentRow, currentRow);
	}
}