package algoritmos;

import libs.luan.Portugues;
import libs.luan.Strings;
import libs.luan.fmt;
import libs.tronarko.Tozte;
import libs.tronarko.Tronarko;
import libs.tronarko.Tronarkofmt;

public class Ferias {

    public static void init(String evento_nome,String d1, String d2) {

        Tozte iniciar = Tronarko.getData(d1);
        Tozte terminar = Tronarko.getData(d2);

        Tozte hoje = Tronarko.getTozte();
        long quantidade =  Tronarko.SUPERARKOS_ENTRE_COM_FIM( iniciar, terminar);

        String status = "---";

        if (hoje.isMenorQue(iniciar)) {
            status = "NÃO COMEÇOU";
        } else if (hoje.isMaiorQue(terminar)) {
            status = "ACABOU";
        } else {
            long passou =  Tronarko.SUPERARKOS_ENTRE_COM_FIM( iniciar, hoje);

            status = "CURTINDO - " + passou + " de " + quantidade;
        }

        fmt.print(" - Evento    : " + evento_nome);
        fmt.print(" - Hoje      : " + hoje.getTextoZerado());

        fmt.print(" - Inicio    : " + iniciar.getTextoZerado());
        fmt.print(" - Fim       : " + terminar.getTextoZerado());
        fmt.print(" - Duração   : " + quantidade+ " "+ Portugues.singular_ou_plural(quantidade,"Superarko","Superarkos"));
        fmt.print(" - Status    : " + status);

        Strings.println(Tronarkofmt.EXIBIR_HIPERARKO_DO_TRONARKO_E_MARQUE_TOZTE_FRASES(hoje,9,7003));

    }

}
