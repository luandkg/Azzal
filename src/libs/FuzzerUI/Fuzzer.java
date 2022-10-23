package libs.FuzzerUI;

import azzal.Renderizador;
import azzal.utilitarios.Cor;
import apps.appLetrum.Fonte;
import apps.appLetrum.Maker.FonteRunTime;
import azzal.utilitarios.Cronometro;
import mockui.Interface.Acao;

import java.util.ArrayList;

public class Fuzzer {

    private Cronometro mTempo;

    private boolean clicavel = false;
    private boolean mClicado = false;

    private ArrayList<Botao> mAcionadores;

    private ArrayList<Botao> mBotoes;
    private ArrayList<Botao> mBotoesAcoes;
    private ArrayList<Seletor> mSeletores;
    private ArrayList<Menu> mMenus;

    private ArrayList<Listagem> mListagens;

    private Cor mFundo;
    private Fonte mEscritorPequeno;
    private Fonte mEscritorPequeno2;


    public Fuzzer() {

        mTempo = new Cronometro(200);


        mAcionadores = new ArrayList<Botao>();


        mBotoes = new ArrayList<Botao>();
        mBotoesAcoes = new ArrayList<Botao>();
        mSeletores = new ArrayList<Seletor>();
        mMenus = new ArrayList<Menu>();

        mListagens = new ArrayList<Listagem>();

        mFundo = Cor.getHexCor("#0d191e");

        mEscritorPequeno = new FonteRunTime(new Cor(255, 255, 255), "roboto", 11, true);
        mEscritorPequeno2 = new FonteRunTime(new Cor(255, 255, 255), "roboto", 11, false);

    }

    public Cor getFundo() {
        return mFundo;
    }

    public Botao onBotao(int x, int y, int l, int a, String texto) {

        Botao eBotao = new Botao(x, y, l, a, texto);

        eBotao.setVariacao(new Cor(200, 200, 0), new Cor(200, 150, 0));

        mAcionadores.add(eBotao);
        mBotoes.add(eBotao);

        return eBotao;
    }

    public Botao onBotaoAcao(int x, int y, int l, int a, String texto) {

        Botao eBotao = new Botao(x, y, l, a, texto);

        mAcionadores.add(eBotao);
        mBotoesAcoes.add(eBotao);

        return eBotao;
    }


    public Seletor onSeletor(int x, int y, int l, int a, String texto) {

        Botao eBotao = new Botao(x, y, l, a, texto);

        eBotao.setVariacao(new Cor(200, 30, 0), new Cor(200, 30, 0));

        Seletor st = new Seletor(eBotao, 0, 1);
        mAcionadores.add(eBotao);

        mSeletores.add(st);


        return st;
    }


    public Menu onMenu(ArrayList<Menu> menu_principal,int x, int y, int l, int a, String texto) {

        Botao eBotao = new Botao(x, y, l, a, texto);

        eBotao.setVariacao(new Cor(200, 30, 0), new Cor(200, 30, 0));

        Menu mn = new Menu(eBotao,menu_principal);
        mAcionadores.add(eBotao);

        mMenus.add(mn);
        menu_principal.add(mn);

        return mn;
    }

    public void onListagem(Listagem eListagem) {
        mListagens.add(eListagem);

        Botao BTN_Menos = onBotaoAcao(eListagem.getX(), eListagem.getY() + (eListagem.getMaximo() * 42), 140, 15, "-");

        BTN_Menos.setAcao(new Acao() {
            @Override
            public void onClique() {
                if (eListagem.getIndice() > 0) {
                    eListagem.setIndice(eListagem.getIndice() - 1);
                }
            }
        });


        Botao BTN_Mais = onBotaoAcao(eListagem.getX() + 160, eListagem.getY() + (eListagem.getMaximo() * 42), 140, 15, "+");

        BTN_Mais.setAcao(new Acao() {
            @Override
            public void onClique() {
                if (eListagem.getIndice() < (eListagem.getItens().size() - eListagem.getMaximo())) {
                    eListagem.setIndice(eListagem.getIndice() + 1);
                }

            }
        });
    }

