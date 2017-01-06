import java.util.*;
import java.io.*;

public class summerRise {
  private static String text = "";
  private ArrayList<String>paras = new ArrayList<String>();
  private static String[] para;
  private static String[][]words;

  public static void paraSplit(String txt) {
    para = txt.split("\n\n");
  }

  public static String removePunctuation(String txt) {
    txt =  txt.replace(", ", " ");
    txt =  txt.replace(". ", " ");
    txt =  txt.replace(".\n\n", " ");
    txt =  txt.replace(".\n", " ");
    //Trying to replace the last period of the article with nothing, still does not work.
    if (txt.endsWith(".\n") || txt.endsWith(".")) {
      txt = txt.substring(0,txt.length() - 2);
    }
    txt = txt.replace("\n", "");
    return txt;
  }

  //Trying to split each word and put them into an ArrayList. Did not finish, splitted the whole text file but did not start keeping track of number of occurences.
  public static int wordCount(String txt) {
    txt = removePunctuation(txt);
    txt = txt.toLowerCase();
    int count = 1;
    String[]temp = txt.split(" ");
    Arrays.sort(temp);
    System.out.println(Arrays.toString(temp));
    for (int i = 0; i < temp.length -1 ; i ++) {
	if (!temp[i].equals(temp[i+1])) {
	    count += 1;
	}
    }
    return count;
  }


  public static void loadText(String filename) {
    try{
      Scanner scan = new Scanner(new File(filename));
      while (scan.hasNext()) {
        text += scan.nextLine() + "\n";
      }
    }catch(FileNotFoundException e) {
      System.out.println("File not found");
    }
    //System.out.println(text);
  }

  public static void main (String[]args) {
    loadText(args[0]);
    paraSplit(text);
    wordCount(text);
    /*for (int i = 0; i < words.size(); i ++) {
      System.out.println(words.get(i));
      }*/
    System.out.println(wordCount(text));
    //System.out.println(Arrays.toString(para));
  }
}
