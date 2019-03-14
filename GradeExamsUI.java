package edu.wit.dcsn.comp1050.lab02 ;

import java.util.Scanner ;

/**
 * Comp 1050-05 - Computer Science II
 * Lab 2 - Grade Multiple Choice Exams
 * Alyza Diaz Rodriguez
 * Due: M 2/19/2018
 */

/**
 * @author Alyza Diaz Rodriguez
 * @version 1.0.0 2018-02-13
 *
 *         Create a class to grade student responses to a multiple choice test.
 *         You will create a single-dimensional array for the answer key and a
 *         multi(two)-dimensional array to hold the students’ answers.
 *         Prompt the user to enter the answer key, number of students taking the
 *         exam, and the answers for each student.
 *         <ul>
 *         <li>You must use the provided stub methods.
 *         <li>Complete all Javadoc comment blocks.
 *         <li>Properly document your code with line and/or non-Javadoc block comments.
 *         <li>Call the methods in GradeExams to grade the exams.
 *         </ul> 
 */
public final class GradeExamsUI{
    /**
     * prevent instantiating this class
     */
    private GradeExamsUI() {}
   
    /**
     * Application to grade multiple-choice exams
     *  
     * @param args -unused-
     */
    public static void main(String[] args){
        // descriptive introductory text
        displayIntro() ;
        
        // access the keyboard so user can provide the answer key and students' answers
        Scanner keyboard = new Scanner( System.in ) ;
        
        // get the answer key
        char[] answerKey = getAnswerKey( keyboard ) ;
        
        // get the students' answers
        char[][] studentsAnswers = getStudentsAnswers( keyboard, answerKey ) ;
        
        // release Scanner/keyboard resources
        keyboard.close();
        
        // grade the exams: displays the results on a per-student basis and summary statistics
        GradeExams.gradeAllExams( answerKey, studentsAnswers );
    }
    
    /**
     * Utility method to display identifying information and a description of what it does
     */
    private static void displayIntro(){
        System.out.println( "Comp 1050, Computer Science II" ) ;
        System.out.println( "Lab 2: Grade Exams" ) ;
        System.out.println(  ) ;
        System.out.println( "This class grades multiple-choice exams given user-supplied" ) ;
        System.out.println( "student answers and answer key.  Information about each student's" ) ;
        System.out.println( "exam results are displayed, followed by a summary of the performance" ) ;
        System.out.println( "of all students on a per-question basis." ) ;
    }
    
    /**
     * 
     * @param input 
     * @return the user-supplied answer key
     */
    private static char[] getAnswerKey(Scanner input){
    	System.out.println();
    	System.out.print("Enter the answer key as a single string: ");
    	char[] answerKey = input.nextLine().toUpperCase().toCharArray();
    	
        return answerKey ;
    }
    
    /**
     * 
     * @param answerKey
     * @return the user-supplied student answers
     */
    private static char[][] getStudentsAnswers(Scanner input, char[] answerKey){
    	System.out.print("Enter the number of students taking the exam: ");
    	int students = input.nextInt();
    	
    	char[][] studentsAnswers = new char[students][answerKey.length];
    	System.out.println("Enter the students' answers, each as a single string: ");
    	for(int i=0;i<students;i++){
    		studentsAnswers[i] = input.next().toCharArray();
    	}
    	
        return studentsAnswers ;
    }    
}