    public void update(double dt, int px, int py, boolean ePrecionado) {

        mClicado = false;

        mTempo.esperar();

        //   System.out.println("Pode..." + mTempo.get() + " :: " + mTempo.getFim()  + " ->> " + mTempo.foiEsperado());

        if (mTempo.foiEsperado()) {
            clicavel = true;
        }

        if (clicavel) {
            if (ePrecionado) {
                mTempo.zerar();
                clicavel = false;
                mClicado = true;
                //   System.out.println("Cliquei...");
            }
        }

        if (mClicado) {

            for (Botao eBotao : mAcionadores) {
                if (eBotao.temVariacao()) {
                    eBotao.setCor(eBotao.getCorNormal());
                }
            }

            for (Botao eBotao : mAcionadores) {
                if (eBotao.getClicado(px, py)) {

                    if (eBotao.temVariacao()) {
                        eBotao.setCor(eBotao.getCorPressionado());
                    }

                    if (eBotao.temAcao()) {
                        eBotao.clicar();
                        break;
                    }

                }
            }

        }

        if (!ePrecionado) {
            for (Botao eBotao : mAcionadores) {
                if (eBotao.temVariacao()) {
                    eBotao.setCor(eBotao.getCorNormal());
                }
            }
        }
    }

    public void onDraw(Renderizador mRenderizador) {
        for (Botao eBotao : mBotoes) {
            onBotao(mRenderizador, eBotao);
        }

        for (Botao eBotao : mBotoesAcoes) {
            onBotaoAcao(mRenderizador, eBotao);
        }




        for (Seletor eSeletor : mSeletores) {
            drawSeletor(mRenderizador, eSeletor);
        }

        for (Menu eMenu : mMenus) {
            drawMenu(mRenderizador, eMenu);
        }

        for (Listagem eListagem : mListagens) {
            drawListagem(mRenderizador, eListagem);
        }


    }

    public void drawListagem(Renderizador mRenderizador, Listagem eListagem) {


        int item_inicio = eListagem.getIndice();
        int item_fim = eListagem.getIndice() + eListagem.getMaximo();


        mEscritorPequeno.escreva(eListagem.getX(), eListagem.getY() - 15, eListagem.getNome());

        int dy = 5;
        int ii = 0;

        for (ItemAcao eItem : eListagem.getItens()) {
            if (ii >= item_inicio && ii < item_fim) {
                if (eItem.isDisponivel()) {
                    onBotaoVermelho(mRenderizador, eListagem.getX(), eListagem.getY() + dy, 300, 30, eItem.getTexto());
                } else {
                    onBotaoVermelhoOff(mRenderizador, eListagem.getX(), eListagem.getY() + dy, 300, 30, eItem.getTexto());
                }
                dy += 42;
            }
            ii += 1;
        }

        mRenderizador.drawRect(eListagem.getX() - 15, eListagem.getY() - 25, 330, 42 * eListagem.getMaximo() + 50, new Cor(255, 255, 255));

    }


    public void onBotao(Renderizador mRenderizador, Botao eBotao) {

        mRenderizador.drawRect_Pintado(eBotao.getX(), eBotao.getY(), eBotao.getL(), eBotao.getA(), eBotao.getCor());

        int la = mEscritorPequeno.getLarguraDe(eBotao.getTexto());
        if (la < eBotao.getL()) {
            int s = (eBotao.getL() - la) / 2;

            mEscritorPequeno.escreva(eBotao.getX() + s, eBotao.getY() + 5, eBotao.getTexto());

        } else {
            mEscritorPequeno.escreva(eBotao.getX() + 20, eBotao.getY() + 5, eBotao.getTexto());
        }
    }

