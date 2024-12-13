package libs.entt;

import libs.luan.Lista;
import libs.luan.Strings;

public class Entidade {

    private final Lista<Tag> mTags;
    private final Lista<Entidade> mEntidades;

    public Entidade() {
        mTags = new Lista<Tag>();
        mEntidades = new Lista<Entidade>();
    }

    public Tag proc_at(String eNome) {
        boolean enc = false;
        Tag eTag = null;

        for (Tag tag : mTags) {
            if (tag.is_nome(eNome)) {
                eTag = tag;
                enc = true;
                break;
            }
        }

        if (!enc) {
            eTag = new Tag(eNome);
            mTags.adicionar(eTag);
        }

        return eTag;
    }

    public String at(String eNome) {
        Tag tag = proc_at(eNome);
        return tag.getValor();
    }

    public String at(String eNome, String eValor) {
        Tag tag = proc_at(eNome);
        tag.setValor(eValor);
        return tag.getValor();
    }

    public String at(String eNome, int eValor) {
        Tag tag = proc_at(eNome);
        tag.setInteiro(eValor);
        return tag.getValor();
    }

    public String at(String eNome, long eValor) {
        Tag tag = proc_at(eNome);
        tag.setLong(eValor);
        return tag.getValor();
    }

    public String at(String eNome, double eValor) {
        Tag tag = proc_at(eNome);
        tag.setDouble(eValor);
        return tag.getValor();
    }

    public int atInt(String eNome) {
        Tag tag = proc_at(eNome);
        return tag.asInt();
    }


    public int atInt(String eNome, int eValor) {
        Tag tag = proc_at(eNome);
        tag.setInteiro(eValor);
        return tag.asInt();
    }

    public int atIntOuPadrao(String eNome, int ePadrao) {
        Tag tag = proc_at(eNome);
        if (tag.getValor().length() > 0) {
            return tag.asInt();
        } else {
            tag.setInteiro(ePadrao);
        }
        return tag.asInt();
    }

    public long atLong(String eNome) {
        Tag tag = proc_at(eNome);
        return tag.asLong();
    }

    public long atLong(String eNome, long eValor) {
        Tag tag = proc_at(eNome);
        tag.setLong(eValor);
        return tag.asLong();
    }

    public double atDouble(String eNome) {
        Tag tag = proc_at(eNome);
        return tag.asDouble();
    }

    public double atDouble(String eNome, double eValor) {
        Tag tag = proc_at(eNome);
        tag.setDouble(eValor);
        return tag.asDouble();
    }

    public double atDoubleOuPadrao(String eNome, double ePadrao) {
        Tag tag = proc_at(eNome);
        if (tag.getValor().length() > 0) {
            return tag.asDouble();
        } else {
            tag.setDouble(ePadrao);
        }
        return tag.asDouble();
    }


    public boolean existe(String eNome) {
        boolean enc = false;

        for (Tag tag : mTags) {
            if (tag.is_nome(eNome)) {
                enc = true;
                break;
            }
        }

        return enc;
    }


    public void at_remover(String eNome) {

        for (Tag tag : mTags) {
            if (tag.is_nome(eNome)) {
                mTags.remover(tag);
                break;
            }
        }

    }


    public Lista<Tag> tags() {
        return mTags;
    }


    // FUNCOES ESPECIAIS


    public int somar(String a1, String a2) {
        return atInt(a1) + atInt(a2);
    }

    public Lista<Entidade> getEntidades() {
        return mEntidades;
    }


    public void atSeMenor(String att_nome, int att_valor) {
        int valorcorrente = atInt(att_nome);
        if (att_valor < valorcorrente) {
            at(att_nome, att_valor);
        }
    }

    public void atSeMaior(String att_nome, int att_valor) {
        int valorcorrente = atInt(att_nome);
        if (att_valor > valorcorrente) {
            at(att_nome, att_valor);
        }
    }


    public void atSeMenor(String att_nome, double att_valor) {
        double valorcorrente = atDouble(att_nome);
        if (att_valor < valorcorrente) {
            at(att_nome, att_valor);
        }
    }

    public void atSeMaior(String att_nome, double att_valor) {
        double valorcorrente = atDouble(att_nome);
        if (att_valor > valorcorrente) {
            at(att_nome, att_valor);
        }
    }


    public boolean is(String nome, String valor) {

        boolean ret = false;

        for (Tag tag : mTags) {
            if (tag.is_nome(nome)) {
                if (tag.getValor().contentEquals(valor)) {
                    ret = true;
                }
                break;
            }
        }

        return ret;
    }

