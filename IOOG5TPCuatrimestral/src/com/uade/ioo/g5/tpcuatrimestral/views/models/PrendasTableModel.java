package com.uade.ioo.g5.tpcuatrimestral.views.models;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.uade.ioo.g5.tpcuatrimestral.views.PrendaView;

public class PrendasTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<String> columnNames;

	private ArrayList<PrendaView> prendas;

	public PrendasTableModel() {
		prendas = new ArrayList<PrendaView>();
		this.columnNames = new ArrayList<String>();
	}

	/**
	 * @param columnNames
	 */
	public PrendasTableModel(ArrayList<String> columnNames) {
		this();
		this.columnNames = columnNames;

	}

	/**
	 * @param columnNames
	 * @param prendas
	 */
	public PrendasTableModel(ArrayList<String> columnNames, ArrayList<PrendaView> prendas) {
		super();
		this.columnNames = columnNames;
		this.prendas = prendas;
	}

	@Override
	public String getColumnName(int column) {
		return columnNames.get(column);
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public int getRowCount() {
		return prendas.size();
	}

	public void addRow(PrendaView material) {
		prendas.add(material);
		fireTableRowsInserted(prendas.size() - 1, prendas.size() - 1);
	}

	public void setRow(PrendaView material, int index) {
		prendas.set(index, material);
		fireTableRowsUpdated(index, index);
	}

	public PrendaView getItemRow(int row) {
		return prendas.get(row);
	}

	public void removeRow(int row) {
		prendas.remove(row);
		fireTableRowsDeleted(row, row);
	}

	@Override
	public Object getValueAt(int row, int col) {
		PrendaView mat = prendas.get(row);
		switch (col) {
		case 0:
			return mat.getCodigo();
		case 1:
			return mat.getNombre();
		case 2:
			return mat.getStock();
		case 3:
			return mat.getEstacion();
		case 4:
			return mat.getDescripcion();
		default:
			return null;
		}
	}
}
