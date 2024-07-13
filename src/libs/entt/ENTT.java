package libs.entt;

import libs.arquivos.ds.DS;
import libs.arquivos.ds.DSItem;
import libs.dkg.DKG;
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

    public static Entidade CRIAR(String nome1, String valor1,String nome2,String valor2) {
        Entidade e = new Entidade();
        e.at(nome1, valor1);
        e.at(nome2, valor2);
        return e;
    }

    public static Entidade CRIAR(String nome1, int valor1,String nome2,String valor2) {
        Entidade e = new Entidade();
        e.at(nome1, valor1);
        e.at(nome2, valor2);
        return e;
    }

    public static Entidade CRIAR(String nome1, String valor1,String nome2,int valor2) {
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

    public static void EXIBIR_TABELA_COM_TITULO(Lista<Entidade> objetos,String sTitulo) {

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

        int tracos_metade = (tracos/2)-(sTitulo.length()/2);

        fmt.print(fmt.repetir("-", tracos));
        fmt.print("|{}{}{}|",fmt.repetir(" ",tracos_metade),sTitulo,fmt.repetir(" ",tracos_metade));
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

    public static Lista<Entidade> COLETAR(Lista<Entidade> entts, String campo_nome, String campo_valor) {
        Lista<Entidade> filtrados = new Lista<Entidade>();

        for (Entidade e : entts) {
            if (e.at(campo_nome).contentEquals(campo_valor)) {
                filtrados.adicionar(e);
            }
        }


        return filtrados;
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

    public static int CONTAGEM(Lista<Entidade> entts){
        return entts.getQuantidade();
    }

}
