//package Main_logic;
//import java.awt.Window;
import javax.swing.JFrame;

public class Main_logic 
{ 
	public static void main(String[] args)
	{
		Jogo jogo = new Jogo();
		
		JFrame janela = new JFrame("2048");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setResizable(false);
		janela.add(jogo);
		janela.pack();
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);
		
		jogo.start();
	}
}