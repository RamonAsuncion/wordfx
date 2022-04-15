/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2022
 * Instructor: Prof. Brian King
 *
 * Name: Olivia Peters
 * Section: 02- 11am
 * Date: 4/15/22
 * Time: 12:56 pm
 *
 * Project: csci205_final_project
 * Package: main.tilemvc
 * Class: Used Words
 *
 * Description: determines whether or not word has already been used
 *
 * ****************************************
 */

package main.tilemvc;

import java.io.*;
import java.util.Scanner;

public class UsedWords {

    /**
     * Method to create the file usedwords.txt if it does not exist.
     * List of secret words that have been used so they are not reused
     *
     * @throws IOException - if input error
     */
    public static void createFile() throws IOException {
        //create file
        File dir = new File("src");
        File file = new File(dir, "usedwords.txt");

        // add to file
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
        bufferedWriter.close();
    }

    /**
     * Method to add the word to usedwords.txt after it has been guessed by the user.
     * Also adds if user did not guess in 6 tries.
     *
     * @param word - the secret word
     * @throws IOException - if input error
     */
    public static void addToList(String word) throws IOException {
        String fileName = "src/usedwords.txt";
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true));
        bufferedWriter.write(word + "\n");
        bufferedWriter.close();
    }

    /**
     * Method to check whether the random secret word is in the list usedwords.txt
     *
     * @param word - secret word
     * @return - true or false, if word is in list
     * @throws IOException - if input error
     */
    public static boolean isWordUsed(String word) throws IOException {
        boolean ans = false;
        Scanner scnr = new Scanner(new FileInputStream("src/usedwords.txt"));
        // scan for word to see if already in usedwords.txt
        while (scnr.hasNext()) {
            String nextWord = scnr.next();
            if (nextWord.equals(word)) {
                ans = true;
                break;
            }
        }
        return ans;
    }
}