import java.util.*;
import java.io.*;

public class summerRise {
    private static String text = "";
    private ArrayList<String>paras = new ArrayList<String>();
    private static String[] para;

    public static void paraSplit(String txt) {
	para = txt.split("\n\n");
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
	System.out.println(text);
    }

    public static void main (String[]args) {
	loadText(args[0]);
	paraSplit(text);
	System.out.println(Arrays.toString(para));
    }
}
