import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TextProcessorTest {
    @Test
  public void testTextProcessor() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(
                TextProcessorTest.class.getResourceAsStream("test.txt")));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        String text = sb.toString();
        System.out.println(new TextProcessor().process(text, 30));

    }
}
