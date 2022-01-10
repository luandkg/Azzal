package Luan;

public class TTY {

    public String inFixo(String s, int t) {

        while (s.length() < t) {
            s = " " + s;
        }

        return s;
    }

    public String intNum(int i, int c) {
        String s = String.valueOf(i);
        while (s.length() < c) {
            s = "0" + s;
        }
        return s;
    }
    public String LongNum(long i, int c) {
        String s = String.valueOf(i);
        while (s.length() < c) {
            s = "0" + s;
        }
        return s;
    }




    public String getTempoFormatado(long t) {

        if (t < 1000) {
            return t + " ms";
        } else {

            int s = 0;
            while (t >= 1000) {
                t -= 1000;
                s += 1;
            }

            if (s >= 60) {
                int min = 0;
                while (s >= 60) {
                    s -= 60;
                    min += 1;
                }

                return min + " min " + s + " s";

            } else {
                return s + " s";
            }

        }

    }

    public long getTempoTarefa(Tarefa eTarefa) {
        long eAntes = System.currentTimeMillis();

        eTarefa.executar();

        long eDepois = System.currentTimeMillis();
        return eDepois - eAntes;
    }

    public String getTempoFormatadoTarefa(Tarefa eTarefa) {
        long eAntes = System.currentTimeMillis();

        eTarefa.executar();

        long eDepois = System.currentTimeMillis();
        return getTempoFormatado(eDepois - eAntes);
    }

}
