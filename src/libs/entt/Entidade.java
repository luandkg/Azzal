package libs.entt;

import libs.luan.Lista;

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
                if (tag_valor==valor) {
                    ret = true;
                }
                break;
            }
        }

        return ret;
    }

}
