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

/*
 * Homework 00, intended to take "questions" and "answers" files
 * Combines the two files into a new file, putting each answer below their respective question
 */
public class Homework00 {
    public static void main(String[] args) {
        try {
            /*
             * Creating ability to read / write to files
             */
            BufferedReader AnsReader = new BufferedReader(new FileReader("Homework00\\Answers.txt"));
            BufferedReader QueReader = new BufferedReader(new FileReader("Homework00\\Questions.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("Homework00\\Solutions.txt"));
            
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
        } catch (IOException e) {
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