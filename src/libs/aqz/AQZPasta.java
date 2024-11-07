package libs.aqz;

import libs.armazenador.Armazenador;
import libs.armazenador.ItemDoBanco;
import libs.bs.Colecao;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.*;
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

                remover(inode_existente);

                fmt.print("Alocacao :: Inicio");
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

            remover(inode_existente);

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

                remover(inode_existente);

                mColecao.remover(item);
                break;
            }
        }


    }


    private Lista<Long> getInodes(long mInode) {

        Unico<Long> mInodes = new Unico<Long>(Matematica.LONG_IGUALAVEL());


        mArmazenador.getArquivador().setPonteiro(mInode);

        mInodes.item(mInode);

        fmt.print("Buscando iNode : {}", mInode);

        int inode_status = mArmazenador.getArquivador().get_u8();
        long inode_proximo = mArmazenador.getArquivador().get_u64();

        mArmazenador.getArquivador().setPonteiro(mInode + 9L);

        int nome_tamanho = mArmazenador.getArquivador().get_u32();

        if (nome_tamanho >= 1024) {
            nome_tamanho = 1024;
        }

        //  byte[] nome_bytes = mArmazenador.getArquivador().get_u8_array(nome_tamanho);

        mArmazenador.getArquivador().setPonteiro(mInode + 2000);
        long inode_primario_tamanho = (long) mArmazenador.getArquivador().get_u32();


        // e_arquivo.at("INode.Status", inode_status);
        // e_arquivo.at("Nome.Tamanho", nome_tamanho);
        //  e_arquivo.at("Nome", new String(nome_bytes, StandardCharsets.UTF_8));
        //  e_arquivo.at("INodeDadosTamanho", inode_primario_tamanho);
        //  e_arquivo.at("ProximoINode", inode_proximo);

        // mNome = new String(nome_bytes, StandardCharsets.UTF_8);
        int blocos_quantidade = 1;
        long arquivo_tamanho = (long) inode_primario_tamanho;

        long ultima_posicao_valida = mArmazenador.getArquivador().getLength();


        while (inode_proximo > 0) {

            long inode_aqui = inode_proximo;

            fmt.print("Buscando iNode : {}", inode_aqui);

            if (inode_aqui >= ultima_posicao_valida) {
                break;
            }

            mInodes.item(inode_aqui);

            //   fmt.print("Novo inode :: {}",inode_aqui);

            mArmazenador.getArquivador().setPonteiro(inode_proximo);
            inode_status = mArmazenador.getArquivador().get_u8();
            inode_proximo = mArmazenador.getArquivador().get_u64();

            mArmazenador.getArquivador().setPonteiro(inode_aqui + 2000);
            long inode_tamanho = (long) mArmazenador.getArquivador().get_u32();
            arquivo_tamanho += inode_tamanho;

            // fmt.print("Passando em inode {} ->> {}", inode_aqui, inode_tamanho);

            blocos_quantidade += 1;
        }

        //   mTamanho = arquivo_tamanho;

        return mInodes.toLista();
    }


    private void remover(long mInode) {


        Lista<Entidade> volumes = mAZVolumeInternamente.volume_listar();
        ENTT.EXIBIR_TABELA_COM_NOME(volumes, "@VOLUMES");

        Lista<Long> mInodes = getInodes(mInode);


        fmt.print("Remover inodes : {}", mInodes.getQuantidade());


        mArmazenador.getArquivador().setPonteiro(mInode);

        long bloco_tamanho = Matematica.KB(64);

        for (Long inode : mInodes) {

            boolean tem_vid = false;
            int volume_id = 0;

            long enc_volume_mapa_inicio = 0;
            long enc_volume_dados_inicio = 0;

            for (Entidade volume : volumes) {

                long volume_dados_inicio = volume.atLong("DadosInicio");
                long volume_dados_fim = volume.atLong("DadosFim");

                if (inode >= volume_dados_inicio && inode < volume_dados_fim) {
                    tem_vid = true;
                    volume_id = volume.atInt("VID");

                    enc_volume_mapa_inicio = volume.atLong("MapaInicio");
                    enc_volume_dados_inicio = volume_dados_inicio;
                    break;
                }

            }

            if (tem_vid) {
                long bloco_id = (inode - enc_volume_dados_inicio) / bloco_tamanho;
                fmt.print("Removendo INode : VID : {} - Bloco : {} -->> BlocoID : {}", volume_id, inode, bloco_id);

                mArmazenador.getArquivador().setPonteiro(enc_volume_mapa_inicio + bloco_id);
                mArmazenador.getArquivador().set_u8((byte) 0);

                mArmazenador.getArquivador().setPonteiro(enc_volume_dados_inicio + ((long) bloco_id * Matematica.KB(64)));
                int status_bloco = mArmazenador.getArquivador().get_u8();

                fmt.print("Status do bloco :: {}", status_bloco);

                mArmazenador.getArquivador().setPonteiro(enc_volume_dados_inicio + ((long) bloco_id * Matematica.KB(64)));
                mArmazenador.getArquivador().set_u8((byte) 1);
                mArmazenador.getArquivador().set_u64(0);

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
