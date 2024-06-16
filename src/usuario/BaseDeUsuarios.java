package usuario;

import java.io.Serializable;
import java.util.ArrayList;

public class BaseDeUsuarios implements Serializable{
	private static final long serialVersionUID = 1L;
	//ArrayList que va a hacer de base de datos, guarda objetos de la clase usuario (id, username, contraseña, etc)
	private ArrayList <Usuario> listaUsuarios;
		
	
	private boolean usuarioRegistrado(int id, String username, String mail) {
		for (int i=0; i<listaUsuarios.size(); i++) {
			Usuario user = listaUsuarios.get(i);
			if (user.getId() == id || user.getUsername().equals(username) || user.getMail().equals(mail)==true) {
				return true;
			}
		}
		return false;
	}

	public void iniciarBaseDeUsuarios() {
		this.listaUsuarios = new ArrayList<>();
	}
	
	public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
	
	public ArrayList<Usuario> getListaUsuario(){
		return this.listaUsuarios;
	}
	
	public boolean agregarUsuario(Usuario usuario) {
		String username = usuario.getUsername();
		String mail = usuario.getMail();
		int id = usuario.getId();
		if (usuarioRegistrado(id, username, mail)==false) {
			listaUsuarios.add(usuario);
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
		for(int i=0; i<listaUsuarios.size(); i++) {
			//creamos el objeto usuario para validar sus datos
			Usuario user = listaUsuarios.get(i);
			//Comparamos las credenciales
			if((user.getUsername().equals(username) || user.getMail().equals(username)) && user.getContraseña().equals(contraseña)) {
				return true;
			}
		}
		return false;
	}
	
	public Usuario devolverUsuario(int id) {
		Usuario usuario = null;
		for(int i=0; i<listaUsuarios.size(); i++) {
			if(listaUsuarios.get(i).getId() == id) {
				return listaUsuarios.get(i);
			}
		}
		System.out.println("El usuario no se encuentra registrado.");
		return usuario;
	}
 
	public int getCantidadDeUsuarios() {
		return listaUsuarios.size();
	}
 
	
	
}
 