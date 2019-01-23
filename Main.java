package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        FileOutputStream f = new FileOutputStream(new File("myObjects2.bin"));
        ObjectOutputStream o = new ObjectOutputStream(f);
        List<QuizQuestion> quizQuestionArrayList = new ArrayList<>();
        String prompt = "Ile ludzi mieszka w Polsce?";
        List<String> options = new ArrayList<>();
        options.add("12 milionów");
        options.add("13 milionów");
        options.add("23 miliony");
        options.add("38 miliony");
        String answer = "d";
        String section = "Geografia";
        int points = 1;
        QuizQuestion quizQuestion = new QuizQuestion(prompt, options,answer,section,points);
        quizQuestionArrayList.add(quizQuestion);
        o.writeObject(quizQuestionArrayList);
        o.close();
    }
}
