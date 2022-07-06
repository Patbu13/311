/*
 * Patrick Burroughs
 * CSCE 311
 * July 1st
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
 * Homework 00, intended to take "questions" and "answers" files
 * Combines the two files into a new file, putting each answer below their respective question
 */
public class Homework00 {
    public static void main(String[] args) {

        /*
         * User Interface
         */
        Scanner scanner = new Scanner(System.in);

        System.out.println("Provide the complete path for the Questions.txt file's repository (NOT ending with Questions.txt)");
        String queFile = scanner.nextLine();
        System.out.println("Provide the complete path for the Answers.txt file's repository (NOT ending with Answers.txt)");
        String ansFile = scanner.nextLine();
        System.out.println("Provide the complete path to the repository where you would like the answers file written (ex: \\Homework00)");
        String solFile = scanner.nextLine();
        try {
            /*
             * Creating ability to read / write to files
             */
            BufferedReader AnsReader = new BufferedReader(new FileReader(ansFile + "\\Answers.txt"));
            BufferedReader QueReader = new BufferedReader(new FileReader(queFile + "\\Questions.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter(solFile + "\\Solutions.txt"));
            
            /*
             * Call function to combine files in the previously addressed manner
             */
            BackAndForth(QueReader, AnsReader, writer);

            /*
             * NEED: close readers and writers at some point before program stops or else they will not read/write
             */
            AnsReader.close();
            QueReader.close();
            writer.close();
            scanner.close();
            System.out.println("Complete!");
        } catch (IOException e) {
            System.out.println("File read error");
            e.printStackTrace();
        }

    }

    /*
     * Takes two text files and writes them to a new file, alternating a line from each file
     */
    public static void BackAndForth(BufferedReader file1, BufferedReader file2, BufferedWriter writer) {

        String nextLine;

        try {
            while((nextLine = file1.readLine()) != null) {
                writer.write(nextLine);

                /*
                 * If there's an "answering" line, write it
                 * If not, instead of writing null, make a blank line
                 */
                if ((nextLine = file2.readLine()) != null) {
                    writer.write("\n"+nextLine+"\n");
                } else {
                    writer.write("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}