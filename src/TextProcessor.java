import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextProcessor {
    private class Segment {

       private String segment;

       private int start;

       private int end;

       private Segment(String segment, int start, int end) {
           this.segment = segment;
           this.start = start;
           this.end = end;
       }

       @Override
       public String toString() {
           return end - start == 0 ? segment + "(" + end + ");" :
                   segment + "(" + start + "," + end + ");";
       }
   }

    public String process(String text, int width) {
        if (width > 80 || width < 10) {
            return "ERROR: Width out of rang!";
        }
        if (!validate(text)) {
            return "Error: Invalid character detected!";
        }
        StringBuilder result = new StringBuilder();

        char[] chars = text.toCharArray();
        StringBuilder sb = new StringBuilder();
        boolean flag = true;
        int line, start, end;
        line = 0;
        start = end = 1;

        int tag = 0;
        for (int i = 0; i < chars.length; i++) {
            if (' ' == chars[i]) {
                sb.append(' ');
            } else {
                tag = i;
                break;
            }
            if ((i + 1) % width == 1) {
                line++;
                end = line;
            }
        }
        if (sb.length() > 0) {
            result.append(new Segment(sb.toString(), start, end));
            sb.setLength(0);
        }

        for (int i = tag; i < chars.length; i++) {
            if (' ' == chars[i] && flag) {
                result.append(new Segment(sb.toString(), start, end));
                start = end = line;
                if ((i + 1) % width == 1) {
                    start = end = line + 1;
                }
                sb.setLength(0);
                flag = false;
            } else if (chars[i] != ' ' && !flag) {
                result.append(new Segment(sb.toString(), start, end));
                start = end = line;
                if ((i + 1) % width == 1) {
                    start = end = line + 1;
                }
                sb.setLength(0);
                flag = true;
            }
            if ((i + 1) % width == 1) {
                line++;
                end = line;
            }
            sb.append(chars[i]);
        }

        if (sb.length() > 0) {
            result.append(new Segment(sb.toString(), start, end));
        }

        return result.toString();
    }

    private boolean validate(String str) {
        char[] chars = str.toCharArray();
        for (char c : chars) {
            if (' ' == c) {
                continue;
            }
            if (!((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
                return false;
            }
        }
        return true;
    }
}
