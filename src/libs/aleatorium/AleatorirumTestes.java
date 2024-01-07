package libs.aleatorium;

public class AleatorirumTestes {

    public void testar() {

        Aleatorium a = new Aleatorium();
        a.mostrar();

        int v0 = 0;
        int v1 = 0;
        int v2 = 0;
        int v3 = 0;
        int v4 = 0;
        int v5 = 0;
        int v6 = 0;
        int v7 = 0;
        int v8 = 0;
        int v9 = 0;

        for (int s = 0; s < 30; s++) {

            int sorteado = a.get();

            System.out.println(" -->> Sortear :: " + sorteado);

            if (sorteado == 0) {
                v0 += 1;
            }
            if (sorteado == 1) {
                v1 += 1;
            }
            if (sorteado == 2) {
                v2 += 1;
            }
            if (sorteado == 3) {
                v3 += 1;
            }
            if (sorteado == 4) {
                v4 += 1;
            }
            if (sorteado == 5) {
                v5 += 1;
            }
            if (sorteado == 6) {
                v6 += 1;
            }
            if (sorteado == 7) {
                v7 += 1;
            }
            if (sorteado == 8) {
                v8 += 1;
            }
            if (sorteado == 9) {
                v9 += 1;
            }

        }

        System.out.println(" :: 0 -->> " + v0);
        System.out.println(" :: 1 -->> " + v1);
        System.out.println(" :: 2 -->> " + v2);
        System.out.println(" :: 3 -->> " + v3);
        System.out.println(" :: 4 -->> " + v4);
        System.out.println(" :: 5 -->> " + v5);
        System.out.println(" :: 6 -->> " + v6);
        System.out.println(" :: 7 -->> " + v7);
        System.out.println(" :: 8 -->> " + v8);
        System.out.println(" :: 9 -->> " + v9);

    }
}
