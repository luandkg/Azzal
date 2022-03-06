package AppAttuz.Assessorios;

public class Escalometro {

    public void exemplo(){

        escalar(0, 100, 50);
        escalar(0, 300, 150);

        double o = emOutra(0, 100, 0, 300, 50);
        System.out.println("Outra   :: " + o);


    }

    public double emOutra(int min_a, int max_a, int min_b, int max_b, int val_a) {

        int delta_a = max_a - min_a;
        double taxa_a = (double) delta_a / 100.0;
        double prop_a = ((double) (val_a - min_a) / (double) delta_a) * 100.0;

        int delta_b = max_b - min_b;
        double taxa_b = (double) delta_b / 100.0;

        double o = prop_a * taxa_b;

        return o;
    }

    public void escalar(int min, int max, int val) {

        System.out.println("Min   :: " + min);
        System.out.println("Max   :: " + max);

        int delta = max - min;
        System.out.println("Delta :: " + delta);

        System.out.println("Valor :: " + val);

        double taxa = (double) delta / 100.0;

        double prop = ((double) (val - min) / (double) delta) * 100.0;

        System.out.println("Taxa  :: " + taxa);
        System.out.println("Prop  :: " + prop);

    }


}
