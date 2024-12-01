package projetos.aoc2024;

public abstract  class AOC_2024_DAY {


    private int mProblemaNumero;

    public AOC_2024_DAY(  int eProblemaNumero){
        mProblemaNumero=eProblemaNumero;
    }

    public int getProblemaNumero(){
        return mProblemaNumero;
    }

    public abstract void parte_1();
    public abstract void parte_2();

}
