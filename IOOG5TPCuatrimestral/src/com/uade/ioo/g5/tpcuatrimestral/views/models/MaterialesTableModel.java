package com.uade.ioo.g5.tpcuatrimestral.views.models;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.uade.ioo.g5.tpcuatrimestral.views.MaterialView;

public class MaterialesTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<String> columnNames;

	private ArrayList<MaterialView> materiales;

	public MaterialesTableModel() {
		materiales = new ArrayList<MaterialView>();
		this.columnNames = new ArrayList<String>();
	}

	/**
	 * @param columnNames
	 */
	public MaterialesTableModel(ArrayList<String> columnNames) {
		this();
		this.columnNames = columnNames;

	}

	/**
	 * @param columnNames
	 * @param materiales
	 */
	public MaterialesTableModel(ArrayList<String> columnNames, ArrayList<MaterialView> materiales) {
		super();
		this.columnNames = columnNames;
		this.materiales = materiales;
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
		return materiales.size();
	}

	public void addRow(MaterialView material) {
		materiales.add(material);
		fireTableRowsInserted(materiales.size() - 1, materiales.size() - 1);
	}

	public void setRow(MaterialView material, int index) {
		materiales.set(index, material);
		fireTableRowsUpdated(index, index);
	}

	public MaterialView getItemRow(int row) {
		return materiales.get(row);
	}

	public void removeRow(int row) {
		materiales.remove(row);
		fireTableRowsDeleted(row, row);
	}

	@Override
	public Object getValueAt(int row, int col) {
		MaterialView mat = materiales.get(row);
		switch (col) {
		case 0:
			return mat.getCodigo();
		case 1:
			return mat.getNombre();
		case 2:
			return mat.getStock();
		case 3:
			return mat.getCosto();
		case 4:
			return mat.getDescripcion();
		default:
			return null;
		}
	}
}
