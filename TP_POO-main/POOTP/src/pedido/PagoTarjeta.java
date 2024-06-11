package pedido;

public class PagoTarjeta extends Pago {
	int numTarjeta;
	int cuotas;
	double recargo;
	
	
	@Override
	public double montoFinal(double montoInicial) {
		if(cuotas == 2) {
			recargo = 6;
		} else if(cuotas == 3) {
			recargo = 12;
		} else if (cuotas == 6) {
			recargo = 20;
		}
		//si el valor de recargo es porcentual
		System.out.println( cuotas + " cuotas de: " + montoInicial * (100 + recargo) / 100 / cuotas );
		return montoInicial * (100 + recargo) / 100;
	}
}
