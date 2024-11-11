package libs.aqz.volume;

import apps.app_campeonatum.VERIFICADOR;
import libs.aqz.utils.AZSequenciador;
import libs.aqz.utils.ItemDoBancoTX;
import libs.armazenador.Armazenador;
import libs.armazenador.ParticaoMestre;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Matematica;
import libs.luan.Opcional;
import libs.luan.fmt;
import libs.tempo.Calendario;

import java.nio.charset.StandardCharsets;

public class AZVolumeInternamente {

    // DESENVOLVEDOR : LUAN FREITAS - luandkg@gmail.com
    // CRIADO : 2024 11 06

    public static final int VOLUME_BLOCOS_QUANTIDADE = 5000;
    public static final int VOLUME_INODE_TAMANHO = Matematica.KB(64);
    public static final int VOLUME_INODE_DADOS_TAMANHO = Matematica.KB(60);

    private Armazenador mArmazenador;

    public final static boolean DEBUG=false;

    public static final String COLECAO_VOLUMES_DADOS = "@Volumes::Dados";
    public static final String COLECAO_VOLUMES_SEQUENCIAS = "@Volumes::Sequencias";


    public AZVolumeInternamente(String eArquivo) {
        mArmazenador = new Armazenador(eArquivo);
    }

    public AZVolumeInternamente(Armazenador eArmazenador) {
        mArmazenador = eArmazenador;
    }


    public Lista<Entidade> volume_listar() {
        ParticaoMestre volumes = mArmazenador.getParticaoMestre(  COLECAO_VOLUMES_DADOS);

        Lista<Entidade> e_volumes = new Lista<Entidade>();

        for (ItemDoBancoTX item : volumes.getItensTX()) {
            Entidade obj = ENTT.PARSER_ENTIDADE(item.lerTextoTX());
            e_volumes.adicionar(obj);

        }

        return e_volumes;
    }

    public void volume_criar() {

        ParticaoMestre volumes = mArmazenador.getParticaoMestre( COLECAO_VOLUMES_DADOS);

        ParticaoMestre mSequencias = mArmazenador.getParticaoMestre(  COLECAO_VOLUMES_SEQUENCIAS);
        AZSequenciador.organizar_sequencial(mSequencias, "@Volume.ChaveUnica");
        AZSequenciador.zerar_sequencial(mSequencias, "@Volume.ChaveUnica");


        mArmazenador.getArquivador().setPonteiro(mArmazenador.getArquivador().getLength());

        long volume_inicio = mArmazenador.getArquivador().getPonteiro();
        long volume_mapa_inicio = mArmazenador.getArquivador().getPonteiro();

        for (int bloco = 0; bloco < VOLUME_BLOCOS_QUANTIDADE; bloco++) {
            mArmazenador.getArquivador().set_u8((byte) 0);
        }

        long volume_mapa_fim = mArmazenador.getArquivador().getPonteiro();
        long volume_dados_inicio = mArmazenador.getArquivador().getPonteiro();

        for (int bloco = 0; bloco < VOLUME_BLOCOS_QUANTIDADE; bloco++) {
            mArmazenador.getArquivador().set_u8_em_bloco(Matematica.KB(64), (byte) 0);
        }

        long volume_dados_fim = mArmazenador.getArquivador().getPonteiro();


        Entidade novo_volume = new Entidade();
        novo_volume.at("VID", volumes.getItensContagem());
        novo_volume.at("Tipo", "VOLUME");
        novo_volume.at("Ponteiro", volume_inicio);
        novo_volume.at("MapaInicio", volume_mapa_inicio);
        novo_volume.at("MapaFim", volume_mapa_fim);
        novo_volume.at("DadosInicio", volume_dados_inicio);
        novo_volume.at("DadosFim", volume_dados_fim);
        novo_volume.at("Data", Calendario.getTempoCompleto());
        volumes.adicionarTX(novo_volume);

    }


    public Lista<Entidade> volume_listar_dados() {

        Lista<Entidade> e_volumes = volume_listar();
        Lista<Entidade> volumes_dados = new Lista<Entidade>();

        for (Entidade volume : e_volumes) {
            Entidade volume_dado = ENTT.CRIAR_EM(volumes_dados);
            volume_dado.at("VID", volume.at("VID"));

            long mapa_inicio = volume.atLong("MapaInicio");
            long mapa_fim = volume.atLong("MapaFim");

            long dados_inicio = volume.atLong("DadosInicio");
            long dados_fim = volume.atLong("DadosFim");

            volume_dado.at("Mapa.Tamanho", mapa_fim - mapa_inicio);
            volume_dado.at("Dados.Tamanho", dados_fim - dados_inicio);

            long objetos_livres = 0;
            long objetos_ocupados = 0;
            long objetos_raiz = 0;


            mArmazenador.getArquivador().setPonteiro(mapa_inicio);

            for (int mapa = 0; mapa < VOLUME_BLOCOS_QUANTIDADE; mapa++) {
                int mapa_status = mArmazenador.getArquivador().get_u8();

                if (mapa_status == 0) {
                    objetos_livres += 1;
                } else {
                    objetos_ocupados += 1;
                    if (mapa_status == 2) {
                        objetos_raiz += 1;
                    }
                }

            }


            volume_dado.at("Objetos.Livre", objetos_livres);
            volume_dado.at("Objetos.Ocupado", objetos_ocupados);
            volume_dado.at("Objetos.Raiz", objetos_raiz);

        }


        return volumes_dados;
    }

