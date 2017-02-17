package examples;

/**
 * Created by Ivan_Surus on 2/17/2017.
 */
public class PatternExam {

    public static void main(String[] args) {
    String URL = "http://foo.com/blah_blah.jpg";
        String pattern = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$@iS";
        String pattern2 = "#\\b(([\\w-]+://?|www[.])[^\\s()<>]+(?:\\([\\w\\d]+\\)|([^[:punct:]\\s]|/)))#iS";
        String pattern3 = "(https?|ftp|file)://[-a-zA-Z0-9+@#/%?=~_|!:,.;]*[-a-zA-Z0-9+@#/%=~_|]";

        System.out.println(URL.matches(pattern3));
    }

}
