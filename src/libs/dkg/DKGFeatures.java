package libs.dkg;


import libs.luan.*;
import libs.tempo.Data;

public class DKGFeatures {

    // FEATURE 22.10.05 - renomear_objetos_iguais_sequencialmente
    public static void renomear_objetos_iguais_sequencialmente(Lista<DKGObjetoOuAtributo> lista) {

        String sPrimeiro = "";
        boolean isPrimeiro = true;

        int quantidade = 0;
        int igual = 0;

        for (DKGObjetoOuAtributo obj : lista) {

            if (obj.isObjeto()) {
                if (isPrimeiro) {
                    isPrimeiro = false;
                    sPrimeiro = obj.getObjeto().getNome();
                    igual += 1;
                } else {
                    if (obj.getObjeto().getNome().contentEquals(sPrimeiro)) {
                        igual += 1;
                    }
                }
                quantidade += 1;
            }

        }

        if (quantidade > 1) {
            if (quantidade == igual) {
                quantidade = 0;
                for (DKGObjetoOuAtributo obj : lista) {
                    if (obj.isObjeto()) {
                        obj.getObjeto().setNome(sPrimeiro + " {" + quantidade + "}");
                        quantidade += 1;
                    }
                }
            }
        }

    }


    // FEATURE 22.10.10 - obter_ou_criar
    public static DKGObjetoFuturo obter_ou_criar(DKGObjeto objeto_pai, String item, String eID, String eValor) {


        DKGObjetoFuturo ret = null;
        boolean existe = false;
        for (DKGObjeto objeto_corrente : objeto_pai.getObjetos()) {
            if (objeto_corrente.identifique(eID).getValor().contentEquals(eValor)) {
                existe = true;
                ret = new DKGObjetoFuturo(objeto_corrente, DKGObjetoFuturo.EXISTENTE);
                break;
            }
        }

        if (!existe) {
            DKGObjeto objeto_novo = objeto_pai.criarObjeto(item);
            objeto_novo.identifique(eID, eValor);
            ret = new DKGObjetoFuturo(objeto_novo, DKGObjetoFuturo.NOVO);
        }

        return ret;
    }

    public static DKGObjetoFuturo obter_ou_criar(Lista<DKGObjeto> objetos, String item, String eID, String eValor) {


        DKGObjetoFuturo ret = null;
        boolean existe = false;
        for (DKGObjeto objeto_corrente : objetos) {
            if (objeto_corrente.identifique(eID).getValor().contentEquals(eValor)) {
                existe = true;
                ret = new DKGObjetoFuturo(objeto_corrente, DKGObjetoFuturo.EXISTENTE);
                break;
            }
        }

        if (!existe) {
            DKGObjeto objeto_novo = new DKGObjeto(item);
            objetos.adicionar(objeto_novo);

            objeto_novo.identifique(eID, eValor);
            ret = new DKGObjetoFuturo(objeto_novo, DKGObjetoFuturo.NOVO);
        }

        return ret;
    }


    // FEATURE 22.10.12 - ordendar_objetos_texto
    public static Lista<DKGObjeto> ordenar_objetos_texto(Lista<DKGObjeto> objetos, String atributo) {

        DKGObjeto temp;
        int n = objetos.getQuantidade();

        for (int j = 0; j < n - 1; j++) {
            for (int i = j + 1; i < n; i++) {

                String vj = objetos.get(j).identifique(atributo).getValor();
                String vi = objetos.get(i).identifique(atributo).getValor();
                if (Ordenador.ORDENAR_STRING_NAO_SENSITIVA().emOrdem(vj, vi) == Ordenavel.MAIOR) {
                    temp = objetos.get(j);
                    objetos.set(j, objetos.get(i));
                    objetos.set(i, temp);
                }

            }
        }

        return objetos;

    }

