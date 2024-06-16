package pedido;

import java.util.Scanner;

public class PagoTarjeta extends Pago {
	int numTarjeta;
	int cuotas;
	double recargo;
	Scanner scanner = new Scanner(System.in);
	
	
	@Override
	public double montoFinal(double montoInicial) {
		//Solicitamos la cantidad de cuotas a abonar
		System.out.println("Ingrese la cantidad de cuotas que desea (La cantidad de cuotas pueden ser 2, 3 o 6), cada una con su respectivo recargo. ");
		cuotas = scanner.nextInt();
		
		//Válidamos que las cuotas ingresadas sean correctas
		while(cuotas != 2 && cuotas != 3 && cuotas != 6) {
			System.out.println("Error, esa cantidad de cuotas no está disponible. Ingrese de nuevo");
			cuotas = scanner.nextInt();
		}
		
		//Dependiendo las cuotas asignamos un recargo distinto
		if(cuotas == 2) {
			recargo = 6;
		} else if(cuotas == 3) {
			recargo = 12;
		} else if (cuotas == 6) {
			recargo = 20;
		}
		//calculamos el precio final --> precio inicial + recargo (nro de cuotas) lo mostramos y lo retornamos.
		System.out.println( cuotas + " cuotas de: " + montoInicial * (100 + recargo) / 100 / cuotas );
		return montoInicial * (100 + recargo) / 100;
	}
}
