package apps.app_atzum.app;

import apps.app_letrum.Fonte;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.utilitarios.Cor;
import libs.luan.Lista;
import libs.luan.Par;
import libs.luan.Strings;
import libs.mockui.Interface.BotaoCor;
import libs.mockui.Interface.Clicavel;

public class GrupoDeBotoesGrandes {

    private Clicavel mClicavel;

    private Lista<Par<String, BotaoCor>> mCamadas;
    private int mCamadaPXInicio = 0;
    private int mCamadaPX = 0;
    private String mCamada = "";

    private Cores mCores;

    private int mPY;

    private BotaoSinalizador mBotaoSinalizador;

    private int mTamanho;
    private int mAfastamentoX;
    private boolean mExibirTexto;

    public GrupoDeBotoesGrandes(int eCamadaPXInicio, int ePY) {

        mClicavel = new Clicavel();
        mCamadaPXInicio = eCamadaPXInicio;

        mCamadaPX = mCamadaPXInicio;
        mCamadas = new Lista<Par<String, BotaoCor>>();

        mCores = new Cores();
        mPY = ePY;

        mBotaoSinalizador = BotaoSinalizador.ACIMA_DIRETA;

        mTamanho = 50;
        mAfastamentoX = 50;
        mExibirTexto = true;
    }

    public void setAfastamentoX(int eAfastamentoX) {
        mAfastamentoX = eAfastamentoX;
    }

    public void setTamanho(int eTamanho) {
        mTamanho = eTamanho;
    }

    public void exibirTexto(boolean eExibirTexto) {
        mExibirTexto = eExibirTexto;
    }

    public void setSinalizador(BotaoSinalizador eBotaoSinalizador) {
        mBotaoSinalizador = eBotaoSinalizador;
    }

    public void zerar() {
        mClicavel.zerar();
        mCamadaPX = mCamadaPXInicio;
        mCamadas.limpar();
    }

    public BotaoCor criarCamada(String nome, Cor eCor) {
        BotaoCor eBotao = mClicavel.criarBotaoCor(new BotaoCor(mCamadaPX, mPY, mTamanho, mTamanho, eCor));
        eBotao.setTexto(nome);
        mCamadas.adicionar(new Par<String, BotaoCor>(nome, eBotao));
        mCamadaPX += mAfastamentoX;
        return eBotao;
    }

    public BotaoCor criarCamadaComNome(String nome, String eBotaoNome, Cor eCor) {
        BotaoCor eBotao = mClicavel.criarBotaoCor(new BotaoCor(mCamadaPX, mPY, mTamanho, mTamanho, eCor));
        eBotao.setTexto(eBotaoNome);
        mCamadas.adicionar(new Par<String, BotaoCor>(nome, eBotao));
        mCamadaPX += mAfastamentoX;
        return eBotao;
    }

    public void aplicarCamada(String nome) {
        for (Par<String, BotaoCor> item : mCamadas) {
            if (Strings.isIgual(item.getChave(), nome)) {
                item.getValor().clicar();
                break;
            }
        }
    }

    public int getQuantidade() {
        return mCamadas.getQuantidade();
    }

    public void setSelecionado(String selecionado) {
        mCamada = selecionado;
    }

    public void update(double dt, int px, int py, boolean ePrecionado) {
        mClicavel.update(dt, px, py, ePrecionado);
    }


    public void render(Renderizador g, Fonte ESCRITOR_NORMAL_BRANCO) {

        mClicavel.onDraw(g);

        // marcar camada selecionada
        int camada_indo = mCamadaPXInicio;
        for (Par<String, BotaoCor> item : mCamadas) {

            if (mExibirTexto) {
                // ESCRITOR_NORMAL_BRANCO_GRANDE.escreva(item.getValor().getX(),item.getValor().getY(), String.valueOf( item.getChave().charAt(0)));
                ESCRITOR_NORMAL_BRANCO.escrevaCentralizado(item.getValor().getX() + (item.getValor().getLargura() / 2), item.getValor().getY() + item.getValor().getAltura() + 5, item.getValor().getTexto());
            }

            if (Strings.isIgual(item.getChave(), mCamada)) {

                Cor cor_botao = g.getPixel(camada_indo + (mTamanho / 2), item.getValor().getY() + mTamanho + 5);


                if (mBotaoSinalizador == BotaoSinalizador.LATERAL_DIREITA) {
                    g.drawCirculoCentralizado_Pintado(camada_indo + (mTamanho / 2) + (mTamanho/2)-5,  item.getValor().getY()+(mTamanho/2), (mTamanho / 2) - 3, mCores.getBranco());
                    g.drawCirculoCentralizado_Pintado(camada_indo + (mTamanho / 2) + (mTamanho/2)-5,  item.getValor().getY()+(mTamanho/2), (mTamanho / 5) + 2, cor_botao);
                } else {
                    g.drawCirculoCentralizado_Pintado(camada_indo + (mTamanho / 2) + (mTamanho/2)-5, item.getValor().getY(), (mTamanho / 2) - 3, mCores.getBranco());
                    g.drawCirculoCentralizado_Pintado(camada_indo + (mTamanho / 2) + (mTamanho/2)-5, item.getValor().getY(), (mTamanho / 5) + 2, cor_botao);
                }

                // break;
            }
            camada_indo += mAfastamentoX;
        }

    }

    public String getSelecionado() {
        return mCamada;
    }

}
