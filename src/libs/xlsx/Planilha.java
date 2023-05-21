package libs.xlsx;

import java.util.ArrayList;

public class Planilha {

    private String mTitulo;
    private ArrayList<PlanilhaLinha> mLinhas;

    public Planilha(){
        mTitulo="";
        mLinhas=new ArrayList<PlanilhaLinha>();
    }

    public String getTitulo(){return mTitulo;}
    public void setTitulo(String t){mTitulo=t;}



    public void adicionar(PlanilhaLinha linha){mLinhas.add(linha);}

    public ArrayList<PlanilhaLinha> getLinhas(){return mLinhas;}


    public int maxLinhas(){return mLinhas.size();}

    public int maxColunas(){
        int c = 0;
        for(PlanilhaLinha l : mLinhas){
            if(l.maxColunas()>c){
                c=l.maxColunas();
            }
        }
        return c;}

}
