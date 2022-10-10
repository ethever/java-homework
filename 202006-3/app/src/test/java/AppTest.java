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
        InputStream stream1 = new ByteArrayInputStream("1 2 3\nop 1 open 1 door 0\nop 1 g sre\nop 1 u xiaop\nxiaoc 2 sre ops open door room302\nxiaop 1 ops open door room501\nxiaoc 2 sre ops remove door room302\n".getBytes());
        System.setIn(stream1);
        App.main(null);

        assertEquals("1\n1\n0\n", outContent.toString());
        outContent.reset();
    }
}