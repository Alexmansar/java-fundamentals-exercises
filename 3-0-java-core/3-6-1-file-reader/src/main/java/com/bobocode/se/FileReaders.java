package com.bobocode.se;

import java.io.FileReader;
import java.io.IOException;

/**
 * {@link FileReaders} provides an API that allow to read whole file into a {@link String} by file name.
 */
public class FileReaders {

    /**
     * Returns a {@link String} that contains whole text from the file specified by name.
     *
     * @param fileName a name of a text file
     * @return string that holds whole file content
     */
    public static String readWholeFile(String fileName) {
        String str;
        FileReader reader = null;
        try {
            reader = new FileReader(fileName);
            str = String.valueOf(reader.read());
        } catch (IOException e) {
            str = "";
        }
     /*   try {
            reader.ready();
        } catch (IOException e) {
            str = reader.toString();
        }
     */
        return str;
    }
}
