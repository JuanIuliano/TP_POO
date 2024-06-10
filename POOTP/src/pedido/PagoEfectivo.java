package pedido;

public class PagoEfectivo extends Pago{
	double descuento;

	@Override
	public double montoFinal(double montoInicial) {
		//si el valor de descuento es porcentual
		return montoInicial * (100 - descuento) / 100;
		
	}
	
	
}
