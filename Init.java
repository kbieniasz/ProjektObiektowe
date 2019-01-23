package com.company;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Init {
    public static void main(String[] args) throws IOException {
        // write your code here
        FileOutputStream f = new FileOutputStream(new File("myExams.bin"));
        ObjectOutputStream o = new ObjectOutputStream(f);
        List<Exam> examArrayList = new ArrayList<>();
        o.writeObject(examArrayList);
        o.close();
    }
}
