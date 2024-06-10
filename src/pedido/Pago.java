package pedido;

public abstract class Pago {
	double montoInicial;
	
	public abstract double montoFinal(double montoInicial);
}
