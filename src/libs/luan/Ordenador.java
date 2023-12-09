package libs.luan;


public class Ordenador {

    public static <T> void ordenar_vetor_crescente(Vetor<T> vetor, Ordenavel<T> algoritmo_de_ordenacao) {

        int n = vetor.getCapacidade();
        T temp = null;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                int ordem = algoritmo_de_ordenacao.emOrdem(vetor.get(j - 1), vetor.get(j));

                if (ordem == Ordenavel.MAIOR) {
                    temp = vetor.get(j - 1);
                    vetor.set(j - 1, vetor.get(j));
                    vetor.set(j, temp);
                }

            }
        }

    }

    public static <T> void ordenar_vetor_decrescente(Vetor<T> vetor, Ordenavel<T> algoritmo_de_ordenacao) {

        int n = vetor.getCapacidade();
        T temp = null;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                int ordem = algoritmo_de_ordenacao.emOrdem(vetor.get(j - 1), vetor.get(j));

                if (ordem == Ordenavel.MENOR) {
                    temp = vetor.get(j - 1);
                    vetor.set(j - 1, vetor.get(j));
                    vetor.set(j, temp);
                }

            }
        }

    }


    public static <T> void ordenar_lista_crescente(Lista<T> lista, Ordenavel<T> algoritmo_de_ordenacao) {

        int n = lista.getQuantidade();
        T temp = null;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                int ordem = algoritmo_de_ordenacao.emOrdem(lista.get(j - 1), lista.get(j));

                if (ordem == Ordenavel.MAIOR) {
                    temp = lista.get(j - 1);
                    lista.set(j - 1, lista.get(j));
                    lista.set(j, temp);
                }

            }
        }

    }

    public static <T> void ordenar_lista_decrescente(Lista<T> lista, Ordenavel<T> algoritmo_de_ordenacao) {

        int n = lista.getQuantidade();
        T temp = null;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                int ordem = algoritmo_de_ordenacao.emOrdem(lista.get(j - 1), lista.get(j));

                if (ordem == Ordenavel.MENOR) {
                    temp = lista.get(j - 1);
                    lista.set(j - 1, lista.get(j));
                    lista.set(j, temp);
                }

            }
        }

    }


    public static Ordenavel<String> ORDENAR_STRING_NAO_SENSITIVA() {
        return new Ordenavel<String>() {
            @Override
            public int emOrdem(String a, String b) {

                a = a.toLowerCase();
                b = b.toLowerCase();

                int resp = Ordenavel.IGUAL;

                String alfabeto_numerico = "0123456789_abcdefghijklmnopqrstuvwxyz";

                int comparar_ate = a.length();

                if (a.length() != b.length()) {
                    comparar_ate = Matematica.menor(a.length(), b.length());
                }

                int i = 0;
                boolean continuar = true;

                while (i < comparar_ate && continuar) {
                    int chave_a = Strings.indice(alfabeto_numerico, String.valueOf(a.charAt(i)));
                    int chave_b = Strings.indice(alfabeto_numerico, String.valueOf(b.charAt(i)));

                    if (chave_a < chave_b) {
                        continuar = false;
                        resp = Ordenavel.MENOR;
                    } else if (chave_a > chave_b) {
                        continuar = false;
                        resp = Ordenavel.MAIOR;
                    }

                    i += 1;
                }

                return resp;
            }
        };

    }


    public static Ordenavel<Integer> ORDENAR_INTEIRO() {
        return new Ordenavel<Integer>() {
            @Override
            public int emOrdem(Integer a, Integer b) {


                int resp = Ordenavel.IGUAL;

                if (a > b) {
                    resp = Ordenavel.MAIOR;
                } else if (a < b) {
                    resp = Ordenavel.MENOR;
                }


                return resp;
            }
        };

    }

}
