package apps.app_attuz.Sociedade;

import libs.luan.Aleatorio;
import libs.luan.Lista;
import libs.luan.Strings;
import libs.luan.Unico;


public class NomeadorBurgo {

    public static String getSimples() {

        String primeiro_nome = "";

        Lista<String> iniciais = new Lista<String>();
        Lista<String> silabas = new Lista<String>();
        Lista<String> terminais = new Lista<String>();

        iniciais.adicionar("ala");
        iniciais.adicionar("edo");
        iniciais.adicionar("uko");
        iniciais.adicionar("exa");
        iniciais.adicionar("ipo");
        iniciais.adicionar("oma");

        iniciais.adicionar("uppa");
        iniciais.adicionar("azo");
        iniciais.adicionar("evta");
        iniciais.adicionar("onko");

        silabas.adicionar("ta");
        silabas.adicionar("de");
        silabas.adicionar("co");
        silabas.adicionar("re");
        silabas.adicionar("mu");
        silabas.adicionar("zu");
        silabas.adicionar("za");
        silabas.adicionar("we");
        silabas.adicionar("ti");
        silabas.adicionar("quo");
        silabas.adicionar("qua");

        silabas.adicionar("maz");
        silabas.adicionar("fez");
        silabas.adicionar("tra");
        silabas.adicionar("cro");

        silabas.adicionar("pla");
        silabas.adicionar("bra");
        silabas.adicionar("bro");
        silabas.adicionar("dru");
        silabas.adicionar("kre");
        silabas.adicionar("bri");

        terminais.adicionar("pas");
        terminais.adicionar("lom");
        terminais.adicionar("rem");
        terminais.adicionar("sis");
        terminais.adicionar("nov");
        terminais.adicionar("tel");


        int quantidade_de_silabas = 2 + Aleatorio.aleatorio(2);
        for (int s = 0; s < quantidade_de_silabas; s++) {
            if (s == (quantidade_de_silabas - 1)) {
                primeiro_nome += silabas.get(Aleatorio.aleatorio(silabas.getQuantidade()));
            } else {
                if(s==0){
                    if(Aleatorio.aleatorio(100)>70){
                        primeiro_nome += iniciais.get(Aleatorio.aleatorio(iniciais.getQuantidade()));
                    }else{
                        primeiro_nome += terminais.get(Aleatorio.aleatorio(terminais.getQuantidade()));
                    }
                }else{
                    primeiro_nome += terminais.get(Aleatorio.aleatorio(terminais.getQuantidade()));
                }
            }
        }

        return primeiro_nome;
    }


    public static String getSimplesUnico(Lista<String> unicos){

        String simples = Strings.CAPTALIZAR(getSimples());
        while (unicos.existe(Strings.IGUALAVEL(), simples)) {
            //  fmt.print("Gerar outro :: {}", nome);
            simples = Strings.CAPTALIZAR(getSimples());
        }

        return simples;
    }

}
