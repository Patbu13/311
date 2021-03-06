package Homework03;
/**
 * Patrick Burroughs
 * Homework03 - JJ Shepherd's CSCE 311
 * Determines whether or not a number of
 * projects in a workshop may be deadlocked.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

 public class Homework03 {

    static int projects = 0, resources = 0;
    static ArrayList<Integer> sequence = new ArrayList<>();

    public static void main(String[] args) {

        /*
         * User Interface
         */
        Scanner scanner = new Scanner(System.in);
        System.out.println("Provide the complete path for ALL of the text files' repository (NOT ending with *.txt)");
        String repo = scanner.nextLine();
        
        try {
            /*
             * Creating ability to read / write to files
             */
            BufferedReader AllocReader = new BufferedReader(new FileReader(repo + "\\alloc.txt"));
            BufferedReader ReqReader = new BufferedReader(new FileReader(repo + "\\req.txt"));
            BufferedReader AvailReader = new BufferedReader(new FileReader(repo + "\\avail.txt"));
            
            projects = Integer.parseInt(AllocReader.readLine());
            resources = Integer.parseInt(AllocReader.readLine());

            /*
             * Call function to read the files in the previously addressed manner
             */
            ArrayList<ArrayList<Integer>> AllocMatrix = FormMatrix(AllocReader);
            ArrayList<ArrayList<Integer>> ReqMatrix = FormMatrix(ReqReader);
            ArrayList<ArrayList<Integer>> AvailMatrix = FormMatrix(AvailReader);                

            /*
             * Check for a deadlock
             * If one exists : suggest project to remove
             * If none exist : suggest sequence of project action
             */

             if (Deadlock(AllocMatrix, ReqMatrix, AvailMatrix)) {
                //send req and find proj w/ max resources requested
                System.out.println("There is a deadlock, however, this can be eliminated by putting off Project #" + checkNeedy(ReqMatrix));
             }
             else {
                System.out.println("There is no deadlock.\nSequence: " + sequence);
             }

            /*
             * NEED: close readers and writers at some point before program stops or else they will not read/write
             */
            AllocReader.close();
            ReqReader.close();
            AvailReader.close();
            scanner.close();
        } catch (IOException e) {
            System.out.println("File read error");
            e.printStackTrace();
        }

    }

    /**
     * Takes the matrix assembled in a text file, reads, and creates
     * ArrayList<ArrayList> matrix holding text file info
     * 
     * *** I made this way more complex than it needed to be for projects/resources
     * just wanted to test how I would access files in diff ways ***
     * 
     * @param file current file that needs read & formatted to matrix
     * @return newly created matrix
     */
    public static ArrayList<ArrayList<Integer>> FormMatrix(BufferedReader file) {
        String nextLine;
        String[] row;
        ArrayList<ArrayList<Integer>> newMatrix = new ArrayList<ArrayList<Integer>>();
 
        try {
            
            /*
             * Used to skip initial info about # of projects and # of types of resources
             */
            file.mark(100);
            while ((file.readLine().split("\t").length == 1)) {file.mark(100);}
            file.reset();

            /*
             * Crafts matrix from text file
             */
            while((nextLine = file.readLine()) != null) {
                row = nextLine.split("\t");
                ArrayList<Integer> vector = new ArrayList<>();

                for (int i = 0; i < row.length; i++) {
                    vector.add(Integer.parseInt(row[i]));
                }

                newMatrix.add(vector);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newMatrix;
    }
    
    /**
     * Used to find any deadlocks in the project plan and return if one is found or not
     * @param alloc matrix for allocated resources
     * @param req matrix for requested resources
     * @param avail matrix for available resources
     * @return whether a deadlock is found or not
     */
    public static boolean Deadlock(ArrayList<ArrayList<Integer>> alloc, ArrayList<ArrayList<Integer>> req, ArrayList<ArrayList<Integer>> avail) {
        int seqFin = 0, lockCount = 0;
        //iterate through projects until deadlock or sequence found
        for (int i = 0; seqFin < alloc.size(); i++) {
            
            //check for deadlock
            if (lockCount == projects) {
                return true;
            }
            //if this project has already been put into sequence
            if (sequence.indexOf(i%projects) != -1) {
                continue;
            }
            //iterate values for each resource for a project
            for (int j = 0; j <= resources; j++) {
                //if resources can be met for a project
                if (j == resources) {
                    seqFin++;
                    sequence.add(i%projects);
                    lockCount=0;
                    //adds the previously allocated resources to available
                    for (int k = 0; k < resources; k++) {
                        avail.get(0).set(k, alloc.get(i%projects).get(k)+avail.get(0).get(k));
                    }
                    continue;
                }
                //if req too much of anything, that project is currently unattainble
                if (alloc.get(i%projects).get(j) + avail.get(0).get(j) < req.get(i%projects).get(j)) {
                    j = resources+1;
                    lockCount++;
                }
                
            }
        }

        return false;
    }

    /**
     * Finds the most resource intensive project to put off to break the deadlock
     * @param req the request matrix
     * @return
     */
    public static Integer checkNeedy(ArrayList<ArrayList<Integer>> req) {
        int maxRes = 0, tempRes = 0, theMax = 0;
        for (ArrayList<Integer> i : req) {
            for (int j : i) {
                tempRes += j;
            }
            if (tempRes > maxRes) {
                maxRes = tempRes;
                theMax = req.indexOf(i);
            }
        }
        return theMax;
    }
 }