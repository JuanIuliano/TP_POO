package Autopartes;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;
 
public class Autoparte implements AutoparteInterface, Serializable{
	//-------------ATRIBUTOS-------------//
	private static final long serialVersionUID = 1L;
	int id;
	String denominacion;
	String descripcion;
	String categoria;
	String marca;
	String vehiculo;
	String modelo;
	double precioUnitario;
	String enlace;
	int stock;
	int stockMinimo;
	
	
	//-----------------METODOS------------------//
	
	//Constructor
	public Autoparte(int id, String denominacion, String descripcion, String categoria, String marca, String vehiculo,
			String modelo, double precioUnitario, String enlace, int stock, int stockMinimo) {
		super();
		this.id = id;
		this.denominacion = denominacion;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.marca = marca;
		this.vehiculo = vehiculo;
		this.modelo = modelo;
		this.precioUnitario = precioUnitario;
		this.enlace = enlace;
		this.stock = stock;
		this.stockMinimo = stockMinimo;
	}
	
	
 
	//Getters y setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDenominacion() {
		return denominacion;
	}
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(String vehiculo) {
		this.vehiculo = vehiculo;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public double getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public String getEnlace() {
		return enlace;
	}
	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getStockMinimo() {
		return stockMinimo;
	}
	public void setStockMinimo(int stockMinimo) {
		this.stockMinimo = stockMinimo;
	}
	
	
	//Metodos
	
	//Método que sirve para cambiar los datos de un objeto autoparte
	public void modificarDatos() {
		int opcion = 0;
		//Crear objeto de la clase Scanner
		Scanner scanner = new Scanner(System.in);
		//Menú
				System.out.println("-------------------------------");
				System.out.println("Menú de la autoparte: "+this.denominacion);
				System.out.println();
				System.out.println("[1] Modificar datos");
				System.out.println("[2] Aumentar stock");
				System.out.println("[3] Descontar stock");
				System.out.println("-------------------------------");
				System.out.println("Ingresá una opción: (-1 para cancelar)");
				while(true) {
					try {
						opcion = scanner.nextInt();
						if (opcion==-1) {
							System.out.println("Modificaciòn cancelada.");
							return;
						}
					//El ciclo sirve para validar que la opcion sea correcta. No deberia poder ingresar un numero fuera del rango 1-3, ni un string **Excepciones
						while(opcion<1 || opcion>3) {
							System.out.println("Error, ingrese una opción válida.");
							opcion = scanner.nextInt();
							//faltaria el manejador de excepciones por si se inresa algo != a int
							}
						break;
						
			}catch(InputMismatchException e) {
				System.out.println("Error, ingrese un número entero.");
				scanner.next();
			}
		}
		
		
	switch(opcion) { //Modificar datos
	case 1:
			//MODIFICAR DATOS
			//Menú
			opcion=0;
			System.out.println("-------------------------------");
			System.out.println("Cambiando autoparte");
			System.out.println();
			System.out.println("[1] Modificar ID");
			System.out.println("[2] Modificar denominación");
			System.out.println("[3] Modificar descripción");
			System.out.println("[4] Modificar categoria");
			System.out.println("[5] Modificar marca");
			System.out.println("[6] Modificar vehiculo");
			System.out.println("[7] Modificar modelo");
			System.out.println("[8] Modificar precio");
			System.out.println("[9] Modificar enlace");
			System.out.println("[10] Modificar stock mínimo");
			System.out.println("-------------------------------");
			System.out.println("Ingresá una opción: (-1 para cancelar)");
			while(true) {
				try {
					opcion = scanner.nextInt();
					if (opcion==-1) {
						System.out.println("Modificaciòn cancelada.");
						return;
					}
					
					//Validamos la opcion elegida **Excepciones
					while (opcion<1 || opcion>10) {
						System.out.println("Error, ingrese una opción válida.");
						opcion = scanner.nextInt();
					}
					break;
			}catch(InputMismatchException e) {
				System.out.println("Error, ingrese un número entero.");
				scanner.next();
			}
		}
		//Dependiendo de la opción elegida modificamos el dato
		switch(opcion){
			case 1: //Modificar ID
				int nuevaID = 0;
				System.out.println("Ingrese la nueva ID (-1 para cancelar)");
				while(true) {
					try {
						scanner.nextLine(); //Esto es para limpiar el buffer
						nuevaID = scanner.nextInt();
						if (nuevaID == -1) {
							System.out.println("Modificaciòn cancelada.");
							return;
						}
						break;
					}catch(InputMismatchException e) {
						System.out.println("Error, por favor ingrese un número entero.");
						scanner.next();
					}
				}
				//Asignamos la nueva ID
				this.id = nuevaID;
				break;
			
			case 2: //Modificar denominación
				System.out.println("Ingrese la nueva denominación (-1 para cancelar)");
				scanner.nextLine(); //Esto es para limpiar el buffer
				String nuevaDenom = scanner.nextLine();
				if (nuevaDenom.equals("-1")) {
					System.out.println("Modificaciòn cancelada.");
					return;
				}
				//Asignmaos la nueva denominación
				this.denominacion = nuevaDenom;
				break;
			
			case 3: //Modificar descripción
				System.out.println("Ingrese la nueva descripción (-1 para cancelar)");
				scanner.nextLine(); //Esto es para limpiar el buffer
				String nuevaDesc = scanner.nextLine();
				if (nuevaDesc.equals("-1")) {
					System.out.println("Modificaciòn cancelada.");
					return;
				}
				//Asignamos la nueva descripción
				this.descripcion = nuevaDesc;
				break;
			
			case 4: //Modificar categoría
				System.out.println("Ingrese la nueva categoría (-1 para cancelar)");
				scanner.nextLine(); //Esto es para limpiar el buffer
				String nuevaCat = scanner.nextLine();
				if (nuevaCat.equals("-1")) {
					System.out.println("Modificaciòn cancelada.");
					return;
				}
				//Asignamos la nueva categoría
				this.categoria = nuevaCat;
				break;
				
			case 5: //Modificar marca
				System.out.println("Ingrese la nueva marca (-1 para cancelar)");
				scanner.nextLine(); //Esto es para limpiar el buffer
				String nuevaMarca = scanner.nextLine();
				if (nuevaMarca.equals("-1")) {
					System.out.println("Modificaciòn cancelada.");
					return;
				}
				//Asignamos la nueva marca
				this.marca = nuevaMarca;
				break;
				
			case 6: //Modificar vehiculo
				System.out.println("Ingrese el nuevo vehículo (-1 para cancelar)");
				scanner.nextLine(); //Esto es para limpiar el buffer
				String nuevoVehiculo = scanner.nextLine();
				if (nuevoVehiculo.equals("-1")) {
					System.out.println("Modificaciòn cancelada.");
					return;
				}
				//Asignamos el nuevo vehiculo
				this.vehiculo = nuevoVehiculo;
				break;
				
			case 7: //Modificar modelo
				System.out.println("Ingrese el nuevo modelo (-1 para cancelar)");
				scanner.nextLine(); //Esto es para limpiar el buffer
				String nuevoModelo = scanner.nextLine();
				if (nuevoModelo.equals("-1")) {
					System.out.println("Modificaciòn cancelada.");
					return;
				}
				//Asignamos el nuevo modelo
				this.modelo = nuevoModelo;
				break;
			
			case 8: //Modificar precio
				double nuevoPrecio = 0;
				System.out.println("Ingrese el nuevo precio unitario (-1 para cancelar)");
				while(true) {
					try {
						scanner.nextLine(); //Esto es para limpiar el buffer
						nuevoPrecio = scanner.nextDouble(); // **Excepciones
						if (nuevoPrecio == -1) {
							System.out.println("Modificaciòn cancelada.");
							return;
						}
						break;
						}catch(InputMismatchException e) {
							System.out.println("Error, por favor ingrese un número entero.");
							scanner.next();
						}	
					}
				
				//Asignamos el nuevo precio
				this.precioUnitario = nuevoPrecio;
				break;
			
			case 9: //Modificar enlace
				System.out.println("Ingrese el nuevo enlace (-1 para cancelar)");
				scanner.nextLine(); //Esto es para limpiar el buffer
				String nuevoEnlace = scanner.nextLine();
				if (nuevoEnlace.equals("-1")) {
					System.out.println("Modificaciòn cancelada.");
					return;
				}
				//Asignamos el nuevo enlace
				this.enlace = nuevoEnlace;
				break;
			
			case 10: //Modificar stock mínimo
				int nuevoStockMin = 0;
				System.out.println("Ingrese el nuevo stock mínimo (-1 para cancelar)");
				while(true) {
					try {
						scanner.nextLine(); //Esto es para limpiar el buffer
						nuevoStockMin = scanner.nextInt();
						while (nuevoStockMin>this.stock) {
							if (nuevoStockMin == -1) {
								System.out.println("Modificaciòn cancelada.");
								return;
							}
							System.out.println("El stock mínimo no puede ser mayor que el stock disponible actualmente ("+this.stock+"). Ingrese de nuevo (-1 para cancelar)");
							nuevoStockMin = scanner.nextInt();
						}
						break;
					}catch(InputMismatchException e) {
						System.out.println("Error, por favor ingrese un número entero.");
						scanner.next();
					}
				}
				//Asignamos el nuevo stock mínimo
				this.stockMinimo = nuevoStockMin;
				break;
		}
		break;
		
		
	case 2:
		System.out.println("Ingrese la cantidad de stock nuevo (-1 para cancelar)");
		while(true) {
			try {
				int nuevoStock = scanner.nextInt();
				if (nuevoStock == -1) {
					System.out.println("Modificaciòn cancelada.");
					return;
				}
				//Calculamos y asignamos el nuevo stock
				this.stock = this.stock + nuevoStock;
				break;
			}catch(InputMismatchException e) {
				System.out.println("Error, por favor ingrese un número entero.");
				scanner.next();
			}
		}
		break;
		
		
	case 3:
		System.out.println("Ingrese la cantidad de unidades a descontar (-1 para cancelar)");
		while(true) {
			try {
					int menosStock = scanner.nextInt();
					if (menosStock == -1) {
						System.out.println("Modificaciòn cancelada.");
						return;
					}
					//Calculamos y asignamos el nuevo stock
					//Vemos que no haya menos stock del stock mínimo
					if(this.stock-menosStock<this.stockMinimo) {
						System.out.println("No se puede realizar la operación porque quedarías con menos stock del mínimo permitido. Ingrese de nuevo");
						menosStock = scanner.nextInt();
					}
					else {
					this.stock = this.stock - menosStock;
					}
					break;
				}catch(InputMismatchException e) {
					System.out.println("Error, por favor ingrese un número entero.");
					scanner.next();
				}
			}	
			break;
		}
	
	System.out.println("Cambios realizados con èxito.");
	}
			
			
	
	@Override
	public void mostrarAutoparte() {
		System.out.println("-----------------");
		System.out.println("ID: "+this.getId());
		System.out.println("Denominaciòn: "+this.getDenominacion());
		System.out.println("Descripciòn: "+this.getDescripcion());
		System.out.println("Categorìa: "+this.getCategoria());
		System.out.println("Vehìculo: "+this.getVehiculo());
		System.out.println("Modelo: "+this.getModelo());
		System.out.println("Precio: "+this.getPrecioUnitario());
		System.out.println("Enlace: "+this.getEnlace());
		System.out.println("Stock: "+this.getStock());
		System.out.println("Stock mìnimo: "+this.getStockMinimo());
		if (this.getStock()<=this.getStockMinimo()) {
			System.out.println();
			System.out.println("ESTE PRODUCTO LLEGÓ AL STOCK MÍNIMO.");
		}
		System.out.println("-----------------");
		System.out.println();
	}
	
	@Override
	public void borrarAutoparte(Autoparte autoparte) {
		autoparte = null;
	}
	
	@Override
	public void restarStock(Autoparte autoparte, int cantidad) {
		autoparte.stock = autoparte.stock - cantidad;
	}
	
	@Override
	public void sumarStock(Autoparte autoparte, int cantidad) {
		autoparte.stock = autoparte.stock + cantidad;
	}
	
}
 