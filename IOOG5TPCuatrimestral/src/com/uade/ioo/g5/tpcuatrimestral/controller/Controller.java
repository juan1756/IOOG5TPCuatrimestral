package com.uade.ioo.g5.tpcuatrimestral.controller;

import java.util.ArrayList;

import com.uade.ioo.g5.tpcuatrimestral.model.Factura;
import com.uade.ioo.g5.tpcuatrimestral.model.Material;
import com.uade.ioo.g5.tpcuatrimestral.model.OrdenCompra;
import com.uade.ioo.g5.tpcuatrimestral.model.Prenda;
import com.uade.ioo.g5.tpcuatrimestral.model.PrendaSinTemporada;
import com.uade.ioo.g5.tpcuatrimestral.model.PrendaTemporada;
import com.uade.ioo.g5.tpcuatrimestral.model.Proveedor;
import com.uade.ioo.g5.tpcuatrimestral.model.enums.Estacion;
import com.uade.ioo.g5.tpcuatrimestral.views.ItemMaterialView;
import com.uade.ioo.g5.tpcuatrimestral.views.MaterialView;
import com.uade.ioo.g5.tpcuatrimestral.views.OrdenCompraView;
import com.uade.ioo.g5.tpcuatrimestral.views.PrendaView;
import com.uade.ioo.g5.tpcuatrimestral.views.ProveedorView;

/**
 * 
 */
public class Controller {

	/**
     * 
     */
	private ArrayList<OrdenCompra> ordenesDeCompra;

	/**
	 * 
	 */
	private ArrayList<Proveedor> proveedores;

	/**
     * 
     */
	private ArrayList<Factura> facturas;

	/**
     * 
     */
	private ArrayList<Prenda> prendas;

	/**
	 * 
	 */
	private ArrayList<Material> materiales;

	/**
	 * 
	 */
	private Prenda prendaActual;

	/**
	 * 
	 */
	private Factura facturaActual;

	/**
	 * 
	 */
	private int ultimoCodigoPrenda;

	/**
	 * 
	 */
	private int ultimoCodigoMaterial;

	/**
	 * 
	 */
	private int ultimoCodigoOrdenCompra;

	/**
     * 
     */
	private Controller() {
		ordenesDeCompra = new ArrayList<OrdenCompra>();
		proveedores = new ArrayList<Proveedor>();
		facturas = new ArrayList<Factura>();
		prendas = new ArrayList<Prenda>();
		materiales = new ArrayList<Material>();

		/**
		 * OBJETOS DUMMY
		 */
		materiales.add(new Material(0, "Lana fina", "DESCRIP1", 1, 10, 10));
		materiales.add(new Material(1, "SEDA", "DESCRIP2", 1, 10, 10));
		materiales.add(new Material(2, "Lana Gruesa", "DESCRIP3", 5, 4, 10));
		proveedores.add(new Proveedor(38444826, "Juan Jose", (ArrayList<Material>) materiales.clone()));
		PrendaTemporada prendaTemporada = new PrendaTemporada();
		prendaTemporada.setCodigo(0);
		prendaTemporada.setDescripcion("asddd");
		prendaTemporada.setNombre("TOGA DE HILO AZUR");
		prendaTemporada.setStock(1);
		prendaTemporada.addItemMaterial(materiales.get(0), 10);
		prendaTemporada.addItemMaterial(materiales.get(2), 2);
		prendaTemporada.setEstacion(Estacion.OTONIO);
		prendas.add(prendaTemporada);
		/**
		 * FIN OBJETOS DUMMY
		 */
	}

	private static Controller controller;

	public static Controller getInstance() {
		if (controller == null) {
			controller = new Controller();
		}
		return controller;
	}

	/**
	 * Inicia la creación de una prenda
	 */
	public void iniciarCreacionPrenda() {
		prendaActual = new PrendaSinTemporada();
	}

	/**
	 * Inicia la creación de una prenda con Temporada
	 * 
	 */
	public void iniciarCreacionPrendaTemporada() {
		prendaActual = new PrendaTemporada();
	}

