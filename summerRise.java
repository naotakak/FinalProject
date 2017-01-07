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
	if (txt.endsWith(".\n") || txt.endsWith(".")) {
	    txt = txt.substring(0,txt.length() - 2);
	}
	txt = txt.replace("\n", "");
	return txt;
    }
    
    public static String wordCount(String txt) {
	txt = removePunctuation(txt);
	txt = txt.toLowerCase();
	int count = 1;
	String[]temp = txt.split(" ");
	Arrays.sort(temp);
	System.out.println(Arrays.toString(temp));
	for (int i = 0; i < temp.length - 1 ; i ++) {
	    if (!temp[i].equals(temp[i+1])) {
		count += 1;
	    }
	}
	System.out.println(count);
	words = new String[count][2];
	for (int i = 0; i < words.length ; i ++) {
	    words[i][1] = "0";
	}
	/*This is trying to compare the current word to the next word in temp. If they are not the same,
	then the current word is added to the words array in words[i][0], and words[i][1] is set to 1.
	If they are the same, then 1 is supposed to be added to words[i][1]. The point of the index variable
	is in case of repeated elements in the temp array - this will allow for counting the # of occurences
	instead of just skipping over them in theory.
	*/
	int index = 0;
	for (int i = 0; i < temp.length - 1; i ++) {
	    if (!temp[i].equals(temp[i + 1])) {
		words[index][0] = temp[i + 1];
		words[index][1] = "1";
		System.out.println(index);
		index ++;
	    }
	    else {
		words[index - 1][1] = "" + (Integer.parseInt(words[index - 1][1]) + 1);
		System.out.println("x");
		//index ++;
	    }
	}
        String s = "";
	for (int i = 0; i < words.length; i ++) {
	    s += words[i][0] + ", ";
	    s += words[i][1] + "  ";
	}
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
    /*for (int i = 0; i < words.size(); i ++) {
      System.out.println(words.get(i));
      }*/
	System.out.println(wordCount(text));
	//System.out.println(Arrays.toString(para));
    }
}
