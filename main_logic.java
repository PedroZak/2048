import java.awt.Window;
import javax.swing.JFrame;

public class main_logic 
{
	
	public void main(String[] args)
	{
		Jogo jogo = new Jogo();
		
		JFrame janela = new JFrame("2048");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setResizable(false);
		janela.add(jogo);
		janela.pack();
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);
		
		jogo.inicializar();
	}
}