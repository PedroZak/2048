import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Jogo extends JPanel implements KeyListener, Runnable
{
	private static final long serialVersionUID = 1L;
	public static final int X_TELA = 400;
	public static final int Y_TELA = 630;
	public static final Font texto = new Font("Arial", Font.PLAIN, 26);
	private Thread jogo;
	private boolean rodando;
	private BufferedImage imagem = new BufferedImage (X_TELA, Y_TELA, BufferedImage.TYPE_INT_RGB);
	
	private long startTime;
    private long elapsed;
    private boolean set;
        
    public Jogo()
    {
        setFocusable(true);
        setPreferredSize(new Dimension(X_TELA, Y_TELA));
        addKeyListener(this);
    }
    
    private void update()
    {
        
    }
        
    private void render()
    {
    	Graphics2D g=(Graphics2D) imagem.getGraphics();
    	g.setColor(Color.white);
    	g.fillRect(0,0,X_TELA,Y_TELA);
        //
        g.dispose();
            
        Graphics2D g2d = (Graphics2D) getGraphics();
        g2d.drawImage(imagem,0,0,null);
        g2d.dispose();
    }    
                
	@Override
	public void run() 
	{
		int fps = 0, updates = 0;
		long fpsTimer = System.currentTimeMillis();
		double nsPerUpdate =1000000000.0/60;
		
		//ultima atualizacao do programa
		double then = System.nanoTime();
		double unprocessed = 0;
		
		while(rodando)
		{
			boolean shouldRender = false;
			double now = System.nanoTime();
			unprocessed += (now - then)/nsPerUpdate;
			then = now; 

			//
			while(unprocessed>=1)
			{
				updates++;
				update();
				unprocessed--;
				shouldRender = true;    
			}
			//
			if(shouldRender)
			{
				fps++;
				render();
				shouldRender = false;    
			}
			else
			{
				try
				{
					Thread.sleep(1);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		
		//
		if(System.currentTimeMillis() - fpsTimer >1000)
		{
			System.out.printf("%d fps %d updates", fps, updates);
			System.out.println();
			fps=0;
			updates=0;
			fpsTimer += 1000;     
		}
	}
	
	public synchronized void start()
	{
		if (rodando) return;
		rodando = true;
		jogo = new Thread(this, "jogo");
		jogo.start();
	}

	public synchronized void stop ()
	{
		if (!rodando) return;
		rodando = false;
		System.exit(0);
	}
        
	@Override
	public void keyTyped(KeyEvent e) 
	{
		
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		
	}
}