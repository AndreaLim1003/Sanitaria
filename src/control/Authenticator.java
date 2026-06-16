package control;

import database.ClientiRegistratiDao;
import entity.EntityClienteRegistrato;
import exception.DAOException;

public class Authenticator{
	
	public boolean registrati(String nome,String cognome, String email, String password, String indirizzo,String telefono) throws DAOException {
		ClientiRegistratiDao crd = new ClientiRegistratiDao(); 
		return crd.registrati(nome, cognome, email, password, indirizzo, telefono);
	}
	
	public EntityClienteRegistrato login(String email,String pw) throws DAOException {
		ClientiRegistratiDao crd = new ClientiRegistratiDao(); 
		return crd.login(email, pw);
	}
}