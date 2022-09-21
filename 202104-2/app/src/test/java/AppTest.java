import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class AppTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private final InputStream originalIn = System.in;

    @Before
    public void setUpStreams() {
        outContent.reset();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(new PrintStream(originalOut));
        System.setErr(new PrintStream(originalErr));
        System.setIn(originalIn);
    }

    @Test
    public void taskShouldWorkProperty() {
        InputStream stream1 = new ByteArrayInputStream("4 16 1 6\n0 1 2 3\n4 5 6 7\n8 9 10 11\n12 13 14 15\n".getBytes());
        System.setIn(stream1);
        App.main(null);

        assertEquals("7\n", outContent.toString());

    }

    @Test
    public void shouldWork() {
        InputStream stream1 = new ByteArrayInputStream("4 16 1 6\n0 1 2 3\n4 5 6 7\n8 9 10 11\n12 13 14 15\n".getBytes());
        System.setIn(stream1);

        int[][] A = new int[605][605];
        int res = 0;
        int[][] acc = new int[605][605];

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int L = scanner.nextInt();
        int r = scanner.nextInt();
        int t = scanner.nextInt();

        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                A[i][j] = scanner.nextInt();
                acc[i][j] = acc[i - 1][j] + acc[i][j - 1] - acc[i - 1][j - 1] + A[i][j];
            }
        }
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                int left = Math.max(j - r, 1);
                int right = Math.min(j + r, n);
                int upper = Math.max(i - r, 1);
                int down = Math.min(i + r, n);
                int near = acc[down][right] - acc[down][left - 1] - acc[upper - 1][right] + acc[upper - 1][left - 1];
                if (near <= (down - upper + 1) * (right - left + 1) * t) {
                    res++;
                }
            }
        }

        System.out.println(res);

        assertEquals("7\n", outContent.toString());
    }
}