package edu.gatech.seclass.encode;

import java.io.*;
import java.nio.file.*;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

/**
 * This is a Georgia Tech provided code example for use in assigned private GT repositories. Students and other users of this template
 * code are advised not to share it with other students or to make it available on publicly viewable websites including
 * repositories such as github and gitlab.  Such sharing may be investigated as a GT honor code violation. Created for CS6300.
 *
 * Empty Main class for compiling Assignment 6.
 * DO NOT ALTER THIS CLASS or implement it.
 */

    public static void main(String[] args) throws IllegalArgumentException {

        //initialization
        String arg = "";
        String result = "";
        String fcontent = "";
        String w_delimiter = "";
        String r_delimiter = "";
        String k_delimiter = "";
        Boolean writefile = true;
        int i = 0;
        boolean valid_opt = true;
        boolean file_exist = true;

        //see if the file has any argument inside.
        if(args == null || args.length == 0) {
            usage();
        }
        else
        {
            //see if the filename is not 0.
            String filename = args[args.length -1];
            if(filename == null || filename.length() == 0)
                file_not_found();

            if(filename.startsWith("-"))
            {
                usage();
            }
            else
            {
                //read the file to fcontent. try until you get it right.
                try
                {
                    Path file_path = Paths.get(filename);
                    if(Files.exists(file_path))
                    {
                        fcontent = readstring(filename);
                    }
                    else
                    {
                        file_not_found();
                        file_exist = false;
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

                //option 1. encode file with no opts
                //check if there is opt or not
                if(args.length == 1 && file_exist)
                {
                    result = empty_opt(fcontent);
                }

                //While file exists, perform other cases.
                while (i < args.length - 1 && file_exist)
                {
                    arg = args[i].trim();

                    //option 2. -w
                    if(arg.equals("-w"))
                    {
                        //i+1 is the filename or next opt then w_delimiter is ""
                        if( (i+1) == (args.length-1) || args[i+1].startsWith("-") )
                        {
                            //result is empty
                            if (result.length() == 0)
                                result = w_opt(fcontent,"");
                            else
                                result = w_opt(result,"");
                        }
                        else
                        {
                            //if there is a delimiter, then push w_delimiter to method.
                            w_delimiter = args[i+1];
                            if (result.length() == 0)
                                result = w_opt(fcontent,w_delimiter);
                            else
                                result = w_opt(result,w_delimiter);
                            i++;
                        }
                    }
                    //option 3. same for checking for r OPT
                    else if(arg.equals("-r"))
                    {
                        //r needs to have string after -r
                        if( (i+1) != (args.length-1) && !args[i+1].startsWith("-"))
                        {
                            r_delimiter = args[i+1];
                            if (result.length() == 0)
                                result = r_opt(fcontent,r_delimiter);
                            else
                                result = r_opt(result,r_delimiter);
                            i++;
                        }
                        else
                        {
                            if (result.length() == 0)
                                result = fcontent;
                                usage();
                        }
                    }
                    //option 4. same for checking for k OPT
                    else if(arg.equals("-k"))
                    {
                        //r needs to have string after -k
                        if( (i+1) != (args.length-1) && !args[i+1].startsWith("-"))
                        {
                            k_delimiter = args[i+1];
                            if (result.length() == 0)
                                result = k_opt(fcontent,k_delimiter);
                            else
                                result = k_opt(result,k_delimiter);
                            i++;
                        }
                        else
                        {
                            if (result.length() == 0)
                                result = fcontent;
                                usage();
                        }
                    }

                    //option 5. c opt.
                    else if(arg.equals("-c"))
                    {
                        if (result.length() == 0)
                            result = c_opt(fcontent);
                        else
                            result = c_opt(result);
                    }
                    else if(arg.equals("-r") || arg.equals("-k"))
                    {
                        //do nothing
                    }
                    else
                    {
                        valid_opt = false;
                        break;
                    }
                    ++i;

                }

                //option 6. invalid opts
                if(valid_opt == false)
                {
                    usage();
                }
                else
                {
                    //write String to file
                    if(file_exist)
                    {
                        if(writefile)
                            writefile(result,filename);
                        else
                            System.out.println(result);
                    }
                }
            }
        }
    }


    private static String readstring(String filename) throws IOException
    {
        String fcontent = "";
        try
        {
            Path file_path = Paths.get(filename);
            byte[] encoded = Files.readAllBytes(file_path);
            fcontent = new String(encoded, "UTF-8");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return fcontent;
    }

    //If no opt, use -w as default. it will reverse words.
    private static String empty_opt(String fcontent)
    {
        String delimiters = " ";
        String[] words = fcontent.split(delimiters);
        String reversedString = "";
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            String reverseWord = "";
            for (int j = word.length() - 1; j >= 0; j--) {
                reverseWord = reverseWord + word.charAt(j);
            }
            reversedString = reversedString + reverseWord + " ";
        }
        return reversedString;
    }

    //encode -w without delimiter and with delimiter
    // Reverses characters in each word.

    private static String w_opt(String fcontent, String w_delimiter) {

        //if there is no w delimiter, then reverse characters in words separated by whitespace.
        //.split is the whitespace
        if (w_delimiter.length() == 0) {

            String[] words = fcontent.split(" ");
            String reversedString = "";
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                String reverseWord = "";
                for (int j = word.length() - 1; j >= 0; j--) {
                    reverseWord = reverseWord + word.charAt(j);
                }
                reversedString = reversedString + reverseWord + " ";
            }
            return reversedString;

        }
        else
        {
            //if there is a delimiter, need to add the character here.
            String[] words = fcontent.split(w_delimiter);
            String reversedString = "";
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                String reverseWord = "";
                for (int j = word.length() - 1; j >= 0; j--) {
                    reverseWord = reverseWord + word.charAt(j);
                }
                reversedString = reversedString + reverseWord + " ";
            }
            return reversedString;
        }
    }

    //If r_opt, remove the character from the string. Non alphabetic characters are unaffected.
    private static String r_opt(String fcontent, String r_delimiter) {
        String result = "";
        for (int i = 0; i < fcontent.length(); i++) {
            String character = String.valueOf(fcontent.charAt(i));
            if (!r_delimiter.contains(character.toUpperCase()) && !r_delimiter.contains(character.toLowerCase()))
                result = result.concat(character);
        }
        return result;
    }
    private static String k_opt(String fcontent, String k_delimiter) {
        String result = "";
        for (int i = 0; i < fcontent.length(); i++) {
            String character = String.valueOf(fcontent.charAt(i));
            if (k_delimiter.contains(character.toUpperCase()) || k_delimiter.contains(character.toLowerCase()))
                result = result.concat(character);
        }
        return result;
    }

    //PERFECT: If -c, it will reverse the capitalization of lowercase to uppercase and uppercase to lowercase.
    private static String c_opt(String fcontent)
    {
        char[] chars = fcontent.toCharArray();
        for (int i = 0; i < chars.length; i++)
        {
            char c = chars[i];
            if (Character.isUpperCase(c))
            {
                chars[i] = Character.toLowerCase(c);
            }
            else if (Character.isLowerCase(c))
            {
                chars[i] = Character.toUpperCase(c);
            }
        }
        return String.valueOf(chars);
    }

    private static void writefile(String result, String filename)
    {
        try (PrintWriter out = new PrintWriter(filename))
        {
            out.print(result);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void usage()
    {
        System.err.println("Usage: encode [-w [string]] [-r string | -k string] [-c] <filename>");
    }

    private static void file_not_found()
    {
        System.err.println("File Not Found");
    }

}
