package org.jivesoftware.openfire.util;

/**
 * Utility class to perform common String manipulation algorithms.
 */
public class StringUtils {
    
    private static final char[] LT_ENCODE = "&lt;".toCharArray();
    private static final char[] GT_ENCODE = "&gt;".toCharArray();

    
    /**
     * This method takes a string which may contain HTML tags (ie, &lt;b&gt;,
     * &lt;table&gt;, etc) and converts the '&lt;' and '&gt;' characters to
     * their HTML escape sequences. It will also replace LF  with &lt;br&gt;.
     *
     * @param in the text to be converted.
     * @return the input string with the characters '&lt;' and '&gt;' replaced
     *         with their HTML escape sequences.
     */
    public static String escapeHTMLTags(String in) {
        return escapeHTMLTags(in, true);
    }
    
        /**
     * This method takes a string which may contain HTML tags (ie, &lt;b&gt;,
     * &lt;table&gt;, etc) and converts the '&lt;' and '&gt;' characters to
     * their HTML escape sequences.
     *
     * @param in the text to be converted.
     * @param includeLF set to true to replace \n with <br>.
     * @return the input string with the characters '&lt;' and '&gt;' replaced
     *         with their HTML escape sequences.
     */
    public static String escapeHTMLTags(String in, boolean includeLF) {
        if (in == null) {
            return null;
        }
        char ch;
        int i = 0;
        int last = 0;
        char[] input = in.toCharArray();
        int len = input.length;
        StringBuilder out = new StringBuilder((int)(len * 1.3));
        for (; i < len; i++) {
            ch = input[i];
            if (ch > '>') {
            }
            else if (ch == '<') {
                if (i > last) {
                    out.append(input, last, i - last);
                }
                last = i + 1;
                out.append(LT_ENCODE);
            }
            else if (ch == '>') {
                if (i > last) {
                    out.append(input, last, i - last);
                }
                last = i + 1;
                out.append(GT_ENCODE);
            }
            else if (ch == '\n' && includeLF == true) {
                if (i > last) {
                    out.append(input, last, i - last);
                }
                last = i + 1;
                out.append("<br>");
            }
        }
        if (last == 0) {
            return in;
        }
        if (i > last) {
            out.append(input, last, i - last);
        }
        return out.toString();
    }

    
}
