import java.util.*;
import java.io.*;

public class summerRise {
    private static String text = "";

    public static void main (String[]args) {
	try{
	String filename = args[0];
	Scanner scan = new Scanner(new File(filename));
	while (scan.hasNext()) {
	    text += scan.nextLine() + "\n";
	}
	}catch(FileNotFoundException e) {
	    System.out.println("File not found");
	}
	System.out.println(text);
    }
}
