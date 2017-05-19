
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Grep {

  private static Pattern pattern;

  private static Matcher matcher;


  public static void main(String[] argv) throws Exception {

    if (argv.length < 1) {
        System.err.println("Usage: Grep1 pattern [filename]");
        System.exit(1);
    }



    if (argv.length == 1)
    {
    System.out.println("error");
    }
    if(argv.length == 2)
    {
      for (int i=1; i<argv.length; i++) {
    	  Grep.grep(argv[0],argv[1]);

      }
    }
  }


  public static String grep(String patterns,String fileName) {
   pattern = Pattern.compile(patterns);
  matcher = pattern.matcher("");

    String inputLine = null;


    try {
    	FileReader filereader = new FileReader( fileName );
        BufferedReader br = new BufferedReader( filereader );
        while( ( inputLine = br.readLine() ) != null ) {
        matcher.reset(inputLine);
        if (matcher.lookingAt()) {
          if (fileName != null) {
            System.out.print(fileName + ": ");
          }
          System.out.println(inputLine);
        }
      }
      br.close();
    } catch (IOException e) { System.err.println(e); }
	return inputLine;
  }
}
