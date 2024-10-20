package apps.app_atzum.apps;

import apps.app_atzum.AtzumCreator;
import apps.app_atzum.utils.MassaDeAr;
import apps.app_letrum.Fonte;
import apps.app_letrum.Maker.FonteRunTime;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.Windows;
import libs.azzal.cenarios.Cena;
import libs.azzal.geometria.Ponto;
import libs.azzal.utilitarios.Cronometro;
import libs.imagem.Efeitos;
import libs.luan.*;
import libs.mockui.Interface.Acao;
import libs.mockui.Interface.BotaoCor;
import libs.mockui.Interface.Clicavel;

import java.awt.image.BufferedImage;

public class AtzumAppMassasDeAr extends Cena {

    private String LOCAL = "/home/luan/Imagens/atzum/";
    private BufferedImage mapa = null;

    private int X0 = 300;
    private int Y0 = 100;

    private int X1 = 0;
    private int Y1 = 0;


    private Cores mCores;


    private Fonte ESCRITOR_NORMAL;
    private Fonte ESCRITOR_NORMAL_VERMELHO;
    private Fonte ESCRITOR_NORMAL_AZUL;
    private Fonte ESCRITOR_BRANCO_GRANDE;

    private boolean mGPS_OK = false;
    private int mGPS_PX = 0;
    private int mGPS_PY = 0;


    private RefBool EXECUTANDO = new RefBool(false);

    private Clicavel mClicavel;
    private BotaoCor BTN_ZERAR;

    private Lista<MassaDeAr> mMassasDeAr;
    private Cronometro mCron;

    private int superarko=1;


    @Override
    public void iniciar(Windows eWindows) {
        mCores = new Cores();

        ESCRITOR_NORMAL = new FonteRunTime(mCores.getVerde(), 10);
        ESCRITOR_NORMAL_VERMELHO = new FonteRunTime(mCores.getVermelho(), 10);
        ESCRITOR_NORMAL_AZUL = new FonteRunTime(mCores.getAzul(), 10);
        ESCRITOR_BRANCO_GRANDE= new FonteRunTime(mCores.getBranco(), 50);

        mapa = AtzumCreator.GET_RENDER_FUNDO_PRETO_MARGEM_OCEANICA().toImagemSemAlfa();



        Renderizador render2 = new Renderizador(mapa);


        mapa = render2.toImagemSemAlfa();


        mapa = Efeitos.reduzir(mapa, mapa.getWidth() / 2, mapa.getHeight() / 2);

        X1 = X0 + mapa.getWidth();
        Y1 = Y1 + mapa.getHeight();




        mClicavel = new Clicavel();

        BTN_ZERAR = mClicavel.criarBotaoCor(new BotaoCor(200, 50, 50, 50, mCores.getPreto()));
        BTN_ZERAR.setAcao(new Acao() {
            @Override
            public void onClique() {
                if (EXECUTANDO.get()) {
                    EXECUTANDO.set(false);
                } else {
                    EXECUTANDO.set(true);
                }
            }
        });


        mMassasDeAr = new Lista<MassaDeAr>();


        mMassasDeAr.adicionar(new MassaDeAr("MIZ_A", "miz", "FRIO", 100, 1));
        mMassasDeAr.adicionar(new MassaDeAr("MOP_A", "mop", "QUENTE", 300, 1));
        mMassasDeAr.adicionar(new MassaDeAr("MUT_A", "mut", "FRIO", 100, 1));
        mMassasDeAr.adicionar(new MassaDeAr("MOX_A", "mox", "FRIO", 100, 1));

        mMassasDeAr.adicionar(new MassaDeAr("RAF_A", "raf", "QUENTE", 50, 1));
        mMassasDeAr.adicionar(new MassaDeAr("REZ_A", "rez", "QUENTE", 50, 1));
        mMassasDeAr.adicionar(new MassaDeAr("RUC_A", "ruc", "QUENTE", 50, 1));

        mMassasDeAr.adicionar(new MassaDeAr("REC_B", "rez", "QUENTE", 300, -1));
     //   mMassasDeAr.adicionar(new MassaDeAr("MUT_B", "mut", "FRIO", 400-300, -1));
        mMassasDeAr.adicionar(new MassaDeAr("RAF_B", "raf", "FRIO", 500, 1));


        mMassasDeAr.adicionar(new MassaDeAr("MUT_C", "mut", "FRIO", 250+300, -1));
        mMassasDeAr.adicionar(new MassaDeAr("REZ_C", "rez", "QUENTE", 400, -1));
        mMassasDeAr.adicionar(new MassaDeAr("RAF_C", "raf", "QUENTE", 400, -1));
        mMassasDeAr.adicionar(new MassaDeAr("RUC_C", "ruc", "QUENTE", 450+130, 1));


        mCron = new Cronometro(100);

    }


