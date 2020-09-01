package Dominio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class Archivo
{
	public Archivo()
	{
		
	}
	
	public void crearArchivo(ArrayList<Persona> listaPersonas)
	{
		try
		{
			String ruta="C:\\Users\\Usuario\\Desktop\\people\\Tarea2_Grupo1_Lab4\\Resultado.txt";
			
			File archi= new File(ruta);
		
			if(!archi.exists())
			{
				archi.createNewFile();
				try
				{
					FileWriter entrada= new FileWriter(archi, true);
					BufferedWriter miBuff= new BufferedWriter(entrada);
					
					Iterator<Persona> it= listaPersonas.iterator();
					while(it.hasNext())
					{
						miBuff.write(it.next().toString()+"\n");
					}
					
					miBuff.close();
					entrada.close();
					System.out.println("Archivo generado exitosamente!");
				}
				catch(Exception ex)
				{
					ex.getStackTrace();
				}
			}
			else
			{
				System.out.println("El archivo ya existe");
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();;
		}
	}
}
