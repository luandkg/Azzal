package projetos.aoc2024;

import libs.luan.fmt;

public abstract  class AOC_2024_DAY {


    private int mProblemaNumero;
    private String mProblemaNome;

    public AOC_2024_DAY(  int eProblemaNumero,String eNome){
        mProblemaNumero=eProblemaNumero;
        mProblemaNome=eNome;
    }

    public int getProblemaNumero(){
        return mProblemaNumero;
    }

    public String getProblemaNome(){
        return mProblemaNome;
    }

    public abstract void parte_1();
    public abstract void parte_2();


    public String getArquivoDica(){
        return AOC_2024.GET_ARQUIVO("TIP_"+ fmt.zerado(mProblemaNumero,2)+".txt");
    }

    public String getArquivoEntrada(){
        return AOC_2024.GET_ARQUIVO("DAY_"+ fmt.zerado(mProblemaNumero,2)+".txt");
    }

}
