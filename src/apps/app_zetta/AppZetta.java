package apps.app_zetta;

import apps.app_atzum.app.BotaoSinalizador;
import apps.app_letrum.Fonte;
import apps.app_letrum.Maker.FonteRunTime;
import libs.azzal.AzzalUnico;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.Windows;
import libs.azzal.cenarios.Cena;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.RefInt;
import libs.luan.RefString;
import libs.mockui.Interface.Acao;
import libs.mockui.Interface.BotaoCor;
import libs.mockui.Interface.Clicavel;
import libs.zetta.ZettaColecao;
import libs.zetta.ZettaQuorum;

public class AppZetta extends Cena {


    public static void INICIAR() {
        AzzalUnico.unico("AppZetta", 2000, 800, new AppZetta());
    }


    private Cores mCores;
    private Fonte ESCRITOR_NORMAL_BRANCO;
    private Fonte ESCRITOR_NORMAL_VERMELHO;

    private Clicavel mClicavel;
    private GrupoDeBotoesVertical mBotoesColecoes;

    private String mArquivo = "/home/luan/assets/teste_fazendas/zeta.az";
    //private String mArquivo = "/home/luan/Imagens/atzum/AtzumAnalises.az";

    private Lista<Entidade> mColecoes;
    private Lista<Entidade> mDados;

    private RefInt mPassadorInicio = new RefInt(0);
    private long mQuantidade = 0;

    private int mBarraComprimento = 470;
    private RefString mColecaoSelecionada = new RefString("");
    private boolean mTemColecao = false;


    @Override
    public void iniciar(Windows eWindows) {

        mCores = new Cores();
        ESCRITOR_NORMAL_BRANCO = new FonteRunTime(mCores.getBranco(), 10);
        ESCRITOR_NORMAL_VERMELHO = new FonteRunTime(mCores.getVermelho(), 10);

        mClicavel = new Clicavel();

        ZettaQuorum zetta_quorum = new ZettaQuorum(mArquivo);

        mColecoes = ENTT.CRIAR_LISTA();
        mDados = ENTT.CRIAR_LISTA();

        for (ZettaColecao colecao : zetta_quorum.getColecoes()) {
            Entidade e_colecao = ENTT.CRIAR_EM_SEQUENCIALMENTE(mColecoes, "ID", 1);
            e_colecao.at("Nome", colecao.getNome());
        }

        zetta_quorum.fechar();

        ENTT.EXIBIR_TABELA_COM_TITULO(mColecoes, "COLECOES");


        mBotoesColecoes = new GrupoDeBotoesVertical(50, 100);
        mBotoesColecoes.setSinalizador(BotaoSinalizador.LATERAL_DIREITA);
        mBotoesColecoes.setAfastamentoX(50);
        mBotoesColecoes.setTamanho(20);
        mBotoesColecoes.exibirTexto(true);
        mBotoesColecoes.setMarcadorCorSelecionado(mCores.getBranco());
        mBotoesColecoes.setCorSelecionado(mCores.getVermelho());


        for (Entidade e_colecao : mColecoes) {

            mBotoesColecoes.criarCamada(e_colecao.at("Nome"), mCores.getLaranja()).setAcao(new Acao() {
                @Override
                public void onClique() {

                    mTemColecao = true;
                    mColecaoSelecionada.set(e_colecao.at("Nome"));
                    mPassadorInicio.set(0);

                    mBotoesColecoes.setSelecionado(e_colecao.at("Nome"));
                    //  app.mMapaZoom.update(true);

                    ZettaQuorum zetta_quorum = new ZettaQuorum(mArquivo);

                    ZettaColecao colecao = zetta_quorum.getColecaoSempre(mColecaoSelecionada.get());

                    mQuantidade = colecao.contagem();
                    mDados = colecao.getItensIntervalo(mPassadorInicio.get(), mPassadorInicio.get() + 20);

                    zetta_quorum.fechar();


                }
            });


        }


        BotaoCor voltar = mClicavel.criarBotaoCor(new BotaoCor(360, 580, 20, 20, mCores.getAzul()));
        BotaoCor avancar = mClicavel.criarBotaoCor(new BotaoCor(360, 610, 20, 20, mCores.getAzul()));


        voltar.setAcao(new Acao() {
            @Override
            public void onClique() {

                if (mTemColecao) {

                    mPassadorInicio.set(mPassadorInicio.get() - 20);
                    if (mPassadorInicio.get() < 20) {
                        mPassadorInicio.set(0);
                    }

                    ZettaQuorum zetta_quorum = new ZettaQuorum(mArquivo);

                    ZettaColecao colecao = zetta_quorum.getColecaoSempre(mColecaoSelecionada.get());

                    mQuantidade = colecao.contagem();
                    mDados = colecao.getItensIntervalo(mPassadorInicio.get(), mPassadorInicio.get() + 20);

                    zetta_quorum.fechar();


                }

            }
        });

        avancar.setAcao(new Acao() {
            @Override
            public void onClique() {
                if (mTemColecao && (mPassadorInicio.get() + 20) < mQuantidade) {
                    mPassadorInicio.set(mPassadorInicio.get() + 20);

                    ZettaQuorum zetta_quorum = new ZettaQuorum(mArquivo);

                    ZettaColecao colecao = zetta_quorum.getColecaoSempre(mColecaoSelecionada.get());

                    mQuantidade = colecao.contagem();
                    mDados = colecao.getItensIntervalo(mPassadorInicio.get(), mPassadorInicio.get() + 20);

                    zetta_quorum.fechar();


                }
            }
        });

    }

