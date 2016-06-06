package com.learningobjects.dean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class createOSProcess {

    public static void main(String[] args) {
        try {
            String line;
            List<String> params = java.util.Arrays.asList("ls"); //"git", "pull"
            // Runtime.getRuntime().exec(new String[] {"npm", "help"});
            ProcessBuilder pb = new ProcessBuilder(params); //
            Process p = pb.start(); //
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            while ((line = in.readLine()) != null
                    ) {

              System.out.println(line);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
