package apps.app_zetta;

import apps.app_atzum.app.BotaoSinalizador;
import apps.app_letrum.Fonte;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.utilitarios.Cor;
import libs.luan.Lista;
import libs.luan.Par;
import libs.luan.Strings;
import libs.mockui.Interface.BotaoCor;
import libs.mockui.Interface.Clicavel;

public class GrupoDeBotoesVertical {

    private Clicavel mClicavel;

    private Lista<Par<String, BotaoCor>> mCamadas;
    private int mCamadaPYInicio = 0;
    private int mCamadaPY = 0;
    private String mCamada = "";

    private Cores mCores;

    private int mPX;

    private BotaoSinalizador mBotaoSinalizador;

    private int mTamanho;
    private int mAfastamentoY;
    private boolean mExibirTexto;

    private boolean mMarcadorTemCorSelecionado = false;
    private Cor mMarcadorCorSelecionado = null;

    private boolean mTemCorSelecionado = false;
    private Cor mCorSelecionado = null;

    public GrupoDeBotoesVertical(int ePX, int eCamadaPYInicio) {

        mClicavel = new Clicavel();
        mCamadaPYInicio = eCamadaPYInicio;

        mPX = ePX;
        mCamadaPY = eCamadaPYInicio;
        mCamadas = new Lista<Par<String, BotaoCor>>();

        mCores = new Cores();

        mBotaoSinalizador = BotaoSinalizador.ACIMA_DIRETA;

        mTamanho = 50;
        mAfastamentoY = 100;
        mExibirTexto = true;
    }

    public void setAfastamentoY(int eAfastamentoY) {
        mAfastamentoY = eAfastamentoY;
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

    public void setMarcadorCorSelecionado(Cor eCorSelecionado) {
        mMarcadorTemCorSelecionado = true;
        mMarcadorCorSelecionado = eCorSelecionado;
    }

    public void setCorSelecionado(Cor eCorSelecionado) {
        mTemCorSelecionado = true;
        mCorSelecionado = eCorSelecionado;
    }

    public void zerar() {
        mClicavel.zerar();
        mCamadaPY = mCamadaPYInicio;
        mCamadas.limpar();
    }

    public BotaoCor criarCamada(String nome, Cor eCor) {
        BotaoCor eBotao = mClicavel.criarBotaoCor(new BotaoCor(mPX, mCamadaPY, mTamanho, mTamanho, eCor));
        eBotao.setTexto(nome);
        mCamadas.adicionar(new Par<String, BotaoCor>(nome, eBotao));
        mCamadaPY += mAfastamentoY;
        return eBotao;
    }

    public BotaoCor criarCamadaComNome(String nome, String eBotaoNome, Cor eCor) {
        BotaoCor eBotao = mClicavel.criarBotaoCor(new BotaoCor(mPX, mCamadaPY, mTamanho, mTamanho, eCor));
        eBotao.setTexto(eBotaoNome);
        mCamadas.adicionar(new Par<String, BotaoCor>(nome, eBotao));
        mCamadaPY += mAfastamentoY;
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
        int camada_indo = mCamadaPYInicio;
        for (Par<String, BotaoCor> item : mCamadas) {

            if (mExibirTexto) {
                ESCRITOR_NORMAL_BRANCO.escreva(item.getValor().getX() + item.getValor().getLargura() + 10, item.getValor().getY(), item.getValor().getTexto());
            }

            if (Strings.isIgual(item.getChave(), mCamada)) {

                if(mTemCorSelecionado){
                    g.drawRect_Pintado(item.getValor().getX(), item.getValor().getY(), item.getValor().getLargura(), item.getValor().getAltura(),mCorSelecionado);
                }

                Cor cor_botao = g.getPixel(mPX + (mTamanho / 2), camada_indo + (mTamanho / 2) + 5);

                if (mMarcadorTemCorSelecionado) {
                    cor_botao = mMarcadorCorSelecionado;
                }


                if (mBotaoSinalizador == BotaoSinalizador.LATERAL_DIREITA) {
                    g.drawCirculoCentralizado_Pintado(mPX + (mTamanho / 2) + (mTamanho / 2) - 5, item.getValor().getY() + (mTamanho / 2), (mTamanho / 2) - 3, mCores.getBranco());
                    g.drawCirculoCentralizado_Pintado(mPX + (mTamanho / 2) + (mTamanho / 2) - 5, item.getValor().getY() + (mTamanho / 2), (mTamanho / 5) + 2, cor_botao);
                } else {
                    g.drawCirculoCentralizado_Pintado(mPX + (mTamanho / 2) + (mTamanho / 2) - 5, item.getValor().getY(), (mTamanho / 2) - 3, mCores.getBranco());
                    g.drawCirculoCentralizado_Pintado(mPX + (mTamanho / 2) + (mTamanho / 2) - 5, item.getValor().getY(), (mTamanho / 5) + 2, cor_botao);
                }

                // break;
            }
            camada_indo += mAfastamentoY;
        }

    }

    public String getSelecionado() {
        return mCamada;
    }

}

