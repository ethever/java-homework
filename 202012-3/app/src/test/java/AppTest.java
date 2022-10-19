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
        
        InputStream stream1 = new ByteArrayInputStream("10\nC /A/B/1 1024\nC /A/B/2 1024\nC /A/B/1/3 1024\nC /A 1024\nR /A/B/1/3\nQ / 0 1500\nC /A/B/1 100\nQ / 0 1500\nR /A/B\nQ / 0 1\n".getBytes());
        System.setIn(stream1);
        App.main(null);

        assertEquals("Y\nY\nN\nN\nY\nN\nY\nY\nY\nY\n", outContent.toString());
        outContent.reset();

        // InputStream stream2 = new ByteArrayInputStream("3 10\n2 5 8\n".getBytes());
        // System.setIn(stream2);
        // App.main(null);

        // assertEquals("15", outContent.toString());
        // outContent.reset();
    }
}