package metodos;

public class Validadores {

	public static boolean codigoValidado(String code)
	{
		//if (!code.contains("JA")) //Si no tiene JA delante
		//{
		//	return false;
		//}else 
		if (Integer.parseInt(code.substring(2))!=0) //Si tiene datos numéricos después de las dos letras. Ejemplo: JA01
		{
			return true;
		}else {
			return false;
		}	
	}
}
