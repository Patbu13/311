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

public class Homework00 {
    public static void main(String[] args) {
        try {
            BufferedReader AnsReader = new BufferedReader(new FileReader("Answers.txt"));
            BufferedReader QueReader = new BufferedReader(new FileReader("Questions.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("Solution.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}