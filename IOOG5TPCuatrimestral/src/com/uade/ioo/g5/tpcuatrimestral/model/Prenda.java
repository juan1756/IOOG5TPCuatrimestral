package com.uade.ioo.g5.tpcuatrimestral.model;

import java.util.ArrayList;

import com.uade.ioo.g5.tpcuatrimestral.views.ItemMaterialView;
import com.uade.ioo.g5.tpcuatrimestral.views.PrendaView;

/**
 * 
 */
public abstract class Prenda {

	/**
     * 
     */
	private int codigo;

	/**
     * 
     */
	private String nombre;

	/**
	 * 
	 */
	private String descripcion;

	/**
     * 
     */
	private int stock;

	/**
	 * 
	 */
	protected ArrayList<ItemMaterial> materiales;

	/**
     * 
     */
	public Prenda() {
		materiales = new ArrayList<ItemMaterial>();
	}

	/**
	 * @return
	 */
	public abstract float getPrecioVenta();

	public boolean addItemMaterial(Material material, int cantidad) {
		ItemMaterial item = obtenerItem(material);
		if (item == null) {
			return this.materiales.add(new ItemMaterial(material, cantidad));
		}
		return false;
	}

	public boolean removeMaterial(Material material) {
		ItemMaterial item = obtenerItem(material);
		if (item != null) {
			return materiales.remove(item);
		}
		return false;
	}

	public void modificarItemMaterial(Material material, int cantidad) {
		ItemMaterial item = obtenerItem(material);
		if (item != null) {
			item.setCantidad(cantidad);
		}
	}

	private ItemMaterial obtenerItem(Material material) {
		ItemMaterial item = null;
		int pos = 0;
		while (pos < materiales.size() && item == null) {
			if (materiales.get(pos).isMaterial(material)) {
				item = materiales.get(pos);
			}
			pos++;
		}

		return item;
	}

	/**
	 * @return true if codigo matches
	 */
	public boolean isCodigo(int codigo) {
		return this.codigo == codigo;
	}

	/**
	 * @return the materiales
	 */
	public ArrayList<ItemMaterialView> getMateriales() {
		ArrayList<ItemMaterialView> views = new ArrayList<ItemMaterialView>();
		for (int i = 0; i < materiales.size(); i++) {
			ItemMaterial item = materiales.get(i);
			views.add(item.getView());
		}
		return views;
	}

	public void clearMateriales() {
		materiales.clear();
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public boolean restarStock(int cant) {
		if (cant <= stock) {
			this.stock = stock - cant;
			return true;
		}
		return false;
	}

	public PrendaView getView() {
		String estacion = null;
		if (this instanceof PrendaTemporada) {
			estacion = ((PrendaTemporada) this).getEstacion().toString();
		}
		return new PrendaView(codigo, nombre, descripcion, stock, getMateriales(), estacion);
	}
}