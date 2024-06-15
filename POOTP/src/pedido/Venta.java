package pedido;

import java.io.Serializable;
import java.util.Map;
import java.util.Scanner;
import Autopartes.Autoparte;

public class Venta implements Serializable{
	private static final long serialVersionUID = 1L;
	Pedido pedido;
	String medioPago;
	double montoInicial;
	double montoFinal;
	
	
	
	
	public Venta(Pedido pedido, String medioPago, double montoInicial, double montoFinal) {
		super();
		this.pedido = pedido;
		this.medioPago = medioPago;
		this.montoInicial = montoInicial;
		this.montoFinal = montoFinal;
	}




	public Venta() {}




	public Pedido getPedido() {
		return pedido;
	}




	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}




	public String getMedioPago() {
		return medioPago;
	}




	public void setMedioPago(String medioPago) {
		this.medioPago = medioPago;
	}




	public double getMontoInicial() {
		return montoInicial;
	}




	public void setMontoInicial(double montoInicial) {
		this.montoInicial = montoInicial;
	}




	public double getMontoFinal() {
		return montoFinal;
	}




	public void setMontoFinal(double montoFinal) {
		this.montoFinal = montoFinal;
	}




	public boolean registrarVenta(Pedido p){
		Scanner sc = new Scanner(System.in);
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
			medioPago = "efectivo";
			break;
		
		case 2:
			PagoDebito pagodeb = new PagoDebito();
			montoFinal = pagodeb.montoFinal(montoInicial);
			medioPago = "Débito";
			break;
			
		case 3:
			PagoTarjeta pagotar = new PagoTarjeta();
			montoFinal = pagotar.montoFinal(montoInicial);
			medioPago = "Crédito";
			break;
		}
		System.out.println("EL MONTO FINAL A ABONAR ES: "+montoFinal);
		System.out.println("[s] Confirmar venta");
		System.out.println("Cualquier tecla para cancelar");
		String conf = sc.next();
		boolean realizada = false;
		if (conf.equals("s")){
			Venta v = new Venta(p, medioPago, montoInicial, montoFinal);
			System.out.println("Venta realizada.");
			System.out.println();
			System.out.println("Generando recibo..");
			Recibo r = new Recibo();
			r.generarRecibo(v);
			realizada = true;
		}
		else {
			System.out.println("Venta cancelada");
		}
		System.out.println("Ingresa cualquier tecla para continuar");
		sc.next();
		return realizada;
	}
}
