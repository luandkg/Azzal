package libs.meta_functional;

public abstract class Acao {

    public abstract void fazer();


    public static Acao ACAO_VAZIA(){

      return new Acao() {
            @Override
            public void fazer() {


            }
        };
    }
}