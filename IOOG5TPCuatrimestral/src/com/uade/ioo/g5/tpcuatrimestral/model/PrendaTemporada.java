package com.uade.ioo.g5.tpcuatrimestral.model;

import com.uade.ioo.g5.tpcuatrimestral.model.enums.Estacion;

/**
 * 
 */
public class PrendaTemporada extends Prenda {

	/**
     * 
     */
	private Estacion estacion;

	/**
	 * @return
	 */
	@Override
	public float getPrecioVenta() {
		float precio = 0;
		for (int i = 0; i < materiales.size(); i++) {
			precio += materiales.get(i).getCosto();
		}
		return precio / 100 * 115;
	}

	public void setEstacion(Estacion estacion) {
		this.estacion = estacion;
	}

	public Estacion getEstacion() {
		return estacion;
	}
}