	public PrendaView iniciarModificacionPrenda(int codigo) {
		Prenda p = buscarPrenda(codigo);
		if (p != null) {
			return p.getView();
		}
		return null;
	}

	public boolean agregarPrenda(String nombre, String descripcion, int stock) {
		Prenda p = buscarPrenda(nombre);
		if (p == null) {
			// Stock en cero por ahora
			// Generar codigo
			prendaActual.setCodigo(++ultimoCodigoPrenda);
			prendaActual.setNombre(nombre);
			prendaActual.setDescripcion(descripcion);
			prendaActual.setStock(stock);
			return prendas.add(prendaActual);
		}
		return false;
	}

	public boolean agregarPrendaTemporada(String nombre, String descripcion, int stock, String estacion) {
		Prenda p = buscarPrenda(nombre);
		if (p == null) {
			// Stock en cero por ahora
			// Generar codigo
			prendaActual.setCodigo(++ultimoCodigoPrenda);
			prendaActual.setNombre(nombre);
			prendaActual.setDescripcion(descripcion);
			prendaActual.setStock(stock);
			if (prendaActual instanceof PrendaTemporada) {
				((PrendaTemporada) prendaActual).setEstacion(Estacion.getEstacion(estacion));
			}
			return prendas.add(prendaActual);
		}
		return false;
	}

	public boolean modificarPrenda(int codigo, String nombre, String descripcion, ArrayList<ItemMaterialView> materiales) {
		prendaActual = buscarPrenda(codigo);
		if (prendaActual != null && prendaActual instanceof PrendaSinTemporada) {
			prendaActual.setNombre(nombre);
			prendaActual.setDescripcion(descripcion);
			prendaActual.clearMateriales();
			for (ItemMaterialView item : materiales) {
				agregarItemMaterial(item.getCodigo(), item.getCantidad());
			}
			return true;
		}
		return false;
	}

	public boolean modificarPrendaTemporada(int codigo, String nombre, String descripcion, ArrayList<ItemMaterialView> materiales, String estacion) {
		prendaActual = buscarPrenda(codigo);
		if (prendaActual != null && prendaActual instanceof PrendaTemporada) {
			prendaActual.setNombre(nombre);
			prendaActual.setDescripcion(descripcion);
			((PrendaTemporada) prendaActual).setEstacion(Estacion.getEstacion(estacion));
			prendaActual.clearMateriales();
			for (ItemMaterialView item : materiales) {
				agregarItemMaterial(item.getCodigo(), item.getCantidad());
			}
			return true;
		}
		return false;
	}

	public boolean borrarPrenda(int codigo) {
		Prenda p = buscarPrenda(codigo);
		if (p != null) {
			return prendas.remove(p);
		}
		return false;
	}

	public boolean agregarItemMaterial(int codigo, int cantidad) {
		Material material = buscarMaterial(codigo);
		if (material != null) {
			return prendaActual.addItemMaterial(material, cantidad);
		}
		return false;
	}

	public boolean borrarItemMaterial(int codigo) {
		Material material = buscarMaterial(codigo);
		if (material != null) {
			return prendaActual.removeMaterial(material);
		}
		return false;
	}

