package pedido;

public class PagoEfectivo extends Pago{
	double descuento;

	@Override
	public double montoFinal(double montoInicial) {
		//si el valor de descuento es menor a 1
		return montoInicial * descuento;
		//si el valor de descuento es porcentual
		//return montoInicial * descuento / 100;
		
	}
	
	
}
