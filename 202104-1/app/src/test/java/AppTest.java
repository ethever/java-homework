import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

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
        
        InputStream stream1 = new ByteArrayInputStream("4 4 16\n0 1 2 3\n4 5 6 7\n8 9 10 11\n12 13 14 15\n".getBytes());
        System.setIn(stream1);
        App.main(null);

        assertEquals("1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1", outContent.toString());
        outContent.reset();

        InputStream stream2 = new ByteArrayInputStream("7 11 8\n0 7 0 0 0 7 0 0 7 7 0\n7 0 7 0 7 0 7 0 7 0 7\n7 0 0 0 7 0 0 0 7 0 7\n7 0 0 0 0 7 0 0 7 7 0\n7 0 0 0 0 0 7 0 7 0 0\n7 0 7 0 7 0 7 0 7 0 0\n0 7 0 0 0 7 0 0 7 0 0\n".getBytes());
        System.setIn(stream2);
        App.main(null);

        assertEquals("48 0 0 0 0 0 0 29", outContent.toString());
        outContent.reset();
    }
}