    public static Lista<DKGObjeto> ordendar_objetos_texto_reverso(Lista<DKGObjeto> objetos, final String atributo) {

        DKGObjeto temp;
        int n = objetos.getQuantidade();

        for (int j = 0; j < n - 1; j++) {
            for (int i = j + 1; i < n; i++) {

                String vj = objetos.get(j).identifique(atributo).getValor();
                String vi = objetos.get(i).identifique(atributo).getValor();
                if (Ordenador.ORDENAR_STRING_NAO_SENSITIVA().emOrdem(vj, vi) == Ordenavel.MENOR) {
                    temp = objetos.get(j);
                    objetos.set(j, objetos.get(i));
                    objetos.set(i, temp);
                }

            }
        }

        return objetos;


    }


    public static Lista<DKGObjeto> ordenar_objetos_i32(Lista<DKGObjeto> objetos, String atributo) {

        DKGObjeto temp;
        int n = objetos.getQuantidade();

        for (int j = 0; j < n - 1; j++) {
            for (int i = j + 1; i < n; i++) {

                int vj = objetos.get(j).identifique(atributo).getInteiro(0);
                int vi = objetos.get(i).identifique(atributo).getInteiro(0);

                if (vj < vi) {
                    temp = objetos.get(j);
                    objetos.set(j, objetos.get(i));
                    objetos.set(i, temp);
                }
            }
        }

        return objetos;
    }

    public static Lista<DKGObjeto> ordenar_objetos_i32_reverso(Lista<DKGObjeto> objetos, final String atributo) {
        DKGObjeto temp;
        int n = objetos.getQuantidade();

        for (int j = 0; j < n - 1; j++) {
            for (int i = j + 1; i < n; i++) {

                int vj = objetos.get(j).identifique(atributo).getInteiro(0);
                int vi = objetos.get(i).identifique(atributo).getInteiro(0);

                if (vj > vi) {
                    temp = objetos.get(j);
                    objetos.set(j, objetos.get(i));
                    objetos.set(i, temp);
                }
            }
        }

        return objetos;
    }

    public static Lista<DKGObjeto> ordenar_objetos_i64(Lista<DKGObjeto> objetos, String atributo) {

        DKGObjeto temp;
        int n = objetos.getQuantidade();

        for (int j = 0; j < n - 1; j++) {
            for (int i = j + 1; i < n; i++) {

                long vj = objetos.get(j).identifique(atributo).getLong(0);
                long vi = objetos.get(i).identifique(atributo).getLong(0);

                if (vj < vi) {
                    temp = objetos.get(j);
                    objetos.set(j, objetos.get(i));
                    objetos.set(i, temp);
                }
            }
        }

        return objetos;
    }

    public static Lista<DKGObjeto> ordendar_objetos_f32(Lista<DKGObjeto> objetos, String atributo) {

        DKGObjeto temp;
        int n = objetos.getQuantidade();

        for (int j = 0; j < n - 1; j++) {
            for (int i = j + 1; i < n; i++) {

                float vj = objetos.get(j).identifique(atributo).getFloat(0.0f);
                float vi = objetos.get(i).identifique(atributo).getFloat(0.0f);

                if (vj < vi) {
                    temp = objetos.get(j);
                    objetos.set(j, objetos.get(i));
                    objetos.set(i, temp);
                }
            }
        }

        return objetos;
    }

    public static Lista<DKGObjeto> ordendar_objetos_f64(Lista<DKGObjeto> objetos, String atributo) {

        DKGObjeto temp;
        int n = objetos.getQuantidade();

        for (int j = 0; j < n - 1; j++) {
            for (int i = j + 1; i < n; i++) {

                double vj = objetos.get(j).identifique(atributo).getDouble(0.0);
                double vi = objetos.get(i).identifique(atributo).getDouble(0.0);

                if (vj > vi) {
                    temp = objetos.get(j);
                    objetos.set(j, objetos.get(i));
                    objetos.set(i, temp);
                }
            }
        }

        return objetos;
    }

