package metodos;

public class Validadores {

	public static boolean codigoValidado(String code)
	{
		//if (!code.contains("JA")) //Si no tiene JA delante
		//{
		//	return false;
		//}else 
		if (Integer.parseInt(code,2)!=0) //Si tiene datos num�ricos despu�s de las dos letras. Ejemplo: JA01
		{
			return true;
		}else {
			return false;
		}	
	}
}
