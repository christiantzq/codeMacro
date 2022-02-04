package com.kurisu.codemacro.interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import com.kurisu.codemacro.exceptions.BadSyntaxException;
import com.kurisu.codemacro.instructions.codeblocks.Function;

public class SimpleInterpreter implements CanInterprete {

    @Override
    public Function interprete(String scriptPath) throws BadSyntaxException, IOException {

        FileInputStream inputStream = null;
        Scanner scanner = null;
        try {
            inputStream = new FileInputStream(scriptPath);
            scanner = new Scanner(inputStream, "UTF-8");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // System.out.println(line);
            }
            // note that Scanner suppresses exceptions
            if (scanner.ioException() != null) {
                throw scanner.ioException();
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (scanner != null) {
                scanner.close();
            }
        }

        return null;
    }

}
