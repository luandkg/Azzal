package libs.entt;

import libs.arquivos.ds.DS;
import libs.arquivos.ds.DSItem;
import libs.dkg.DKG;
import libs.dkg.DKGAtributo;
import libs.dkg.DKGObjeto;
import libs.luan.*;
import libs.oo.ODS;
import libs.xlsx.Planilha;
import libs.xlsx.PlanilhaLinha;
import libs.xlsx.XLSX;

public class ENTT {

    public static Entidade CRIAR(String nome, String valor) {
        Entidade e = new Entidade();
        e.at(nome, valor);
        return e;
    }

    public static Entidade CRIAR(String nome, int valor) {
        Entidade e = new Entidade();
        e.at(nome, valor);
        return e;
    }

    public static Entidade CRIAR(String nome, long valor) {
        Entidade e = new Entidade();
        e.at(nome, valor);
        return e;
    }

    public static Entidade CRIAR(String nome1, String valor1, String nome2, String valor2) {
        Entidade e = new Entidade();
        e.at(nome1, valor1);
        e.at(nome2, valor2);
        return e;
    }

    public static Entidade CRIAR(String nome1, int valor1, String nome2, String valor2) {
        Entidade e = new Entidade();
        e.at(nome1, valor1);
        e.at(nome2, valor2);
        return e;
    }

    public static Entidade CRIAR(String nome1, String valor1, String nome2, int valor2) {
        Entidade e = new Entidade();
        e.at(nome1, valor1);
        e.at(nome2, valor2);
        return e;
    }

    public static Entidade GET_UNICO(Lista<Entidade> mEntts, String eNome, String eValor) {
        Entidade ret = null;

        for (Entidade e : mEntts) {
            if (e.at(eNome).contentEquals(eValor)) {
                ret = e;
                break;
            }
        }

        return ret;
    }

    public static boolean EXISTE(Lista<Entidade> mEntts, String eNome, String eValor) {

        boolean existe = false;

        for (Entidade existente : mEntts) {
            String existente_chave = existente.at(eNome);
            if (existente_chave.contentEquals(eValor)) {
                existe = true;
                break;
            }
        }

        return existe;
    }

    public static Entidade GET_SEMPRE(Lista<Entidade> mEntts, String eNome, String eValor) {
        Entidade ret = null;
        boolean enc = false;

        for (Entidade e : mEntts) {
            if (e.at(eNome).contentEquals(eValor)) {
                ret = e;
                enc = true;
                break;
            }
        }

        if (!enc) {
            ret = new Entidade();
            ret.at(eNome, eValor);
            mEntts.adicionar(ret);
        }
        return ret;
    }

    public static Entidade GET_SEMPRE(Lista<Entidade> mEntts, String eNome, int eValor) {
        Entidade ret = null;
        boolean enc = false;

        for (Entidade e : mEntts) {
            if (e.atInt(eNome) == (eValor)) {
                ret = e;
                enc = true;
                break;
            }
        }

        if (!enc) {
            ret = new Entidade();
            ret.at(eNome, eValor);
            mEntts.adicionar(ret);
        }
        return ret;
    }

    public static Entidade GET_SEMPRE_TENTATIVA(Lista<Entidade> mEntts, String eNome, int eValor) {
        Entidade ret = null;
        boolean enc = false;

        for (Entidade e : mEntts) {
            if (e.atIntOuPadrao(eNome,0) == (eValor)) {
                ret = e;
                enc = true;
                break;
            }
        }

        if (!enc) {
            ret = new Entidade();
            ret.at(eNome, eValor);
            mEntts.adicionar(ret);
        }
        return ret;
    }

    public static Opcional<Entidade> GET_OPCIONAL(Lista<Entidade> mEntts, String eNome, String eValor) {

        for (Entidade e : mEntts) {
            if (e.at(eNome).contentEquals(eValor)) {
                return Opcional.OK(e);
            }
        }

        return Opcional.CANCEL();

    }


    public static int GET_INTEIRO_MAIOR(Lista<Entidade> mEntts, String eNome) {
        int maior = 0;

        for (Entidade e : mEntts) {
            if (e.atIntOuPadrao(eNome, 0) > maior) {
                maior = e.atInt(eNome);
            }
        }

        return maior;
    }

    public static int GET_INTEIRO_MENOR(Lista<Entidade> mEntts, String eNome) {
        int menor = 0;

        boolean is_primeiro = true;

        for (Entidade e : mEntts) {
            if(is_primeiro){
                menor=e.atInt(eNome);
                is_primeiro=false;
            }else{
                if (e.atIntOuPadrao(eNome, 0) < menor) {
                    menor = e.atInt(eNome);
                }
            }

        }

        return menor;
    }

    public static Lista<Entidade> GET_ATRIBUTOS(Lista<Entidade> entidades) {

        Unico<String> atributos = new Unico<String>(Strings.IGUALAVEL());

        for (Entidade e : entidades) {
            for (Tag tag : e.tags()) {
                atributos.item(tag.getNome());
            }
        }

        Lista<Entidade> e_atts = new Lista<Entidade>();
        for (String item : atributos) {
            e_atts.adicionar(ENTT.CRIAR("Nome", item));
        }

        return e_atts;
    }