    public static Lista<DKGObjeto> ordendar_objetos_f64_reverso(Lista<DKGObjeto> objetos, final String atributo) {
        DKGObjeto temp;
        int n = objetos.getQuantidade();

        for (int j = 0; j < n - 1; j++) {
            for (int i = j + 1; i < n; i++) {

                double vj = objetos.get(j).identifique(atributo).getDouble(0.0);
                double vi = objetos.get(i).identifique(atributo).getDouble(0.0);

                if (vj < vi) {
                    temp = objetos.get(j);
                    objetos.set(j, objetos.get(i));
                    objetos.set(i, temp);
                }
            }
        }

        return objetos;
    }

    // FEATURE 22.11.13 - obter_ou_criar

    public static Opcional<DKGObjeto> getObjetoComID(Lista<DKGObjeto> objetos, String eObjeto, String eAtributo, String eValor) {

        Opcional<DKGObjeto> ret = new Opcional<DKGObjeto>();

        for (DKGObjeto proc : objetos) {
            if (proc.getNome().contentEquals(eObjeto)) {
                if (proc.identifique(eAtributo).getValor().contentEquals(eValor)) {
                    ret.set(proc);
                    break;
                }
            }
        }

        return ret;

    }

    // FEATURE 23.03.30 - filtrar, unicos e atributosDe

    public static Lista<DKGObjeto> filtrar(Lista<DKGObjeto> objetos, String atributo_nome, int atributo_inteiro) {

        Lista<DKGObjeto> filtrados = new Lista<DKGObjeto>();

        for (DKGObjeto px : objetos) {
            if (px.identifique(atributo_nome).getInteiro(0) == (atributo_inteiro)) {
                filtrados.adicionar(px);
            }
        }

        return filtrados;
    }

    public static Lista<DKGObjeto> filtrar(Lista<DKGObjeto> objetos, String atributo_nome, String atributo_valor) {
        Lista<DKGObjeto> filtrados = new Lista<DKGObjeto>();

        for (DKGObjeto objeto_corrente : objetos) {
            if (objeto_corrente.identifique(atributo_nome).isValor(atributo_valor)) {
                filtrados.adicionar(objeto_corrente);
            }
        }

        return filtrados;
    }

    public static void remover_varios(DKGObjeto ePai, Lista<DKGObjeto> objetos) {

        for (DKGObjeto px : objetos) {
            ePai.getObjetos().remover(px);
        }

    }


    public static Lista<DKGObjeto> unicos(Lista<DKGObjeto> objetos, String atributo_nome) {

        Lista<String> unicidade = new Lista<String>();
        Lista<DKGObjeto> filtrados = new Lista<DKGObjeto>();

        for (DKGObjeto aluno : objetos) {
            if (!unicidade.existe(aluno.identifique(atributo_nome).getValor())) {
                filtrados.adicionar(aluno);
                unicidade.adicionar(aluno.identifique("ID").getValor());
            }
        }

        return filtrados;
    }

    public static Lista<String> atributosDe(Lista<DKGObjeto> objetos, String atributo_nome) {

        Lista<String> unicidade = new Lista<String>();

        for (DKGObjeto aluno : objetos) {

            String turma = aluno.identifique(atributo_nome).getValor();
            if (!unicidade.existe(turma)) {
                unicidade.adicionar(turma);
            }

        }

        return unicidade;
    }

    public static Lista<String> filtrar_unicos(Lista<DKGObjeto> objetos, String att) {

        Lista<String> ret = new Lista<>();

        for (DKGObjeto item : objetos) {

            String valor = item.identifique(att).getValor();
            if (!ret.existe(valor)) {
                ret.adicionar(valor);
            }
        }


        return ret;
    }

    public static int contagem_igual(Lista<DKGObjeto> objetos, String att, String att_valor) {

        int contagem = 0;

        for (DKGObjeto item : objetos) {
            if (item.identifique(att).isValor(att_valor)) {
                contagem += 1;
            }
        }

        return contagem;
    }

    public static int contagem_inteiro_somar(Lista<DKGObjeto> objetos, String att) {

        int contagem = 0;

        for (DKGObjeto item : objetos) {
            contagem += item.identifique(att).getInteiro();
        }

        return contagem;
    }

