package empresa;
import usuario.Usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Autopartes.Autoparte;
import Autopartes.Catalogo;
import cliente.Cliente;
import pedido.Pedido;
import pedido.Recibo;
import pedido.Venta;
import usuario.BaseDeUsuarios;
 
public class Empresa {
	public static Catalogo catalogo = new Catalogo();
	//Creamos objeto Tutta para gestionar pedidos y ventas
	public static Tutta t = new Tutta();
	

	//Método para ingresar al sistema
	public static void ingresar(BaseDeUsuarios base) {
		
		//Creamos objeto de la clase Scanner
		Scanner scanner = new Scanner(System.in);
		System.out.println("-------------------------");
		while (true) {
			System.out.println("INGRESO AL SISTEMA --(para iniciar sesión: user:ejemplo - contraseña: ejemplo123)");
			System.out.println("[1] Registrarse");
			System.out.println("[2] Iniciar sesión");
			System.out.println("Ingrese una opción");
			int opc = scanner.nextInt();
			scanner.nextLine();
			//Validamos que la opcion sea válida
			while(opc<1 || opc>2) {
				System.out.println("Opción no válida, ingrese de nuevo");
				opc = scanner.nextInt();
				scanner.nextLine();
			}
			//Si el usuario quiere registrarse
			if(opc==1) {
				int ok=0;
				while(ok==0) {
					int id = base.getCantidadDeUsuarios();
					System.out.println("CREANDO USUARIO");
					System.out.println();
					System.out.println("Ingresá tu nombre de usuario:");
					String username = scanner.nextLine();
					System.out.println("Ingresá tu mail:");
					String mail = scanner.nextLine();
					System.out.println("Ingresá tu contraseña:");
					String contraseña = scanner.nextLine();
					//Creamos el usuario
					Usuario user = new Usuario(id, username, contraseña, mail);
					//Lo agregamos a la base de usuarios
					if(base.agregarUsuario(user)==true) {
						System.out.println();
						System.out.println("Usuario registrado con éxito.");
						System.out.println("------------------------------");
						ok=1;
						Usuario usuario = base.devolverUsuario(id);
						if (usuario!=null) {
							usuario.verInfo(usuario);
							System.out.println("------------------------------");
							}
						break;
					}
				}
			}
			//Si el usuario quiere iniciar sesión con una cuenta ya creada
			else if(opc==2) {
				while(true) {
					System.out.println("INICIANDO SESIÓN");
					System.out.println();
					System.out.println("Ingresa tu nombre de usuario o mail:");
					String username = scanner.nextLine();
					System.out.println("Ingresa tu contraseña:");
					String contraseña = scanner.nextLine();
					
					//Llamamos a la función para válidar credenciales
					//Si las credenciales son correctas, iniciamoss sesión
					if(base.ValidarCredenciales(username, contraseña)==true) {
						System.out.println();
						System.out.println("INICIO DE SESIÓN EXITOSO.");
						return;
					}
					else {//Si las credenciales no son correctas
						System.out.println("Credenciales incorrectas. Ingrese de nuevo.");
						System.out.println();
					}
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		
		
		//Iniciamos sesión
		BaseDeUsuarios base = new BaseDeUsuarios();
		//Añadimos un usuario a modo de ejemplo
		Usuario ejemplo = new Usuario(base.getCantidadDeUsuarios(), "ejemplo", "ejemplo123", "ejemplo@gmail.com");
		base.agregarUsuario(ejemplo);
		ingresar(base);
		//Creamos objeto de la clase Scanner
		Scanner scanner = new Scanner(System.in);
		//Inicializamos catálogo
		catalogo.inicializarCatalogo();
		//Creamos objeto Tutta para gestionar pedidos y ventas
		Tutta t = new Tutta();
		//Variable que sirve para cortar el ciclo del menú
		int flag=0;
		
		//AUTOPARTES PRECARGADAS A MODO DE EJEMPLO
		// Ejemplo de uso del constructor para cada autoparte
 
		// Autoparte 1: Pistón
		Autoparte piston = new Autoparte(001, "Pistón", "Componente de motor para convertir la energía de combustión en energía mecánica.", "Motor", "Bosch", "Automóvil", "Todos los modelos", 50.00, "www.etc.com/piston", 20, 20);
		catalogo.cargarAutoparte(piston);
 
		// Autoparte 2: Filtro de aire
		Autoparte filtroAire = new Autoparte(002, "Filtro de aire", "Filtra el aire que ingresa al motor para evitar la entrada de partículas dañinas.","Sistema de admisión", "Mann-Filter", "Camioneta", "SUV", 15.50, "www.etc.com/filtro-aire", 30, 30);
		catalogo.cargarAutoparte(filtroAire);
 
		// Autoparte 3: Batería
		Autoparte bateria = new Autoparte(003, "Batería de automóvil", "Suministra energía eléctrica para encender el motor y alimentar los sistemas eléctricos del vehículo.", "Sistema eléctrico", "Exide", "Automóvil", "Sedán", 120.00, "www.etc.com/bateria", 50, 10);
		 catalogo.cargarAutoparte(bateria);
 
		// Autoparte 4: Amortiguador
		Autoparte amortiguador = new Autoparte(004, "Amortiguador trasero", "Absorbe y disipa la energía cinética generada por la suspensión del vehículo.", "Suspensión", "Monroe", "Automóvil", "Hatchback", 80.00, "www.etc.com/amortiguador", 80, 15);
		catalogo.cargarAutoparte(amortiguador);
 
		// Autoparte 5: Pastillas de freno
		Autoparte pastillasFreno = new Autoparte(005, "Pastillas de freno delanteras", "Componentes que presionan contra el disco de freno para detener el vehículo.", "Frenos", "Brembo", "Camioneta", "Todoterreno", 65.00, "www.etc.com/pastillas-freno", 120, 25);
		catalogo.cargarAutoparte(pastillasFreno);
		
		
		//-------------MENÚ PRINCIPAL-----------------//
		while (flag==0) {
			System.out.println();
			System.out.println("-------------------------------");
			System.out.println("MENÚ PRINCIPAL");
			System.out.println();
			System.out.println("[1] Cargar autoparte");
			System.out.println("[2] Modificar autoparte");
			System.out.println("[3] Eliminar autoparte");
			System.out.println("[4] Listar productos / Reservar pedido");
			System.out.println("[5] Listar/Cancelar pedidos");
			System.out.println("[6] Registrar venta");
			System.out.println("[7] Listar clientes"); //ver sus respectivos pedidos
			System.out.println();
			System.out.println("[-1] Cerrar sesión");
			System.out.println("[-2] SALIR");
			System.out.println("-------------------------------");
			System.out.println("Ingresá una opción: ");
			int opcion = scanner.nextInt();
			System.out.println();
			
			//AGREGAR VALIDACION Y MANEJO DE EXCEPCIONES
			
			switch (opcion) {
			case -2: //Finalizar programa
				System.out.println();
				System.out.println();
				System.out.println("Programa finalizado.");
				flag=1;
				break;
				
			case -1: //Cerrar sesión
				System.out.println();
				ingresar(base);
				break;
				
			case 1: //Cargar autoparte
				Autoparte autoparte = null;
				catalogo.cargarAutoparte(autoparte);
				System.out.println("Ingresà cualquier tecla para continuar.");
				scanner.next();
				break;
				
			case 2: //Modificar autoparte
				System.out.println("---MENÚ DE MODIFICACIÓN---");
				catalogo.listarCatalogo();
				System.out.println();
				System.out.println("Ingresá el ID de la autoparte que querés cambiar");
				int idMod = scanner.nextInt();
				catalogo.modificarAutoparte(idMod);
				System.out.println("Ingresà cualquier tecla para continuar.");
				scanner.next();
				break;
			case 3: //Eliminar autoparte
				System.out.println("Ingresa el ID de la autoaparte que quieras borrar");
				int idBorrar = scanner.nextInt();
				catalogo.eliminarAutoparte(idBorrar);
				System.out.println("Ingresà cualquier tecla para continuar.");
				scanner.next();
				break;
			
			case 4: //Listar catálogo - crear pedidos
				t.crearPedido();
				break;
				
			case 5: //Ver pedidos / Cancelar pedidos
				t.cancelarPedido();
				break;
				
			case 6: //Registrar venta
				t.mostrarPedidos();
				System.out.println();
				boolean existe = false;
				Pedido p = null;
				
				while (!existe) {
					System.out.println("Ingrese el [ID] del pedido a vender");
					int idP = scanner.nextInt();
					p = t.devolverPedido(idP);
					if(p != null){
						break;
					}
					else {
						System.out.println("No existe un pedido con ese ID, ingrese otro.");
					}
				}
				
				//Registramos la venta
				Venta venta = new Venta();
				if(p != null) {
					venta.registrarVenta(p);
				}
				else {
					System.out.println("Ocurrió un error.");
				}
				break;
				
			case 7:
				if(t.getClientes().size() != 0) {
					t.mostrarClientes();
					int op = 0;
					int id = 0;
					while(true) {
						System.out.println("Ingrese el [ID] del cliente que quiera ver sus pedidos: ");
						System.out.println("Presione [-1] para salir.");
						id = scanner.nextInt();
						if (id == -1) {
							break;
						}
						Cliente c = t.getClienteId(id);
						if(c != null) {
							for (Pedido pedido : t.getPedidos()) {
								if(pedido.getCliente() == c) {
									pedido.mostrarPedido();
								}
							}
						}
						else {
							System.out.println("No exite cliente con ese ID.");
						}
					}
				}else {
					System.out.println("No hay clientes cargados en el sistema.");
					System.out.println("Presione cualquier tecla para continuar...");
					scanner.next();
				}
				break;
			}
		}
	}

	
}