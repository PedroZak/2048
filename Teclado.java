
import java.awt.event.KeyEvent;

public class Teclado {

	public static boolean[] pressionado = new boolean[256];
	public static boolean[] anterior = new boolean[256];
	public static int i;
	
	private Teclado()
	{ }
	
	//recebe varios botoes do telado, multiplos botoes
	
	public static void atualizacao()
	{
		for(i = 0; i < 4; i++)
		{
			if(i == 0)
			{
				anterior[KeyEvent.VK_LEFT] = pressionado[KeyEvent.VK_LEFT];
			}
			if(i == 1)
			{
				anterior[KeyEvent.VK_RIGHT] = pressionado[KeyEvent.VK_RIGHT];
			}
			if(i == 2)
			{
				anterior[KeyEvent.VK_UP] = pressionado[KeyEvent.VK_UP];
			}
			if(i == 3)
			{
				anterior[KeyEvent.VK_DOWN] = pressionado[KeyEvent.VK_DOWN];
			}
		}
	}
	
	//recebe botao pressionado
	public static void botaoPressionado(KeyEvent e)
	{
		pressionado[e.getKeyCode()] = true;
	}
	
	//recebe botao liberado
	public static void botaoLiberado(KeyEvent e)
	{
		pressionado[e.getKeyCode()] = false;
	}
	
	public static boolean escolha(int keyEvent)
	{
		return !pressionado[keyEvent] && anterior[keyEvent];
	}	
}