    public static int contagem_double_maior(Lista<DKGObjeto> objetos, String att, double valor_ref) {

        int contagem = 0;

        for (DKGObjeto item : objetos) {
            if (item.identifique(att).getDouble() > valor_ref) {
                contagem += 1;
            }
        }

        return contagem;
    }

    public static int contagem_double_maior_igual(Lista<DKGObjeto> objetos, String att, double valor_ref) {

        int contagem = 0;

        for (DKGObjeto item : objetos) {
            if (item.identifique(att).getDouble() >= valor_ref) {
                contagem += 1;
            }
        }

        return contagem;
    }

    public static int contagem_double_menor(Lista<DKGObjeto> objetos, String att, double valor_ref) {

        int contagem = 0;

        for (DKGObjeto item : objetos) {
            if (item.identifique(att).getDouble() < valor_ref) {
                contagem += 1;
            }
        }

        return contagem;
    }

    public static int contagem_double_menor_igual(Lista<DKGObjeto> objetos, String att, double valor_ref) {

        int contagem = 0;

        for (DKGObjeto item : objetos) {
            if (item.identifique(att).getDouble() <= valor_ref) {
                contagem += 1;
            }
        }

        return contagem;
    }

    public static int contagem_double_igual(Lista<DKGObjeto> objetos, String att, double valor_ref) {

        int contagem = 0;

        for (DKGObjeto item : objetos) {
            if (item.identifique(att).getDouble() == valor_ref) {
                contagem += 1;
            }
        }

        return contagem;
    }

    public static int contagem_double_diferente(Lista<DKGObjeto> objetos, String att, double valor_ref) {

        int contagem = 0;

        for (DKGObjeto item : objetos) {
            if (item.identifique(att).getDouble() != valor_ref) {
                contagem += 1;
            }
        }

        return contagem;
    }


    public static String exibir_em_lista(Lista<DKGObjeto> objetos, String att) {
        String ret = "";

        for (DKGObjeto item : objetos) {
            ret += item.identifique(att).getValor() + " ";
        }

        return ret;
    }


    public static Lista<DKGObjeto> filtrar_double_maior_igual(Lista<DKGObjeto> objetos, String att, double valor_ref) {

        Lista<DKGObjeto> ret = new Lista<DKGObjeto>();

        for (DKGObjeto item : objetos) {
            if (item.identifique(att).getDouble() >= valor_ref) {
                ret.adicionar(item);
            }
        }

        return ret;
    }

    public static Lista<DKGObjeto> filtrar_double_menor(Lista<DKGObjeto> objetos, String att, double valor_ref) {

        Lista<DKGObjeto> ret = new Lista<DKGObjeto>();

        for (DKGObjeto item : objetos) {
            if (item.identifique(att).getDouble() < valor_ref) {
                ret.adicionar(item);
            }
        }

        return ret;
    }

    public static double somar_double(Lista<DKGObjeto> objetos, String att) {

        double valor = 0.0;

        for (DKGObjeto item : objetos) {
            valor += item.identifique(att).getDouble();
        }

        return valor;
    }


    public static int contagem_data_igual(Lista<DKGObjeto> objetos, String att, Data att_data) {

        int contagem = 0;

        for (DKGObjeto item : objetos) {
            if (Data.toData(item.identifique(att).getValor()).isIgual(att_data)) {
                contagem += 1;
            }
        }

        return contagem;
    }


    public static int contagem_inteiro_maior_igual(Lista<DKGObjeto> objetos, String att, int valor_ref) {

        int contagem = 0;

        for (DKGObjeto item : objetos) {
            if (item.identifique(att).getInteiro() >= valor_ref) {
                contagem += 1;
            }
        }

        return contagem;
    }

    public static int contagem_inteiro_menor(Lista<DKGObjeto> objetos, String att, int valor_ref) {

        int contagem = 0;

        for (DKGObjeto item : objetos) {
            if (item.identifique(att).getInteiro() < valor_ref) {
                contagem += 1;
            }
        }

        return contagem;
    }


