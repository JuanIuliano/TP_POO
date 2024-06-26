package Autopartes;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import empresa.Empresa;
import empresa.Tutta;
 
public class Catalogo {
	//Creamos objeto de la clase Scanner
	Scanner scanner = new Scanner(System.in);
	//Lista que contiene las instancias de la clase Autoparte
	public ArrayList <Autoparte> catalogo;
	private ArrayList <Autoparte> autopartesStockMin;
	int inicializado;

	
	public void inicializarCatalogo() {
		catalogo = new ArrayList<>();
		autopartesStockMin = new ArrayList<>();
		inicializado = 1;
	}
	
	public void setCatalogo(ArrayList<Autoparte> catalogo) {
		this.catalogo = catalogo;
	}
	
	public boolean autoparteExistente(int id) {
		for(int i=0; i<catalogo.size(); i++) {
			if (catalogo.get(i).getId() == id) { //Si la autoparte está en la lista
				return true; //devolvemos true
			}
		}
		return false; //si no encontramos la autoparte en la lista, devovlemos false
	}
	
	public Autoparte devolverAutoparte(int id) {
		for(int i=0; i<catalogo.size(); i++) {
			if (catalogo.get(i).getId() == id) { //Si la autoparte está en la lista
				return catalogo.get(i);
			}
		}
		return null;
	}
	
	public void listarCatalogo() {
		if (this.inicializado != 1) {
			System.out.println("Error, el catálogo no está inicializado.");
			return;
		}
		for (int i=0; i<catalogo.size(); i++) {
			Autoparte autoparte = catalogo.get(i);
			autoparte.mostrarAutoparte();
			if (autoparte.getStock()==autoparte.getStockMinimo()) {
				if (!autopartesStockMin.contains(autoparte)) {
					autopartesStockMin.add(autoparte);
					}
				}
	
			}
		}
	
	
	public void cargarAutoparte(Autoparte autoparte) {
		//Si estamos agregando una autoparte ya creada:
		if (autoparte != null){
			//la cargamos directamente
			catalogo.add(autoparte);
		}
		
		//Si no recibimos una autoparte ya creada como paràmetro:
		else {
				//Primero creamos la autoparte
				System.out.println("Cargando autoparte..");
				System.out.println("Para cancelar la carga, ingresa -1 en cualquiera de los campos.");
				System.out.println();
				
				System.out.println("Ingresá el ID");
				//scanner.nextLine(); //Esto es para limpiar el buffer
				int id = -100;
				while(true) {
					try {
						id = scanner.nextInt();
						if(id==-1) {
							System.out.println("Carga cancelada.");
							return;
						}
						break;
					}catch(InputMismatchException e) {
						System.out.println("Error, por favor ingrese un número entero.");
						scanner.next();
					}
				}
				
				// Ciclo para validar que el ID no este ocupado
				while (autoparteExistente(id)==true) {
					System.out.println("La autoparte ya existe y esta cargada en el catálogo. Ingrese otra");
					while(true) {
						try {
						scanner.nextLine(); //Esto es para limpiar el buffer
						id = scanner.nextInt();
						break;
						}catch(InputMismatchException e) {
							System.out.println("Error, por favor ingrese un número entero");
							scanner.next();
						}
					}
					if(id==-1) {
						System.out.println("Carga cancelada.");
						return;
					}
				}
				System.out.println("Ingresá la denominación");
				scanner.nextLine(); //Esto es para limpiar el buffer
				String denominacion = scanner.nextLine();
				if(denominacion.equals("-1")) {
					System.out.println("Carga cancelada.");
					return;
				}
				System.out.println("Ingresá la descripcion");
				//scanner.nextLine(); //Esto es para limpiar el buffer
				String descripcion = scanner.nextLine();
				if(descripcion.equals("-1")) {
					System.out.println("Carga cancelada.");
					return;
				}
				System.out.println("Ingresá la categoría");
				//scanner.nextLine(); //Esto es para limpiar el buffer
				String categoria = scanner.nextLine();
				if(categoria.equals("-1")) {
					System.out.println("Carga cancelada.");
					return;
				}
				System.out.println("Ingresá la marca");
				//scanner.nextLine(); //Esto es para limpiar el buffer
				String marca = scanner.nextLine();
				if(marca.equals("-1")) {
					System.out.println("Carga cancelada.");
					return;
				}
				System.out.println("Ingresá el vehiculo");
				//scanner.nextLine(); //Esto es para limpiar el buffer
				String vehiculo = scanner.nextLine();
				if(vehiculo.equals("-1")) {
					System.out.println("Carga cancelada.");
					return;
				}
				System.out.println("Ingresá el modelo");
				//scanner.nextLine(); //Esto es para limpiar el buffer
				String modelo = scanner.nextLine();
				if(modelo.equals("-1")) {
					System.out.println("Carga cancelada.");
					return;
				}
				System.out.println("Ingresá el precio unitario");
				double precioUnitario;
				//scanner.nextLine(); //Esto es para limpiar el buffer
				while(true) {
					try {
						precioUnitario = scanner.nextDouble();
						break;
					}catch(InputMismatchException e) {
						System.out.println("Error, por favor ingrese un número entero.");
						scanner.next();
					}
				}
				if(precioUnitario == -1) {
					System.out.println("Carga cancelada.");
					return;
				}
				System.out.println("Ingresá el enlace");
				scanner.nextLine(); //Esto es para limpiar el buffer
				String enlace = scanner.nextLine();
				if(enlace.equals("-1")) {
					System.out.println("Carga cancelada.");
					return;
				}
				System.out.println("Ingresá el stock mínimo");
				int stockMinimo;
				//scanner.nextLine(); //Esto es para limpiar el buffer
				while(true) {
					try {
						stockMinimo = scanner.nextInt();
						break;
					}catch(InputMismatchException e) {
						System.out.println("Error, por favor ingrese un número entero.");
						scanner.next();
					}
				}
				if(stockMinimo == -1) {
					System.out.println("Carga cancelada.");
					return;
				}
				System.out.println("Ingresá el stock");
				int stock;
				scanner.nextLine(); //Esto es para limpiar el buffer
				while(true) {
					try {
						stock = scanner.nextInt();
						break;
					}catch(InputMismatchException e) {
						System.out.println("Error, por favor ingrese un número entero.");
						scanner.next();
					}
				}
				if(stock == -1) {
					System.out.println("Carga cancelada.");
					return;
				}
				else { // validacion de stock
					while(stock<stockMinimo) {
						System.out.println("Error, el stock no puede ser menor al stock mìnimo. Ingrese el stock de nuevo");
						while(true) {
							try {
								stock = scanner.nextInt();
								break;
							}catch(InputMismatchException e) {
								System.out.println("Error, por favor ingrese un número entero.");
								scanner.next();
							}
						}
					}
				}
				
				//Creamos el objeto con todos estos datos y la mostramos al cliente
				Autoparte autoparteNueva = new Autoparte(id, denominacion, descripcion, categoria, marca, vehiculo, modelo, precioUnitario, enlace, stock, stockMinimo);
				System.out.println("Asì quedò tu autoparte.");
				autoparteNueva.mostrarAutoparte();
				
				//Confirmar carga
				System.out.print("Confirmar carga: (s/n): ");
				String confirmacion = scanner.next();
				System.out.println();
				
				if (confirmacion.equals("s")) {
				//Lo cargamos
				catalogo.add(autoparteNueva);
				System.out.println("La autoparte fue cargada con èxito");
				System.out.println();
				}
				else {
					System.out.println("Carga cancelada.");
					System.out.println();
				}
		}
	}
	
