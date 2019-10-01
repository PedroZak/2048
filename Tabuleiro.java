import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class Tabuleiro {

   public static final int linhas =4;
   public static final int colunas =4;
   
   private final int pecasIniciais = 2;
   private Pe�as[][] tab;
    private boolean morto;
    private boolean ganhar;
    private BufferedImage tabuleiro;
    private BufferedImage tabuleiroF;
    private int x;
    private int y;
    
    private static int ESPACO =10; // espaco etre pecas
    private static int TAB_X_TELA= (colunas+1)* ESPACO + colunas * Pe�as.X_PECA;
    private static int TAB_Y_TELA= (linhas+1)* ESPACO + linhas * Pe�as.Y_PECA;
    
    private boolean hasStarted;
    
    public Tabuleiro(int x, int y)
    {
        this.x = x;
        this.y = y;
        tab = new Pe�as[linhas][colunas];
        tabuleiro = new BufferedImage(TAB_X_TELA,TAB_Y_TELA, BufferedImage.TYPE_INT_BGR);
        tabuleiroF = new BufferedImage(TAB_X_TELA,TAB_Y_TELA, BufferedImage.TYPE_INT_BGR);
         
        createBoardImage();
    }
    private void createBoardImage()  //desenhar o fundo da tela
    {
        Graphics2D g=(Graphics2D) tabuleiro.getGraphics();
        g.setColor(Color.darkGray);
        g.fillRect(0,0,TAB_X_TELA,TAB_Y_TELA);
        g.setColor(Color.lightGray);
        
        for(int lin = 0; lin < linhas;lin++ ){
            for(int col= 0;col<colunas;col++){
                int x = ESPACO+ ESPACO*col + Pe�as.X_PECA*col;
                int y = ESPACO+ ESPACO*lin + Pe�as.Y_PECA*lin;
                g.fillRoundRect(x,y,Pe�as.X_PECA,Pe�as.Y_PECA, Pe�as.ARC_X_PECA,Pe�as.ARC_Y_PECA);
                
            }
        }
    }
    public void render (Graphics2D g){
        Graphics2D g2d = (Graphics2D)tabuleiroF.getGraphics();
        g2d.drawImage(tabuleiro,0,0,null);
        //
        
        g.drawImage(tabuleiroF, x, y, null);
        g2d.dispose();
    }
    public void update(){
        checkKeys();
        
    }
    public void checkKeys()
    {
        if(Teclado.typed(KeyEvent.VK_ESQ))
        {
        	//move tiles 
        	if(!hasStarted) hasStarted = true;
        }
        if(Teclado.typed(KeyEvent.VK_DIR))
        {
        	//move tiles 
        	if(!hasStarted) hasStarted = true;
        }
        if(Teclado.typed(KeyEvent.VK_CIMA))
        {
        	//move tiles 
        	if(!hasStarted) hasStarted = true;
        }
        if(Teclado.typed(KeyEvent.VK_BAIXO))
        {
        	//move tiles 
        	if(!hasStarted) hasStarted = true;
        }
    }
}

