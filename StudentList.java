import java.io.*;
import java.text.*;
import java.util.*;

public class StudentList {
    public static String students;
    public static Contants constant = new Contants();
    public static String studentName[];

    public static void reader() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(constant.fileName)));
            students = reader.readLine();
            studentName = students.split(constant.split);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void writer(String lastUpdate) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(constant.fileName, false));
            bufferedWriter.flush();
            bufferedWriter.write(lastUpdate);
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {

//		Check arguments
        if (args.length != 1) {
            System.out.println(constant.invalid);
        }
        if (args[0].equals(constant.showNames)) {
            System.out.println(constant.loadingData);
            reader();
            for (String name : studentName) {
                System.out.println(name);
            }
            System.out.println(constant.loadedData);
        } else if (args[0].equals(constant.randomName)) {
            System.out.println(constant.loadingData);
            reader();
            Random random = new Random();
            System.out.println(studentName[random.nextInt(studentName.length)]);
            System.out.println(constant.loadedData);
        } else if (args[0].contains(constant.addName)) {
            System.out.println(constant.loadingData);
            reader();
            try {
                String t = args[0].substring(1);
                DateFormat dateFormat = new SimpleDateFormat(constant.dateFormat);
                writer(students + ", " + args[0].substring(1) + constant.lastUpdate + dateFormat.format(new Date()));
            } catch (Exception e) {
                System.out.println(e);
            }
            System.out.println(constant.loadedData);
        } else if (args[0].contains(constant.query)) {
            System.out.println(constant.loadingData);
            reader();
            for (int idx = 0; idx < studentName.length; idx++) {
                if (studentName[idx].equals(args[0].substring(1))) {
                    System.out.println(constant.found);
                    break;
                }
            }
            System.out.println(constant.loadedData);
        } else if (args[0].contains(constant.countWords)) {
            System.out.println(constant.loadingData);
            reader();
            System.out.println(studentName.length + constant.wordsFound);
            System.out.println(constant.loadedData);
        } else {
            System.out.println(constant.invalid);
        }
    }
}