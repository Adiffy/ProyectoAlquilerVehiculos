package InterfazUsuario;

import java.util.Random;

public class BarraDeCarga {

	
	public static void pintar()
	{
		Random aleatorio = new Random();
		int limite = 1; //Tendremos números random del 0 al 1 
		int numeroAleatorio = aleatorio.nextInt(limite);
		System.out.println("Guardando...");
		for (int i=0;i<40;i++)
		{
			i+=i*5;
			System.out.println(""+i+"%");
		}
		switch (numeroAleatorio)
		{ //Escribimos sin saltar 99 . . . o 100 . . .
		case 0:
			System.out.print("99%");
			System.out.print(".");
			System.out.print(".");
			System.out.print(".");
		case 1:
			System.out.print("100%");
			System.out.print(".");
			System.out.print(".");
			System.out.print(".");
		}
		
	}
}
	
