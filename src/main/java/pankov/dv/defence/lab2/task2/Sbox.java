package pankov.dv.defence.lab2.task2;

public class Sbox {

    // data structure to store sbox
    private static int sbox[][] = new int[16][16];
    private static int inverseSbox[][] = new int[16][16];

    static {

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                int entry = Sbox.compute(i, j);
                Sbox.sbox[i][j] = entry;
                // calculate inverse entry
                int invi = (entry & (15 << 4)) >> 4;
                int invj = entry & (15);
                Sbox.inverseSbox[invi][invj] = (i << 4) | j;
            }
        }
    }

    // computes sbox entry and inverse sbox entry
    private static int compute(int i, int j) {
        int a = (i << 4) | j;
        int c = 0x63;
        int inverse;
        if (i == 0 && j == 0)
            inverse = 0;
        else inverse = Polynomial.inverse(new Polynomial(a)).get();
        int b[] = new int[8];
        for (int k = 0; k < 8; k++) {
            b[k] = Math.min(1, inverse & (1 << k));
        }
        int bprime[] = new int[8];
        for (int k = 0; k < 8; k++) {
            bprime[k] = 0;
            bprime[k] = b[k] ^ b[(k + 4) % 8] ^ b[(k + 5) % 8] ^ b[(k + 6) % 8] ^ b[(k + 7) % 8];
            bprime[k] = bprime[k] ^ (Math.min(1, c & (1 << k)));
        }
        int aprime = 0;
        for (int k = 0; k < 8; k++) {
            aprime = aprime | (bprime[k] << k);
        }
        return aprime;
    }

    // returns entry at i,j of Sbox
    public static int getSbox(int i, int j) {
        return Sbox.sbox[i][j];
    }

    // returns entry at i,j of Inverse Sbox
    public static int getInvSbox(int i, int j) {
        return Sbox.inverseSbox[i][j];
    }

}
