package algoritmos.carzane;

import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.fmt;

public class Cartaze {

    public static final String PONTO_AMARELO="Amarelo";
    public static final String PONTO_VERMELHO="Vermelho";
    public static final String PONTO_CINZA="Cinza";
    public static final String PONTO_VERDE="Verde";
    public static final String PONTO_AZUL="Azul";
    public static final String PONTO_PRETO="Preto";
    public static final String PONTO_BRANCO="Branco";

    public static void init(){

        int tron_anterior=-1;

        Cartaze cartaze = new Cartaze();

        for(int s=0;s<6000;s++){

            if(cartaze.mPontos.get(0).getTronarko()!=tron_anterior){

                ENTT.EXIBIR_TABELA(cartaze.toDados());

                tron_anterior=cartaze.mPontos.get(0).getTronarko();
            }

            cartaze.passarSuperarko();

        }

        if(cartaze.mPontos.get(0).getTronarko()!=tron_anterior){

            ENTT.EXIBIR_TABELA(cartaze.toDados());

            tron_anterior=cartaze.mPontos.get(0).getTronarko();
        }



    }


    private Lista<Ponto> mPontos;
private int mSuperarko=0;
private int mTronarko=0;

    public Cartaze(){
        mPontos = new Lista<Ponto>();

        mPontos.adicionar(CRIAR_PONTO_AMARELO());
        mPontos.adicionar(CRIAR_PONTO_VERMELHO());
        mPontos.adicionar(CRIAR_PONTO_VERDE());
        mPontos.adicionar(CRIAR_PONTO_AZUL());
        mPontos.adicionar(CRIAR_PONTO_CINZA());
        mPontos.adicionar(CRIAR_PONTO_PRETO());
        mPontos.adicionar(CRIAR_PONTO_BRANCO());

    }

    public Lista<Ponto> getPontos(){return mPontos;}

    public int getSuperarko(){return mSuperarko;}
    public int getTronarko(){return mTronarko;}

    private Ponto CRIAR_PONTO_AMARELO(){
        Ponto amarelo = new  Ponto(PONTO_AMARELO,50,22,60,0,1);

        amarelo.adicionar_movimento(new Movimento(Movimento.ValorAlterar,+1));
        amarelo.adicionar_movimento(new Movimento(Movimento.SeValorIgual,50,Movimento.XAlterar,+1));
        amarelo.adicionar_movimento(new Movimento(Movimento.SeValorIgual,80,Movimento.YAlterar,+1));
        amarelo.adicionar_movimento(new Movimento(Movimento.SeValorIgual,100,Movimento.ValorDefinir,20));

        return amarelo;
    }

    private Ponto CRIAR_PONTO_VERMELHO(){
        Ponto vermelho = new   Ponto(PONTO_VERMELHO,80,5,10,0,1);

        vermelho.adicionar_movimento(new Movimento(Movimento.ValorAlterar,-1));
        vermelho.adicionar_movimento(new Movimento(Movimento.SeValorIgual,90,Movimento.XAlterar,-1));
        vermelho.adicionar_movimento(new Movimento(Movimento.SeValorIgual,20,Movimento.YAlterar,-1));
        vermelho.adicionar_movimento(new Movimento(Movimento.SeValorIgual,0,Movimento.ValorDefinir,150));

        return vermelho;
    }

    private Ponto CRIAR_PONTO_CINZA(){
        Ponto cinza = new  Ponto(PONTO_CINZA,60,90,80,0,1);

        cinza.adicionar_movimento(new Movimento(Movimento.ValorAlterar,+1));
        cinza.adicionar_movimento(new Movimento(Movimento.SeValorIgual,50,Movimento.XAlterar,+1));
        cinza.adicionar_movimento(new Movimento(Movimento.SeValorIgual,100,Movimento.XAlterar,+1));
        cinza.adicionar_movimento(new Movimento(Movimento.SeValorIgual,120,Movimento.YAlterar,+1));
        cinza.adicionar_movimento(new Movimento(Movimento.SeValorIgual,150,Movimento.ValorDefinir,60));

        return cinza;
    }

