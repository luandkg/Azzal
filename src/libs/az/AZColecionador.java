package libs.az;

import libs.armazenador.*;
import libs.dkg.DKGFeatures;
import libs.dkg.DKGObjeto;
import libs.luan.Lista;

public class AZColecionador {

    private Armazenador mArmazenador;
    private Banco s_sequencias;

    public AZColecionador(String eArquivo) {
        mArmazenador = new Armazenador(eArquivo);
        s_sequencias = AZSequenciador.organizar_banco(mArmazenador, "@Sequencias");
    }

    public Colecao getColecao(String eNome) {
        return new Colecao(eNome, mArmazenador, s_sequencias, AZSequenciador.organizar_banco(mArmazenador, eNome));
    }

    public Unicidade getSettum(String eNome) {
        return new Unicidade(eNome, mArmazenador, s_sequencias, AZSequenciador.organizar_banco(mArmazenador, eNome));
    }

    public long getSequenciaID(String eNome) {
        return AZSequenciador.get_sequencial_contador(s_sequencias, eNome);
    }


    public void remover_colecao(String eNome) {
        mArmazenador.banco_remover(eNome);
    }

    public ItemDoBanco getItemDireto(long ePointeiro) {

        ItemDoBanco item = mArmazenador.getItemDireto(ePointeiro);
        if (item.existe()) {
            return item;
        } else {
            return null;
        }

    }


    public Armazenador getMomentum() {
        return mArmazenador;
    }

    public void fechar() {
        mArmazenador.fechar();
    }


    public static void checar(String eArquivo) {

        if (!Armazenador.existe(eArquivo)) {
            Armazenador.criar(eArquivo);
        }

    }


    public void auto_analisar() {


        Colecao colecao_analise = this.getColecao("@Analise");
        colecao_analise.limpar();
        colecao_analise.zerarSequencial();

        Lista<DKGObjeto> objetos_analisados = new Lista<DKGObjeto>();

        for (Banco b : getMomentum().getBancos()) {

            DKGObjeto obj_analise = new DKGObjeto("Analise");

            obj_analise.identifique("BID").setLong(b.getID());
            obj_analise.identifique("Nome").setValor(b.getNome());

            obj_analise.identifique("Itens").setLong(b.getItensContagem());

            obj_analise.identifique("Disponibilidade").setInteiro(b.getDisponibilidade());
            obj_analise.identifique("Usabilidadade").setInteiro(b.getUsabilidade());

            obj_analise.identifique("Capitulos_Contagem").setLong(b.getCapitulosContagem());
            obj_analise.identifique("Paginas_Contagem").setLong(b.getPaginasContagem());

            obj_analise.identifique("Indice_Disponibilidade").setInteiro(b.getIndiceDisponibilidade());
            obj_analise.identifique("Indice_Usabilidade").setInteiro(b.getIndiceUsabilidade());
            obj_analise.identifique("Indice_Paginas").setLong(b.getIndicePaginasContagem());
            obj_analise.identifique("Indice_Contagem").setLong(b.getIndiceItensTotalContagem());
            obj_analise.identifique("Indice_Utilizados").setLong(b.getIndiceItensUtilizadoContagem());

            objetos_analisados.adicionar(obj_analise);

        }

        for(DKGObjeto obj_analise : DKGFeatures.ordenar_objetos_texto(objetos_analisados,"Nome")){
            colecao_analise.adicionar(obj_analise);
        }
    }
}