    public boolean volume_tem_bloco_disponivel() {

        boolean tem = false;

        Lista<Entidade> e_volumes = volume_listar();

        for (Entidade volume : e_volumes) {

            long mapa_inicio = volume.atLong("MapaInicio");
            long mapa_fim = volume.atLong("MapaFim");


            mArmazenador.getArquivador().setPonteiro(mapa_inicio);

            for (int mapa = 0; mapa < VOLUME_BLOCOS_QUANTIDADE; mapa++) {
                int mapa_status = mArmazenador.getArquivador().get_u8();

                if (mapa_status == 0) {
                    tem = true;
                    break;
                }

            }

        }


        return tem;
    }


    public Lista<AQZVolume> volume_listar_volumes() {

        Lista<Entidade> e_volumes = volume_listar();
        Lista<AQZVolume> volumes_dados = new Lista<AQZVolume>();

        for (Entidade volume : e_volumes) {

            long mapa_inicio = volume.atLong("MapaInicio");
            long mapa_fim = volume.atLong("MapaFim");

            long dados_inicio = volume.atLong("DadosInicio");
            long dados_fim = volume.atLong("DadosFim");

            AQZVolume volume_dado = new AQZVolume(mArmazenador.getArquivador(), volume.atIntOuPadrao("VID", 0), mapa_inicio, mapa_fim, dados_inicio, dados_fim);
            volumes_dados.adicionar(volume_dado);

        }


        return volumes_dados;
    }


    public Opcional<Long> arquivo_alocar(String eNome, String eConteudo) {

        byte[] bytes = eConteudo.getBytes(StandardCharsets.UTF_8);

        return arquivo_alocar(eNome, bytes);
    }