	public void eliminarAutoparte(int id) {
		//vemos si la autoparte está
		if (autoparteExistente(id) == false) {
			System.out.println("La autoparte que estás intentando eliminar no existe.");
		}
		else {
			//Buscamos el indice de la autoparte
			for (int i=0; i<catalogo.size(); i++) {
				if(catalogo.get(i).getId() == id) {
					//Obtenemos la denominaciòn de la autoparte una vez que ya tenemos su ID
					Autoparte ap = catalogo.get(i);
					String autoparteDenom =  ap.getDenominacion();
					//una vez encontrada, simplemente la eliminamos
					catalogo.remove(ap);
					//Eliminamos el objeto Autoparte
					ap.borrarAutoparte(ap);
					System.out.println("La autoparte "+autoparteDenom+" fue borrada con èxito");
				}
			}
		}
	}
	
	public void modificarAutoparte(int id) {
		//Vemos que la autoparte exista
		if(autoparteExistente(id) == false) {
			System.out.println("La autoparte que queres modificar no existe.");
		}
		else {
			Autoparte autoparte = null;
			//si existe, la buscamos en la lista
			for(int i=0; i<catalogo.size(); i++) {
				autoparte = catalogo.get(i);
				if (autoparte.getId() == id) {
					break;
				}
			}
			
			//Una vez que encontramos la autoparte a modificar, llamamos a la funcion para hacerlo
			if(autoparte == null) {
				System.out.println("Hubo un error y no se encontro el indice de la autoparte en la lista.");
			}
			else {
				autoparte.modificarDatos();
			}
		}
	}
	
	public void restarStock(int id, int cantidad) {
		if(autoparteExistente(id) == false) {
			System.out.println("La autoparte que queres modificar no existe.");
		}
		else {
			for (Autoparte autoparte : catalogo) {
	            if (autoparte.getId() == id) {
	            	autoparte.restarStock(autoparte, cantidad);
	                break;
	            }
	        }
			
		}
	}
	
	public void sumarStock(int id, int cantidad) {
		if(autoparteExistente(id) == false) {
			System.out.println("La autoparte que queres modificar no existe.");
		}
		else {
			//si existe, la buscamos en la lista
			for (Autoparte autoparte2 : catalogo) {
	            if (autoparte2.getId() == id) {
	            	autoparte2.sumarStock(autoparte2, cantidad);
	                break;
	            }
	        }
			
		}
	}
	
	public ArrayList<Autoparte> getCatalogo(){
		return this.catalogo;
	}
	
}
 