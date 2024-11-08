package libs.aqz;

import libs.aqz.colecao.AZInternamente;
import libs.aqz.utils.AZSequenciometro;
import libs.aqz.volume.AQZArquivoInternamente;
import libs.aqz.volume.AQZInode;
import libs.aqz.volume.AZVolumeInternamente;
import libs.armazenador.Armazenador;
import libs.aqz.utils.ItemDoBanco;
import libs.aqz.colecao.Colecao;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Opcional;
import libs.luan.Par;
import libs.luan.fmt;
import libs.tronarko.Tronarko;

import java.nio.charset.StandardCharsets;

public class AQZPasta {

    // DESENVOLVEDOR : LUAN FREITAS - luandkg@gmail.com
    // CRIADO : 2024 11 06


    private Armazenador mArmazenador;
    private AZVolumeInternamente mAZVolumeInternamente;
    private AZInternamente mAZInternamente;
    private AZSequenciometro mAZSequenciometro;
    private Colecao mColecao;
    private String mColecaoNome;

    public final static boolean DEBUG=false;

    public AQZPasta(String eArquivo, String eNomeColecao) {

        mColecaoNome = eNomeColecao;

        Armazenador.CHECAR(eArquivo);

        mArmazenador = new Armazenador(eArquivo);
        mAZInternamente = new AZInternamente(mArmazenador);
        mAZVolumeInternamente = new AZVolumeInternamente(mArmazenador);
        mAZSequenciometro = new AZSequenciometro(mArmazenador, "@Volume.ChaveUnica");

        mColecao = mAZInternamente.colecao_orgarnizar_e_obter(eNomeColecao);

        mAZSequenciometro.organizar();

    }

    public void fechar() {
        mArmazenador.fechar();
    }


    public void dump() {

        Lista<Entidade> dados = mColecao.getObjetos();

        ENTT.ATRIBUTO_TORNAR_ULTIMO(dados, "Tamanho");
        for (Entidade arquivo : dados) {
            arquivo.at("TamanhoFormatado", fmt.formatar_tamanho_precisao_dupla(arquivo.atLong("Tamanho")));
        }

        ENTT.EXIBIR_TABELA_COM_NOME(dados, "DUMP AQZPasta :: " + mColecaoNome);
    }

    public void crescer_se_precisar() {
        if (mAZVolumeInternamente.getBlocosLivres() < 1000) {
            mAZVolumeInternamente.volume_criar();
        }
    }

    public boolean adicionar(String eNome, byte[] bytes) {

        crescer_se_precisar();

        int arquivo_id = mAZSequenciometro.getSequencial();

        Lista<Entidade> arquivos = mColecao.getObjetos();

        if (!ENTT.EXISTE(arquivos, "Nome", eNome)) {

            Opcional<Long> op_inode = mAZVolumeInternamente.arquivo_alocar(mColecaoNome + "::" + arquivo_id, bytes);

            if (op_inode.isOK()) {
                Entidade arquivo = new Entidade();
                arquivo.at("INode", op_inode.get());
                arquivo.at("Nome", eNome);
                arquivo.at("Tamanho", bytes.length);
                arquivo.at("DDC", Tronarko.getTronAgora().getTextoZerado());
                arquivo.at("DDM", Tronarko.getTronAgora().getTextoZerado());
                mColecao.adicionar(arquivo);

                return true;
            }

        }

        return false;
    }