	public boolean modificarItemMaterial(int codigo, int cantidad) {
		Material material = buscarMaterial(codigo);
		if (material != null) {
			if (material != null) {
				prendaActual.modificarItemMaterial(material, cantidad);
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param codigo
	 * @param nombre
	 * @param precio
	 * @param stock
	 * @param puntoDePedido
	 * @param dniProveedor
	 * @return
	 */
	public boolean agregarMaterial(String nombre, String descripcion, float precio, int stock, int puntoDePedido, int dniProveedor) {

		Proveedor p = buscarProveedor(dniProveedor);
		if (p == null) {
			return false;
		}

		Material m = buscarMaterial(nombre);
		if (m == null) {
			m = new Material(++this.ultimoCodigoMaterial, nombre, descripcion, stock, puntoDePedido, precio);
			p.agregarMaterial(m);
			materiales.add(m);
			return true;
		}
		return false;
	}

	/**
	 * Inicia una Venta
	 */
	public void iniciarVenta() {
		facturaActual = new Factura();
	}

	public boolean agregarItemFactura(int codigo, int cantidad) {
		Prenda p = buscarPrenda(codigo);
		if (p != null) {
			if (!facturaActual.existe(p)) {
				return facturaActual.agregarItem(p, cantidad);
			}
		}
		return false;
	}

	public boolean borrarItemFactura(int codigo) {
		Prenda p = buscarPrenda(codigo);
		if (p != null) {
			return facturaActual.borrarItem(p);
		}
		return false;
	}

	public boolean cerrarVenta() {
		facturaActual.calcularImporte();
		facturaActual.restarStock();
		return facturas.add(facturaActual);
	}

	public ArrayList<OrdenCompraView> generarOrdenesDeCompra() {
		ArrayList<OrdenCompra> ordenesGeneradas = new ArrayList<OrdenCompra>();
		ArrayList<OrdenCompraView> ordenesView = new ArrayList<OrdenCompraView>();
		for (Proveedor proveedor : proveedores) {
			OrdenCompra ordenActual = new OrdenCompra(++ultimoCodigoOrdenCompra, proveedor);
			if (ordenActual.haveItems()) {
				ordenesGeneradas.add(ordenActual);
			}
		}
		if (ordenesGeneradas.size() > 0) {
			this.ordenesDeCompra.addAll(ordenesGeneradas);
			for (OrdenCompra orden : ordenesGeneradas) {
				ordenesView.add(orden.getOrdenCompraView());
			}
		}
		return ordenesView;
	}

	private Prenda buscarPrenda(int codigo) {
		int pos = 0;
		Prenda prenda = null;
		while (pos < prendas.size() && prenda == null) {
			if (prendas.get(pos).isCodigo(codigo)) {
				prenda = prendas.get(pos);
			}
			pos++;
		}
		return prenda;
	}

	private Prenda buscarPrenda(String nombre) {
		int pos = 0;
		Prenda prenda = null;
		if (nombre != null) {
			while (pos < prendas.size() && prenda == null) {
				if (nombre.equals(prendas.get(pos).getNombre())) {
					prenda = prendas.get(pos);
				}
				pos++;
			}
		}
		return prenda;
	}

	private Material buscarMaterial(int codigo) {
		int pos = 0;
		Material m = null;
		while (pos < materiales.size() && m == null) {
			if (materiales.get(pos).isCodigo(codigo)) {
				m = materiales.get(pos);
			}
			pos++;
		}
		return m;
	}

	private Material buscarMaterial(String nombre) {
		int pos = 0;
		Material m = null;
		if (nombre != null) {
			while (pos < materiales.size() && m == null) {
				if (nombre.equals(materiales.get(pos).getNombre())) {
					m = materiales.get(pos);
				}
				pos++;
			}
		}
		return m;
	}

	private Proveedor buscarProveedor(int dni) {
		int pos = 0;
		Proveedor p = null;
		while (pos < proveedores.size() && p == null) {
			if (proveedores.get(pos).isDni(dni)) {
				p = proveedores.get(pos);
			}
			pos++;
		}
		return p;
	}

	public ArrayList<PrendaView> getPrendas() {
		ArrayList<PrendaView> prendasView = new ArrayList<PrendaView>();
		for (int i = 0; i < prendas.size(); i++) {
			prendasView.add(prendas.get(i).getView());
		}
		return prendasView;
	}

	public ArrayList<MaterialView> getMateriales() {
		ArrayList<MaterialView> materialesView = new ArrayList<MaterialView>();
		for (int i = 0; i < materiales.size(); i++) {
			materialesView.add(materiales.get(i).getView());
		}
		return materialesView;
	}

	public ArrayList<ProveedorView> getProveedores() {
		ArrayList<ProveedorView> proveedoresView = new ArrayList<ProveedorView>();
		for (int i = 0; i < proveedores.size(); i++) {
			proveedoresView.add(proveedores.get(i).getView());
		}
		return proveedoresView;
	}

}