    public static Lista<Entidade> GET_CAMPOS(Lista<Entidade> mEntts, Lista<String> campos) {
        Lista<Entidade> ret = new Lista<Entidade>();

        for (Entidade e : mEntts) {
            Entidade nova = new Entidade();
            for (String campo : campos) {
                nova.at(campo, e.at(campo));
            }
            ret.adicionar(nova);
        }

        return ret;
    }

    public static Entidade ATRIBUTO_INTEIRO_MENOR(Lista<Entidade> mEntts, String eNome) {
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

    public static Entidade ATRIBUTO_INTEIRO_MAIOR(Lista<Entidade> mEntts, String eNome) {
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


    public static void REMOVER_SE(Lista<Entidade> mEntts, String eNome, String eValor) {

        Lista<Entidade> remover = new Lista<Entidade>();

        for (Entidade e : mEntts) {
            if (e.at(eNome).contentEquals(eValor)) {
                remover.adicionar(e);
            }
        }

        for (Entidade e : remover) {
            mEntts.remover(e);
            // fmt.print("Removendo -->:> ");
        }

    }

    public static void REMOVER_SE(Lista<Entidade> mEntts, String eNome, int eValor) {

        Lista<Entidade> remover = new Lista<Entidade>();

        for (Entidade e : mEntts) {
            if (e.atInt(eNome) == (eValor)) {
                remover.adicionar(e);
            }
        }

        for (Entidade e : remover) {
            mEntts.remover(e);
            // fmt.print("Removendo -->:> ");
        }

    }

    public static void SEQUENCIAR(Lista<Entidade> mEntts, String eNome, int eInicio) {

        int valor = eInicio;

        for (Entidade e : mEntts) {
            e.at(eNome, valor);
            valor += 1;
        }


    }


    // FUNCOES ESPECIAIS

    public static int ATRIBUTO_SOMAR(Lista<Entidade> mEntts, String nome) {
        int somatorio = 0;

        for (Entidade e : mEntts) {
            somatorio += e.atInt(nome);
        }

        return somatorio;
    }

    public static int ATRIBUTO_SOMAR_E_AGREGAR(Lista<Entidade> mEntts, String a1, String a2) {
        int t1 = ATRIBUTO_SOMAR(mEntts, a1);
        int t2 = ATRIBUTO_SOMAR(mEntts, a2);

        return t1 + t2;
    }

    public static void EXPORTAR_DKG(Lista<Entidade> mEntts, String arquivo) {

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

    public static Lista<Entidade> CRIAR_DE_XLSX(XLSX eXLSX) {

        Lista<Entidade> planilhas = new Lista<Entidade>();

        for (Planilha planilha : eXLSX.getPlanilhas()) {

            Entidade e_planilha = new Entidade();
            e_planilha.at("Titulo", planilha.getTitulo());
            e_planilha.atInt("Linhas", planilha.getLinhas().getQuantidade());
            e_planilha.atInt("Colunas", planilha.maxColunas());

            for (PlanilhaLinha linha : planilha.getLinhas()) {
                Entidade e_linha = new Entidade();

                int coluna_id = 0;

                for (String coluna_valor : linha.getColunas()) {
                    e_linha.at(String.valueOf(coluna_id), coluna_valor);
                    coluna_id += 1;
                }

                e_planilha.getEntidades().adicionar(e_linha);
            }

            planilhas.adicionar(e_planilha);

        }

        return planilhas;
    }

    public static Lista<Entidade> CRIAR_DE_ODS(ODS eODS) {

        Lista<Entidade> planilhas = new Lista<Entidade>();

        for (Planilha planilha : eODS.getPlanilhas()) {

            Entidade e_planilha = new Entidade();
            e_planilha.at("Titulo", planilha.getTitulo());
            e_planilha.atInt("Linhas", planilha.getLinhas().getQuantidade());
            e_planilha.atInt("Colunas", planilha.maxColunas());

            for (PlanilhaLinha linha : planilha.getLinhas()) {
                Entidade e_linha = new Entidade();

                int coluna_id = 0;

                for (String coluna_valor : linha.getColunas()) {
                    e_linha.at(String.valueOf(coluna_id), coluna_valor);
                    coluna_id += 1;
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

                if (obj_coluna.atIntOuPadrao("Tamanho", 0) < tamanho) {
                    obj_coluna.at("Tamanho", tamanho);
                }

                if (obj_coluna.atIntOuPadrao("Tamanho", 0) < att.getNome().length()) {
                    obj_coluna.at("Tamanho", att.getNome().length());
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

        for (Indexado<Entidade> obj : Indexamento.indexe(colunas)) {
            int tt = obj.get().atIntOuPadrao("Tamanho", 0) + 5;
            if (obj.index() == 0) {
                cabecalho += "|" + fmt.espacar_depois(obj.get().at("Nome"), tt);
            } else {
                cabecalho += " |" + fmt.espacar_depois(obj.get().at("Nome"), tt);
            }
        }


        fmt.print("{}", cabecalho + " |");
        fmt.print(fmt.repetir("-", tracos));

        for (Indexado<Entidade> obj : Indexamento.indexe(objetos)) {

            String linha = "";

            for (Indexado<Entidade> coluna : Indexamento.indexe(colunas)) {
                int tt = coluna.get().atInt("Tamanho") + 5;

                if (coluna.index() == 0) {
                    linha += "|" + fmt.espacar_depois(obj.get().at(coluna.get().at("Nome")), tt);
                } else {
                    linha += " |" + fmt.espacar_depois(obj.get().at(coluna.get().at("Nome")), tt);
                }

            }

            fmt.print("{}", linha + " |");

        }
        fmt.print(fmt.repetir("-", tracos));

    }

    public static void EXIBIR_TABELA_PREFIXO(String prefixo, Lista<Entidade> objetos) {

        Lista<Entidade> colunas = new Lista<Entidade>();

        for (Entidade obj : objetos) {

            for (Tag att : obj.tags()) {
                int tamanho = att.getValor().length();
                Entidade obj_coluna = CRIAR_UNICO(colunas, "Nome", att.getNome());

                if (obj_coluna.atIntOuPadrao("Tamanho", 0) < tamanho) {
                    obj_coluna.at("Tamanho", tamanho);
                }

                if (obj_coluna.atIntOuPadrao("Tamanho", 0) < att.getNome().length()) {
                    obj_coluna.at("Tamanho", att.getNome().length());
                }

            }
        }

        int tracos = 0;
        for (Entidade obj : colunas) {
            int tt = obj.atInt("Tamanho") + 5 + 2;
            tracos += tt;
        }
        tracos += 2;

        fmt.print(prefixo + fmt.repetir("-", tracos));

        String cabecalho = "";

        for (Indexado<Entidade> obj : Indexamento.indexe(colunas)) {
            int tt = obj.get().atIntOuPadrao("Tamanho", 0) + 5;
            if (obj.index() == 0) {
                cabecalho += "|" + fmt.espacar_depois(obj.get().at("Nome"), tt);
            } else {
                cabecalho += " |" + fmt.espacar_depois(obj.get().at("Nome"), tt);
            }
        }


        fmt.print(prefixo + "{}", cabecalho + " |");
        fmt.print(prefixo + fmt.repetir("-", tracos));

        for (Indexado<Entidade> obj : Indexamento.indexe(objetos)) {

            String linha = "";

            for (Indexado<Entidade> coluna : Indexamento.indexe(colunas)) {
                int tt = coluna.get().atInt("Tamanho") + 5;

                if (coluna.index() == 0) {
                    linha += "|" + fmt.espacar_depois(obj.get().at(coluna.get().at("Nome")), tt);
                } else {
                    linha += " |" + fmt.espacar_depois(obj.get().at(coluna.get().at("Nome")), tt);
                }

            }

            fmt.print(prefixo + "{}", linha + " |");

        }
        fmt.print(prefixo + fmt.repetir("-", tracos));

    }

    public static void EXIBIR_TABELA_COM_TITULO(Lista<Entidade> objetos, String sTitulo) {

        Lista<Entidade> colunas = new Lista<Entidade>();

        for (Entidade obj : objetos) {

            for (Tag att : obj.tags()) {
                int tamanho = att.getValor().length();
                Entidade obj_coluna = CRIAR_UNICO(colunas, "Nome", att.getNome());

                if (obj_coluna.atIntOuPadrao("Tamanho", 0) < tamanho) {
                    obj_coluna.at("Tamanho", tamanho);
                }

                if (obj_coluna.atIntOuPadrao("Tamanho", 0) < att.getNome().length()) {
                    obj_coluna.at("Tamanho", att.getNome().length());
                }

            }
        }

        int tracos = 0;
        for (Entidade obj : colunas) {
            int tt = obj.atInt("Tamanho") + 5 + 2;
            tracos += tt;
        }
        tracos += 2;

        int tracos_metade = (tracos / 2) - (sTitulo.length() / 2);

        fmt.print(fmt.repetir("-", tracos));

        int contando_todos = tracos_metade + sTitulo.length() + tracos_metade;
        fmt.print("|{}{}{}|", fmt.repetir(" ", tracos_metade - 1), sTitulo, fmt.repetir(" ", tracos_metade - 1));


        fmt.print(fmt.repetir("-", tracos));


        String cabecalho = "";

        for (Indexado<Entidade> obj : Indexamento.indexe(colunas)) {
            int tt = obj.get().atIntOuPadrao("Tamanho", 0) + 5;
            if (obj.index() == 0) {
                cabecalho += "|" + fmt.espacar_depois(obj.get().at("Nome"), tt);
            } else {
                cabecalho += " |" + fmt.espacar_depois(obj.get().at("Nome"), tt);
            }
        }


        fmt.print("{}", cabecalho + " |");
        fmt.print(fmt.repetir("-", tracos));

        for (Indexado<Entidade> obj : Indexamento.indexe(objetos)) {

            String linha = "";

            for (Indexado<Entidade> coluna : Indexamento.indexe(colunas)) {
                int tt = coluna.get().atInt("Tamanho") + 5;

                if (coluna.index() == 0) {
                    linha += "|" + fmt.espacar_depois(obj.get().at(coluna.get().at("Nome")), tt);
                } else {
                    linha += " |" + fmt.espacar_depois(obj.get().at(coluna.get().at("Nome")), tt);
                }

            }

            fmt.print("{}", linha + " |");

        }
        fmt.print(fmt.repetir("-", tracos));

    }


    public static Entidade CRIAR_UNICO(Lista<Entidade> entts, String eID, String eValor) {

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

    public static Lista<DKGObjeto> ENTIDADE_TO_OBJETO(Lista<Entidade> entts) {
        Lista<DKGObjeto> objetos = new Lista<DKGObjeto>();

        for (Entidade e : entts) {
            DKGObjeto obj = new DKGObjeto("Item");

            for (Tag tag : e.tags()) {
                obj.identifique(tag.getNome(), tag.getValor());
            }

            objetos.adicionar(obj);
        }

        return objetos;
    }


    public static Lista<String> FILTRAR_UNICOS(Lista<Entidade> entts, String campo) {

        Unico<String> valores = new Unico<String>(Strings.IGUALAVEL());

        for (Entidade e : entts) {
            valores.item(e.at(campo));
        }

        return valores.toLista();
    }

    public static Lista<Entidade> FILTRAR_UNICOS_EM_ENTIDADES(Lista<Entidade> entts, String campo) {

        Unico<String> valores = new Unico<String>(Strings.IGUALAVEL());

        for (Entidade e : entts) {
            valores.item(e.at(campo));
        }

        Lista<Entidade> unicos_entts = CRIAR_LISTA();
        for (String item : valores.toLista()) {
            Entidade novo = CRIAR_EM(unicos_entts);
            novo.at(campo, item);
        }

        return unicos_entts;
    }

    public static Lista<Entidade> COLETAR(Lista<Entidade> entts, String campo_nome, String campo_valor) {
        Lista<Entidade> filtrados = new Lista<Entidade>();

        for (Entidade e : entts) {
            if (e.at(campo_nome).contentEquals(campo_valor)) {
                filtrados.adicionar(e);
            }
        }


        return filtrados;
    }

    public static Lista<Entidade> COLETAR_VAZIO(Lista<Entidade> entts, String campo_nome) {
        Lista<Entidade> filtrados = new Lista<Entidade>();

        for (Entidade e : entts) {
            if (e.isVazio(campo_nome)) {
                filtrados.adicionar(e);
            }
        }


        return filtrados;
    }


    public static int CONTAGEM(Lista<Entidade> entts, String campo_nome, String campo_valor) {
        int contagem = 0;

        for (Entidade e : entts) {
            if (e.at(campo_nome).contentEquals(campo_valor)) {
                contagem += 1;
            }
        }


        return contagem;
    }

    public static int CONTAGEM(Lista<Entidade> entts, String campo_nome, int campo_valor) {
        int contagem = 0;

        for (Entidade e : entts) {
            if (e.atInt(campo_nome)==campo_valor) {
                contagem += 1;
            }
        }


        return contagem;
    }


    public static int CONTAGEM_UNICOS(Lista<Entidade> entts, String campo_nome ) {
        return FILTRAR_UNICOS(entts,campo_nome).getQuantidade();
    }


        public static Lista<Entidade> COLETAR_DIFERENTE_DE(Lista<Entidade> entts, String campo_nome, String campo_valor) {
        Lista<Entidade> filtrados = new Lista<Entidade>();

        for (Entidade e : entts) {
            if (!e.at(campo_nome).contentEquals(campo_valor)) {
                filtrados.adicionar(e);
            }
        }


        return filtrados;
    }

    public static Lista<Entidade> COLETAR(Lista<Entidade> entts, String campo_nome, int campo_valor) {
        Lista<Entidade> filtrados = new Lista<Entidade>();

        for (Entidade e : entts) {
            if (e.atInt(campo_nome) == (campo_valor)) {
                filtrados.adicionar(e);
            }
        }


        return filtrados;
    }

    public static Lista<Entidade> AGRUPAR(Lista<Entidade> entts, String campo) {

        Lista<String> grupos = FILTRAR_UNICOS(entts, campo);
        Lista<Entidade> entts_por_grupo = new Lista<Entidade>();

        for (String grupo : grupos) {
            Entidade e_grupo = new Entidade();
            e_grupo.at(campo, grupo);

            for (Entidade e : COLETAR(entts, campo, grupo)) {
                e_grupo.getEntidades().adicionar(e);
            }

            entts_por_grupo.adicionar(e_grupo);

        }

        return entts_por_grupo;
    }


    public static Lista<Entidade> AGRUPAR_E_ORDENAR(Lista<Entidade> entts, String campo) {
        return ENTT.ORDENAR_TEXTO(ENTT.AGRUPAR(entts, campo), campo);
    }


    public static Lista<Entidade> ORDENAR_TEXTO(Lista<Entidade> entts, String campo) {


        int n = entts.getQuantidade();
        Entidade temp = null;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                if (entts.get(j - 1).at(campo).compareTo(entts.get(j).at(campo)) > 0) {
                    temp = entts.get(j - 1);
                    entts.set(j - 1, entts.get(j));
                    entts.set(j, temp);

                }

            }
        }

        return entts;
    }

    public static Lista<Entidade> ORDENAR_INTEIRO(Lista<Entidade> entts, String campo) {


        int n = entts.getQuantidade();
        Entidade temp = null;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                if (entts.get(j - 1).atInt(campo) > (entts.get(j).atInt(campo))) {
                    temp = entts.get(j - 1);
                    entts.set(j - 1, entts.get(j));
                    entts.set(j, temp);

                }

            }
        }

        return entts;
    }

    public static Lista<Entidade> ORDENAR_INTEIRO_DECRESCENTE(Lista<Entidade> entts, String campo) {


        int n = entts.getQuantidade();
        Entidade temp = null;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                if (entts.get(j - 1).atInt(campo) < (entts.get(j).atInt(campo))) {
                    temp = entts.get(j - 1);
                    entts.set(j - 1, entts.get(j));
                    entts.set(j, temp);

                }

            }
        }

        return entts;
    }

    public static Lista<Entidade> ORDENAR_DOUBLE_DECRESCENTE(Lista<Entidade> entts, String campo) {


        int n = entts.getQuantidade();
        Entidade temp = null;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                if (entts.get(j - 1).atDouble(campo) < (entts.get(j).atDouble(campo))) {
                    temp = entts.get(j - 1);
                    entts.set(j - 1, entts.get(j));
                    entts.set(j, temp);

                }

            }
        }

        return entts;
    }

