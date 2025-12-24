package Task2;

import java.util.Arrays;
import java.util.Scanner;

public class StudentGradeCalculator {
    public static void arracorrect(String[] subjectnames, int[] subjectmarks, int totalsubjects){
        System.out.println("If you found above information correct, please verify (yes/no)");
        String infocorrect=takescan.nextLine();
        if (infocorrect.equals("yes")){
            System.out.println("Thanks for verification of the correct information");
        }else if(infocorrect.equals("no")){
            System.out.println("Enter Integer value associated with the incorrect information");
            int incorrectrow=takescan.nextInt();
            takescan.nextLine();
            System.out.println("is the correction with subject name or it's marks input must be (name or mark)");
            String correction =takescan.nextLine();
            if(correction.equals("name")){
                System.out.println("Enter corrected subject name:");
                subjectnames[incorrectrow-1]=takescan.nextLine();
                for(int i=0;i<totalsubjects;i++){
                   System.out.println((i+1)+"." +subjectnames[i] +"="+subjectmarks[i]);
                }
                arracorrect(subjectnames, subjectmarks, totalsubjects);
            }else if(correction.equals("mark")){
                System.out.println("Enter corrected subject mark:");
                subjectmarks[incorrectrow-1]=takescan.nextInt();
                takescan.nextLine();
                for(int i=0;i<totalsubjects;i++){
                   System.out.println((i+1)+"." +subjectnames[i] +"="+subjectmarks[i]);
                }
                arracorrect(subjectnames, subjectmarks, totalsubjects);
            }else{
                System.out.println("There must be mistake while typing please retype (name or mark)");
                arracorrect(subjectnames, subjectmarks, totalsubjects);
            }
        }else{
            System.out.println("There must be mistake while typing please retype (yes/no)");
            arracorrect(subjectnames, subjectmarks, totalsubjects);
        }
    }
    private static final Scanner takescan = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("i am yash welcome you to Student Grade Calculator \nEach subject has total mark 100");
        String[] gradesys={
            "A+: 75% & Above (Distinction)",
            "A: 60% - 74% (First Division)",
            "B: 45% - 59% (Second Division)",
            "C: 35% - 44% (Pass Grade)",
            "D: Below 35% (Failed)"
        };
        for (int g=0; g<gradesys.length; g++){
            System.out.println(gradesys[g]);
        }
        System.out.println("Enter total number of subjects");
        int totalsubjects =takescan.nextInt();
        takescan.nextLine();
        String[] subjectnames= new String[totalsubjects];
        int[] subjectmarks= new int[totalsubjects];
        for(int i=0;i<=subjectnames.length-1;i++){
            System.out.println("Enter subject name (single subject name only):");
            String subnames= takescan.nextLine();
            subjectnames[i]=subnames;
            System.out.printf("Enter subject marks (which belongs to %s ):%n", subnames);
            int submarks= takescan.nextInt();
            takescan.nextLine();
            subjectmarks[i]=submarks;
        }
        for(int i=0;i<totalsubjects;i++){
            System.out.println((i+1)+"."+subjectnames[i] +"="+subjectmarks[i]);
        }
        arracorrect(subjectnames, subjectmarks, totalsubjects);
        int totalsummark=(Arrays.stream(subjectmarks).sum());
        System.out.println("total marks "+totalsummark+" out of "+totalsubjects*100);
        double avgPercentage=(totalsummark+0.0)/(totalsubjects+0.0);
        System.out.printf("Average Percentage %.2f out of 100 \n", avgPercentage);
        if(avgPercentage>=75){
            System.out.println("Congratulation You excel this exam with A+ (distinction)");
        }else if(60<=avgPercentage && avgPercentage<75){
            System.out.println("Congratulation You excel this exam with A (first division)");
        }else if(45<=avgPercentage && avgPercentage<60){
            System.out.println("Congratulation You excel this exam with B (second division)");
        }else if(35<=avgPercentage && avgPercentage<45){
            System.out.println("Congratulation You excel this exam with C (pass grade)");
        }else{
            System.out.println("We encourage your hardwork you got D (failed) in this exam");
        }
        takescan.close();
    }
}
