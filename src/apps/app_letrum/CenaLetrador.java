package apps.app_letrum;

import libs.azzal.cenarios.Cena;
import libs.azzal.geometria.Retangulo;
import libs.azzal.Mouse;
import libs.azzal.Renderizador;
import libs.azzal.Teclado;
import libs.azzal.utilitarios.Cor;
import libs.azzal.Windows;
import apps.app_letrum.Maker.FonteRunTime;
import libs.luan.ArquivoTexto;

import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class CenaLetrador extends Cena {

    private Fonte mLetramento;


    private BufferedImage mImagem;
    private int[] mPixels;
    private int ma;
    private int ml;

    private Mouse mMouse;
    private Teclado mTeclado;

    private int mPosicaoX;

    private int eComecarX;
    private int eComecarY;

    private boolean mMarcado;
    private int mMarcadoX;

    private WidgetTextBox mWidgetTextBox1;
    private WidgetTextBox mWidgetTextBox2;

    private ArrayList<Integer> mAcima;
    private ArrayList<Integer> mAbaixo;

    private Retangulo mIniciar;
    private Retangulo mTerminar;

    public CenaLetrador() {


        mLetramento = new FonteRunTime(new Cor(255,0,0),10);

        mIniciar = new Retangulo(1000, 400, 100, 25);
        mTerminar = new Retangulo(1150, 400, 100, 25);

        try {
            mImagem = ImageIO.read(new File("/home/luan/Documentos/fonte8.png"));
            ma = mImagem.getHeight();
            ml = mImagem.getWidth();

            int d = 6;
            int s = 4;

            d = 2;
            s = 2;

            int q = (ma - d - s) * (ml);

            mPixels = new int[q];

            int geral = 0;


            for (int y = d; y < (ma - s); y++) {
                for (int x = 0; x < ml; x++) {
                    mPixels[geral] = mImagem.getRGB(x, y);
                    geral += 1;
                }
            }

            ma = ma - d - s;

            GeradorDeFonte eGeradorDeFonte = new GeradorDeFonte();
            eGeradorDeFonte.gerar(mPixels, ml, ma, "FontePadrao", "res/fonte08.txt");


        } catch (IOException e) {
            e.printStackTrace();
        }

        mPosicaoX = 0;

        eComecarX = 10;
        eComecarY = 100;

        mMarcado = false;
        mMarcadoX = 0;

        mWidgetTextBox1 = new WidgetTextBox(50, 350, mLetramento);
        mWidgetTextBox1.setTexto("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

        mWidgetTextBox2 = new WidgetTextBox(50, 400, mLetramento);

        mAcima = new ArrayList<Integer>();
        mAbaixo = new ArrayList<Integer>();

        carregar();
    }


    @Override
    public void iniciar(Windows eWindows) {

        eWindows.setTitle("Cena Modelo");


        mMouse = eWindows.getMouse();
        mTeclado = eWindows.getTeclado();

    }


    @Override
    public void update(double dt) {


        mWidgetTextBox1.update();
        mWidgetTextBox2.update();

        if (mMouse.isClicked()) {
            if (mWidgetTextBox1.estaDentro(mMouse.getX(), mMouse.getY())) {
                mWidgetTextBox1.editar();
                mWidgetTextBox2.bloquear();
            } else if (mWidgetTextBox2.estaDentro(mMouse.getX(), mMouse.getY())) {
                mWidgetTextBox2.editar();
                mWidgetTextBox1.bloquear();

            }
        }

        if (mWidgetTextBox1.estaEditando()) {

            mWidgetTextBox1.receberTeclado(mTeclado);

        } else if (mWidgetTextBox2.estaEditando()) {

            mWidgetTextBox2.receberTeclado(mTeclado);

        } else {

            if (mTeclado.foiPressionado(KeyEvent.VK_T)) {
                mWidgetTextBox1.editar();
            }


            if (mTeclado.foiPressionado(KeyEvent.VK_A)) {
                mPosicaoX += 1;
                System.out.println("Posicao X :: " + (mPosicaoX));
            } else if (mTeclado.foiPressionado(KeyEvent.VK_S)) {
                mPosicaoX += 2;
                System.out.println("Posicao X :: " + (mPosicaoX));
            } else if (mTeclado.foiPressionado(KeyEvent.VK_D)) {
                mPosicaoX += 3;
                System.out.println("Posicao X :: " + (mPosicaoX));
            } else if (mTeclado.foiPressionado(KeyEvent.VK_F)) {
                mPosicaoX += 4;
                System.out.println("Posicao X :: " + (mPosicaoX));
            } else if (mTeclado.foiPressionado(KeyEvent.VK_G)) {
                mPosicaoX += 5;
                System.out.println("Posicao X :: " + (mPosicaoX));
            } else if (mTeclado.foiPressionado(KeyEvent.VK_H)) {
                mPosicaoX += 6;
                System.out.println("Posicao X :: " + (mPosicaoX));
            } else if (mTeclado.foiPressionado(KeyEvent.VK_J)) {
                mPosicaoX += 7;
                System.out.println("Posicao X :: " + (mPosicaoX));
            } else if (mTeclado.foiPressionado(KeyEvent.VK_K)) {
                mPosicaoX += 8;
                System.out.println("Posicao X :: " + (mPosicaoX));
            } else if (mTeclado.foiPressionado(KeyEvent.VK_RIGHT)) {
                mPosicaoX += 10;
                System.out.println("Posicao X :: " + (mPosicaoX));
            }

            if (mTeclado.foiPressionado(KeyEvent.VK_Q)) {
                mPosicaoX -= 1;
                System.out.println("Posicao X :: " + (mPosicaoX));
            } else if (mTeclado.foiPressionado(KeyEvent.VK_W)) {
                mPosicaoX -= 2;
                System.out.println("Posicao X :: " + (mPosicaoX));
            } else if (mTeclado.foiPressionado(KeyEvent.VK_E)) {
                mPosicaoX -= 3;
                System.out.println("Posicao X :: " + (mPosicaoX));
            } else if (mTeclado.foiPressionado(KeyEvent.VK_R)) {
                mPosicaoX -= 4;
                System.out.println("Posicao X :: " + (mPosicaoX));
            } else if (mTeclado.foiPressionado(KeyEvent.VK_LEFT)) {
                mPosicaoX -= 10;
                System.out.println("Posicao X :: " + (mPosicaoX));
            }

            if (mPosicaoX < 0) {
                mPosicaoX = 0;
            }

            if (mTeclado.foiPressionado(KeyEvent.VK_M)) {

                if (mMarcado) {

                    if (mPosicaoX != mMarcadoX) {
                        mMarcadoX = mPosicaoX;
                    } else {
                        mMarcado = false;
                    }

                } else {
                    mMarcado = true;
                    mMarcadoX = mPosicaoX;
                }

            }

            if (mTeclado.foiPressionado(KeyEvent.VK_UP)) {
                if (!mAcima.contains(mPosicaoX)) {
                    mAcima.add(mPosicaoX);
                }
            }
            if (mTeclado.foiPressionado(KeyEvent.VK_DOWN)) {
                if (!mAbaixo.contains(mPosicaoX)) {
                    mAbaixo.add(mPosicaoX);
                }
            }
            if (mTeclado.foiPressionado(KeyEvent.VK_P)) {


                String tAcima = "";
                for (Integer eAcima : mAcima) {
                    tAcima += eAcima + " ";
                }

                ArquivoTexto.arquivo_escrever("res/acima.txt", tAcima);

                String tAbaixo = "";
                for (Integer eAcima : mAbaixo) {
                    tAbaixo += eAcima + " ";
                }

                ArquivoTexto.arquivo_escrever("res/abaixo.txt", tAbaixo);


            }

            if (mTeclado.foiPressionado(KeyEvent.VK_L)) {

                carregar();

            }

            if (mTeclado.foiPressionado(KeyEvent.VK_SPACE)) {

                associar();

            }

        }

        mMouse.liberar();
    }



    public void carregar(){

        mAcima.clear();
        mAbaixo.clear();

        carregarAcima();
        carregarAbaixo();

    }

    public void carregarAcima() {
        String tAcima = ArquivoTexto.arquivo_ler("res/acima.txt");
        int i = 0;
        int o = tAcima.length();
        String mCorrente = "";

        while (i < o) {
            String l = String.valueOf(tAcima.charAt(i));

            if (l.contentEquals(" ")) {
                if (mCorrente.length() > 0) {
                    mAcima.add(Integer.parseInt(mCorrente));
                    mCorrente = "";
                }
            } else {
                mCorrente += l;
            }
            i += 1;
        }
    }

    public void carregarAbaixo() {
        String tAbaixo = ArquivoTexto.arquivo_ler("res/abaixo.txt");
        int i = 0;
        int o = tAbaixo.length();
        String mCorrente = "";

        while (i < o) {
            String l = String.valueOf(tAbaixo.charAt(i));

            if (l.contentEquals(" ")) {
                if (mCorrente.length() > 0) {
                    mAbaixo.add(Integer.parseInt(mCorrente));
                    mCorrente = "";
                }
            } else {
                mCorrente += l;
            }
            i += 1;
        }
    }

    @Override
    public void draw(Renderizador mRenderizador) {

        mRenderizador.limpar(new Cor(255, 255, 255));


        mLetramento.setRenderizador(mRenderizador);

        mRenderizador.drawPixels(eComecarX, eComecarY, mPixels, ml, ma);

        for (int y = 0; y < mLetramento.getAltura() + 10; y++) {
            mRenderizador.drawPixel(eComecarX + mPosicaoX, (eComecarY - 5) + y, new Cor(255, 0, 0));
        }

        for (int x = 0; x < 5; x++) {
            mRenderizador.drawPixel(eComecarX + mPosicaoX - x, (eComecarY) + mLetramento.getAltura() + 5, new Cor(255, 0, 0));
            mRenderizador.drawPixel(eComecarX + mPosicaoX - x, (eComecarY) + mLetramento.getAltura() + 6, new Cor(255, 0, 0));

            mRenderizador.drawPixel(eComecarX + mPosicaoX - x, (eComecarY) + mLetramento.getAltura() + 8, new Cor(255, 0, 0));
            mRenderizador.drawPixel(eComecarX + mPosicaoX - x, (eComecarY) + mLetramento.getAltura() + 9, new Cor(255, 0, 0));

        }

        for (int x = 0; x < 5; x++) {
            mRenderizador.drawPixel(eComecarX + mPosicaoX + x, (eComecarY) + mLetramento.getAltura() + 5, new Cor(255, 0, 0));
            mRenderizador.drawPixel(eComecarX + mPosicaoX + x, (eComecarY) + mLetramento.getAltura() + 6, new Cor(255, 0, 0));

            mRenderizador.drawPixel(eComecarX + mPosicaoX + x, (eComecarY) + mLetramento.getAltura() + 8, new Cor(255, 0, 0));
            mRenderizador.drawPixel(eComecarX + mPosicaoX + x, (eComecarY) + mLetramento.getAltura() + 9, new Cor(255, 0, 0));

        }


        mLetramento.escreva(50, 250, "POSICAO = " + mPosicaoX);

        if (mMarcado) {

            for (int y = 0; y < mLetramento.getAltura() + 10; y++) {
                mRenderizador.drawPixel(eComecarX + mMarcadoX, (eComecarY - 5) + y, new Cor(150, 200, 0));
            }

            mLetramento.escreva(50, 300, "MARCADO = " + mMarcadoX);
        }


        mWidgetTextBox1.render(mRenderizador);
        mWidgetTextBox2.render(mRenderizador);


        mRenderizador.drawPixels(eComecarX, 600, mPixels, ml, ma);

        for (Integer eAcima : mAcima) {
            for (int y = 0; y < mLetramento.getAltura() + 10; y++) {
                mRenderizador.drawPixel(eComecarX + eAcima, (600 - 5) + y, new Cor(150, 200, 0));
            }
        }

        mRenderizador.drawPixels(eComecarX, 650, mPixels, ml, ma);

        for (Integer eAbaixo : mAbaixo) {
            for (int y = 0; y < mLetramento.getAltura() + 10; y++) {
                mRenderizador.drawPixel(eComecarX + eAbaixo, (650 - 5) + y, new Cor(255, 0, 0));
            }
        }


        mRenderizador.drawRect_Pintado(mIniciar, new Cor(0, 255, 0));
        mRenderizador.drawRect_Pintado(mTerminar, new Cor(255, 0, 0));



    }


    public void associar() {


        String mFaixa = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789.:;=+-(){}[]<>/\\";

        mAcima = ordenarForcaBruta(mAcima);
        mAbaixo = ordenarForcaBruta(mAbaixo);

        int i = 0;
        int o = mFaixa.length();

        while (i < o) {
            String l = String.valueOf(mFaixa.charAt(i));

            String eAcima = "";
            String eAbaixo = "";
            int ea = 0;
            int eb = 0;

            if (i < mAcima.size()) {
                eAcima = String.valueOf(mAcima.get(i));
                ea = mAcima.get(i);
            }
            if (i < mAbaixo.size()) {
                eAbaixo = String.valueOf(mAbaixo.get(i));
                eb = mAbaixo.get(i);

            }

            while (eAcima.length() < 3) {
                eAcima = "0" + eAcima;
            }
            while (eAbaixo.length() < 3) {
                eAbaixo = "0" + eAbaixo;
            }
            // System.out.println("Letra " + l + " -->> [ " + eAcima + " , " + eAbaixo + " ] ");


            System.out.println(" this.adicionarLetra(\"" + l + "\", " + ea + ", " + eb + "); ");

            i += 1;
        }

    }


    public ArrayList<Integer> ordenarForcaBruta(ArrayList<Integer> numeros) {

        ArrayList<Integer> mOrdem = new ArrayList<Integer>();

        while (numeros.size() > 0) {
            int menor = numeros.get(0);
            int pos = 0;
            int posmenor = 0;
            for (Integer numero : numeros) {
                if (numero < menor) {
                    menor = numero;
                    posmenor = pos;
                }
                pos += 1;
            }
            mOrdem.add(menor);
            numeros.remove(posmenor);
        }


        return mOrdem;
    }


}
