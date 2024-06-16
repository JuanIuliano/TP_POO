package empresa;

import usuario.Usuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.io.IOException;
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

	// Método para ingresar al sistema
	public static void ingresar(BaseDeUsuarios base) {

		// Creamos objeto de la clase Scanner
		Scanner scanner = new Scanner(System.in);
		System.out.println("-------------------------");
		while (true) {
			int opc = 0;
			try {
				System.out.println("INGRESO AL SISTEMA --(para iniciar sesión: user:ejemplo - contraseña: ejemplo123)");
				System.out.println("[1] Registrarse");
				System.out.println("[2] Iniciar sesión");
				System.out.println("Ingrese una opción");
				opc = scanner.nextInt();
				scanner.nextLine();
				// Validamos que la opcion sea válida
				while (opc < 1 || opc > 2) {
					System.out.println("Opción no válida, ingrese de nuevo");
					opc = scanner.nextInt();
					scanner.nextLine();
				}

			} catch (InputMismatchException e) {
				System.out.println("Error: Debe ingresar [1] ó [2]");
				scanner.next();
			}
			// Si el usuario quiere registrarse
			if (opc == 1) {
				int ok = 0;
				while (ok == 0) {
					int id = base.getCantidadDeUsuarios();
					System.out.println("CREANDO USUARIO");
					System.out.println();
					System.out.println("Ingresá tu nombre de usuario:");
					String username = scanner.nextLine();
					System.out.println("Ingresá tu mail:");
					String mail = scanner.nextLine();
					System.out.println("Ingresá tu contraseña:");
					String contraseña = scanner.nextLine();
					// Creamos el usuario
					Usuario user = new Usuario(id, username, contraseña, mail);
					// Lo agregamos a la base de usuarios
					if (base.agregarUsuario(user) == true) {
						System.out.println();
						System.out.println("Usuario registrado con éxito.");
						System.out.println("------------------------------");
						ok = 1;
						Usuario usuario = base.devolverUsuario(id);
						if (usuario != null) {
							usuario.verInfo(usuario);
							System.out.println("------------------------------");
						}
						break;
					}
				}
			}
			// Si el usuario quiere iniciar sesión con una cuenta ya creada
			else if (opc == 2) {
				while (true) {
					System.out.println("INICIANDO SESIÓN");
					System.out.println();
					System.out.println("Ingresa tu nombre de usuario o mail:");
					String username = scanner.nextLine();
					System.out.println("Ingresa tu contraseña:");
					String contraseña = scanner.nextLine();

					// Llamamos a la función para válidar credenciales
					// Si las credenciales son correctas, iniciamoss sesión
					if (base.ValidarCredenciales(username, contraseña) == true) {
						System.out.println();
						System.out.println("INICIO DE SESIÓN EXITOSO.");
						return;
					} else {// Si las credenciales no son correctas
						System.out.println("Credenciales incorrectas. Ingrese de nuevo.");
						System.out.println();
					}
				}
			}
		}

	}

	public static void main(String[] args) {
		// Cargamos la base de datos
		Tutta t = null;
		try {
			t = Tutta.cargarBBDD();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Iniciamos sesión
		BaseDeUsuarios base = new BaseDeUsuarios();
		base.iniciarBaseDeUsuarios();
		// Cargamos a la lista de usuarios los usuarios existentes
		base.setListaUsuarios(t.getListaUsuario());
		// Llamamos al método para iniciar sesión
		ingresar(base);

		// Creamos objeto de la clase Scanner
		Scanner scanner = new Scanner(System.in);
		// Inicializamos catálogo
		catalogo.inicializarCatalogo();
		catalogo.setCatalogo(t.getCatalogo());
		// Variable que sirve para cortar el ciclo del menú
		int flag = 0;

		// -------------MENÚ PRINCIPAL-----------------//
		while (flag == 0) {
			int opcion = 0;
			try {

				System.out.println();
				System.out.println("-------------------------------");
				System.out.println("MENÚ PRINCIPAL");
				System.out.println();
				System.out.println("[1] Cargar autoparte");
				System.out.println("[2] Modificar autoparte");
				System.out.println("[3] Eliminar autoparte");
				System.out.println("[4] Listar productos / Reservar pedido / Venta directa");
				System.out.println("[5] Listar / Cancelar pedidos");
				System.out.println("[6] Registrar venta con pedido previo");
				System.out.println("[7] Listar clientes / Ver pedidos asociados"); // ver sus respectivos pedidos
				System.out.println();
				System.out.println("[-1] Cerrar sesión");
				System.out.println("[-2] SALIR");
				System.out.println("-------------------------------");
				System.out.println("Ingresá una opción: ");
				opcion = scanner.nextInt();
				System.out.println();
			} catch (InputMismatchException e) {
				System.out.println("Error: Debe ingresar un Número Entero válido");
				scanner.next();
			}
			// AGREGAR VALIDACION Y MANEJO DE EXCEPCIONES

			switch (opcion) {
			case -2: // Finalizar programa
				System.out.println();
				System.out.println();
				// Guardamos el catalogo en Tutta para después serialziarlo
				t.setCatalogo(catalogo.getCatalogo());
				// Guardamos la base de usuarios en Tutta para después serializarlo
				t.setListaUsuario(base.getListaUsuario());
				// SERIALIZAMOS LOS DATOS
				try {
					t.guardarBBDD();
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("Programa finalizado.");
				flag = 1;
				break;

			case -1: // Cerrar sesión
				System.out.println();
				ingresar(base);
				break;

			case 1: // Cargar autoparte
				Autoparte autoparte = null;
				catalogo.cargarAutoparte(autoparte);
				System.out.println("Ingresà cualquier tecla para continuar.");
				scanner.next();
				break;

			case 2: // Modificar autoparte
				System.out.println("---MENÚ DE MODIFICACIÓN---");
				catalogo.listarCatalogo();
				System.out.println();
				System.out.println("Ingresá el ID de la autoparte que querés cambiar");
				int idMod = scanner.nextInt();
				catalogo.modificarAutoparte(idMod);
				System.out.println("Ingresà cualquier tecla para continuar.");
				scanner.next();
				break;
			case 3: // Eliminar autoparte
				System.out.println("Ingresa el ID de la autoaparte que quieras borrar");
				int idBorrar = scanner.nextInt();
				catalogo.eliminarAutoparte(idBorrar);
				System.out.println("Ingresà cualquier tecla para continuar.");
				scanner.next();
				break;

			case 4: // Listar catálogo - crear pedidos o venta directa
				int menu = 0;
				catalogo.listarCatalogo();
				/**/
				int ida = 0;
				int cantidad = 0;

				// inicializamos carrito
				Map<Autoparte, Integer> autopartes = new HashMap<Autoparte, Integer>();
				int opc = 0;
				Cliente cliente = null;

				// Seleccionamos el cliente que reservará el pedido/si no existe lo creamos
				System.out.println("CREANDO CARRITO");
				System.out.println("[1] Cliente existente");
				System.out.println("[2] Nuevo cliente");
				System.out.println("[3] Ir al menú");
				while (true) {
					try {
						System.out.println("Seleccione una opción: ");
						opc = scanner.nextInt();
						if (opc != 1 && opc != 2 && opc != 3) {
							System.out.println("Opción incorrecta");
						} else {
							break;
						}
					} catch (InputMismatchException e) {
						System.out.println("Error: Debe ingresar [1], [2] ó [3]");
						scanner.next();
					}
				}
				if (opc == 1) {
					if (t.getClientes().size() != 0) {
						t.mostrarClientes();
						cliente = t.seleccionarCliente();
					} else {
						System.out.println("No hay clientes cargados en el sistema.");
						System.out.println("presione cualquier tecla para continuar...");
						ida = -1; // necesario para que no se pueda hacer un pedido, sin este paso se crearía un
									// pedido sin cliente.
						scanner.next();
					}
				} else if (opc == 2) {
					cliente = t.crearCliente();
				} else {
					System.out.println("Presione cualquier tecla para continuar");
					scanner.next();
					break;
				}

				while (ida != -1) {
					try {
						System.out.println("Ingresá [ID] de la autoparte que quiere agregar al carrito ");
						if (autopartes.size() > 0) {
							System.out.println("Ingresá [-1] para continuar ");
						} else {
							System.out.println("Ingresá [-1] para salir ");
						}
						ida = scanner.nextInt();

						if (ida == -1) {
							if (autopartes.size() > 0) {
								ida = 0; // necesario para que no se pueda hacer un pedido, sin este paso se crearía un
											// pedido sin cliente.
							}
							break;
						}
						// Si la autoparte en cuestión existe pedimos la cantidad a reservar
						if (Empresa.catalogo.autoparteExistente(ida)) {
							System.out.println("Ingresá la cantidad que quiere agregar: ");
							cantidad = scanner.nextInt();
							System.out.println();
							// Calculamos si la cantidad a reservar no excede al stock
							if (Empresa.catalogo.devolverAutoparte(ida).getStock() >= cantidad) {
								// Obtenemos el objeto autoparte correspondiente a el ID
								Autoparte autoparteExistente = Empresa.catalogo.devolverAutoparte(ida);
								// Lo agregamos al hashmap (obtenemos la cantidad actual y le sumamos la nueva)
								autopartes.put(autoparteExistente,
										autopartes.getOrDefault(autoparteExistente, 0) + cantidad);
								// Actualizamos stock
								Empresa.catalogo.restarStock(ida, cantidad);
								System.out.println("Agregando al carrito: "
										+ Empresa.catalogo.devolverAutoparte(ida).getDenominacion() + " x " + cantidad);
								System.out.println();
							} else {
								System.out.println("Error al realizar la reserva, las cantidades exceden al stock.");
								System.out.println();
							}

						} else {
							System.out.println("Esa autoparte no existe.");
							System.out.println();
						}
					} catch (InputMismatchException e) {
						System.out.println("Error: Debe ingresar un Número Entero Válido");
						scanner.next();
					}
				}

				/**/
				while (menu != 1 && menu != 2 && ida != -1) {
					try {
						System.out.println("[1] crear un pedido");
						System.out.println("[2] crear una venta");
						menu = scanner.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("Error: Debe ingresar un Número Entero Válido");
						scanner.next();
					}
				}
				if (menu == 1) {
					t.crearPedido(autopartes, cliente);

				} else if (menu == 2) {
					t.crearVentaDirecta(autopartes, cliente);
				}
				break;

			case 5: // Ver pedidos / Cancelar pedidos
				t.cancelarPedido();
				break;

			case 6: // Registrar venta
				if (t.pedidosVacio() == false) {

					t.mostrarPedidos();
					System.out.println();
					boolean existe = false;
					Pedido p = null;

					while (!existe) {
						try {
							System.out.println("Ingrese el [ID] del pedido a vender");
							int idP = scanner.nextInt();
							p = t.devolverPedido(idP);
							if (p != null) {
								break;
							} else {
								System.out.println("No existe un pedido con ese ID, ingrese otro.");
							}
						} catch (InputMismatchException e) {
							System.out.println("Error: Debe ingresar un Número Entero Válido");
							scanner.next();
						}
					}

					// Registramos la venta
					Venta venta = new Venta();
					if (p != null) {
						boolean realizada = venta.registrarVenta(p);
						if (realizada == true) {
							t.eliminarPedido(p);
						}
					} else {
						System.out.println("Ocurrió un error.");
					}
				} else {
					System.out.println("No hay ningún pedido en el sistema.");
					System.out.println("Ingrese una tecla para continuar");
					scanner.next();
				}
				break;

			case 7:
				if (t.getClientes().size() != 0) {
					t.mostrarClientes();
					int op = 0;
					int id1 = 0;
					while (true) {
						try {
							System.out.println("Ingrese el [ID] del cliente que quiera ver sus pedidos: ");
							System.out.println("Presione [-1] para salir.");
							id1 = scanner.nextInt();
							if (id1 == -1) {
								break;
							}
							Cliente c = t.getClienteId(id1);
							if (c != null) {
								int cantPedidos = 0;
								boolean hayPedidos = false;
								for (Pedido pedido : t.getPedidos()) {
									if (pedido.getCliente() == c) {
										cantPedidos++;
										int i = 1;
										System.out.println("--------------");
										System.out.println("ID: " + pedido.getIdPedido());
										System.out.println("Autopartes del pedido: ");
										for (Map.Entry<Autoparte, Integer> entry : pedido.getAutopartes().entrySet()) {
											System.out.println(i + " - " + entry.getKey().getDenominacion() + " x "
													+ entry.getValue());
											i++;
										}
										System.out.println("--------------");
										hayPedidos = true;
									}
								}
								if (hayPedidos == false) {
									System.out.println("Ese cliente no ha hecho ningún pedido.");
								} else {
									System.out.println(
											c.getNombreApellido() + " ha realizado " + cantPedidos + " pedidos.");
								}
							} else {
								System.out.println("No exite cliente con ese ID.");
							}
						} catch (InputMismatchException e) {
							System.out.println("Error: Debe ingresar un Número Entero Válido");
							scanner.next();
						}
					}
				} else {
					System.out.println("No hay clientes cargados en el sistema.");
					System.out.println("Presione cualquier tecla para continuar...");
					scanner.next();
				}
				break;
			}
		}
	}

}