    public boolean is(String nome, int valor) {

        boolean ret = false;

        for (Tag tag : mTags) {
            if (tag.is_nome(nome)) {
                int tag_valor = Integer.parseInt(tag.getValor());
                if (tag_valor == valor) {
                    ret = true;
                }
                break;
            }
        }

        return ret;
    }

    public boolean is(String nome, long valor) {

        boolean ret = false;

        for (Tag tag : mTags) {
            if (tag.is_nome(nome)) {
                long tag_valor = Long.parseLong(tag.getValor());
                if (tag_valor == valor) {
                    ret = true;
                }
                break;
            }
        }

        return ret;
    }


    public boolean isInteiro(String att_nome) {
        String att_valor = at(att_nome);
        if (!att_valor.isEmpty()) {
            return Strings.isNumero(att_valor);
        }
        return false;
    }


    public String toTexto() {

        String texto = "";
        for (Tag tag : mTags) {
            texto += tag.getNome() + " = " + tag.getValor() + "\n";
        }

        return texto;
    }

    public boolean isVazio(String att_nome) {
        boolean valido = true;
        for (Tag tag : mTags) {
            if (tag.is_nome(att_nome)) {
                valido = tag.getValor().isEmpty();
                break;
            }
        }
        return valido;
    }


    public boolean isDiferente(String att_nome, String att_valor) {
        return !at(att_nome).contentEquals(att_valor);
    }


    public void atConglomoreUnico(String att_nome, String att_valor) {

        boolean existe = false;

        for (String massa : Strings.DIVIDIR_POR(this.at(att_nome), "|")) {
            massa = massa.replace("|", "").replace(" ", "").trim();
            if (!massa.isEmpty()) {
                if (massa.contentEquals(att_valor)) {
                    existe = true;
                    break;
                }
            }
        }

        if (!existe) {
            if (this.at(att_nome).isEmpty()) {
                this.at(att_nome, " " + att_valor);
            } else {
                this.at(att_nome, this.at(att_nome) + " | " + att_valor);
            }
        }

    }


    public boolean atributo_existe(String nome) {
        boolean existe = false;

        for (Tag a : mTags) {
            if (a.getNome().contentEquals(nome)) {
                existe = true;
                break;
            }
        }

        return existe;
    }

    public boolean isValido(String nome) {

        boolean valido = false;

        for (Tag a : mTags) {
            if (a.is_nome(nome)) {
                if (!a.getNome().isEmpty()) {
                    valido = true;
                }
                break;
            }

        }

        return valido;
    }


    public void tornar_primeiro(String att_nome) {

        Lista<Tag> copia = new Lista<Tag>();

        for (Tag tag : mTags) {
            copia.adicionar(tag);
        }

        mTags.limpar();

        at(att_nome, at(att_nome));

        for (Tag tag : copia) {
            at(tag.getNome(), tag.getValor());
        }

    }


    public void tornar_ultimo(String att_nome) {

        Lista<Tag> copia = new Lista<Tag>();

        String att_valor = at(att_nome);

        for (Tag tag : mTags) {
            copia.adicionar(tag);
        }

        mTags.limpar();


        for (Tag tag : copia) {
            if (!tag.is_nome(att_nome)) {
                at(tag.getNome(), tag.getValor());
            }
        }

        at(att_nome, att_valor);

    }


    public void atributo_depois(String att_antes, String att_novo) {

        Lista<Tag> copia = new Lista<Tag>();

        for (Tag tag : mTags) {
            copia.adicionar(tag);
        }

        mTags.limpar();

        for (Tag tag : copia) {
            at(tag.getNome(), tag.getValor());
            if (tag.is_nome(att_antes)) {
                at(att_novo, "");
            }
        }

    }

    public void trocar_valores(String att_alfa, String att_beta) {
        String s_alfa = at(att_alfa);
        String s_beta = at(att_beta);

        at(att_alfa, s_beta);
        at(att_beta, s_alfa);

    }

    public void at_renomear(String att_alfa, String att_beta) {

        for (Tag tag : tags()) {
            if (tag.is_nome(att_alfa)) {
                tag.setNome(att_beta);
                break;
            }
        }

    }


    public Entidade getCopia(){
        Entidade copia = new Entidade();

        for(Tag tag : mTags){
            copia.at(tag.getNome(),tag.getValor());
        }

        for(Entidade e : mEntidades){
            copia.getEntidades().adicionar(e.getCopia());
        }

        return copia;
    }


}
