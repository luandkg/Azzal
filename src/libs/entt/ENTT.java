package libs.entt;

import libs.dkg.*;
import libs.luan.Lista;
import libs.luan.fmt;
import libs.oo.ODS;
import libs.xlsx.Planilha;
import libs.xlsx.PlanilhaLinha;
import libs.xlsx.XLSX;

public class ENTT {


    public static Entidade GET_UNICO(Lista<Entidade> mEntts,String eNome, String eValor) {
        Entidade ret = null;

        for (Entidade e : mEntts) {
            if (e.at(eNome).contentEquals(eValor)) {
                ret = e;
                break;
            }
        }

        return ret;
    }

    public static Entidade ATRIBUTO_INTEIRO_MENOR(Lista<Entidade> mEntts,String eNome) {
        Entidade ret = null;

        int menor = mEntts.get(0).atInt(eNome);

        for (Entidade e : mEntts) {
            if (e.atInt(eNome) < menor) {
                ret = e;
                menor = e.atInt(eNome);
            }
        }

        return ret;
    }

    public static Entidade ATRIBUTO_INTEIRO_MAIOR(Lista<Entidade> mEntts,String eNome) {
        Entidade ret = null;

        int maior = mEntts.get(0).atInt(eNome);

        for (Entidade e : mEntts) {
            if (e.atInt(eNome) > maior) {
                ret = e;
                maior = e.atInt(eNome);
            }
        }

        return ret;
    }




    // FUNCOES ESPECIAIS


    public static int ATRIBUTO_SOMAR(Lista<Entidade> mEntts,String nome) {
        int somatorio = 0;

        for (Entidade e : mEntts) {
            somatorio += e.atInt(nome);
        }

        return somatorio;
    }

    public static int ATRIBUTO_SOMAR_E_AGREGAR(Lista<Entidade> mEntts,String a1, String a2) {
        int t1 = ATRIBUTO_SOMAR(mEntts,a1);
        int t2 = ATRIBUTO_SOMAR(mEntts,a2);

        return t1 + t2;
    }

    public static void EXPORTAR_DKG(Lista<Entidade> mEntts,String arquivo) {

        DKG documento = new DKG();
        DKGObjeto raiz = documento.unicoObjeto("ENTT");

        for (Entidade e : mEntts) {

            DKGObjeto objeto = raiz.criarObjeto("E");
            for (Tag tag : e.tags()) {
                objeto.identifique(tag.getNome(), tag.getValor());
            }

        }

        documento.salvar(arquivo);

    }



    public static Lista<Entidade> CRIAR_DE_XLSX(XLSX eXLSX){

        Lista<Entidade> planilhas = new Lista<Entidade>();

        for (Planilha planilha : eXLSX.getPlanilhas()) {

            Entidade e_planilha = new Entidade();
            e_planilha.at("Titulo",planilha.getTitulo());
            e_planilha.atInt("Linhas",planilha.getLinhas().getQuantidade());
            e_planilha.atInt("Colunas",planilha.maxColunas());

            for (PlanilhaLinha linha : planilha.getLinhas()) {
                Entidade e_linha = new Entidade();

                int coluna_id = 0;

                for (String coluna_valor : linha.getColunas()) {
                    e_linha.at(String.valueOf(coluna_id),coluna_valor);
                    coluna_id+=1;
                }

                e_planilha.getEntidades().adicionar(e_linha);
            }

            planilhas.adicionar(e_planilha);

        }


        return planilhas;
    }

    public static Lista<Entidade> CRIAR_DE_ODS(ODS eODS){

        Lista<Entidade> planilhas = new Lista<Entidade>();

        for (Planilha planilha : eODS.getPlanilhas()) {

            Entidade e_planilha = new Entidade();
            e_planilha.at("Titulo",planilha.getTitulo());
            e_planilha.atInt("Linhas",planilha.getLinhas().getQuantidade());
            e_planilha.atInt("Colunas",planilha.maxColunas());

            for (PlanilhaLinha linha : planilha.getLinhas()) {
                Entidade e_linha = new Entidade();

                int coluna_id = 0;

                for (String coluna_valor : linha.getColunas()) {
                    e_linha.at(String.valueOf(coluna_id),coluna_valor);
                    coluna_id+=1;
                }

                e_planilha.getEntidades().adicionar(e_linha);
            }

            planilhas.adicionar(e_planilha);

        }


        return planilhas;
    }


    public static void EXIBIR_TABELA(Lista<Entidade> objetos) {

        Lista<Entidade> colunas = new Lista<Entidade>();

        for (Entidade obj : objetos) {

            for (Tag att : obj.tags()) {
                int tamanho = att.getValor().length();
                Entidade obj_coluna = CRIAR_UNICO(colunas, "Nome", att.getNome());

                if (obj_coluna.atIntOuPadrao("Tamanho",0) < tamanho) {
                    obj_coluna.at("Tamanho",tamanho);
                }

                if (obj_coluna.atIntOuPadrao("Tamanho",0) < att.getNome().length()) {
                    obj_coluna.at("Tamanho",att.getNome().length());
                }

            }
        }


        int tracos = 0;
        for (Entidade obj : colunas) {
            int tt = obj.atInt("Tamanho") + 5 + 2;
            tracos += tt;
        }
        tracos += 2;

        fmt.print(fmt.repetir("-", tracos));

        String cabecalho = "";
        for (Entidade obj : colunas) {
            int tt = obj.atIntOuPadrao("Tamanho",0) + 5;
            cabecalho += " |" + fmt.espacar_antes(obj.at("Nome"), tt);
        }

        fmt.print("{}", cabecalho + " |");
        fmt.print(fmt.repetir("-", tracos));

        for (Entidade obj : objetos) {

            String linha = "";

            for (Entidade coluna : colunas) {
                int tt = coluna.atInt("Tamanho") + 5;
                linha += " |" + fmt.espacar_antes(obj.at(coluna.at("Nome")), tt);
            }

            fmt.print("{}", linha + " |");

        }
        fmt.print(fmt.repetir("-", tracos));


    }

    public static Entidade CRIAR_UNICO(Lista<Entidade> entts , String eID, String eValor) {

        Entidade ret = null;
        boolean existe = false;
        for (Entidade objeto_corrente : entts) {
            if (objeto_corrente.at(eID).contentEquals(eValor)) {
                existe = true;
                ret = objeto_corrente;
                break;
            }
        }

        if (!existe) {
            Entidade objeto_novo = new Entidade();
            objeto_novo.at(eID, eValor);
            entts.adicionar(objeto_novo);
            ret = objeto_novo;
        }

        return ret;
    }


}
