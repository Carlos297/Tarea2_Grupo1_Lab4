package Dominio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;

public class Principal {

	public static void main(String[] args) {
		
		ArrayList<Persona> listaPersonas;
		
		try 
		{
			listaPersonas = LeerArchivo("PersonasEmpresa.txt");
			/*for (Persona persona : listaPersonas) 
			{
				System.out.println(persona.toString());
			}*/
			
			ordenarLista(listaPersonas);
			
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
	
	//metodo que ordena los apellidos de la Z-a
	public static void ordenarLista(ArrayList<Persona> listaPersonas)
	{
		try 
		{
			Collections.sort(listaPersonas, Collections.reverseOrder((o1, o2) -> Normalizer.normalize(o1.getApellido(),Normalizer.Form.NFD).compareToIgnoreCase(Normalizer.normalize(o2.getApellido(),Normalizer.Form.NFD))));
			for (Persona persona : listaPersonas) 
			{
				System.out.println(persona.toString());
			}	
		} catch (Exception e) 
		{
			System.out.println("Error inesperado...\n");
			e.printStackTrace();
		}
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


