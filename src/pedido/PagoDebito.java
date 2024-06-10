package pedido;

public class PagoDebito extends Pago {
	@Override
	public double montoFinal(double montoInicial) {
		return montoInicial;
	}

}
