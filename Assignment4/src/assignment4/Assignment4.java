/*
3/25/2017 CS-202-18371
This program creates a rudimentary spell-checking program to find the average 
number of comparisons it took to find words in the dictionary and the average 
number of comparisons it performed when it could not find words in the 
dictionary.
 */
package assignment4;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Jonathan Steininger
 */
public class Assignment4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int[] count = new int[1];
        long wordsFound = 0;
        long wordsNotFound = 0;
        long compsFound = 0;
        long compsNotFound = 0;
        MyLinkedList[] list = new MyLinkedList[26];
        for (int i = 0; i < list.length; i++) {
            list[i] = new MyLinkedList<String>();
        }
        try {
            File f = new File("random_dictionary.txt");
            Scanner input = new Scanner(f);
            String current;
            while (input.hasNext()) {
                current = input.next();
                current = current.toLowerCase();
                list[current.charAt(0) - 97].add(current);
            }//while
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            File f = new File("oliver.txt");
            Scanner input = new Scanner(f);
            String[] current = new String[11];

            while (input.hasNext()) {
                current[0] = input.next();
                //System.out.println("First \t" + current[0]);
                //if(!current[0].contains("\'")){
                current[0] = current[0].replaceAll("[^a-zA-Z]", " ");
                //}
                //current[0] = current[0].replaceAll("[^a-zA-Z]", " ");
                current[0] = current[0].toLowerCase();
                current[0] = current[0].trim();
                //System.out.println("Second \t" + current[0]);
                String[] tokens = current[0].split(" ");

                for (int i = 0; i < tokens.length; i++) {
                    /*while(tokens[i].charAt(0) - 'a'<0){
                        tokens[i] = tokens[i].substring(1);
                    }*/
                    if (!tokens[i].isEmpty()) {
                        if (list[tokens[i].charAt(0) - 'a'].contains(tokens[i], count)) {
                            wordsFound++;
                            compsFound += count[0];
                        } else {
                            wordsNotFound++;
                            compsNotFound += count[0];
                        }
                    }
                }//for
            }//while
            input.close();
            System.out.println("Words Found: \t\t\t\t\t" + wordsFound);
            System.out.println("Words Not Found: \t\t\t\t" + wordsNotFound);
            System.out.println("Comparisons Found: \t\t\t\t" + compsFound);
            System.out.println("Comparisons Not Found: \t\t\t\t" + compsNotFound);
            double avgcwf = (double) compsFound / wordsFound;
            double avgcwnf = (double) compsNotFound / wordsNotFound;
            System.out.printf("Average Comparisons for Words Found: \t\t%.2f", avgcwf);
            System.out.printf("\nAverage Comparisons for Words Not Found: \t%.2f\n", avgcwnf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//main
}//class
