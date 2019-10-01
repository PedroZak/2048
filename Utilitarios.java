import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;

//classe utilizada para centralizar o numero dentro da peça de acordo com o numero 

public class Utilitarios 
{
	public static int getRecebeLargura(String recebe, Font Fonte, Graphics2D g)
	{
		g.setFont(Fonte);
		Rectangle2D fronteiras = g.getFontMetrics().getStringBounds(recebe, g);
		return(int)fronteiras.getWidth();
	}
	
	public static int getRecebeAltura(String recebe, Font Fonte, Graphics2D g)
	{
		g.setFont(Fonte);
		if(recebe.length() == 0)
			return 0;
		TextLayout layout = new TextLayout(recebe, Fonte, g.getFontRenderContext());
		return(int) layout.getBounds().getHeight();
	}
}