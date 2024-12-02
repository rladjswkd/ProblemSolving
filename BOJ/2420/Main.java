import java.io.IOException;

class Main {
    private static int read() throws IOException {
        int n = 0, c, s = System.in.read();

        if (s != 45)
            n = s & 15;
        while (48 <= (c = System.in.read()) && c <= 57)
            n = (n << 3) + (n << 1) + (c & 15);
        return s != 45 ? n : ~n + 1;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(Math.abs((long)read() - (long)read()));
    }
}