    @Override
    public void update(double dt) {

        int px = getWindows().getMouse().getX();
        int py = getWindows().getMouse().getY();

        mGPS_OK = false;

        if (px >= X0 && py >= Y0 && px < X1 && py < Y1) {
            mGPS_OK = true;
            mGPS_PX = ((px - X0) * 2);
            mGPS_PY = ((py - Y0) * 2) - 5;
        }

        mClicavel.update(dt, px, py, getWindows().getMouse().isPressed());

        if (getWindows().getMouse().isClicked()) {

            if (mGPS_OK) {


            }

            getWindows().getMouse().liberar();
        }

        mCron.esperar();
        if (mCron.foiEsperado()) {

            if (EXECUTANDO.get()) {

                for (MassaDeAr massa : mMassasDeAr) {
                    massa.proximo();
                }
                fmt.print(mMassasDeAr.get(0).getIndice());

                superarko+=1;
                if(superarko>500){
                    superarko=1;
                }

            }


        }


    }

    @Override
    public void draw(Renderizador g) {

        g.limpar(mCores.getPreto());

        ESCRITOR_NORMAL.setRenderizador(g);
        ESCRITOR_NORMAL_VERMELHO.setRenderizador(g);
        ESCRITOR_NORMAL_AZUL.setRenderizador(g);
        ESCRITOR_BRANCO_GRANDE.setRenderizador(g);




            g.drawImagem(X0, Y0, mapa);
        mClicavel.onDraw(g);

        if (EXECUTANDO.get()) {
            g.drawRect_Pintado(BTN_ZERAR.getX(), BTN_ZERAR.getY(),BTN_ZERAR.getLargura(),BTN_ZERAR.getAltura(),mCores.getVerde() );
        }else{
            g.drawRect_Pintado(BTN_ZERAR.getX(), BTN_ZERAR.getY(),BTN_ZERAR.getLargura(),BTN_ZERAR.getAltura(),mCores.getVermelho() );
        }




        if (mGPS_OK) {
            ESCRITOR_NORMAL.escreva(50, 50, "GPS ON");
            ESCRITOR_NORMAL.escreva(50, 70, "X = " + mGPS_PX);
            ESCRITOR_NORMAL.escreva(50, 90, "Y = " + mGPS_PY);
        } else {
            ESCRITOR_NORMAL_VERMELHO.escreva(50, 50, "GPS FAILED");
        }


        for (MassaDeAr massa : mMassasDeAr) {
            for (Ponto pt : massa.getPercurso()) {
                g.drawCirculoCentralizado_Pintado((pt.getX() / 2) + X0, (pt.getY() / 2) + Y0, 2, massa.getCor());
            }
        }


        int info_py = 300;

        for (MassaDeAr massa : mMassasDeAr) {

            g.drawCirculoCentralizado_Pintado((massa.getCorrente().getX() / 2) + X0, (massa.getCorrente().getY() / 2) + Y0, 20, massa.getCor());
            g.drawCirculoCentralizado_Pintado((massa.getCorrente().getX() / 2) + X0, (massa.getCorrente().getY() / 2) + Y0, 10, mCores.getPreto());
            g.drawCirculoCentralizado_Pintado((massa.getCorrente().getX() / 2) + X0, (massa.getCorrente().getY() / 2) + Y0, 5, massa.getCor());


            if(massa.isFrio()){
                ESCRITOR_NORMAL_AZUL.escreva((massa.getCorrente().getX() / 2) + X0, (massa.getCorrente().getY() / 2) + Y0 + 20, massa.getInfo());
                ESCRITOR_NORMAL_AZUL.escreva(50, info_py, massa.getInfo() + " :: "+massa.getIndice());
            }else{
                ESCRITOR_NORMAL_VERMELHO.escreva((massa.getCorrente().getX() / 2) + X0, (massa.getCorrente().getY() / 2) + Y0 + 20, massa.getInfo());
                ESCRITOR_NORMAL_VERMELHO.escreva(50, info_py, massa.getInfo() + " :: "+massa.getIndice());
            }

            info_py+=30;
        }

        int hiperarko = 1;
        int csuper = superarko;
        while(csuper>=50){
            hiperarko+=1;
            csuper-=50;
        }

        ESCRITOR_BRANCO_GRANDE.escreva(600,50,"Superarko "+ superarko + " :: "+hiperarko);
    }
}