    private Ponto CRIAR_PONTO_VERDE(){
        Ponto cinza = new  Ponto(PONTO_VERDE,30,40,5,0,1);

        cinza.adicionar_movimento(new Movimento(Movimento.ValorAlterar,+1));
        cinza.adicionar_movimento(new Movimento(Movimento.SeValorIgual,40,Movimento.XAlterar,+1));
        cinza.adicionar_movimento(new Movimento(Movimento.SeValorIgual,80,Movimento.XAlterar,+1));
        cinza.adicionar_movimento(new Movimento(Movimento.SeValorIgual,160,Movimento.ValorDefinir,5));

        return cinza;
    }

    private Ponto CRIAR_PONTO_AZUL(){
        Ponto cinza = new  Ponto(PONTO_AZUL,60,90,30,0,1);

        cinza.adicionar_movimento(new Movimento(Movimento.ValorAlterar,+1));
        cinza.adicionar_movimento(new Movimento(Movimento.SeValorIgual,60,Movimento.YAlterar,+1));
        cinza.adicionar_movimento(new Movimento(Movimento.SeValorIgual,120,Movimento.YAlterar,+1));
        cinza.adicionar_movimento(new Movimento(Movimento.SeValorIgual,180,Movimento.ValorDefinir,30));

        return cinza;
    }

    private Ponto CRIAR_PONTO_BRANCO(){
        Ponto cinza = new  Ponto(PONTO_BRANCO,0,0,0,0,1);

        cinza.adicionar_movimento(new Movimento(Movimento.ValorAlterar,+1));
        cinza.adicionar_movimento(new Movimento(Movimento.SeValorIgual,10,Movimento.XAlterar,+1));
        cinza.adicionar_movimento(new Movimento(Movimento.SeValorIgual,10,Movimento.YAlterar,+1));

        cinza.adicionar_movimento(new Movimento(Movimento.SeValorIgual,120,Movimento.XAlterar,-2));

        cinza.adicionar_movimento(new Movimento(Movimento.SeValorIgual,150,Movimento.ValorDefinir,0));

        return cinza;
    }

    private Ponto CRIAR_PONTO_PRETO(){
        Ponto cinza = new  Ponto(PONTO_PRETO,100,100,100,0,1);

        cinza.adicionar_movimento(new Movimento(Movimento.ValorAlterar,-1));
        cinza.adicionar_movimento(new Movimento(Movimento.SeValorIgual,50,Movimento.XAlterar,-1));
        cinza.adicionar_movimento(new Movimento(Movimento.SeValorIgual,50,Movimento.YAlterar,-1));

        cinza.adicionar_movimento(new Movimento(Movimento.SeValorIgual,20,Movimento.YAlterar,+2));

        cinza.adicionar_movimento(new Movimento(Movimento.SeValorIgual,0,Movimento.ValorDefinir,100));

        return cinza;
    }

    public void passarSuperarko(){

        for(Ponto p : mPontos){

            for(Movimento mov : p.getMovimentos()){
                mov.aplicar(p);
            }


            if(p.getX()<=0){
                p.setX(100+p.getX());
            }
            if(p.getY()<=0){
                p.setY(100+p.getY());
            }

            if(p.getX()>100){
                p.setX(p.getX()-100);
            }
            if(p.getY()>100){
                p.setY(p.getY()-100);
            }

            p.setSuperarko(p.getSuperarko()+1);
            if(p.getSuperarko()>500){
                p.setSuperarko(1);
                p.setTronarko(p.getTronarko()+1);
            }
        }

        mSuperarko+=1;
        if(mSuperarko>500){
            mSuperarko=1;
           mTronarko+=1;
        }
    }


    public Lista<Entidade> toDados(){
        Lista<Entidade> entts = new Lista<Entidade>();

        for(Ponto ponto : mPontos){
            Entidade e = new Entidade();
            e.at("Nome",ponto.getNome());
            e.at("X",ponto.getX());
            e.at("Y",ponto.getY());
            e.at("Valor",ponto.getValor());

            e.at("Tronarko",ponto.getTronarko());
            e.at("Superarko",ponto.getSuperarko());

            entts.adicionar(e);
        }

        return entts;
    }


}