    public static Lista<DKGObjeto> clonagem(Lista<DKGObjeto> objetos) {

        Lista<DKGObjeto> copia = new Lista<DKGObjeto>();

        for (DKGObjeto item : objetos) {
            copia.adicionar(item);
        }

        return copia;
    }


    public static Lista<String> filtrar_double_3_maiores_unicos(Lista<DKGObjeto> objetos, String att_nome) {

        Lista<DKGObjeto> copia = clonagem(objetos);
        ordendar_objetos_f64_reverso(copia, att_nome);

        Lista<String> unicos = new Lista<>();

        for (DKGObjeto item : copia) {

            String valor = fmt.doubleNumC2(item.identifique(att_nome).getDouble());
            if (!unicos.existe(String.valueOf(valor))) {
                unicos.adicionar(String.valueOf(valor));
            }

            if (unicos.getQuantidade() >= 3) {
                break;
            }
        }

        return unicos;
    }


    public static Lista<String> filtrar_double_3_menores_unicos(Lista<DKGObjeto> objetos, String att_nome) {

        Lista<DKGObjeto> copia = clonagem(objetos);
        ordendar_objetos_f64(copia, att_nome);

        Lista<String> unicos = new Lista<>();

        for (DKGObjeto item : copia) {

            String valor = fmt.doubleNumC2(item.identifique(att_nome).getDouble());
            if (!unicos.existe(String.valueOf(valor))) {
                unicos.adicionar(String.valueOf(valor));
            }

            if (unicos.getQuantidade() >= 3) {
                break;
            }
        }

        return unicos;
    }

    public static DKGObjeto objeto_unico(Lista<DKGObjeto> objetos, String nome, String atributo, String valor) {
        boolean existe = false;
        DKGObjeto ret = null;

        for (DKGObjeto item : objetos) {
            if (item.identifique(atributo).isValor(valor)) {
                existe = true;
                ret = item;
                break;
            }
        }

        if (!existe) {
            ret = new DKGObjeto(nome);
            ret.identifique(atributo, valor);
            objetos.adicionar(ret);
        }

        return ret;
    }



    public static void EXIBIR_TABELA(Lista<DKGObjeto> objetos){

        Lista<DKGObjeto> colunas = new Lista<DKGObjeto>();

        for (DKGObjeto obj : objetos) {

            for (DKGAtributo att : obj.getAtributos()) {
                int tamanho = att.getValor().length();
                DKGObjetoFuturo obj_coluna = DKGFeatures.obter_ou_criar(colunas, "Coluna", "Nome", att.getNome());

                if (obj_coluna.getObjeto().identifique("Tamanho").getInteiro(0) < tamanho) {
                    obj_coluna.getObjeto().identifique("Tamanho").setInteiro(tamanho);
                }

                if (obj_coluna.getObjeto().identifique("Tamanho").getInteiro(0) < att.getNome().length()) {
                    obj_coluna.getObjeto().identifique("Tamanho").setInteiro(att.getNome().length());
                }

            }
        }


        int tracos = 0;
        for (DKGObjeto obj : colunas) {
            int tt = obj.identifique("Tamanho").getInteiro(0) + 5 + 2;
            tracos += tt;
        }
        tracos += 2;

        fmt.print(fmt.repetir("-", tracos));

        String cabecalho = "";
        for (DKGObjeto obj : colunas) {
            int tt = obj.identifique("Tamanho").getInteiro(0) + 5;
            cabecalho += " |" + fmt.espacar_antes(obj.identifique("Nome").getValor(), tt);
        }

        fmt.print("{}", cabecalho);
        fmt.print(fmt.repetir("-", tracos));

        for (DKGObjeto obj : objetos) {

            String linha = "";

            for (DKGObjeto coluna : colunas) {
                int tt = coluna.identifique("Tamanho").getInteiro(0) + 5;
                linha += " |" + fmt.espacar_antes(obj.identifique(coluna.identifique("Nome").getValor()).getValor(), tt);
            }

            fmt.print("{}", linha + " |");

        }
        fmt.print(fmt.repetir("-", tracos));


    }
}

