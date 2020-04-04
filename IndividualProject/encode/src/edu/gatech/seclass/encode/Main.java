package edu.gatech.seclass.encode;

import java.io.*;
import java.nio.file.*;

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
        String content = "";
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
                //read the file to content. try until you get it right.
                try
                {
                    Path file_path = Paths.get(filename);

                    if(Files.exists(file_path))
                    {
                        content = readstring(filename);
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
                    result = empty_opt(content);
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
                                result = w_opt(content,"");
                            else
                                result = w_opt(result,"");
                        }
                        else
                        {
                            //if there is a delimiter, then push w_delimiter to method.
                            w_delimiter = args[i+1];
                            if (result.length() == 0)
                                result = w_opt(content,w_delimiter);
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
                                result = r_opt(content,r_delimiter);
                            else
                                result = r_opt(result,r_delimiter);
                            i++;
                        }
                        else
                        {
                            if (result.length() == 0)
                                result = content;
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
                                result = k_opt(content,k_delimiter);
                            else
                                result = k_opt(result,k_delimiter);
                            i++;
                        }
                        else
                        {
                            if (result.length() == 0)
                                result = content;
                        }
                    }

                    //option 5. c opt.
                    else if(arg.equals("-c"))
                    {
                        if (result.length() == 0)
                            result = c_opt(content);
                        else
                            result = c_opt(result);
                    }
                    i++;

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
        String content = "";
        try
        {
            Path file_path = Paths.get(filename);
            byte[] encoded = Files.readAllBytes(file_path);
            content = new String(encoded, "UTF-8");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return content;
    }

    //If no opt, use -w as default. it will reverse words.
    private static String empty_opt(String content)
    {
        String[] words = new String[0];
        words = content.split(" ");
        String reversedString = "";
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            String reverseWord = "";
            for (int j = word.length() - 1; j >= 0; j--) {
                reverseWord = reverseWord + word.charAt(j);
            }
            reversedString = reversedString + reverseWord + " ";
        }
        return String.valueOf(words);
    }

    //encode -w without delimiter and with delimiter
    // Reverses characters in each word.

    private static String w_opt(String content, String w_delimiter) {

        //if there is no w delimiter, then reverse characters in words separated by whitespace.
        //.split is the whitespace
        String[] words = new String[0];
        if (w_delimiter.length() == 0) {
            words = content.split(" ");
            String reversedString = "";
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                String reverseWord = "";
                for (int j = word.length() - 1; j >= 0; j--) {
                    reverseWord = reverseWord + word.charAt(j);
                }
                reversedString = reversedString + reverseWord + " ";
            }
            return String.valueOf(words);
        }
        else
        {
            //if there is a delimiter, need to add the character here.
            words = content.split(w_delimiter);
            String reversedString = "";
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                String reverseWord = "";
                for (int j = word.length() - 1; j >= 0; j--) {
                    reverseWord = reverseWord + word.charAt(j);
                }
                reversedString = reversedString + reverseWord + " ";
            }
            return String.valueOf(words);
        }
    }

    //r_opt. if specified, it will remove the selected characters.
    private static String r_opt(String content, String r_content) {

        String result = null;
        while (content.length() != 0) {
            result = content.replaceAll(content, r_content);
        }
        return String.valueOf(result);
    }

    //k_opt. if specified, it will keep the selected characters.
    private static String k_opt(String content, String k_content) {
        String result = null;
        while (content.length() != 0) {
            result = content.replaceAll(content, k_content);
        }
        return String.valueOf(result);
    }

    //If -c, it will reverse the capitalization of lowercase to uppercase and uppercase to lowercase.
    private static String c_opt(String content)
    {
        char[] chars = content.toCharArray();
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
