import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Jogo extends JPanel implements KeyListener, Runnable
{
	//private static final long serialVersionUID = 1L;
	public static final int tamanhoemX_TELA = 400;
	public static final int tamanhoemY_TELA = 630;
	public static final Font texto = new Font("Arial", Font.PLAIN, 26);
	private Thread jogo;
	private boolean execucao;
	private BufferedImage imagens = new BufferedImage (tamanhoemX_TELA, tamanhoemY_TELA, BufferedImage.TYPE_INT_RGB)
	
	@Override
	public void run() {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}
