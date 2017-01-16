import java.util.*;
import java.io.*;
import java.net.*;

public class summerRise {
    private static String text = "";
    private static String[][] sentencePoints;
    private static String[][]words;
    private static ArrayList<String>irrelevant = new ArrayList<String>();
    private static ArrayList<String>titleWords;
    private static String title;

    public summerRise() {
    }
    
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

    private static String replaceExceptions(String txt) {
	txt = txt.replace("Mr. ", "Mr` ");
	txt = txt.replace("Ms. ", "Ms` ");
	txt = txt.replace("Dr. ", "Dr` ");
	txt = txt.replace("Mrs. ", "Mrs` ");
	txt = txt.replace("Mr. ", "Mr` ");
	txt = txt.replace("Jr. ", "Jr` ");
	txt = txt.replace("Sr. ", "Sr` ");
	txt = txt.replace("vs. ", "vs` ");
	txt = txt.replace("... ", "``` ");
	txt = txt.replace(" A. ", " A` ");
	txt = txt.replace(" B. ", " B` ");
	txt = txt.replace(" C. ", " C` ");
	txt = txt.replace(" D. ", " D` ");
	txt = txt.replace(" E. ", " E` ");
	txt = txt.replace(" F. ", " F` ");
	txt = txt.replace(" G. ", " G` ");
	txt = txt.replace(" H. ", " H` ");
	txt = txt.replace(" I. ", " I` ");
	txt = txt.replace(" J. ", " J` ");
	txt = txt.replace(" K. ", " K` ");
	txt = txt.replace(" L. ", " L` ");
	txt = txt.replace(" M. ", " M` ");
	txt = txt.replace(" N. ", " N` ");
	txt = txt.replace(" O. ", " O` ");
	txt = txt.replace(" P. ", " P` ");
	txt = txt.replace(" Q. ", " Q` ");
	txt = txt.replace(" R. ", " R` ");
	txt = txt.replace(" S. ", " S` ");
	txt = txt.replace(" T. ", " T` ");
	txt = txt.replace(" U. ", " U` ");
	txt = txt.replace(" V. ", " V` ");
	txt = txt.replace(" W. ", " W` ");
	txt = txt.replace(" X. ", " X` ");
	txt = txt.replace(" Y. ", " Y` ");
	txt = txt.replace(" Z. ", " Z` ");
	return txt;
    }
    
    public static void sentenceSplit(String txt) {
	txt = replaceExceptions(txt);
	String[]sentencePointsTemp = txt.split("(?<!\\w\\.\\w.)(?<![A-Z][a-z]\\.)(?<=\\.|\\?)(\\s|[A-Z].*)");
	sentencePoints = new String[sentencePointsTemp.length][2];
	for (int i = 0; i < sentencePointsTemp.length; i++) {
	    sentencePoints[i][0] = sentencePointsTemp[i];
	    sentencePoints[i][1] = "0";
	}

    }

    public static void assignPoints(String txt) {
	sentenceSplit(txt);
	sentencePoints[0][1] = "" + Integer.parseInt(sentencePoints[0][1]) + 2;
	sentencePoints[sentencePoints.length - 1][1] = "" + Integer.parseInt(sentencePoints[sentencePoints.length - 1][1]) + 1;
	for (int i = 0; i < sentencePoints.length; i ++) {
	    String tempSentence = sentencePoints[i][0].toLowerCase();
	    tempSentence = removePunctuation(tempSentence);
	    for (int y = 0; y < titleWords.size(); y++) {
		if (tempSentence.contains(titleWords.get(y))) {
		    sentencePoints[i][1] = Integer.toString(Integer.parseInt(sentencePoints[i][1]) + 2);
		}
	    }
	    for (int n = 0; n < words.length; n++) {
		if (tempSentence.contains(words[n][0]) && Integer.parseInt(words[n][1]) > 1) {
		    sentencePoints[i][1] =
			Integer.toString(Integer.parseInt(sentencePoints[i][1])
					 + (Integer.parseInt(words[n][1]) - 1));
		}
	    }
	}
    }
    
