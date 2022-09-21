import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class AppTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private final InputStream originalIn = System.in;

    @Before
    public void setUpStreams() {
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
        outContent.reset();

        InputStream stream1 = new ByteArrayInputStream("11\n3 1 2 0 0 2 0 4 5 0 2\n".getBytes());
        System.setIn(stream1);
        App.main(null);

        assertEquals("5\n", outContent.toString());
    }

    @Test
    public void taskShouldWorkProperty2() {
        outContent.reset();
        InputStream stream2 = new ByteArrayInputStream("3\n1 0 0\n".getBytes());
        System.setIn(stream2);
        App.main(null);

        assertEquals("1\n", outContent.toString());
    }

    @Test
    public void taskShouldWorkProperty3() {
        outContent.reset();
        InputStream stream2 = new ByteArrayInputStream("3\n0 0 0\n".getBytes());
        System.setIn(stream2);
        App.main(null);

        assertEquals("0\n", outContent.toString());
    }

    @Test
    public void taskShouldWorkProperty4() {
        outContent.reset();
        InputStream stream2 = new ByteArrayInputStream("14\n5 1 20 10 10 10 10 15 10 20 1 5 10 15\n".getBytes());
        System.setIn(stream2);
        App.main(null);

        assertEquals("4\n", outContent.toString());
    }

    @Test
    public void uniqueShouldWork() {
        outContent.reset();
        int[] t = new int[] {0, 1};
        int[] nt = new int[] {0, 1};

        int len = App.unique(t);

        assertEquals(len, 2);
        assertArrayEquals(t, nt);
    }

    @Test
    public void uniqueShouldWork2() {
        ArrayList<Integer> l = new ArrayList<Integer>();
        l.add(0);
        l.add(0);
        l.add(0);
        l.add(0);
        l.add(0);
        l.add(0);
        l.add(1);
        l.add(1);
        l.add(1);
        l.add(1);
        l.add(1);
        l.add(1);
        l.add(1);
        l.add(1);
        l.add(2);
        l.add(2);
        l.add(2);
        l.add(2);
        l.add(3);
        l.add(2);
        l.add(2);
        l.add(2);

        for (int i = 0; i < l.size(); i++) {
            Integer cur = l.get(i);
            if (i + 1 < l.size()) {
                Integer nex = l.get(i + 1);
                if (cur == nex) {
                    l.remove(i + 1);
                    i--;
                }
            }
        }

        ArrayList<Integer> m = new ArrayList<Integer>();
        m.add(0);
        m.add(1);
        m.add(2);
        m.add(3);
        m.add(2);
        assertEquals(l, m);
    }

    @Test
    public void unique2ShouldWork1() {
        int[] temp = new int[] { 0, 1, 2, 2, 3, 3, 4, 4, 5, 5, 5, 6, 6, 6, 0, 0, 0 };
        int[] temp2 = new int[] { 0, 1, 2, 3, 4, 5, 6, 0, 5, 5, 5, 6, 6, 6, 0, 0, 0 };

        int index = 1;
        for (int i = 1, len = temp.length; i < len; i++) {
            if (temp[i] != temp[i - 1]) {
                temp[index++] = temp[i];
            }
        }

        assertEquals(index, 8);

        assertArrayEquals(temp, temp2);
    }
}