    public Opcional<Long> arquivo_alocar(String eNome, byte[] bytes) {


        Lista<AQZVolume> volumes = volume_listar_volumes();

        byte[] nome_bytes = eNome.getBytes(StandardCharsets.UTF_8);


        VERIFICADOR.DEVE_SER_VERDADEIRO(nome_bytes.length < 1024, "Nome Muito Grande !");

        int tamanho_real = bytes.length;
        int blocos = tamanho_real / AZVolumeInternamente.VOLUME_INODE_DADOS_TAMANHO;
        int max_tamanho = blocos * AZVolumeInternamente.VOLUME_INODE_DADOS_TAMANHO;

        if (tamanho_real > max_tamanho) {
            blocos += 1;
        }

        int tem_blocos_disponiveis = 0;

        for (AQZVolume volume : volumes) {
            volume.atualizar();
            tem_blocos_disponiveis += volume.getBlocosDisponiveis();
            if (tem_blocos_disponiveis >= blocos) {
                break;
            }
        }

        //  fmt.print("Blocos - Precisa : {}", blocos);

        if (blocos == 1) {

            AQZVolumeBloco bloco_primario = null;
            boolean encontrado = false;

            for (AQZVolume volume : volumes) {
                volume.atualizar();
                if (volume.getBlocosDisponiveis() > 0) {
                    bloco_primario = volume.getUmBlocoDisponivel();
                    encontrado = true;
                    break;
                }
            }

            if (encontrado) {
                // fmt.print("Bloco Primario");
                //  fmt.print("\t VolumeID :: {}", bloco_primario.getVolume().getVolumeID());
                //  fmt.print("\t BlocoID  :: {}", bloco_primario.getBlocoID());

                bloco_primario.marcarComoRaiz();
                bloco_primario.setNome(nome_bytes);
                bloco_primario.setDados(bytes);
                bloco_primario.marcar_ultimo();

                return Opcional.OK(bloco_primario.getPonteiroDados());

            }


        } else {

            Lista<AQZVolumeBloco> blocos_alocados = new Lista<AQZVolumeBloco>();

            boolean encontrado = false;
            int alocando = 0;

            for (AQZVolume volume : volumes) {

                volume.atualizar();

                while (volume.getBlocosDisponiveis() > 0) {
                    AQZVolumeBloco bloco_alocavel = volume.getUmBlocoDisponivel();
                    if (alocando == 0) {
                        bloco_alocavel.marcarComoRaiz();
                    } else {
                        bloco_alocavel.marcarComoOcupado();
                    }

                    bloco_alocavel.marcar_ultimo();

                    encontrado = true;
                    blocos_alocados.adicionar(bloco_alocavel);
                    if (blocos_alocados.getQuantidade() == blocos) {
                        break;
                    }
                    alocando += 1;
                    volume.atualizar();
                }
                if (blocos_alocados.getQuantidade() == blocos) {
                    break;
                }
            }

            if (encontrado && blocos_alocados.getQuantidade() == blocos) {

                // fmt.print("\t Blocos Alocados :: {}", blocos_alocados.getQuantidade());

                blocos_alocados.get(0).marcarComoRaiz();
                blocos_alocados.get(blocos_alocados.getQuantidade() - 1).marcar_ultimo();

                AQZVolumeBloco bloco_primario = blocos_alocados.get(0);
                bloco_primario.setNome(nome_bytes);
                // bloco_primario.setDados(bytes);

                int i = 0;
                int o = bytes.length;
                int u = 0;

                int cluster = AZVolumeInternamente.VOLUME_INODE_DADOS_TAMANHO;

                int blocos_i = 0;
                int blocos_o = blocos_alocados.getQuantidade();

                for (AQZVolumeBloco bloco : blocos_alocados) {
                    u += cluster;
                    int tam = cluster;
                    if (u > o) {
                        u = o;
                        tam = u - i;
                    }

                    //  fmt.print("\t Alocando em BlocoID  :: {} -- {} ->> {} - {}", bloco.getBlocoID(),bloco.getPonteiroDados(), i, u);

                    byte[] zona = new byte[tam];

                    int b = 0;
                    for (int a = i; a < u; a++) {
                        zona[b] = bytes[a];
                        b += 1;
                    }

                    //  fmt.print("\t Gravar Dados : {} :: {}",i,u);
                    //   fmt.print("\t D :: {} - {} - {}", Inteiro.byteToInt(zona[0]),Inteiro.byteToInt(zona[1]),Inteiro.byteToInt(zona[2]) );

                    bloco.setDados(zona);
                    i += cluster;

                    if (blocos_i + 1 >= blocos_o) {
                        bloco.marcar_ultimo();

                        if (DEBUG) {
                            fmt.print("Marcando Ultimo :: {}", bloco.getPonteiroDados());
                        }

                    } else {
                        bloco.marcar_proximo(blocos_alocados.get(blocos_i + 1).getPonteiroDados());
                        if (DEBUG) {
                            fmt.print("Marcando proximo :: {} ->> {}", bloco.getPonteiroDados(), blocos_alocados.get(blocos_i + 1).getPonteiroDados());
                        }

                    }

                    blocos_i += 1;
                }

                return Opcional.OK(bloco_primario.getPonteiroDados());

            } else {
                throw new RuntimeException("Quantidade de blocos alocados insuficiente :: " + blocos + " / " + blocos_alocados.getQuantidade());
            }

        }

        return Opcional.CANCEL();
    }

    public void arquivos_dump() {

        Lista<AQZVolume> volumes = volume_listar_volumes();

        for (AQZVolume volume : volumes) {
            volume.arquivos_dump();
        }

    }

    public Opcional<Entidade> procurar_arquivo(String proc_arquivo_nome) {

        Lista<AQZVolume> volumes = volume_listar_volumes();

        for (AQZVolume volume : volumes) {
            Opcional<Entidade> op_arquivo = volume.procurar_arquivo(proc_arquivo_nome);
            if (op_arquivo.isOK()) {
                return op_arquivo;
            }
        }

        return Opcional.CANCEL();
    }

    public Opcional<AQZArquivoExternamente> procurar_arquivo_externamente(String proc_arquivo_nome) {

        Lista<AQZVolume> volumes = volume_listar_volumes();

        for (AQZVolume volume : volumes) {
            Opcional<AQZArquivoExternamente> op_arquivo = volume.procurar_arquivo_externamente(proc_arquivo_nome);
            if (op_arquivo.isOK()) {
                return op_arquivo;
            }
        }

        return Opcional.CANCEL();
    }


    public void volumes_zerar() {

        Lista<AQZVolume> volumes = volume_listar_volumes();

        for (AQZVolume volume : volumes) {
            volume.zerar();
        }

    }


    public long getBlocosLivres() {

        Lista<Entidade> volumes_dados = volume_listar_dados();
        return ENTT.ATRIBUTO_LONG_SOMAR(volumes_dados, "Objetos.Livre");

    }


    public void analisar_integridade() {

        Lista<AQZVolume> volumes = volume_listar_volumes();

        for (AQZVolume volume : volumes) {
            volume.analisar_integridade();
        }

    }

    public Lista<Entidade> getCorrompidos() {

        Lista<AQZVolume> volumes = volume_listar_volumes();

        Lista<Entidade> corrompidos = new Lista<Entidade>();

        for (AQZVolume volume : volumes) {
            corrompidos.adicionar_varios(volume.getCorrompidos());
        }

        return corrompidos;
    }

    public void fechar() {
        mArmazenador.fechar();
    }

}
