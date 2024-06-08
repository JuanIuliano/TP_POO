package pedido;

public class PagoTarjeta extends Pago {
	int numTarjeta;
	int cuotas;
	double recargo;
	
	
	@Override
	public double montoFinal(double montoInicial) {
		//si el valor de recargo es mayor a 1 y menor a 2
		System.out.println( cuotas + " cuotas de: " + montoInicial * recargo / cuotas );
		return montoInicial * recargo;
		//si el valor de recargo es porcentual
		//return montoInicial * (100 + recargo) / 100;
	}
}