    public void onBotaoAcao(Renderizador mRenderizador, Botao eBotao) {

        mRenderizador.drawRect_Pintado(eBotao.getX(), eBotao.getY(), eBotao.getL(), eBotao.getA(), new Cor(200, 200, 0));

        int la = mEscritorPequeno.getLarguraDe(eBotao.getTexto());
        if (la < eBotao.getL()) {
            int s = (eBotao.getL() - la) / 2;

            mEscritorPequeno.escreva(eBotao.getX() + s, eBotao.getY() - 2, eBotao.getTexto());

        } else {
            mEscritorPequeno.escreva(eBotao.getX() + 20, eBotao.getY() - 2, eBotao.getTexto());
        }

    }

    public void onBotaoVermelho(Renderizador mRenderizador, int px, int py, int l, int a, String texto) {
        mRenderizador.drawRect_Pintado(px, py, l, a, new Cor(180, 30, 0));
        mEscritorPequeno.escreva(px + 20, py + 5, texto);
    }

    public void onBotaoVermelhoOff(Renderizador mRenderizador, int px, int py, int l, int a, String texto) {
        mRenderizador.drawRect_Pintado(px, py, l, a, new Cor(180, 100, 0));
        mEscritorPequeno.escreva(px + 20, py + 5, texto);
    }


    public void drawSeletor(Renderizador mRenderizador, Seletor eBotao) {


        mRenderizador.drawRect_Pintado(eBotao.getX(), eBotao.getY(), 140, 60, eBotao.getCor());

        mRenderizador.drawRect_Pintado(eBotao.getX(), eBotao.getY() + 60, 50, 15, eBotao.getCor());

        mRenderizador.drawRect_Pintado(eBotao.getX() + 55, eBotao.getY() + 60 + 5, 30, 10, eBotao.getEixoCor());

        mRenderizador.drawRect_Pintado(eBotao.getX() + 55 + 35, eBotao.getY() + 60, 50, 15, eBotao.getCor());

        int la = mEscritorPequeno.getLarguraDe(eBotao.getTexto());
        if (la < eBotao.getL()) {
            int s = (eBotao.getL() - la) / 2;

            mEscritorPequeno.escreva(eBotao.getX() + s, eBotao.getY() + (eBotao.getA() / 2) - 5, eBotao.getTexto());

        } else {
            mEscritorPequeno.escreva(eBotao.getX() + 20, eBotao.getY() + (eBotao.getA() / 2) - 5, eBotao.getTexto());
        }

    }

    public void drawMenu(Renderizador mRenderizador, Menu eBotao) {


        mRenderizador.drawRect_Pintado(eBotao.getX(), eBotao.getY(), 140, 60, eBotao.getCor());

        mRenderizador.drawRect_Pintado(eBotao.getX(), eBotao.getY() + 60, 50, 15, eBotao.getCor());

        mRenderizador.drawRect_Pintado(eBotao.getX() + 55, eBotao.getY() + 60 + 5, 30, 10, eBotao.getEixoCor());

        mRenderizador.drawRect_Pintado(eBotao.getX() + 55 + 35, eBotao.getY() + 60, 50, 15, eBotao.getCor());

        int la = mEscritorPequeno.getLarguraDe(eBotao.getTexto());
        if (la < eBotao.getL()) {
            int s = (eBotao.getL() - la) / 2;

            mEscritorPequeno.escreva(eBotao.getX() + s, eBotao.getY() + (eBotao.getA() / 2) - 5, eBotao.getTexto());

        } else {
            mEscritorPequeno.escreva(eBotao.getX() + 20, eBotao.getY() + (eBotao.getA() / 2) - 5, eBotao.getTexto());
        }

    }


    public void setRenderizador(Renderizador mRenderizador) {
        mEscritorPequeno.setRenderizador(mRenderizador);
        mEscritorPequeno2.setRenderizador(mRenderizador);
    }

    public void pequeno_escreva(int x, int y, String frase) {
        mEscritorPequeno.escreva(x, y, frase);
    }

    public void normal_escreva(int x, int y, String frase) {
        mEscritorPequeno2.escreva(x, y, frase);
    }

    public boolean getClicado() {
        return mClicado;
    }
}
