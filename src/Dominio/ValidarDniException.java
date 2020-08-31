package Dominio;

public class ValidarDniException extends Exception {

	public ValidarDniException(){
		
	}

	@Override
	public String getMessage() {
		return "El DNI debe tener 8 digitos";
	}
}
