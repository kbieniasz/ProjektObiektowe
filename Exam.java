package com.company;

import java.io.Serializable;
import java.util.List;

public class Exam implements Serializable {
    private String examName;
    private List<QuizQuestion> quizQuestionList;
    private String studentName;
    private double overallREsult;

    public Exam(String examName, List<QuizQuestion> quizQuestionList) {
        this.examName = examName;
        this.quizQuestionList = quizQuestionList;
    }

    public String getExamName() {
        return examName;
    }

    public List<QuizQuestion> getQuizQuestionList() {
        return quizQuestionList;
    }

    public String toStringWritingVersion()
    {
        String s = this.examName + '\n';
        for(int i=0; i<this.quizQuestionList.size();i++)
        {
            QuizQuestion q = quizQuestionList.get(i);
            s=s+q.toStringWritingVersion() + '\n';
        }


        return s;
    }
}
