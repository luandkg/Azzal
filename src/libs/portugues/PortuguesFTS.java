package libs.portugues;

import libs.luan.Lista;

public class PortuguesFTS {

    private Lista<String> mArtigos;
    private Lista<String> mPreposicoes;
    private Lista<String> mPronomes;
    private Lista<String> mPronomesPossessivos;

    public PortuguesFTS(){

        mArtigos = Lista.CRIAR("O","A","OS","AS","UM","UNS","UMA","UMAS");
        mPreposicoes = Lista.CRIAR("DA","DE","DO","DAS","DOS","PARA","POR","PELO","PELOS","E","OU","QUE","COM","EM","NA","NO","NAS","NOS","A","À","AO","AS","AOS");
        mPronomes = Lista.CRIAR("EU","TÚ","VOCÊ","ELE","ELA","NÓS","VÓS","VOCÊS","ELES","ELAS");
        mPronomesPossessivos = Lista.CRIAR("MEU","MINHA","SEU","SUA","TEU","TUA","DELE","DELA","MEUS","MINHAS","SEUS","SUAS","TUAS","DELES","DELAS","ME","SE","TE","NOS");

    }

    public Lista<String> getArtigos(){
        return mArtigos;
    }

    public Lista<String> getPreposicoes(){
        return mPreposicoes;
    }

    public Lista<String> getPronomes(){
        return mPronomes;
    }

    public Lista<String> getPronomesPossessivos(){
        return mPronomesPossessivos;
    }
}
