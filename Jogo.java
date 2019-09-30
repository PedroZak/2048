import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Jogo extends JPanel implements KeyListener, Runnable
{
	//private static final long serialVersionUID = 1L;
	public static final int X_TELA = 400;
	public static final int Y_TELA = 630;
	public static final Font texto = new Font("Arial", Font.PLAIN, 26);
	private Thread jogo;
	private boolean execucao;
	private BufferedImage imagens = new BufferedImage (X_TELA, Y_TELA, BufferedImage.TYPE_INT_RGB);
	
        private long startTime;
        private long elapsed;
        private boolean set;
        
        public Jogo()
        {
            setFocusable(true);
            setPreferredSize(new Dimension(X_TELA, Y_TELA));
            addKeyListener(this);
          
        }
        private void atualizar()
        {
        
        }
        private void render()
        {
            Graphics2D.g=(Graphics2D) imagens.getGraphics();
            g.setColor(color.White);
            g.fillRect(0,0,X_TELA,Y_TELA);
            g.dispose();
            Graphics2d g2d = Graphics2d getGraphics();
            g2d.drawimage(imagens,0,0,null);
        }
                
                
                
                
                
                
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