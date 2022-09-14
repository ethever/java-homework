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
        
        InputStream stream1 = new ByteArrayInputStream("9 10\n1 2 3 4 5 6 7 8 9\n".getBytes());
        System.setIn(stream1);
        App.main(null);

        assertEquals("45", outContent.toString());
        outContent.reset();

        InputStream stream2 = new ByteArrayInputStream("3 10\n2 5 8\n".getBytes());
        System.setIn(stream2);
        App.main(null);

        assertEquals("15", outContent.toString());
        outContent.reset();
    }
}