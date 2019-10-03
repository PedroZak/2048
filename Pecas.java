import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Pecas 
{
	//variaveis para o desenho da peca
	public static final int X_PECA = 80;
	public static final int Y_PECA = 80;
	public static final int SLIDE_SPEED = 20;
	public static final int ARC_X_PECA = 15; //aumentar para ter um arco maior
	public static final int ARC_Y_PECA = 15; //aumentar para ter um arco maior
	
	//varaiveis em 'private' pois apenas a classe Pecas vai ter acesso
	private int value;
	private BufferedImage imagem_Peca;
	private Color background;
	private Font Fonte;
	private Color Text;
	private Pontos moverPara;
	private int x;
	private int y;
	private boolean animacaoInicial=true;
	private double scaleFirst = 0.1;
	private BufferedImage imagemInicial;
	private boolean combinarAnimacao;
	private double scaleCombine = 1.2;
	private BufferedImage combinarImagem;
	public boolean isCombinarAnimacao() {
		return combinarAnimacao;
	}

	public void setCombinarAnimacao(boolean combinarAnimacao) {
		this.combinarAnimacao = combinarAnimacao;
		if(combinarAnimacao)scaleCombine=1.3;
	}

	private boolean podeCombinar = true;
	
	public Pecas(int value, int x, int y)
	{
		this.value = value;
		this.x = x;
		this.y = y;
		moverPara=new Pontos(x,y);
		imagem_Peca = new BufferedImage(X_PECA, Y_PECA, BufferedImage.TYPE_INT_ARGB);
		imagemInicial = new BufferedImage(X_PECA, Y_PECA, BufferedImage.TYPE_INT_ARGB);
		combinarImagem = new BufferedImage(X_PECA*2, Y_PECA*2, BufferedImage.TYPE_INT_ARGB);
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
		
		//Coloca os numeros no centro da peca
		
		int drawX = X_PECA / 2 - Utilitarios.getRecebeLargura("" + value, Fonte, g) / 2;
		int drawY = Y_PECA / 2 - Utilitarios.getRecebeAltura("" + value, Fonte, g) / 2;
		g.drawString("" + value, drawX, drawY);
		g.dispose();
	}
	
	public void update()
	{ 
		if(animacaoInicial) {
			AffineTransform transform = new AffineTransform();
			transform.translate(X_PECA/2 - scaleFirst*X_PECA/2, Y_PECA - scaleFirst*Y_PECA/2);
			transform.scale(scaleFirst,scaleFirst);
			Graphics2D g2d = (Graphics2D)imagemInicial.getGraphics();
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			g2d.setColor(new Color(0,0,0,0));
			g2d.fillRect(0,0,X_PECA,Y_PECA);
			g2d.drawImage(imagem_Peca, transform, null);
			scaleFirst+=0.1;
			g2d.dispose();
			if(scaleFirst>=1)animacaoInicial=false;
		}
		else if(combinarAnimacao) {
			AffineTransform transform = new AffineTransform();
			transform.translate(X_PECA/2 - scaleCombine*X_PECA/2, Y_PECA - scaleCombine*Y_PECA/2);
			transform.scale(scaleCombine,scaleCombine);
			Graphics2D g2d = (Graphics2D)combinarImagem.getGraphics();
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			g2d.setColor(new Color(0,0,0,0));
			g2d.fillRect(0,0,X_PECA,Y_PECA);
			g2d.drawImage(imagem_Peca, transform, null);
			scaleCombine-=0.05;
			g2d.dispose();
			if(scaleCombine<=1)combinarAnimacao=false;
	  }
	}
	
	public void render(Graphics2D g)
	{
		if(animacaoInicial)
		{
			g.drawImage(imagemInicial,x, y, null);	
		}
		else if(combinarAnimacao)
		{
			g.drawImage(combinarImagem,(int)(x+X_PECA/2-scaleCombine*X_PECA/2), (int)(y+Y_PECA/2-scaleCombine*Y_PECA/2), null);	
		}
		else {
			g.drawImage(imagem_Peca,x, y, null);
		}
		}
			
	
	
	public int getValue()
	{
		return value;
	}
	public void setValue(int value)
	{
		this.value = value;
		drawImage();
	}

	public boolean podeCombinar() {
		return podeCombinar;
	}

	public void setPodeCombinar(boolean podeCombinar) {
		this.podeCombinar = podeCombinar;
	}

	public Pontos getMoverPara() {
		return moverPara;
	}

	public void setMoverPara(Pontos moverPara) {
		this.moverPara = moverPara;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}