    public boolean adicionar_ou_atualizar(String eNome, byte[] bytes) {

        crescer_se_precisar();
        boolean existe = false;


        int arquivo_id = mAZSequenciometro.getSequencial();

        // Sequenciador.DUMP(mSequencias, "@Volume.ChaveUnica");

        //fmt.print("Chave Unica : {}",arquivo_id);

        for (ItemDoBanco item : mColecao.getItens()) {
            Entidade e_item = ENTT.PARSER_ENTIDADE(item.lerTexto());
            if (e_item.is("Nome", eNome)) {

                long inode_existente = e_item.atLong("INode");

                AQZInode.INODE_REMOVER(mAZVolumeInternamente, mArmazenador, inode_existente);

                if (DEBUG) {
                    fmt.print("Alocacao :: Inicio");
                }

                Opcional<Long> op_inode = mAZVolumeInternamente.arquivo_alocar(mColecaoNome + "::" + arquivo_id, bytes);

                if (op_inode.isOK()) {

                    e_item.at("INode", op_inode.get());
                    e_item.at("Tamanho", bytes.length);
                    e_item.at("DDM", Tronarko.getTronAgora().getTextoZerado());

                    item.atualizar(e_item);
                    return true;
                }

                e_item.at("INode", "");
                e_item.at("Status", "Corrompido");
                item.atualizar(e_item);
                break;
            }
        }

        if (!existe) {

            Opcional<Long> op_inode = mAZVolumeInternamente.arquivo_alocar(mColecaoNome + "::" + arquivo_id, bytes);

            if (op_inode.isOK()) {
                Entidade arquivo = new Entidade();
                arquivo.at("INode", op_inode.get());
                arquivo.at("Nome", eNome);
                arquivo.at("Tamanho", bytes.length);
                arquivo.at("DDC", Tronarko.getTronAgora().getTextoZerado());
                arquivo.at("DDM", Tronarko.getTronAgora().getTextoZerado());
                mColecao.adicionar(arquivo);
                return true;
            }

        }


        return false;
    }

    public boolean adicionar_ou_atualizar(String eNome, String conteudo) {
        return adicionar_ou_atualizar(eNome, conteudo.getBytes(StandardCharsets.UTF_8));
    }


    public boolean existe(String eNome) {

        for (ItemDoBanco item : mColecao.getItens()) {
            Entidade e_item = ENTT.PARSER_ENTIDADE(item.lerTexto());
            if (e_item.is("Nome", eNome)) {
                return true;
            }
        }
        return false;
    }

    public void limpar() {

        for (ItemDoBanco item : mColecao.getItens()) {
            Entidade e_item = ENTT.PARSER_ENTIDADE(item.lerTexto());

            long inode_existente = e_item.atLong("INode");

            AQZInode.INODE_REMOVER(mAZVolumeInternamente, mArmazenador, inode_existente);

            mColecao.remover(item);
        }
    }

    public void limparComDebug() {

        for (ItemDoBanco item : mColecao.getItens()) {
            Entidade e_item = ENTT.PARSER_ENTIDADE(item.lerTexto());

            fmt.print("\t >> OPERAÇÃO REMOVER :: {}", e_item.at("Nome"));

            long inode_existente = e_item.atLong("INode");

            AQZInode.INODE_REMOVER(mAZVolumeInternamente, mArmazenador, inode_existente);

            mColecao.remover(item);
        }
    }

    public void limpar_refs() {
        mColecao.limpar();
        mColecao.zerarSequencial();
    }

    public byte[] getBytes(String eNome) {
        byte bytes[] = new byte[10];

        for (ItemDoBanco item : mColecao.getItens()) {
            Entidade e_item = ENTT.PARSER_ENTIDADE(item.lerTexto());
            if (e_item.is("Nome", eNome)) {

                AQZArquivoInternamente aq = new AQZArquivoInternamente(mArmazenador.getArquivador(), e_item.atLong("INode"));
                aq.atualizar();

                bytes = aq.getBytes();

                break;
            }
        }
        return bytes;
    }


    public String getTexto(String eNome) {
        return new String(getBytes(eNome), StandardCharsets.UTF_8);
    }

    public void dump_estrutura_arquivo(String eNome) {

        for (ItemDoBanco item : mColecao.getItens()) {
            Entidade e_item = ENTT.PARSER_ENTIDADE(item.lerTexto());
            if (e_item.is("Nome", eNome)) {

                AQZArquivoInternamente aq = new AQZArquivoInternamente(mArmazenador.getArquivador(), e_item.atLong("INode"));
                aq.atualizar();

                aq.dump_estrutura();

                break;
            }
        }

    }

    public void remover(String eNome) {

        for (ItemDoBanco item : mColecao.getItens()) {
            Entidade e_item = ENTT.PARSER_ENTIDADE(item.lerTexto());
            if (e_item.is("Nome", eNome)) {

                long inode_existente = e_item.atLong("INode");

                e_item.at("INode", "");
                e_item.at("Status", "Removido");
                item.atualizar(e_item);

                AQZInode.INODE_REMOVER(mAZVolumeInternamente, mArmazenador, inode_existente);

                mColecao.remover(item);
                break;
            }
        }


    }


