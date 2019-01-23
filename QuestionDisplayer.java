package com.company;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class QuestionDisplayer extends JFrame implements ActionListener {

    JScrollPane scrollPane, scrollPane2;
    JTextArea pytania;
    JTextArea tPreview;
    JList listWithQuestions;
    JTextArea okienko;
    JButton addToTest, showSelectedQuestions;
    List<QuizQuestion> quizQuestionArrayList = new ArrayList<>();
    JList listWithSelectedQuestions = new JList();
    List <Integer> numerki = new ArrayList<>();
    JLabel testLabel;
    JTextArea exampleTest;
    DefaultListModel model = new DefaultListModel();
    JList listTestowa = new JList(model);
    List<QuizQuestion> selectedQuizQuestions = new ArrayList<>();
    JTextArea wybranePytania;
    private JLabel lExamName;
    public JTextArea tExamName;
    JButton createNewExam;

    public QuestionDisplayer (JFrame owner) throws IOException, ClassNotFoundException {
        setSize(1300, 700);
        setTitle("Baza pytań");
        setLayout(null);

        FileInputStream f0 = new FileInputStream((new File("myObjects2.bin")));
        ObjectInputStream o0 = new ObjectInputStream(f0);
        if(!o0.toString().equals(null))quizQuestionArrayList =(List<QuizQuestion>) o0.readObject();


        //pytania = new JTextArea();
        //scrollPane = new JScrollPane(pytania);
        //add(scrollPane);
        //scrollPane.setBounds(50,50,300,400);
        ///pytania.append(quizQuestionArrayList.toString());


        //List<QuizQuestion> quizQuestionArrayList= new ArrayList<>();



        List <String> promptsList = new ArrayList<>();
        for (int i = 0; i<quizQuestionArrayList.size();i++)
        {
            promptsList.add(quizQuestionArrayList.get(i).getPrompt());
        }

        listWithQuestions = new JList(promptsList.toArray());
        scrollPane = new JScrollPane(listWithQuestions);
        scrollPane.setBounds(50,50,300,400);
        add(scrollPane);
        //listWithQuestions.setBounds(450, 40,300,400);
        //add(listWithQuestions);
        listWithQuestions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tPreview= new JTextArea();
        tPreview.setBounds(400, 50,300,200);
        add(tPreview);
        listWithQuestions.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if(!arg0.getValueIsAdjusting())
                {
                    int i = listWithQuestions.getAnchorSelectionIndex();
                    QuizQuestion quizQuestionToDisplay = quizQuestionArrayList.get(i);
                    tPreview.setText(quizQuestionToDisplay.toString());
                }
            }
        });

        addToTest = new JButton("Dodaj do pytanie do testu");
        addToTest.setBounds(400, 350,200,150);
        add(addToTest);
        addToTest.addActionListener(this);

        listWithSelectedQuestions = new JList<>(selectedQuizQuestions.toArray());
        scrollPane2 = new JScrollPane(listWithSelectedQuestions);
        scrollPane2.setBounds(550,50,300,400);
        //add(scrollPane2);


        wybranePytania = new JTextArea();
        wybranePytania.setBounds(750,50,300,200);
        add(wybranePytania);


        lExamName = new JLabel("Wprowadź nazwę testu");
        lExamName.setBounds(750, 275, 300,50);
        add(lExamName);


        tExamName = new JTextArea("");
        tExamName.setBounds(750,325,300,50);
        add(tExamName);


        createNewExam = new JButton("Stwórz nowy test");
        createNewExam.setBounds(750, 400,200,150);
        add(createNewExam);
        createNewExam.addActionListener(this);



        /*

        addToTest = new JButton("Dodaj do pytanie do testu");
        addToTest.setBounds(350, 450,300,200);
        add(addToTest);
        addToTest.addActionListener(this);


        showSelectedQuestions = new JButton("Pokaż wybrane pytania");
        showSelectedQuestions.setBounds(50, 500, 100, 100);
        showSelectedQuestions.addActionListener(this);
        add(showSelectedQuestions);


        //listWithSelectedQuestions =  new JList();
        listWithSelectedQuestions.setBounds(50,50,300,400);
        add(listWithSelectedQuestions);


        //listWithQuestions.getAnchorSelectionIndex();
        //listWithQuestions
        // listWithQuestions = new JList((Vector) quizQuestionArrayList);
        // listWithQuestions.setBounds(450, 40,300,400);
        //for (int i = 0; i<quizQuestionArrayList.size();i++)
        //{
        //   QuizQuestion q = quizQuestionArrayList.get(i);
        //   listWithQuestions.add
        //}
        //add(listWithQuestions);
        //testLabel = new JLabel("czy cos się pojawi");
        //testLabel.setBounds(1,1,100,100);
        //add(testLabel);


        exampleTest = new JTextArea();
        scrollPane2 = new JScrollPane(exampleTest);
        add(scrollPane2);
        scrollPane2.setBounds(50,50,300,100);


        add(listTestowa);
        listTestowa.setBounds(50,300, 300, 200);

        */
    }






    @Override
    public void actionPerformed(ActionEvent e) {



        Object eventSource = e.getSource();
        if(eventSource == addToTest) {
            int i = listWithQuestions.getAnchorSelectionIndex();
            QuizQuestion selectedQuizQuestion = quizQuestionArrayList.get(i);
            selectedQuizQuestions.add(selectedQuizQuestion);
            List <String> selectedQuizQuestionPrompts = new ArrayList<>();
            listWithSelectedQuestions.removeAll();
            wybranePytania.setText("");
            System.out.println("treter");
            for(int j=0; j<selectedQuizQuestions.size();j++)

            {

                QuizQuestion q = selectedQuizQuestions.get(j);
                selectedQuizQuestionPrompts.add(q.getPrompt());
                JComponent prompt = new JLabel(q.getPrompt());
                //listWithSelectedQuestions.add(prompt);
                System.out.println("xdxd");
                wybranePytania.append('\n'+ q.getPrompt());
            }
        }

        if(eventSource == createNewExam)
        {
            String examName = tExamName.getText();
            Exam exam = new Exam (examName,selectedQuizQuestions);

            ObjectOutputStream zapisObiektu = null;
            try {
                List<Exam> tmpExamList= new ArrayList<>();
                FileInputStream f0 = new FileInputStream(new File ("myExams.bin"));
                System.out.println("Wypisdaasdsdsdsd");
                ObjectInputStream o0 = new ObjectInputStream(f0);
                tmpExamList =(List<Exam>) o0.readObject();

                FileOutputStream f = new FileOutputStream(new File("myExams.bin"));
                ObjectOutputStream o = new ObjectOutputStream(f);
                tmpExamList.add(exam);
                o.writeObject(tmpExamList);
                o.close();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }

            dispose();
        }

        /*
        }
        if(eventSource == showSelectedQuestions)
        {
            List <String> qaz = new ArrayList<>();


            List<QuizQuestion> selected = new ArrayList<>();

            for(int i = 0; i<numerki.size();i++)
            {
                selected.add(quizQuestionArrayList.get(numerki.get(i)));
                qaz.add(selected.get(i).getPrompt());
            }
            //listWithSelectedQuestions = new JList(qaz.toArray());
            //JList tmp = new JList(qaz.toArray());
            //listWithSelectedQuestions = tmp;
            //listWithSelectedQuestions.updateUI();
            //testLabel.setText(qaz.toString());
            exampleTest.setText(qaz.toString());
            for(int i = 0; i<qaz.size(); i++)
            {
                model.addElement(qaz.toString());
            }

        }
        */

    }
}
