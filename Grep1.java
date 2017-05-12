
import java.util.regex.*;
import java.io.*;

/** A command-line grep-like program. No options, but takes a pattern
 * and an arbitrary list of text files.
 */
public class Grep {
  /** The pattern we're looking for */
  protected Pattern pattern;
  /** The matcher for this pattern */
  protected Matcher matcher;

  /** Main will make a Grep object for the pattern, and run it
   * on all input files listed in argv.
   */
  public static void main(String[] argv) throws Exception {

    if (argv.length < 1) {
        System.err.println("Usage: Grep pattern [filename]");
        System.exit(1);
    }

    Grep grep = new Grep(argv[0]);

    if (argv.length == 1)
      grep.process(new BufferedReader(new InputStreamReader(System.in)),
        "(standard input)", false);
    else
      for (int i=1; i<argv.length; i++) {
        grep.process(new BufferedReader(new FileReader(argv[i])),
          argv[i], true);
      }
  }

  /** Construct a Grep1 program */
  public Grep(String patterns) {
    pattern = Pattern.compile(patterns);
    matcher = pattern.matcher("");
  }

  /** Do the work of scanning one file
   * @param ifile BufferedReader object already open
   * @param fileName String Name of the input file
   * @param printFileName Boolean - true to print filename
   * before lines that match.
   */
  public void process(
    BufferedReader inputFile, String fileName, boolean printFileName) {

    String inputLine;

    try {
      while ((inputLine = inputFile.readLine()) != null) {
        matcher.reset(inputLine);
        if (matcher.lookingAt()) {
          if (printFileName) {
            System.out.print(fileName + ": ");
          }
          System.out.println(inputLine);
        }
      }
      inputFile.close();
    } catch (IOException e) { System.err.println(e); }
  }
}
