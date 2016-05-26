package com.uade.ioo.g5.tpcuatrimestral.model.enums;

/**
 * 
 */
public enum Estacion {
	VERANO, OTONIO, INVIERNO, PRIMAVERA;

	public static Estacion getEstacion(String estacion) {
		if ("VERANO".equals(estacion)) {
			return VERANO;
		}
		if ("OTOÑO".equals(estacion)) {
			return OTONIO;
		}
		if ("INVIERNO".equals(estacion)) {
			return INVIERNO;
		}
		if ("PRIMAVERA".equals(estacion)) {
			return PRIMAVERA;
		}
		return null;
	}

	public String toString() {
		if (this == VERANO) {
			return "VERANO";
		}
		if (this == OTONIO) {
			return "OTOÑO";
		}
		if (this == INVIERNO) {
			return "INVIERNO";
		}
		if (this == PRIMAVERA) {
			return "PRIMAVERA";
		}
		return null;
	}
}