    private static String removePunctuation(String txt) {
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
	txt = txt.replace(": ", " ");
	txt = txt.replace(" \"\n", " ");
	txt = txt.replace(".\"", " ");
	if (txt.endsWith(".\"\n")) {
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
	txt = removePunctuation(txt);
	txt = txt.toLowerCase();
	int count = 1;
	String[]temp = txt.split(" ");
	Arrays.sort(temp);
	ArrayList<String>tempArrList = new ArrayList<String>();
	for (int i = 0; i <temp.length; i++){
	    tempArrList.add(temp[i]);
	}
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
    }

    public static void loadText(String filename) {
	try{
	    addIrrelevant();
	    Scanner scan = new Scanner(new File(filename));
	    String tempTitle = scan.nextLine();
	    title = tempTitle;
	    tempTitle = removePunctuation(tempTitle);
	    tempTitle = tempTitle.toLowerCase();
	    String[]tempTitleArr = tempTitle.split(" ");
	    titleWords = new ArrayList<String>(Arrays.asList(tempTitleArr));
	    for (int i = 0; i < titleWords.size(); i++ ) {
		if (irrelevant.contains(titleWords.get(i))) {
		    titleWords.remove(titleWords.get(i));
		}
	    }
	    //System.out.println(titleWords);
	    //System.out.println(title + "\n\n");

	    while (scan.hasNext()) {
		text += scan.nextLine() + "\n";
	    }
	}catch(FileNotFoundException e) {
	    System.out.println("File not found");
	}
    }

    public void loadHTML(String link) {
	String s = "";
	try {
	    addIrrelevant();
	    URL plainText = new URL(
				    "http://boilerpipe-web.appspot.com/extract?url=" +
				    link + "&extractor=ArticleExtractor&output=text&extractImages=&token=");
	    BufferedReader in = new BufferedReader(new InputStreamReader(plainText.openStream()));
	    String inputLine;
	    if ((inputLine = in.readLine()) != null) {
		String tempTitle = inputLine.replaceAll(new String("â".getBytes("UTF-8"), "UTF-8"), "").replaceAll(new String("Â".getBytes("UTF-8"), "UTF-8"), "");
		title = tempTitle;
		tempTitle = removePunctuation(tempTitle);
		tempTitle = tempTitle.toLowerCase();
		String[]tempTitleArr = tempTitle.split(" ");
		titleWords = new ArrayList<String>(Arrays.asList(tempTitleArr));
		for (int i = 0; i < titleWords.size(); i++ ) {
		    if (irrelevant.contains(titleWords.get(i))) {
			titleWords.remove(titleWords.get(i));
		    }
		}
	    }
	    while ((inputLine = in.readLine()) != null) {
		s+=inputLine.replaceAll(new String("â".getBytes("UTF-8"), "UTF-8"), "").replaceAll(new String("Â".getBytes("UTF-8"), "UTF-8"), "").replaceAll(new String(",".getBytes("UTF-8"), "UTF-8"), ",")
		    .replaceAll(new String("’".getBytes("UTF-8"), "UTF-8"), "'").replaceAll(new String("”".getBytes("UTF-8"), "UTF-8"), "\"").replaceAll(new String("“".getBytes("UTF-8"), "UTF-8"), "\"");
	    }
	    in.close();
	}catch (MalformedURLException e) {
	    e.printStackTrace();
	}catch (IOException e) {
	    e.printStackTrace();
	}
	text += s;
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
	paragraph = paragraph.replace("`", ".");
	return title + "\n\n" + paragraph;
    }

    public static void main (String[]args) {
	try {
	    if (args.length > 2 && args[2].equals("url")) {
		summerRise s = new summerRise();
		s.loadHTML(args[0]);
	    }
	    else {
		loadText(args[0]);
	    }
	    wordCount(text);
	    assignPoints(text);
	    System.out.println(sentencePoints.length);
	    System.out.println(Arrays.deepToString(sentencePoints));
	    if (Integer.parseInt(args[1]) <= sentencePoints.length) {
		System.out.println(makeParagraph(sentencePoints,
						 (Integer.parseInt(args[1]))));
	    }
	    else {
		System.out.println("# of sentences longer than article");
	    }
	}catch (NumberFormatException e) {
	    System.out.println("Format: summerRise.java [url/filename] [number of sentences] [url/non-url]");
	}catch (ArrayIndexOutOfBoundsException e) {
	    System.out.println("Format: summerRise.java [url/filename] [number of sentences] [url/non-url]");
	}
    }
}
