package pedido;

import java.util.Map;
import java.util.Scanner;
import Autopartes.Autoparte;

public class Venta {
	
	Pedido pedido;
	String medioPago;
	double montoInicial;
	double montoFinal;
	Scanner sc = new Scanner(System.in);
	
	
	public void registrarVenta(Pedido p){
		//Calcular el precio sumando todas las autopartes del pedido
		//Obtenemos la lista de autopartes del pedido
		Map <Autoparte, Integer>autopartes = p.getAutopartes();
		//Rcorremos las autopartes y acumulamos el precio de todas
		
		for (Map.Entry<Autoparte, Integer> entry : autopartes.entrySet()) {
            montoInicial = (entry.getKey().getPrecioUnitario() * entry.getValue()) + montoInicial;
         }
		System.out.println();
		System.out.println("EL PRECIO INICIAL ES: "+montoInicial);
		System.out.println("Ingrese el medio de pago.");
		System.out.println("[1] para pagar en efectivo.");
		System.out.println("[2] para pagar con débito.");
		System.out.println("[3] para pagar con crédito.");
		int opcion = sc.nextInt();
		switch(opcion) {
		case 1:
			PagoEfectivo pagoef = new PagoEfectivo();
			montoFinal = pagoef.montoFinal(montoInicial);
			break;
		
		case 2:
			PagoDebito pagodeb = new PagoDebito();
			montoFinal = pagodeb.montoFinal(montoInicial);
			break;
			
		case 3:
			PagoTarjeta pagotar = new PagoTarjeta();
			montoFinal = pagotar.montoFinal(montoInicial);
			break;
		}
		System.out.println("EL MONTO FINAL A ABONAR ES: "+montoFinal);
		System.out.println("Confirmar venta (s/n)");
		String conf = sc.next();
		if (conf.equals("s")){
			System.out.println("Venta realizada.");
		}
		else {
			System.out.println("Venta cancelada");
		}
		System.out.println("Ingresa cualquier tecla para continuar");
		sc.next();
		return;
	}
}
