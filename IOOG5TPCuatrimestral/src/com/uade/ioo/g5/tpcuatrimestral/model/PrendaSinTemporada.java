package com.uade.ioo.g5.tpcuatrimestral.model;


public class PrendaSinTemporada extends Prenda {

	@Override
	public float getPrecioVenta() {
		float precio = 0;
		for (int i = 0; i < materiales.size(); i++) {
			precio += materiales.get(i).getCosto();
		}
		return precio;
	}

}
