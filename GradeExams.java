package edu.wit.dcsn.comp1050.lab02 ;

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
 *         The answer key will be provided as a single-dimensional array of char, where each
 *         array element corresponds with a question on the exam.  Students' answers will be
 *         provided as a multi(two)-dimensional array of char where the outer array represents
 *         a student and the inner array represents that student's answers, corresponding to the
 *         answer key.
 *         <p>
 *         Mark an answer correct if the corresponding element of the answer key matches.
 *         <p>
 *         The methods are package visible to enable your ExamGrader to access them.
 */
public final class GradeExams{
    /**
     * prevent instantiating this class
     */
    private GradeExams(){}
    
    /**
     * Test driver
     * 
     * @param args -unused-
     */
    public static void main(String[] args){
        char[] answerKey =              "DBDCCDAEAD".toCharArray() ;  // the correct answers
        char[][] studentsAnswers =    {                             // each student's answers
                                        "ABACCDEEAD".toCharArray(),
                                        "DBABCAEEAD".toCharArray(),
                                        "EDDACBEEAD".toCharArray(),
                                        "CBAEDCEEAD".toCharArray(),
                                        "ABDCCDEEAD".toCharArray(),
                                        "BBECCDEEAD".toCharArray(),
                                        "BBACCDEEAD".toCharArray(),
                                        "EBECCDEEAD".toCharArray(),
                                        "EBECCDEEADX".toCharArray(),
                                        "EBECCDEEA".toCharArray()
                                      } ;
        // descriptive introductory text
        displayIntro() ;

        // grade the exams: display the results on a per-student basis and summary statistics
        gradeAllExams(answerKey, studentsAnswers);
    }
    
    /**
     * Utility method to display identifying information and a description of what it does
     */
    static void displayIntro(){
        System.out.println("Comp 1050, Computer Science II") ;
        System.out.println("Lab 2: Grade Exams") ;
        System.out.println("Due: M 2/19/2018") ;
        System.out.println() ;
        System.out.println("This test driver grades multiple-choice exams given fixed (hard-coded)") ;
        System.out.println("student answers and answer key.  Information about each student's") ;
        System.out.println("exam results are displayed, followed by a summary of the performance") ;
        System.out.println("of all students on a per-question basis.") ;
    }
    
    /**
     * Utility method to grade all exams and display summary statistics
     * 
     * @param answerKey the set of correct answers
     * @param allStudentsAnswers the answers for all of the students
     */
    static void gradeAllExams(char[] answerKey, char[][] allStudentsAnswers){
    	System.out.println();
    	System.out.println("-------------");
    	System.out.printf("Student Grades:%n");
    	System.out.println();
    	
    	String o = (answerKey.length==1) ? "" : "s";
    	System.out.printf("The exam contains %d question%s%n", answerKey.length, o);
    	
    	
    	System.out.print("The correct answers are: ");
        for(int i=0;i<answerKey.length;i++){
         	System.out.print(Character.toUpperCase(answerKey[i]));
         }
        System.out.println();
        System.out.println();
        
        int[] correct = new int[answerKey.length];
        int graded = 0;
         for(int m=0;m<allStudentsAnswers.length;m++){
        	 if(gradeAnExam(m, answerKey, allStudentsAnswers[m], correct)){
        		 graded++;
        	 }
         }
        System.out.println();
    	System.out.println("-------------");
    	System.out.println("Exam Statistics: ");
    	System.out.println();
    	reportStatistics(correct, allStudentsAnswers.length, graded);
    }
    
    /**
     * Utility method to grade a single exam
     * 
     * @param studentId the student's identification number
     * @param answerKey the set of correct answers
     * @param studentAnswers the student's answers
     * @param correctAnswerCount counters for each question to track number of times answered 
     * 		correctly
     * @return true indicates that we were able to grade the exam; false means the number of 
     * 		questions according to the answer key does not match the number of questions answered 
     * 		by the student - does not reflect whether the student 'passed'
     */
    static boolean gradeAnExam(int studentId, char[] answerKey, char[] studentAnswers, int[] correctAnswerCount){
    	double correct = 0.0;	//counts the individual student's correct answers
    	
    	if(studentAnswers.length==answerKey.length){
    		for(int i=0;i<studentAnswers.length;i++){
        		if(Character.toUpperCase(studentAnswers[i])==Character.toUpperCase(answerKey[i])){
        			correctAnswerCount[i]++;
        			correct++;
        		}else{
        			studentAnswers[i] = '-';
        		}
        	}
    		
    		double percent = (correct/answerKey.length)*100;
    		
    		if(percent<10.0){	//ensure correct spacing for percentages
    			System.out.printf("Student %d answered %.0f correct (  %.0f%%): ", studentId+1, correct, percent);
    		}else if(percent<100.0){
    			System.out.printf("Student %d answered %.0f correct ( %.0f%%): ", studentId+1, correct, percent);
    		}else{
    			System.out.printf("Student %d answered %.0f correct (%.0f%%): ", studentId+1, correct, percent);
    		}
    		
    		
        	for(int m=0;m<studentAnswers.length;m++){
        		System.out.print(studentAnswers[m]);
        	}
    	}
    	
    	if(studentAnswers.length!=answerKey.length){
    		String m = (studentAnswers.length==1) ? "" : "s";
    		System.out.printf("Error: Student %d answered %d question%s: ", studentId+1, studentAnswers.length, m);
    		
    		for(int p=0;p<studentAnswers.length;p++){
        		System.out.print(studentAnswers[p]);
        	}
    		System.out.println();
    		return false;
    	}
    	System.out.println();
    	return true;
    }
    
    /**
     * Utility method to display the statistics for an exam
     * 
     * @param correctAnswerCount number of correct responses per question
     * @param studentCount number of students that took the exam
     * @param gradedExamCount number of exams that were actually graded (<= studentCount)
     */
    static void reportStatistics(int[] correctAnswersCount, int studentCount, int gradedExamCount){
    	String s = (studentCount==1) ? "" : "s";
    	System.out.printf("%d student%s took the exam, %d exams were graded.%n", studentCount, s, gradedExamCount);
    	System.out.println();
    	System.out.println("Question # Times Correct");
    	
    	double divide = gradedExamCount*1.0;	//convert gradedExamCount into a double
    	
    	for(int i=0;i<correctAnswersCount.length;i++){
    		String space = (i+1<10) ? "     " : "    ";	//ensure correct spacing
    		System.out.printf("%s%d             %d ", space, i+1, correctAnswersCount[i]);
    		
    		if(Math.floor((correctAnswersCount[i]/divide)*100)<10.0){	//ensure correct spacing with percentages
    			System.out.printf("(  %.0f%%)%n", Math.floor((correctAnswersCount[i]/divide)*100));
    		}else if(Math.floor((correctAnswersCount[i]/divide)*100)<100.0){
    			System.out.printf("( %.0f%%)%n", Math.floor((correctAnswersCount[i]/divide)*100));
    		}else if(Math.floor((correctAnswersCount[i]/divide)*100)>=100.0){
    			System.out.printf("(%.0f%%)%n", Math.floor((correctAnswersCount[i]/divide)*100));
    		}
    	}
    }
}