    public Opcional<Entidade> procurar(String eNome) {

        for (ItemDoBanco item : mColecao.getItens()) {
            Entidade e_item = ENTT.PARSER_ENTIDADE(item.lerTexto());
            if (e_item.is("Nome", eNome)) {
                return Opcional.OK(e_item);
            }
        }
        return Opcional.CANCEL();
    }


    public Opcional<AQZArquivoInternamente> procurarArquivoInternamente(String eNome) {

        for (ItemDoBanco item : mColecao.getItens()) {
            Entidade e_item = ENTT.PARSER_ENTIDADE(item.lerTexto());
            if (e_item.is("Nome", eNome)) {
                AQZArquivoInternamente arq = new AQZArquivoInternamente(mArmazenador.getArquivador(), e_item.atLong("INode"));
                arq.atualizar();
                return Opcional.OK(arq);
            }
        }
        return Opcional.CANCEL();
    }


    public Opcional<Par<Entidade,AQZArquivoInternamente>> procurarArquivoInternamenteComInformacoes(String eNome) {

        for (ItemDoBanco item : mColecao.getItens()) {
            Entidade e_item = ENTT.PARSER_ENTIDADE(item.lerTexto());
            if (e_item.is("Nome", eNome)) {
                AQZArquivoInternamente arq = new AQZArquivoInternamente(mArmazenador.getArquivador(), e_item.atLong("INode"));
                arq.atualizar();
                return Opcional.OK(new Par<Entidade,AQZArquivoInternamente>(e_item,arq));
            }
        }
        return Opcional.CANCEL();
    }



    public void analisar_integridade() {

        Lista<Entidade> corrompidos = new Lista<Entidade>();

        for (ItemDoBanco item : mColecao.getItens()) {
            Entidade e_item = ENTT.PARSER_ENTIDADE(item.lerTexto());

            AQZArquivoInternamente arq = new AQZArquivoInternamente(mArmazenador.getArquivador(), e_item.atLong("INode"));
            boolean is_integro = arq.verificar_integridade();

            if (is_integro) {
                e_item.at("Integridade", "OK");
            } else {
                e_item.at("Integridade", "CORROMPIDO");
            }

            if (e_item.atLong("INode") == 210399880) {
                e_item.at("InodeProcurado", "CORROMPIDO");
            }

            corrompidos.adicionar(e_item);


        }

        ENTT.EXIBIR_TABELA_COM_TITULO(corrompidos, "AQZ Integridade - INodes Corrompidos");

    }


    public Lista<Entidade> getTabelaDeArquivos() {

        Lista<Entidade> dados = mColecao.getObjetos();

        ENTT.ATRIBUTO_TORNAR_ULTIMO(dados, "Tamanho");
        for (Entidade arquivo : dados) {
            arquivo.at("TamanhoFormatado", fmt.formatar_tamanho_precisao_dupla(arquivo.atLong("Tamanho")));
        }

        return dados;
    }

    public void analisar_corrompidos() {

        Lista<Entidade> corrompidos = mAZVolumeInternamente.getCorrompidos();
        Lista<Entidade> arquivos = getTabelaDeArquivos();

        ENTT.ATRIBUTO_REMOVER(corrompidos, "Nome");

        ENTT.EXIBIR_TABELA_COM_TITULO(corrompidos, "@Corrompidos");
        // ENTT.EXIBIR_TABELA_COM_TITULO(arquivos, "@Arquivos");

        Lista<Entidade> arquivos_corrompidos = new Lista<Entidade>();

        for (Entidade corrompido : corrompidos) {
            Opcional<Entidade> op_corrompido = ENTT.GET_OPCIONAL(arquivos, "INode", corrompido.at("INode"));
            if (op_corrompido.isOK()) {
                arquivos_corrompidos.adicionar(op_corrompido.get());
            }
        }

        ENTT.EXIBIR_TABELA_COM_TITULO(arquivos_corrompidos, "@ArquivosCorrompidos");

    }
}
