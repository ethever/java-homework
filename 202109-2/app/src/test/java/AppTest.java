import org.checkerframework.checker.units.qual.K;
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
        ArrayList<Integer> l = new ArrayList<Integer>(100);

        l.add(0);
        l.add(1);

        App.unique(l);

        ArrayList<Integer> m = new ArrayList<Integer>(100);
        m.add(0);
        m.add(1);
        assertEquals(l, m);
    }
    @Test
    public void uniqueShouldWork4() {
        outContent.reset();
        ArrayList<Integer> l = new ArrayList<Integer>(100);

        l.add(0);
        l.add(0);
        l.add(0);
        l.add(0);
        l.add(0);
        l.add(0);
        l.add(0);
        l.add(0);
        l.add(0);
        l.add(0);
        l.add(0);
        l.add(0);
        l.add(0);
        l.add(0);
        l.add(0);
        l.add(0);
        l.add(0);
        l.add(0);

        App.unique(l);

        ArrayList<Integer> m = new ArrayList<Integer>(100);
        m.add(0);
        assertEquals(l, m);
    }

    @Test
    public void uniqueShouldWork3() {
        ArrayList<Integer> l = new ArrayList<Integer>(100);

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

        App.unique(l);

        ArrayList<Integer> r = new ArrayList<Integer>(100);

        r.add(0);
        r.add(1);
        r.add(2);
        r.add(3);
        r.add(2);

        assertEquals(r, l);
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
}