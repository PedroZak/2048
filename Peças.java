import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Peças 
{
	//variaveis para o desenho da peca
	public static final int X_PECA = 80;
	public static final int Y_PECA = 80;
	public static final int SLIDE_SPEED = 20;
	public static final int ARC_X_PECA = 15; //aumentar para ter um arco maior
	public static final int ARC_Y_PECA = 15; //aumentar para ter um arco maior
	
	//varaiveis em 'private' pois apenas a classe Peças vai ter acesso
	private int value;
	private BufferedImage imagem_Peca;
	private Color background;
	private Font Fonte;
	private Color Text;
	private int x;
	private int y;
	
	public Peças(int value, int x, int y)
	{
		this.value = value;
		this.x = x;
		this.y = y;
		imagem_Peca = new BufferedImage(X_PECA, Y_PECA, BufferedImage.TYPE_INT_ARGB);
		drawImage();
	}
	
	public void drawImage()
	{
		Graphics2D g = (Graphics2D)imagem_Peca.getGraphics();
		
		if(value == 2)
		{
			background = new Color(0xe9e9e9);//cor da peca
			Text = new Color(0x000000);//cor do numero
		}
		else if(value == 4)
		{
			background = new Color(0xe6daab);//cor da peca
			Text = new Color(0x000000);//cor do numero
		}
		else if(value == 8)
		{
			background = new Color(0xf79d3d);//cor da peca
			Text = new Color(0xffffff);//cor do numero
		}
		else if(value == 16)
		{
			background = new Color(0xf28007);//cor da peca
			Text = new Color(0xffffff);//cor do numero
		}
		else if(value == 32)
		{
			background = new Color(0xf55e3b);//cor da peca
			Text = new Color(0xffffff);//cor do numero
		}
		else if(value == 64)
		{
			background = new Color(0xff1212);//cor da peca
			Text = new Color(0xffffff);//cor do numero
		}
		else if(value == 128)
		{
			background = new Color(0xe9de84);//cor da peca
			Text = new Color(0xffffff);//cor do numero
		}
		else if(value == 256)
		{
			background = new Color(0xf6e873);//cor da peca
			Text = new Color(0xffffff);//cor do numero
		}
		else if(value == 512)
		{
			background = new Color(0xf5e455);//cor da peca
			Text = new Color(0xffffff);//cor do numero
		}
		else if(value == 1024)
		{
			background = new Color(0xf7e12);//cor da peca
			Text = new Color(0xffffff);//cor do numero
		}
		else if(value == 2048)
		{
			background = new Color(0xffe400);//cor da peca
			Text = new Color(0xffffff);//cor do numero
		}
		//else
		//{
			//background = Color.black;
			//text = Color.white;
		//}
		
		g.setColor(new Color(0,0,0,0)); //passa as cores para transparente
		g.fillRect(0,0,X_PECA,Y_PECA);
		g.setColor(background);
		g.fillRoundRect(0, 0, X_PECA,Y_PECA, ARC_X_PECA, ARC_Y_PECA);
		g.setColor(Text);
		
		if(value <= 64)
		{
			Fonte = Jogo.texto.deriveFont(36f); //tamanho da fonte
		}
		else
		{
			Fonte = Jogo.texto;
		}
		
		g.setFont(Fonte);
		
		//Coloca os numeros no centro da peça
		
		int drawX = X_PECA / 2 - Utilitarios.getRecebeLargura("" + value, Fonte, g) / 2;
		int drawY = Y_PECA / 2 - Utilitarios.getRecebeAltura("" + value, Fonte, g) / 2;
		g.drawString("" + value, drawX, drawY);
		g.dispose();
	}
}