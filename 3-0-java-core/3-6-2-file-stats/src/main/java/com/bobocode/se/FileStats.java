package com.bobocode.se;

import com.bobocode.util.ExerciseNotCompletedException;

/**
 * {@link FileStats} provides an API that allow to get character statistic based on text file. All whitespace characters
 * are ignored.
 */
public class FileStats {
    String fileName;

    public FileStats(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Creates a new immutable {@link FileStats} objects using data from text file received as a parameter.
     *
     * @param fileName input text file name
     * @return new FileStats object created from text file
     */
    public static FileStats from(String fileName) {
        return new FileStats(fileName);
        //     throw new ExerciseNotCompletedException(); //todo
    }

    /**
     *      * Returns a number of occurrences of the particular character.
     *      *
     *      * @param character a specific character
     *      * @return a number that shows how many times this character appeared in a text file
     */
    public int getCharCount(char character) {
        int count = 0;
        char[] chars = fileName.toCharArray();
        for (char c : chars) {
            if (c == character){
                count++;
            }
        }


       /* if (fileName.contains(character)) {
            count++;
        } else {

        }
        if (fileName.length() == 0) {
            throw new FileStatsException("Empty");
        }
       */ return count;
//        throw new ExerciseNotCompletedException(); //todo
    }

    /**
     * Returns a character that appeared most often in the text.
     *
     * @return the most frequently appeared character
     */
    public char getMostPopularCharacter() {
        throw new ExerciseNotCompletedException(); //todo
    }

    /**
     * Returns {@code true} if this character has appeared in the text, and {@code false} otherwise
     *
     * @param character a specific character to check
     * @return {@code true} if this character has appeared in the text, and {@code false} otherwise
     */
    public boolean containsCharacter(char character) {
        throw new ExerciseNotCompletedException(); //todo
    }
}
