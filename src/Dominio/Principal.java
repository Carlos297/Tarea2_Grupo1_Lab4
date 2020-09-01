package Dominio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Principal {

	public static void main(String[] args) {
		
		ArrayList<Persona> listaPersonas;
		
		try {
			listaPersonas = LeerArchivo("PersonasEmpresa.txt");
			for (Persona persona : listaPersonas) {
				System.out.println(persona.toString());
			}
			
		} catch (Exception e) {
			System.out.println("Error inesperado...\n");
			e.printStackTrace();
		}
		
	}
	
	private static ArrayList<Persona> LeerArchivo(String ruta){
		String registro;
		String[] datos;
		Persona personaAux;
		ArrayList<Persona> listaReturn = new ArrayList<Persona>();
		
		try {
			FileReader archivoEntrada = new FileReader(ruta);
			BufferedReader bufferAux = new BufferedReader(archivoEntrada);
			registro = bufferAux.readLine();
			while(registro != null) {
				datos = registro.split("-");
				try {
					if(ValidarDni(datos[2].toString())) {
						personaAux = new Persona(Integer.parseInt(datos[2]),datos[0], datos[1]);
						boolean flag = true;
						for (Persona persona : listaReturn){
							if(persona.equals(personaAux)) {
								flag = false;
								continue;
							}
						}
						if(flag) {
							listaReturn.add(personaAux);
						}
					}
				} catch (Exception e) {
					System.out.println("Error validando personas...\n");
					e.printStackTrace();
				}
				registro = bufferAux.readLine();
			}
			bufferAux.close();
			archivoEntrada.close();
			
		} catch (Exception e) {
			System.out.println("Error procesando archivo de datos...\n");
			e.printStackTrace();
		}
		return listaReturn;
	}
	
	public static boolean ValidarDni(String dni) throws ValidarDniException
	{
		Boolean auxDigitos = false;
		int maxDigitos = 8;
		int cantDigitos = dni.length();
			
		if (cantDigitos == maxDigitos) {
			auxDigitos = true;
		}
		if (auxDigitos == false) {
			throw new ValidarDniException();
		}
		return auxDigitos;
	}

}


