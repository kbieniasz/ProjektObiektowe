package com.company;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class MyWindow extends JFrame  implements ActionListener {


    private JButton bAddQuestion, bDisplayQuestions, bGetExam;
    public QuestionCreator questionCreator;
    public QuestionDisplayer questionDisplayer;
    public ExamDisplayer examDisplayer;




    public MyWindow() throws IOException {
        setSize (300,300);
        setTitle("Super program");
        setLayout(null);

        bAddQuestion = new JButton("Dodaj nowe pytanie");
        bAddQuestion.setBounds(50, 75, 150, 20);
        add(bAddQuestion);
        bAddQuestion.addActionListener(this);

        bDisplayQuestions = new JButton("Zobacz bazę pytań");
        bDisplayQuestions.setBounds(50,95,150,20);
        add(bDisplayQuestions);
        bDisplayQuestions.addActionListener(this);

        bGetExam = new JButton("Wybierz test do pobrania");
        bGetExam.setBounds(50,115,150,20);
        add(bGetExam);
        bGetExam.addActionListener(this);

        /*
        FileOutputStream f = new FileOutputStream(new File("myObjects2.bin"));
        ObjectOutputStream o = new ObjectOutputStream(f);
        List<QuizQuestion> quizQuestionArrayList = new ArrayList<>();
        o.writeObject(quizQuestionArrayList);
        o.close();
        */







    }
    public static void main(String[] args) throws IOException {
        MyWindow mW = new MyWindow();
        mW.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mW.setVisible(true);



    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object eventSource = e.getSource();
        if(eventSource == bAddQuestion)
        {
            if(questionCreator==null)
            {questionCreator = new QuestionCreator(this); }
            questionCreator.setVisible(true);
            // questionCreator.setFocus();
        }
        if(eventSource == bDisplayQuestions)
        {
            try {
                questionDisplayer = new QuestionDisplayer(this);
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            questionDisplayer.setVisible(true);
        }
        if(eventSource==bGetExam)
        {
            System.out.println("tu");
            if(examDisplayer==null)
            {
                try {
                    examDisplayer = new ExamDisplayer(this);
                    System.out.println("utaj");
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
                examDisplayer.setVisible(true);


            }
        }

    }
}
