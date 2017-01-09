import java.util.*;
import java.io.*;

public class summerRise {
  private static String text = "";
  private ArrayList<String>paras = new ArrayList<String>();
  private static String[] para;
  private static String[][] sentencePoints;
  private static String[][]words;
  private static ArrayList<String>irrelevant = new ArrayList<String>();

  public static void addIrrelevant (){
    try{
      Scanner scan = new Scanner(new File("irrelevant.txt"));
      while (scan.hasNext()) {
        irrelevant.add(scan.nextLine());
      }
    }catch(FileNotFoundException e) {
      System.out.println("Irrelevant File not found");
    }

  }
  public static void paraSplit(String txt) {
    para = txt.split("\n\n");
  }

  public static void sentenceSplit(String txt) {
    String[]sentencePointsTemp = txt.split("(?<!\\w\\.\\w.)(?<![A-Z][a-z]\\.)(?<=\\.|\\?)(\\s|[A-Z].*)");
    sentencePoints = new String[sentencePointsTemp.length][2];
    for (int i = 0; i < sentencePointsTemp.length; i++) {
      sentencePoints[i][0] = sentencePointsTemp[i];
      sentencePoints[i][1] = "0";
    }

  }
  //Supposed to sort array based on the second dimension
  /*public static void sortTwoArray(String[][]) {

  }*/
  public static void assignPoints(String txt) {
    sentenceSplit(txt);
    //System.out.println("test");
    for (int i = 0; i < sentencePoints.length; i ++) {
      String tempSentence = sentencePoints[i][0].toLowerCase();
      tempSentence = removePunctuation(tempSentence);
      System.out.println(tempSentence);
      for (int n = 0; n < words.length; n++) {
        if (tempSentence.contains(words[n][0])) {
          if (Integer.parseInt(words[n][1]) > 1) {
            sentencePoints[i][1] = Integer.toString(Integer.parseInt(sentencePoints[i][1]) + (Integer.parseInt(words[n][1]) - 1));
          }
        }
      }
    }
    System.out.println((Arrays.deepToString(sentencePoints)).replace("\n", "\\n"));
  }
  public static String removePunctuation(String txt) {
    txt = txt.replace("\"", "" );
    txt = txt.replace(",\"", "" );
    txt =  txt.replace(", ", " ");
    txt =  txt.replace(". ", " ");
    txt =  txt.replace(".\n\n", " ");
    txt =  txt.replace(".\n", " ");
    txt =  txt.replace("! ", " ");
    txt =  txt.replace("!\n\n", " ");
    txt =  txt.replace("!\n", " ");
    txt =  txt.replace("? ", " ");
    txt =  txt.replace("?\n\n", " ");
    txt =  txt.replace("?\n", " ");
    if (txt.endsWith(".\n")) {
      txt = txt.substring(0,txt.length() - 2);
    }
    else if (txt.endsWith(".")) {
      txt = txt.substring(0,txt.length() - 1);
    }
    else if (txt.endsWith("?")) {
      txt = txt.substring(0,txt.length() - 1);
    }
    else if (txt.endsWith("?\n")) {
      txt = txt.substring(0,txt.length() - 2);
    }

    txt = txt.replace("\n", "");
    return txt;
  }

  public static String wordCount(String txt) {
    addIrrelevant();
    txt = removePunctuation(txt);
    txt = txt.toLowerCase();
    int count = 1;
    String[]temp = txt.split(" ");
    Arrays.sort(temp);
    /*Trying to put everything to an ArrayList so I can remove it easily then replace the other parts of
    the code with the ArrayList rather than the array.
    */
    ArrayList<String>tempArrList = new ArrayList<String>();
    for (int i = 0; i <temp.length; i++){
      tempArrList.add(temp[i]);
    }
    System.out.println(Arrays.toString(temp));
    //System.out.println(tempArrList);
    int tempArrCounter = 0;
    while (tempArrCounter < tempArrList.size()) {
      if (irrelevant.contains(tempArrList.get(tempArrCounter))) {
        tempArrList.remove(tempArrList.get(tempArrCounter));
      }
      else {
        tempArrCounter ++;
      }
    }

    for (int i = 0; i < tempArrList.size(); i ++) {
      System.out.println(tempArrList.get(i));
    }

    for (int i = 0; i < tempArrList.size() - 1 ; i ++) {
      if (!tempArrList.get(i).equals(tempArrList.get(i +1))) {
        count += 1;
      }
    }
    //System.out.println(count);
    words = new String[count][2];
    for (int i = 0; i < words.length ; i ++) {
      words[i][1] = "1";
    }
    /*This is trying to compare the current word to the next word in temp. If they are not the same,
    then the current word is added to the words array in words[i][0], and words[i][1] is set to 1.
    If they are the same, then 1 is supposed to be added to words[i][1]. The point of the index variable
    is in case of repeated elements in the temp array - this will allow for counting the # of occurences
    instead of just skipping over them in theory.
    */
    int index = 0;
    for (int i = 0; i < tempArrList.size() - 1; i ++) {
      if (index == 0) {
        words[0][0] = tempArrList.get(0);
      }
      if (!tempArrList.get(i).equals(tempArrList.get(i+1))) {
        words[index + 1][0] = tempArrList.get(i + 1);
        words[index + 1][1] = "1";
        //System.out.println(index);
        index ++;
      }
      else {
        words[index][1] = "" + (Integer.parseInt(words[index][1]) + 1);
        //System.out.println("x");
        //index ++;
      }
    }
    String s = "";
    for (int i = 0; i < words.length; i ++) {
      s += words[i][0] + ", ";
      s += words[i][1] + "  ";
    }
    System.out.println(Arrays.deepToString(words));
    //System.out.println(irrelevant.contains("b"));
    return s;
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
    assignPoints(text);
    /*for (int i = 0; i < irrelevant.size(); i ++) {
    System.out.println(irrelevant.get(i));
  }*/
  //System.out.println(Arrays.toString(para));
}
}
