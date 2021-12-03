package com.obstrom.io;

import com.obstrom.day1.dayOne;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class InputReader {

    private final URL fileUrl;
    private final File file;

    public InputReader(URL fileUrl) {
        this.fileUrl = fileUrl;
        this.file = new File (fileUrl.getFile());
    }

    public List<String> readToList() {
        try {
            return Files.readAllLines(file.toPath(), Charset.defaultCharset());
        } catch (IOException e) {
            System.err.println("Could not read file '" + file.getName() + "' to list.");
            e.printStackTrace();

            System.exit(1);
            return null;
        }
    }

}
