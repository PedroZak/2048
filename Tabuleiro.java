import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Random;


public class Tabuleiro 
{
   public static final int linhas = 4;
   public static final int colunas = 4;
   
   private final int pecasIniciais = 2;
   private Pecas[][] tab;
   private boolean morto;
   private boolean ganhou;
   private BufferedImage tabuleiro;
   private BufferedImage tabuleiroF;
   private int x;
   private int y;
   private int pontuacao = 0;
   private int maiorPontuacao = 0;
   private Font fontePontuacao;
    
   private static int ESPACO =10; // espaco etre pecas
   public static int TAB_X_TELA= (colunas + 1) * ESPACO + colunas * Pecas.X_PECA;
   public static int TAB_Y_TELA= (linhas + 1) * ESPACO + linhas * Pecas.Y_PECA;
    
   private boolean hasStarted;
   
   //Salvar informacoes
   private String salvarCaminho;
   private String arquivo = "ArquivosSalvos";
    
   public Tabuleiro(int x, int y)
   {
	   try 
	   {
		   salvarCaminho = Tabuleiro.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
	   
	   fontePontuacao = Jogo.texto.deriveFont(24f);
	   this.x = x;
	   this.y = y;
	   tab = new Pecas[linhas][colunas];
	   tabuleiro = new BufferedImage(TAB_X_TELA,TAB_Y_TELA, BufferedImage.TYPE_INT_RGB);
	   tabuleiroF = new BufferedImage(TAB_X_TELA,TAB_Y_TELA, BufferedImage.TYPE_INT_RGB);
        
	   loadHighScore();
	   createBoardImage();
	   start();
   }
   
   private void createSaveData()
   {
	   try
	   {
		   File file = new File(salvarCaminho, arquivo);
		   
		   FileWriter output = new FileWriter(file);
		   BufferedWriter writer = new BufferedWriter(output);
		   writer.write("" + 0);
		   writer.close();
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
   }
   
   private void loadHighScore()
   {
	   try
	   {
		   File f = new File(salvarCaminho, arquivo);
		   
		   if(!f.isFile())
		   {
			   createSaveData();
		   }
		   
		   BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		   maiorPontuacao = Integer.parseInt(reader.readLine());
		   reader.close();
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
   }
   
   private void setHighScore()
   {
	   FileWriter output = null;
	   
	   try
	   {
		   File f = new File(salvarCaminho, arquivo);
		   output = new FileWriter(f);
		   BufferedWriter writer = new BufferedWriter(output);
		   
		   writer.write("" + pontuacao);
		   
		   writer.close();
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
   }
    
   private void createBoardImage()  //desenhar o fundo da tela
   {
	   Graphics2D g =(Graphics2D) tabuleiro.getGraphics();
	   g.setColor(Color.darkGray);
	   g.fillRect(0,0,TAB_X_TELA,TAB_Y_TELA);
	   g.setColor(Color.lightGray);
	   
       for(int lin = 0; lin < linhas;lin++)
       {
    	   for(int col= 0; col < colunas;col++)
    	   {
    		   int x = ESPACO + ESPACO * col + Pecas.X_PECA * col;
    		   int y = ESPACO + ESPACO * lin + Pecas.Y_PECA * lin;
    		   g.fillRoundRect(x,y,Pecas.X_PECA,Pecas.Y_PECA, Pecas.ARC_X_PECA,Pecas.ARC_Y_PECA);
    	   }
       }
   }
   
   private void start()
   {
	   for(int i = 0; i < pecasIniciais; i++)
	   {
		   spawnRandom();
	   }
   }
   
   private void spawnRandom()
   {
	   Random random = new Random();
	   boolean notValid = true;
	   
	   while(notValid)
	   {
		   int location = random.nextInt(linhas * colunas);
		   int linha = location / colunas;
		   int coluna = location % linhas;
		   
		   Pecas atual = tab[linha][coluna];
		   if (atual == null)
		   {
			   int value = random.nextInt(10) < 9 ? 2 : 4;
			   //pegar um valor aleatorio entre 0 e 9
			   //caso seja menor que 9, aparecera 2
			   //caso seja maior que 9, aparecera 4
			   Pecas peca = new Pecas(value, getTileX(coluna), getTileY(linha));
			   tab[linha][coluna] = peca;
			   notValid = false;
		   }
	   }
   }
   
   public int getTileX(int col)
   {
	   return ESPACO + col * Pecas.X_PECA + col * ESPACO;
   }
   
   public int getTileY(int lin)
   {
	   return ESPACO + lin * Pecas.Y_PECA + lin * ESPACO;
   }
    
   public void render(Graphics2D g)
   {
        Graphics2D g2d = (Graphics2D)tabuleiroF.getGraphics();
        g2d.drawImage(tabuleiro,0,0,null);
        
        for(int lin = 0; lin < linhas; lin++)
        {
        	for(int col = 0; col < colunas; col++)
        	{
        		Pecas atual = tab[lin][col];
        		if (atual == null)
        		{
        			continue;
        		}
        		atual.render(g2d);
        	}
        }
        
        g.drawImage(tabuleiroF, x, y, null);
        g2d.dispose();
        g.setColor(Color.lightGray);
        g.setFont(fontePontuacao);
        g.drawString("" + pontuacao, 30, 40);
        g.setColor(Color.red);
        g.drawString("Recorde: " + maiorPontuacao, Jogo.X_TELA - Utilitarios.getRecebeLargura("Recorde: ", fontePontuacao, g) - 20, 40);
    }
    
    public void update()
    {
        checkKeys();
        
        if(pontuacao >= maiorPontuacao)
        {
        	maiorPontuacao = pontuacao;
        }
        
        for(int lin = 0; lin < linhas; lin++)
        {
        	for(int col = 0; col < colunas; col++)
        	{
        		Pecas atual = tab[lin][col];
        		if(atual == null)
        		{
        			continue;
        		}
        		atual.update();
        		//reset
        		if(atual.getValue() == 2048)
        		{
        			ganhou = true;
        		}
        	}
        }
    }
    
    private void resetarPosicao(Pecas atual,int lin, int col)
    {
    	if(atual==null)return;
    	int x = getTileX(col);
    	int y = getTileY(lin);
    	
    	int distX=atual.getX()-x;
    	int distY=atual.getY()-y;
    	
    	if(Math.abs(distX)<Pecas.SLIDE_SPEED)
    	{
    		atual.setX(atual.getX()-distX);
    	}
     	if(Math.abs(distY)<Pecas.SLIDE_SPEED)
    	{
    		atual.setY(atual.getY()-distY);
    	}
     	if(distX<0)
     	{
     		atual.setX(atual.getX()+Pecas.SLIDE_SPEED);
     	}
     	if(distY<0)
     	{
     		atual.setY(atual.getY()+Pecas.SLIDE_SPEED);
     	}
     	if(distX>0)
     	{
     		atual.setX(atual.getX()-Pecas.SLIDE_SPEED);
     	}
     	if(distY>0)
     	{
     		atual.setY(atual.getY()-Pecas.SLIDE_SPEED);
     	}
    	
    	
    }
    
    private boolean mover(int lin, int col, int direcaoHorizontal, int direcaoVertical, Direcao dir){
    
    boolean  podeMover = false;
    Pecas atual = tab[lin][col];
    if(atual==null)return false;
    boolean mover = true;
    int novaColuna = col;
    int novaLinha = lin;
    
    while(mover) 
    {
    	novaColuna +=direcaoHorizontal; 
    	novaLinha += direcaoVertical;
    	if(verificaLimites(dir,novaLinha,novaColuna ))break;
    	
    	if(tab[novaLinha][novaColuna]==null) //checa se a peca eh nula ou espaco vazio
    	{
    		tab[novaLinha][novaColuna] = atual;
    		tab[novaLinha-direcaoVertical][novaColuna-direcaoHorizontal] = null;
    		tab[novaLinha][novaColuna].setMoverPara(new Pontos(novaLinha,novaColuna));
    		podeMover = true;
    	}
    	
    	else if(tab[novaLinha][novaColuna].getValue()==atual.getValue() && tab[novaLinha][novaColuna].podeCombinar()) {
    		tab[novaLinha][novaColuna].setPodeCombinar(false);
    		tab[novaLinha][novaColuna].setValue(tab[novaLinha][novaColuna].getValue()*2);
    		podeMover = true;
    		tab[novaLinha-direcaoVertical][novaColuna-direcaoHorizontal] = null;
    		tab[novaLinha][novaColuna].setMoverPara(new Pontos(novaLinha,novaColuna));
    		tab[novaLinha][novaColuna].setCombinarAnimacao(true);
    		pontuacao += tab[novaLinha][novaColuna].getValue();
    	}
    	else 
    	{
    		mover=false;
    	}
    }
    return podeMover;
    }
    private boolean verificaLimites(Direcao dir, int lin, int col) 
    {
	if(dir==Direcao.ESQ) 
	{
		return col < 0;
	}
	else if(dir==Direcao.DIR) 
	{
		return col > colunas - 1;
	}
	if(dir==Direcao.CIMA) 
	{
		return lin < 0;
	}
	if(dir==Direcao.BAIXO) 
	{
		return lin > linhas - 1;
	}
    return false;
    }

	private void moverPecas(Direcao dir) //move conforme escolhido
    {
    	boolean podeMover =false;
    	int direcaoHorizontal=0;
    	int direcaoVertical=0;
    	if(dir == Direcao.ESQ)
    	{
    		direcaoHorizontal=-1;
    		for(int lin = 0; lin < linhas;lin++)
    		{
    			for(int col = 0; col<colunas;col++)
    			{
    				if (!podeMover)
    				{
    					podeMover = mover(lin, col, direcaoHorizontal, direcaoVertical, dir);
    				}
    				else mover(lin, col, direcaoHorizontal, direcaoVertical, dir);
    			}
    		}
    	}
    	
    	if(dir == Direcao.DIR)
    	{
    		direcaoHorizontal=+1;
    		for(int lin = 0; lin < linhas;lin++)
    		{
    			for(int col = colunas - 1; col>=0;col--)
    			{
    				if (!podeMover)
    				{
    					podeMover = mover(lin, col, direcaoHorizontal, direcaoVertical, dir);
    				}
    				else mover(lin, col, direcaoHorizontal, direcaoVertical, dir);
    			}
    		}
    	}
    	
    	if(dir == Direcao.CIMA)
    	{
    		direcaoVertical=-1;
    		for(int lin =0; lin<linhas;lin++)
    		{
    			for(int col = 0; col<colunas;col++)
    			{
    				if (!podeMover)
    				{
    					podeMover = mover(lin, col, direcaoHorizontal, direcaoVertical, dir);
    				}
    				else mover(lin, col, direcaoHorizontal, direcaoVertical, dir);
    			}
    		}
    	}
    	
    	if(dir == Direcao.BAIXO)
    	{
    		direcaoVertical=1;
    		for(int lin = linhas-1; lin>=0;lin--)
    		{
    			for(int col = 0; col<colunas;col++)
    			{
    				if (!podeMover)
    				{
    					podeMover = mover(lin, col, direcaoHorizontal, direcaoVertical, dir);
    				}
    				else mover(lin, col, direcaoHorizontal, direcaoVertical, dir);
    			}
    		}
    	}
    	
    	else {
    		System.out.println(dir+"Direcao Invalida");
    	}
    	for(int lin = 0; lin<linhas;lin++)
    	{
    		for(int  col=0;col<colunas;col++)
    		{
    			Pecas atual = tab[lin][col];
    			if(atual == null) continue;
    			atual.setPodeCombinar(true);
    		}
    	}
    	if(podeMover)
    	{
    		spawnRandom();
    		checarMorto();
    	}
    }
	private void checarMorto() { //passa por todas pecas e ve se esta morto 
		for (int lin = 0; lin < linhas; lin++)
		{
			for(int col=0; col<colunas;col++)
			{
				if(tab[lin][col]==null) return;
				if(pecasVizinhas(lin,col,tab[lin][col]))
				{
					return;
				}
			}
		}
		morto = true;
		setHighScore();
	}
	
	private boolean pecasVizinhas(int lin, int col, Pecas atual) {
		if(lin>0) {
			Pecas checar = tab[lin-1][col];
			if(checar == null) return true;
			if(atual.getValue()==checar.getValue()) return true;
		}
		if(lin < linhas -1) {
			Pecas checar = tab[lin+1][col];
			if(checar == null) return true;
			if(atual.getValue()==checar.getValue()) return true;
		}
		if(col>0) {
			Pecas checar = tab[lin][col-1];
			if(checar == null) return true;
			if(atual.getValue()==checar.getValue()) return true;
		}
		if(col < colunas -1) {
			Pecas checar = tab[lin][col+1];
			if(checar == null) return true;
			if(atual.getValue()==checar.getValue()) return true;
		}
		return false;	
	}
    
	public void checkKeys()
    {
        if(Teclado.escolha(KeyEvent.VK_LEFT))
        {
        	moverPecas(Direcao.ESQ);
        	if(!hasStarted) hasStarted = true;
        }
        if(Teclado.escolha(KeyEvent.VK_RIGHT))
        {
        	moverPecas(Direcao.DIR);
        	if(!hasStarted) hasStarted = true;
        }
        if(Teclado.escolha(KeyEvent.VK_UP))
        {
        	moverPecas(Direcao.CIMA);
        	if(!hasStarted) hasStarted = true;
        }
        if(Teclado.escolha(KeyEvent.VK_DOWN))
        {
        	moverPecas(Direcao.BAIXO);
        	if(!hasStarted) hasStarted = true;
        }
    }
}

