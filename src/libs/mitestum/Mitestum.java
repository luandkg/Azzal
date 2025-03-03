package libs.mitestum;

import libs.luan.Lista;
import libs.luan.Portugues;
import libs.luan.fmt;

public class Mitestum {

    private String mNome;

    private Lista<MitestumTeste> mTestes;

    public Mitestum(String eNome){
        mNome=eNome;
        mTestes=new Lista<MitestumTeste>();
    }

    public void adicionar_igualdade(String direita,String esquerda){
        mTestes.adicionar(new MitestumTexto(direita,esquerda));
    }

    public void adicionar_igualdade(int direita,int esquerda){
        mTestes.adicionar(new MitestumInteiro(MitestumTipo.TESTE_IGUALDADE,direita,esquerda));
    }

    public void adicionar_maior_igual(int direita,int esquerda){
        mTestes.adicionar(new MitestumInteiro(MitestumTipo.TESTE_MAIOR_IGUAL,direita,esquerda));
    }

    public void validar(){

        fmt.print("------------ "+mNome +" ---------------");

        int sequencia = 1;
        int total = 0;
        int sucesso = 0;

        for(MitestumTeste teste :mTestes ){
            boolean resultado = teste.isOK();

            if(resultado){
                sucesso+=1;
            }

            fmt.print("\t ++ Teste {} ->> {}", sequencia,Portugues.VALIDAR(resultado,"OK","FALHOU"));
            sequencia+=1;
            total+=1;
        }

        fmt.print("");
        fmt.print("\t >> Testes  : {}",total);
        fmt.print("");
        fmt.print("\t >> Sucesso : {}",sucesso);
        fmt.print("\t >> Falhou  : {}",(total-sucesso));
        fmt.print("----------------------------------");

    }

    public void validar_descritivo(){

        fmt.print("------------ "+mNome +" --------------------------------------------------------------------");

        int sequencia = 1;
        int total = 0;
        int sucesso = 0;

        for(MitestumTeste teste :mTestes ){
            boolean resultado = teste.isOK();

            if(resultado){
                sucesso+=1;
            }

            fmt.print("\t ++ Teste {esq5} :: {dir15} - ({} | {}) ->> {}", sequencia,QUAL_TIPO(teste.getTipo()),fmt.espacar_depois(teste.getEsquerdaTexto(),15),fmt.espacar_antes(teste.getDireitaTexto(),15),Portugues.VALIDAR(resultado,"OK","FALHOU"));
            sequencia+=1;
            total+=1;
        }

        fmt.print("");
        fmt.print("\t >> Testes  : {}",total);
        fmt.print("");
        fmt.print("\t >> Sucesso : {}",sucesso);
        fmt.print("\t >> Falhou  : {}",(total-sucesso));
        fmt.print("-----------------------------------------------------------------------------------------");

    }


    public static String QUAL_TIPO(MitestumTipo eTipo){
        String ret = "";

        if(eTipo==MitestumTipo.TESTE_IGUALDADE) {
            ret = "IGUAL";
        }else     if(eTipo==MitestumTipo.TESTE_MAIOR_IGUAL){
            ret="MAIOR || IGUAL";
        }

        return ret;
    }
}
