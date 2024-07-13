package algoritmos.carzane;

import apps.app_letrum.Fonte;
import apps.app_letrum.Maker.FonteRunTime;
import libs.azzal.AzzalUnico;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.Windows;
import libs.azzal.cenarios.Cena;
import libs.azzal.utilitarios.Cor;
import libs.luan.Strings;
import libs.luan.Vetor;
import libs.luan.fmt;

import java.awt.*;

public class AppCartaze extends Cena {

    public static void INIT(){

        AzzalUnico.unico("App Cartaze", 1700, 1080, new AppCartaze());

    }

    private Cores mCores;
    private Vetor<QuadranteCartaze> mQuadrantes;
private   Cartaze mCartaze;

    private Fonte ESCRITOR_NORMAL;


    @Override
    public void iniciar(Windows eWindows) {
        mCores = new Cores();
        mQuadrantes=new Vetor<QuadranteCartaze>(101*101);

        for(int y=0;y<=100;y++) {
            for(int x=0;x<=100;x++) {
                mQuadrantes.set(x+(y*100), new QuadranteCartaze(x,y));
            }
        }



        limpar();
         mCartaze = new Cartaze();


        ESCRITOR_NORMAL = new FonteRunTime(Cor.getRGB(Color.WHITE), 30);


    }

    public void limpar(){
        for(int y=0;y<100;y++) {
            for(int x=0;x<100;x++) {
                mQuadrantes.get(x+(y*100)).limpar();
            }
        }
    }

        public QuadranteCartaze GET_QUADRANTE(int x,int y){
       // fmt.print("X = {} , Y = {}",x,y);

        return  mQuadrantes.get(x+(y*100));
        }

    @Override
    public void update(double dt) {

        int px = getWindows().getMouse().getX();
        int py = getWindows().getMouse().getY();

        limpar();
        mCartaze.passarSuperarko();

        for(Ponto p : mCartaze.getPontos()){

            QuadranteCartaze corrente= GET_QUADRANTE(p.getX(),p.getY());
            corrente.adicionar(p.getNome());

        }

    }

    @Override
    public void draw(Renderizador g) {

        g.limpar(mCores.getPreto());
        ESCRITOR_NORMAL.setRenderizador(g);


        ESCRITOR_NORMAL.escreva(1200,300,"Superarko : "+mCartaze.getSuperarko());
        ESCRITOR_NORMAL.escreva(1200,350,"Tronarko  : "+mCartaze.getTronarko());

        int colunas = 101;
        int linhas = 101;

        int CELULA = 10;
        int CELULA_ITEM = 5;

        CELULA_ITEM=CELULA;

        int coluna_inicio = 50;
        int linha_inicio = 50;

        int linha_fim = linha_inicio+(colunas*CELULA);
        int coluna_fim = coluna_inicio+(linhas*CELULA);



        for(int y=0;y<=100;y++) {
            for(int x=0;x<=100;x++) {

                QuadranteCartaze corrente= GET_QUADRANTE(x,y);

                int px = coluna_inicio+(x*CELULA);
                int py = linha_inicio+(y*CELULA);

                if(corrente.itens().existe(Strings.IGUALAVEL(),"Verde")) {
                    g.drawRect_Pintado(px, py, CELULA_ITEM,CELULA_ITEM, mCores.getVerde());
                }else   if(corrente.itens().existe(Strings.IGUALAVEL(),"Amarelo")){
                    g.drawRect_Pintado(px,py,CELULA_ITEM,CELULA_ITEM,mCores.getAmarelo());
                }else   if(corrente.itens().existe(Strings.IGUALAVEL(),"Vermelho")){
                    g.drawRect_Pintado(px,py,CELULA_ITEM,CELULA_ITEM,mCores.getVermelho());
                }else   if(corrente.itens().existe(Strings.IGUALAVEL(),"Azul")){
                    g.drawRect_Pintado(px,py,CELULA_ITEM,CELULA_ITEM,mCores.getAzul());
                }else   if(corrente.itens().existe(Strings.IGUALAVEL(),"Preto")){
                    g.drawRect_Pintado(px,py,CELULA_ITEM,CELULA_ITEM,mCores.getPreto());

                    g.drawRect_Pintado(px+(CELULA_ITEM/4),py+(CELULA_ITEM/4),CELULA_ITEM/2,CELULA_ITEM/2,mCores.getVerde());

                }else   if(corrente.itens().existe(Strings.IGUALAVEL(),"Branco")){
                    g.drawRect_Pintado(px,py,CELULA_ITEM,CELULA_ITEM,mCores.getBranco());
                }else   if(corrente.itens().existe(Strings.IGUALAVEL(),"Cinza")){
                    g.drawRect_Pintado(px,py,CELULA_ITEM,CELULA_ITEM,mCores.getCinza());
                }


            }
        }

        for(int coluna=0;coluna<=colunas;coluna++){
            g.drawLinha(coluna_inicio+(coluna*CELULA),linha_inicio,coluna_inicio+(coluna*CELULA),linha_fim,mCores.getBranco());
        }

        for(int linha=0;linha<=linhas;linha++){
            g.drawLinha(coluna_inicio,linha_inicio+(linha*CELULA),coluna_fim,linha_inicio+(linha*CELULA),mCores.getBranco());
        }

    }

}