    public static Lista<Entidade> ORDENAR_DOUBLE(Lista<Entidade> entts, String campo) {


        int n = entts.getQuantidade();
        Entidade temp = null;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                if (entts.get(j - 1).atDouble(campo) > (entts.get(j).atDouble(campo))) {
                    temp = entts.get(j - 1);
                    entts.set(j - 1, entts.get(j));
                    entts.set(j, temp);

                }

            }
        }

        return entts;
    }


    public static Entidade GET_UM(Lista<Entidade> entts, String campo_nome, String campo_valor) {

        Entidade buscado = null;

        for (Entidade e : entts) {
            if (e.at(campo_nome).contentEquals(campo_valor)) {
                buscado = e;
                break;
            }
        }

        return buscado;
    }

    public static Entidade GET_UM(Lista<Entidade> entts, String campo_nome, int campo_valor) {

        Entidade buscado = null;

        for (Entidade e : entts) {
            if (e.atInt(campo_nome) == (campo_valor)) {
                buscado = e;
                break;
            }
        }

        return buscado;
    }


    public static void EXIBIR_DETALHADO(Entidade entidade) {

        int nome_tamanho = 0;

        for (Tag a : entidade.tags()) {
            if (a.getNome().length() > nome_tamanho) {
                nome_tamanho = a.getNome().length();
            }
        }

        fmt.print("");

        for (Tag a : entidade.tags()) {
            fmt.print("{} = {}", fmt.espacar_depois(a.getNome(), nome_tamanho + 5), a.getValor());
        }

        ENTT.EXIBIR_TABELA(entidade.getEntidades());

    }


    public static void GUARDAR(Lista<Entidade> mEntts, String arquivo) {

        DS.limpar(arquivo);

        for (Indexado<Entidade> e : Indexamento.indexe(mEntts)) {
            DS.adicionar(arquivo, String.valueOf(e.index()), TO_DOCUMENTO(e.get()));
        }

    }

    public static String GUARDAR_DOCUMENTO(Lista<Entidade> mEntts) {

        TextoDocumento texto = new TextoDocumento();

        for (Indexado<Entidade> e : Indexamento.indexe(mEntts)) {
            texto.adicionarLinha(TO_DOCUMENTO(e.get()));
        }

        return texto.toDocumento();
    }


    public static Lista<Entidade> ABRIR(String arquivo) {

        Lista<Entidade> lista = new Lista<Entidade>();

        for (DSItem item : DS.ler_todos(arquivo)) {
            lista.adicionar(PARSER_ENTIDADE(item.getTexto()));
        }


        return lista;
    }

    public static Entidade PARSER_ENTIDADE(String dados) {
        Entidade e = ENTTBinario.PARSER_TO_OBJETO(dados);
        return e;
    }

    public static String TO_DOCUMENTO(Entidade e) {
        return ENTTBinario.TO_DOCUMENTO(e);
    }

    public static String TO_DOCUMENTO(Lista<Entidade> lista) {

        DKGObjeto raiz = new DKGObjeto("ENTT");

        int pos = 0;

        for (Entidade e : lista) {
            DKGObjeto objeto = new DKGObjeto("Item");
            for (Tag att : e.tags()) {
                objeto.identifique(att.getNome(), att.getValor());
            }
            interno(e, objeto);
            raiz.adicionarObjeto(objeto);
            pos += 1;
        }

        return raiz.toDocumento();
    }

    public static void interno(Entidade entidadePai, DKGObjeto objetoPai) {

        for (Entidade e : entidadePai.getEntidades()) {
            DKGObjeto objeto = objetoPai.criarObjeto("Item");
            for (Tag att : e.tags()) {
                objeto.identifique(att.getNome(), att.getValor());
            }
            interno(e, objeto);
        }

    }

    public static Lista<Entidade> GET_AMOSTRA_PEQUENA(Lista<Entidade> entts) {
        Lista<Entidade> amostra = new Lista<Entidade>();

        Lista<Entidade> entts_copia = entts.getCopia();

        for (int s = 0; s < 10; s++) {

            if (entts_copia.getQuantidade() > 1) {
                Entidade escolhida = ENTT.GET_ALEATORIO(entts_copia);
                amostra.adicionar(escolhida);
                entts_copia.remover(escolhida);
            }

        }

        return amostra;
    }

    public static Lista<Entidade> GET_AMOSTRA(Lista<Entidade> entts) {
        Lista<Entidade> amostra = new Lista<Entidade>();

        Lista<Entidade> entts_copia = entts.getCopia();

        for (int s = 0; s < 100; s++) {

            if (entts_copia.getQuantidade() > 1) {
                Entidade escolhida = ENTT.GET_ALEATORIO(entts_copia);
                amostra.adicionar(escolhida);
                entts_copia.remover(escolhida);
            }

        }

        return amostra;
    }

    public static Entidade GET_ALEATORIO(Lista<Entidade> entts) {

        return entts.get(Aleatorio.aleatorio(entts.getQuantidade()));

    }

    public static Lista<Entidade> GET_FATIA_INICIO(Lista<Entidade> entts) {
        Lista<Entidade> amostra = new Lista<Entidade>();

        Lista<Entidade> entts_copia = entts.getCopia();

        for (int s = 0; s < 100; s++) {

            if (entts_copia.getQuantidade() > 1) {
                Entidade escolhida = entts_copia.get(s);
                amostra.adicionar(escolhida);
                entts_copia.remover(escolhida);
            }

        }

        return amostra;
    }

    public static Lista<Entidade> GET_FATIA_INICIO_PEQUENO(Lista<Entidade> entts) {
        Lista<Entidade> amostra = new Lista<Entidade>();


        for (int s = 0; s < 10; s++) {
            Entidade escolhida = entts.get(s);
            amostra.adicionar(escolhida);
        }

        return amostra;
    }

    public static int CONTAGEM(Lista<Entidade> entts) {
        return entts.getQuantidade();
    }

    public static Lista<Entidade> CRIAR_LISTA() {
        return new Lista<Entidade>();
    }


    public static double ATRIBUTOS_VARIOS_ANALISE_DOUBLE_MENOR(Entidade e, String prefixo, int inicio, int fim) {

        double menor = e.atDouble(prefixo + inicio);
        boolean tem = true;

        for (int i = inicio; i <= fim; i++) {
            double valor = e.atDouble(prefixo + i);
            if (valor < menor) {
                menor = valor;
                tem = true;
            }
        }


        if (tem) {
            return menor;
        } else {
            throw new RuntimeException("ERRO :: ATRIBUTOS_VARIOS_ANALISE_DOUBLE_MENOR");
        }

    }

    public static double ATRIBUTOS_VARIOS_ANALISE_DOUBLE_MAIOR(Entidade e, String prefixo, int inicio, int fim) {

        double maior = e.atDouble(prefixo + inicio);
        boolean tem = true;

        for (int i = inicio; i <= fim; i++) {
            double valor = e.atDouble(prefixo + i);
            if (valor > maior) {
                maior = valor;
                tem = true;
            }
        }


        if (tem) {
            return maior;
        } else {
            throw new RuntimeException("ERRO :: ATRIBUTOS_VARIOS_ANALISE_DOUBLE_MAIOR");
        }

    }

    public static Entidade GET_PRIMEIRO(Lista<Entidade> entts) {
        return entts.get(0);
    }

    public static Entidade GET_ULTIMO(Lista<Entidade> entts) {
        return entts.get(entts.getQuantidade() - 1);
    }


    public static Lista<Entidade> DISPERSAO(Lista<Entidade> entts, String atributo) {

        Lista<Entidade> dispersao = CRIAR_LISTA();

        for (String valor : FILTRAR_UNICOS(entts, atributo)) {

            Entidade e = GET_SEMPRE(dispersao, atributo, valor);
            e.at("Quantidade", COLETAR(entts, atributo, valor).getQuantidade());
        }

        return dispersao;
    }


    public static void DEFINIR(Lista<Entidade> entts, String att_nome, String att_valor) {
        for (Entidade e : entts) {
            e.at(att_nome, att_valor);
        }
    }

    public static void ADICIONAR_VARIOS(Lista<Entidade> entts, Lista<Entidade> adicionar) {
        for (Entidade e : adicionar) {
            entts.adicionar(e);
        }
    }


    public static Entidade CRIAR_EM_SEQUENCIALMENTE(Lista<Entidade> entts,String sequencia_nome) {
        Entidade novo = new Entidade();
        novo.at(sequencia_nome,ENTT.CONTAGEM(entts));
        entts.adicionar(novo);
        return novo;
    }
    public static Entidade CRIAR_EM(Lista<Entidade> entts) {
        Entidade novo = new Entidade();
        entts.adicionar(novo);
        return novo;
    }

    public static Entidade CRIAR_EM(Lista<Entidade> entts,String att_nome,String att_valor) {
        Entidade novo = new Entidade();
        novo.at(att_nome,att_valor);
        entts.adicionar(novo);
        return novo;
    }

    public static void SUBSTITUIR(Lista<Entidade> objetos, String proc_valor, String novo_valor) {


        for (Entidade obj : objetos) {
            for (Tag att : obj.tags()) {
                if (att.isValorIgual(proc_valor)) {
                    att.setValor(novo_valor);
                }
            }
        }

    }

    public static void SUBSTITUIR_INTEIRO_ABAIXO(Lista<Entidade> objetos, int proc_valor, int novo_valor) {

        for (Entidade obj : objetos) {
            for (Tag att : obj.tags()) {
                if (att.isInteiro()) {
                    if (att.asInt() <= proc_valor) {
                        att.setInteiro(novo_valor);
                    }
                }
            }
        }

    }

    public static Lista<Entidade> ABRIR_COM_LIMITE(String arquivo, int limite) {

        Lista<Entidade> lista = new Lista<Entidade>();

        for (DSItem item : DS.ler_todos(arquivo)) {
            if (lista.getQuantidade() < limite) {
                lista.adicionar(PARSER_ENTIDADE(item.getTexto()));
            } else {
                break;
            }
        }


        return lista;
    }

    public static void AT_ALTERAR_NOME(Lista<Entidade> entts, String atributo_antigo, String atributo_novo) {

        for (Entidade item : entts) {
            item.proc_at(atributo_antigo).setNome(atributo_novo);
        }

    }


    public static Lista<Entidade> GET_TOP10(Lista<Entidade> entts) {

        Lista<Entidade> top = new Lista<Entidade>();

        for (Entidade obj : entts) {
            if(CONTAGEM(top)<10){
                top.adicionar(obj);
            }else{
                break;
            }
        }

        return top;
    }


    public static Lista<Entidade> LISTA_TO_ENTIDADES(Lista<String> valores){

        Lista<Entidade> entts = CRIAR_LISTA();

        for(String item : valores){
            CRIAR_EM(entts,"Item",item);
        }

        return entts;

    }

    public static void EXIBIR_TABELA_COM_NOME(Lista<Entidade> entidades_lista, String tabela_nome) {


        String cabecalho = "";

        Lista<Entidade> cabs = new Lista<Entidade>();
        for (Entidade turma : entidades_lista) {
            for (Tag att : turma.tags()) {

                Entidade ent = null;
                String nome = att.getNome();
                int tam = nome.length();


                if (ENTT.EXISTE(cabs, "Nome", nome)) {
                    ent = ENTT.GET_SEMPRE(cabs, "Nome", nome);
                } else {
                    ent = new Entidade();
                    ent.at("Nome", nome);
                    ent.at("Tamanho", tam);
                    cabs.adicionar(ent);
                }

                int tt = ent.atInt("Tamanho");
                if (tam > tt) {
                    ent.at("Tamanho", tam);
                }
            }
        }

        for (Entidade turma : entidades_lista) {
            for (Entidade cab : cabs) {
                int valor = turma.at(cab.at("Nome")).length();
                if (valor > cab.atInt("Tamanho")) {
                    cab.at("Tamanho", valor);
                }
            }
        }


        int linha_tracos = 0;

        for (Entidade turma : cabs) {
            cabecalho += "| " + fmt.espacar_depois(turma.at("Nome"), turma.atInt("Tamanho") + 5);
            linha_tracos += turma.atInt("Tamanho") + 5 + 2;
        }

        fmt.print("{}", fmt.repetir("-", linha_tracos));

        int titulo_tracos_metade = (linha_tracos - 4 - tabela_nome.length()) / 2;

        fmt.print("| " + fmt.repetir(" ", titulo_tracos_metade) + tabela_nome + fmt.repetir(" ", titulo_tracos_metade) + " |");
        fmt.print("{}", fmt.repetir("-", linha_tracos));
        fmt.print(cabecalho + "|");
        fmt.print("{}", fmt.repetir("-", linha_tracos));

        for (Entidade turma : entidades_lista) {
            String linha = "";
            for (Entidade cab : cabs) {
                linha += "| " + fmt.espacar_depois(turma.at(cab.at("Nome")), cab.atInt("Tamanho") + 5);
            }
            fmt.print("{}", linha + "|");
        }

        fmt.print("{}", fmt.repetir("-", linha_tracos));

    }

    public static Lista<Entidade> SLICE(Lista<Entidade> entts, int inicio, int fim) {
        Lista<Entidade> ret = new Lista<Entidade>();

        int i = 0;
        for (Entidade e : entts) {
            if (i >= inicio && i <= fim) {
                ret.adicionar(e);
            }
            if (i > fim) {
                break;
            }
            i += 1;
        }
        return ret;
    }

    public static Lista<Entidade> PARSER(String texto_dados) {

        DKG dkg = new DKG();
        dkg.parser(texto_dados);

        Lista<Entidade> varias = new Lista<Entidade>();

        for (DKGObjeto o : dkg.getObjetos()) {

            Entidade e1 = new Entidade();

            for (DKGAtributo oa : o.getAtributos()) {
                e1.at(oa.getNome(), oa.getValor());
            }

            parser_interno(e1,o);

            varias.adicionar(e1);

        }

        return varias.get(0).getEntidades();

    }

    private static void parser_interno(Entidade e_pai,DKGObjeto o_pai){

        for (DKGObjeto o : o_pai.getObjetos()) {

            Entidade e1 = new Entidade();

            for (DKGAtributo oa : o.getAtributos()) {
                e1.at(oa.getNome(), oa.getValor());
            }

            parser_interno(e1,o);

            e_pai.getEntidades().adicionar(e1);
        }

    }


    public static void ATRIBUTO_ALTERAR(Lista<Entidade> entts,String att_nome,String att_valor){
        for(Entidade e : entts){
            e.at(att_nome,att_valor);
        }
    }

    public static boolean TEM(Lista<Entidade> entts){
       return entts.getQuantidade()>0;
    }


    public static Lista<Entidade> VALORES(String att_nome,Lista<String> valores){
        Lista<Entidade> entts = CRIAR_LISTA();

        for(String item : valores){
            Entidade e = new Entidade();
            e.at(att_nome,item);
            entts.adicionar(e);
        }

        return entts;
    }

    public static Lista<Entidade> VALORES_SEQUENCIADOS(String att_sequencia,String att_nome,Lista<String> valores){
        Lista<Entidade> entts = CRIAR_LISTA();

        for(String item : valores){
            Entidade e = new Entidade();
            e.at(att_sequencia,entts.getQuantidade());
            e.at(att_nome,item);
            entts.adicionar(e);
        }

        return entts;
    }


    public static Lista<Entidade> CRIAR_LISTA_COM(Entidade e){
        Lista<Entidade> ls = new Lista<Entidade>();
        ls.adicionar(e);
        return ls;
    }


    public static Lista<Entidade> CRIAR_LISTA_VALORES_DE(Lista<Entidade> entradas,Lista<String> campos){
        Lista<Entidade> ls = new Lista<Entidade>();

        for(Entidade entrada : entradas){
            Entidade novo = CRIAR_EM(ls);

            for(String campo : campos){
                novo.at(campo,entrada.at(campo));
            }


        }
        return ls;
    }

    public static void ATRIBUTO_TODOS(Lista<Entidade> entradas,String att_nome,String att_valor){

        for(Entidade entidade : entradas){
            entidade.at(att_nome,att_valor);
        }

    }


    public static void ATRIBUTO_TODOS(Lista<Entidade> entradas,String att_nome,int att_valor){

        for(Entidade entidade : entradas){
            entidade.at(att_nome,att_valor);
        }

    }


    public static Lista<Entidade> ABRIR_ZONAS_DE_CARREGAMENTO(String arquivo,int tamanho_zona) {

        Lista<Entidade> lista = new Lista<Entidade>();

        int quantidade = DS.ler_todos(arquivo).getQuantidade();

      int inicio= 0;

        while(quantidade>=tamanho_zona){
            Entidade e_zona = CRIAR_EM(lista);
            e_zona.at("Inicio",inicio);
            e_zona.at("Quantidade",tamanho_zona);
            e_zona.at("Fim",inicio+tamanho_zona);

            inicio+=tamanho_zona;
            quantidade-=tamanho_zona;
        }

        if(quantidade>0){
            Entidade e_zona = CRIAR_EM(lista);
            e_zona.at("Inicio",inicio);
            e_zona.at("Quantidade",quantidade);
            e_zona.at("Fim",inicio+quantidade);

        }

        return lista;
    }


    public static Lista<Entidade> TRANSFORMAR_DE_OBJETOS(Lista<DKGObjeto> objetos) {
        Lista<Entidade> ls = new Lista<Entidade>();

        for (DKGObjeto obj : objetos) {
            Entidade e = new Entidade();

            for (DKGAtributo att : obj.getAtributos()) {
                e.at(att.getNome(), att.getValor());
            }

            ls.adicionar(e);
        }

        return ls;
    }

    public static Entidade PARSER_RAW_TO_ENTIDADE(String dados) {
        DKG eDKG = new DKG();
        eDKG.parser(dados);

        Entidade e = new Entidade();

        if (eDKG.getObjetos().getQuantidade() > 0) {
            DKGObjeto raiz = eDKG.getObjetos().get(0);
            for (DKGAtributo a : raiz.getAtributos()) {
                e.at(a.getNome(), a.getValor());
            }
        }

        return e;
    }

    public static Entidade PARSER_ENTIDADE_QUALQUER(String texto_dados) {

        Entidade e = new Entidade();

        DKGObjeto obj = DKG.PARSER(texto_dados).getObjetos().get(0);

        for (DKGAtributo a : obj.getAtributos()) {
            e.at(a.getNome(), a.getValor());
        }

        for (DKGObjeto o : obj.getObjetos()) {

            Entidade e1 = new Entidade();

            for (DKGAtributo oa : o.getAtributos()) {
                e1.at(oa.getNome(), oa.getValor());
            }

            e.getEntidades().adicionar(e1);
        }


        return e;

    }


    public static Entidade ADICIONAR_EM(Lista<Entidade> entts,Entidade e) {
        entts.adicionar(e);
        return e;
    }


}
