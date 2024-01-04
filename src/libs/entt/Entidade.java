package libs.entt;

import libs.luan.Lista;

public class Entidade {

    private final Lista<Tag> mTags;

    public Entidade() {
        mTags = new Lista<Tag>();
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

    public int atInt(String eNome ) {
        Tag tag = proc_at(eNome);
        return tag.asInt();
    }


    public int atInt(String eNome, int eValor) {
        Tag tag = proc_at(eNome);
        tag.setInteiro(eValor);
        return tag.asInt();
    }

    public long atLong(String eNome ) {
        Tag tag = proc_at(eNome);
        return tag.asLong();
    }
    public long atLong(String eNome, long eValor) {
        Tag tag = proc_at(eNome);
        tag.setLong(eValor);
        return tag.asLong();
    }

    public double atDouble(String eNome ) {
        Tag tag = proc_at(eNome);
        return tag.asDouble();
    }

    public double atDouble(String eNome, double eValor) {
        Tag tag = proc_at(eNome);
        tag.setDouble(eValor);
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


    public int somar(String a1,String a2){
        return atInt(a1)+atInt(a2);
    }



}
