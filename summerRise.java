import java.util.*;
import java.io.*;

public class summerRise {
    private static String text = "";
    private ArrayList<String>paras = new ArrayList<String>();
    private static String[] para;
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
    
    public static String removePunctuation(String txt) {
	txt =  txt.replace(", ", " ");
	txt =  txt.replace(". ", " ");
	txt =  txt.replace(".\n\n", " ");
	txt =  txt.replace(".\n", " ");
	if (txt.endsWith(".\n") || txt.endsWith(".")) {
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
	  the code
	  with the ArrayList rather than the array. However, it does not work in the sense that it does not remove
	  the irrelevant words completely. If an irrelevant word that is sorted at the very end and has more than
	  one occurence, it does not remove it. 
	*/
	ArrayList<String>tempArrList = new ArrayList<String>();
	for (int i = 0; i <temp.length; i++){
	    tempArrList.add(temp[i]);
	}
	System.out.println(Arrays.toString(temp));
	System.out.println(tempArrList);
	for(int i = 0; i <tempArrList.size(); i ++) {
	    String tempWord = tempArrList.get(i);
	    if (irrelevant.contains(tempArrList.get(i))) {
		tempArrList.remove(tempWord);
	    }
	}
	for (int i = 0; i < tempArrList.size(); i ++) {
	    System.out.println(tempArrList.get(i));
	}
	for (int i = 0; i < temp.length - 1 ; i ++) {
	    if (!temp[i].equals(temp[i+1])) {
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
	for (int i = 0; i < temp.length - 1; i ++) {
	    if (index == 0) {
		words[0][0] = temp[0];
	    }
	    if (!temp[i].equals(temp[i + 1])) {
		words[index + 1][0] = temp[i + 1];
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
	//System.out.println(Arrays.deepToString(words));
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
	/*for (int i = 0; i < irrelevant.size(); i ++) {
	  System.out.println(irrelevant.get(i));
	  }*/
	//System.out.println(Arrays.toString(para));
    }
}