    @Override
    public void update(double dt) {

        int px = getWindows().getMouse().getX();
        int py = getWindows().getMouse().getY();

        mClicavel.update(dt, px, py, getWindows().getMouse().isPressed());
        mBotoesColecoes.update(dt, px, py, getWindows().getMouse().isPressed());

        getWindows().getMouse().liberar();
        getWindows().getTeclado().limpar();
    }

    @Override
    public void draw(Renderizador g) {
        g.limpar(mCores.getPreto());

        ESCRITOR_NORMAL_BRANCO.setRenderizador(g);
        ESCRITOR_NORMAL_VERMELHO.setRenderizador(g);

        mBotoesColecoes.render(g, ESCRITOR_NORMAL_BRANCO);
        mClicavel.onDraw(g);


        g.drawRect_Pintado(370, 100, 3, mBarraComprimento, mCores.getAzul());


        ESCRITOR_NORMAL_BRANCO.escrevaCentralizado(370, 80, String.valueOf(mQuantidade));

        if (mTemColecao) {

            if (mPassadorInicio.get() >= 0 && mPassadorInicio.get() < mQuantidade) {

                double taxa = (double) mPassadorInicio.get() / (double) mQuantidade;

                int onde = (int) (taxa * (double) mBarraComprimento);

                g.drawRect_Pintado(370 - 5, 100 + onde, 10, 10, mCores.getBranco());

            }
        }


        int px = 400;
        int py = 100;

        Lista<Entidade> nomes = ENTT.GET_ATRIBUTOS_COM_TAMANHO(mDados);

        if (nomes.possuiObjetos()) {

            int tamanho_padrao = 10;

            for (Entidade att : nomes) {
                if (att.atInt("Tamanho") < tamanho_padrao) {
                    att.at("Tamanho", tamanho_padrao);
                }
            }

        }


        int px_cab = px;

        for (Entidade att : nomes) {
            String valor = att.at("Nome");

            ESCRITOR_NORMAL_VERMELHO.escreva(px_cab, py, valor);
            px_cab += att.atInt("Tamanho") * 12;
        }

        py += 60;

        for (Entidade e : mDados) {

            int px_a = px;

            for (Entidade att : nomes) {
                String valor = e.at(att.at("Nome"));

                ESCRITOR_NORMAL_BRANCO.escreva(px_a, py, valor);
                px_a += att.atInt("Tamanho") * 12;
            }

            py += 20;
        }

    }

}
