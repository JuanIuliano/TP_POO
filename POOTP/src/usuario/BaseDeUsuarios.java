package usuario;
 
public class BaseDeUsuarios {
	Usuario[] listaUsuarios = new Usuario[5];
	int cantidadDeUsuarios = 0;
	
	private boolean usuarioRegistrado(int id, String username, String mail) {
		for (int i=0; i<cantidadDeUsuarios; i++) {
			Usuario user = listaUsuarios[i];
			if (user.getId() == id || user.getUsername().equals(username) || user.getMail().equals(mail)==true) {
				return true;
			}
		}
		return false;
	}
	
	public boolean agregarUsuario(Usuario usuario) {
		String username = usuario.getUsername();
		String mail = usuario.getMail();
		int id = usuario.getId();
		if (usuarioRegistrado(id, username, mail)==false) {
			listaUsuarios[cantidadDeUsuarios] = usuario;
			cantidadDeUsuarios++;
			return true;
			}
		else {
			System.out.println();
			System.out.println("El usuario ya está registrado.");
			System.out.println("Ingrese de nuevo.");
			System.out.println();
			return false;
		}
	}
	
	public boolean ValidarCredenciales(String username, String contraseña) {
		//Recorremos la lista que contiene los usuarios 
		for(int i=0; i<cantidadDeUsuarios; i++) {
			//creamos el objeto usuario para validar sus datos
			Usuario user = listaUsuarios[i];
			//Comparamos las credenciales
			if((user.getUsername().equals(username) || user.getMail().equals(username)) && user.getContraseña().equals(contraseña)) {
				return true;
			}
		}
		return false;
	}
	
	public Usuario devolverUsuario(int id) {
		Usuario usuario = null;
		for(int i=0; i<cantidadDeUsuarios; i++) {
			if(listaUsuarios[i].getId() == id) {
				return listaUsuarios[i];
			}
		}
		System.out.println("El usuario no se encuentra registrado.");
		return usuario;
	}
 
	public Usuario[] getListaUsuarios() {
		return listaUsuarios;
	}
 
	public void setListaUsuarios(Usuario[] listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
 
	public int getCantidadDeUsuarios() {
		return cantidadDeUsuarios;
	}
 
	public void setCantidadDeUsuarios(int cantidadDeUsuarios) {
		this.cantidadDeUsuarios = cantidadDeUsuarios;
	}
	
	
}
 