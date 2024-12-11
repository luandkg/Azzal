package projetos.aoc2024;

import libs.luan.fmt;

public abstract class AOC_2024_DAY {


    private int mProblemaNumero;
    private int mProblemaParte;
    private String mProblemaNome;

    public AOC_2024_DAY(int eProblemaNumero, String eNome) {
        mProblemaNumero = eProblemaNumero;
        mProblemaNome = eNome;
        mProblemaParte=0;
    }

    public int getProblemaNumero() {
        return mProblemaNumero;
    }

    public int getParte(){
        return mProblemaParte;
    }

    public void setParte(int eParte){
        mProblemaParte=eParte;
    }

    public String getProblemaNome() {
        return mProblemaNome;
    }

    public abstract void parte_1();

    public abstract void parte_2();


    public String getArquivoDica() {
        return AOC_2024.GET_ARQUIVO("TIP_" + fmt.zerado(mProblemaNumero, 2) + ".txt");
    }

    public String getArquivoEntrada() {
        return AOC_2024.GET_ARQUIVO("DAY_" + fmt.zerado(mProblemaNumero, 2) + ".txt");
    }



    public void info(int parte, String mensagem) {
        AOC_2024.INFO(getProblemaNumero(), parte, mensagem);
    }

    public void info(int parte, int mensagem) {
        AOC_2024.INFO(getProblemaNumero(), parte, String.valueOf(mensagem));
    }

    public void info(int parte, long mensagem) {
        AOC_2024.INFO(getProblemaNumero(), parte, String.valueOf(mensagem));
    }
}
