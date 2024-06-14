package pedido;

public class PagoDebito extends Pago {
	@Override
	//Pagó con débito no tiene ni descuento ni recargo
	//Simplemente retornamos el valor inical que recibimos como parámetro
	public double montoFinal(double montoInicial) {
		return montoInicial;
	}

}
