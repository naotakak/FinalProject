import java.util.*;
import java.io.*;

public class summerRise {
    private static String text = "";
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
	for (int i = 0; i < sentencePoints.length; i ++) {
	    String tempSentence = sentencePoints[i][0].toLowerCase();
	    tempSentence = removePunctuation(tempSentence);
	    //System.out.println(tempSentence);
	    for (int n = 0; n < words.length; n++) {
		if (tempSentence.contains(words[n][0])) {
		    if (Integer.parseInt(words[n][1]) > 1) {
			sentencePoints[i][1] = 
			    Integer.toString(Integer.parseInt(sentencePoints[i][1])
					     + (Integer.parseInt(words[n][1]) - 1));
		    }
		}
	    }
	}
	System.out.println((Arrays.deepToString(sentencePoints)).replace("\n", "\\n"));
    }
    public static String removePunctuation(String txt) {
	txt = txt.replace("\"", "" );
	txt = txt.replace(",\"", "" );
	txt = txt.replace(", ", " ");
	txt = txt.replace(". ", " ");
	txt = txt.replace(".\n\n", " ");
	txt = txt.replace(".\n", " ");
	txt = txt.replace("! ", " ");
	txt = txt.replace("!\n\n", " ");
	txt = txt.replace("!\n", " ");
	txt = txt.replace("? ", " ");
	txt = txt.replace("?\n\n", " ");
	txt = txt.replace("?\n", " ");
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

    public static void wordCount(String txt) {
	addIrrelevant();
	txt = removePunctuation(txt);
	txt = txt.toLowerCase();
	int count = 1;
	String[]temp = txt.split(" ");
	Arrays.sort(temp);
	ArrayList<String>tempArrList = new ArrayList<String>();
	for (int i = 0; i <temp.length; i++){
	    tempArrList.add(temp[i]);
	}
	//System.out.println(Arrays.toString(temp));
	int tempArrCounter = 0;
	while (tempArrCounter < tempArrList.size()) {
	    if (irrelevant.contains(tempArrList.get(tempArrCounter))) {
		tempArrList.remove(tempArrList.get(tempArrCounter));
	    }
	    else {
		tempArrCounter ++;
	    }
	}
	for (int i = 0; i < tempArrList.size() - 1 ; i ++) {
	    if (!tempArrList.get(i).equals(tempArrList.get(i +1))) {
		count += 1;
	    }
	}
	words = new String[count][2];
	for (int i = 0; i < words.length ; i ++) {
	    words[i][1] = "1";
	}
	int index = 0;
	for (int i = 0; i < tempArrList.size() - 1; i ++) {
	    if (index == 0) {
		words[0][0] = tempArrList.get(0);
	    }
	    if (!tempArrList.get(i).equals(tempArrList.get(i+1))) {
		words[index + 1][0] = tempArrList.get(i + 1);
		words[index + 1][1] = "1";
		index ++;
	    }
	    else {
		words[index][1] = "" + (Integer.parseInt(words[index][1]) + 1);
	    }
	}
	//System.out.println(Arrays.deepToString(words));
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
    }

    private static int maxValue(ArrayList<String>ints) {
	int max = Integer.parseInt(ints.get(0));
	for (int i = 0; i < ints.size(); i ++) {
	    if (Integer.parseInt(ints.get(i)) > max) {
		max = Integer.parseInt(ints.get(i));
	    }
	}
	return max;
    }

    public static String makeParagraph(String[][]sentences, int num) {
	String paragraph = "";
	ArrayList<String>scores = new ArrayList<String>(sentences.length);
	for (int i = 0 ; i < sentences.length ; i ++ ) {
	    scores.add(sentences[i][1]);
	}
	for (int r = 0 ; r < num ; r ++) {
	    paragraph += sentences[scores.indexOf("" + maxValue(scores))][0];
	    scores.set(scores.indexOf("" + maxValue(scores)), "0");
	    sentences[scores.indexOf("" + maxValue(scores))][1] = "0";
	}
        return paragraph;
    }

    public static void main (String[]args) {
	loadText(args[0]);
	paraSplit(text);
	wordCount(text);
	assignPoints(text);
	System.out.println(makeParagraph(sentencePoints, 
					 (sentencePoints.length / 2)));
	/*for (int i = 0; i < irrelevant.size(); i ++) {
	  System.out.println(irrelevant.get(i));
	  }*/
	//System.out.println(Arrays.toString(para));
    }
}
