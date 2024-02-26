/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizmanagement;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Tam Pham
 */
public class QuizManagement {
    public static AnswerManagement am;
    public static QuestionManagement qm;
    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) throws QuestionException {
        try {
         am = new AnswerManagement("src/data/answers.txt");
         am.loadAnswers();
         
         qm = new QuestionManagement("src/data/questions.txt", am);
         Scanner cin = new Scanner(System.in);
         int func;
        while (true) {
       
            System.out.println("---------------QUIZ MANEGEMENT---------------");
            System.out.println("1. Add question.");
            System.out.println("2. Show question bank.");
            System.out.println("3. Create Quiz.");
            System.out.println("4. Quit.");
            System.out.println("Please select a function: ");
            func  = cin.nextInt();
            cin.nextLine();
            String strUserEnterd = "";
          switch (func){
              case 1 : {
                  System.out.println("----QUIZ MANAGEMENT [ADD NEW QUESTION]----");
                  
                  String qContent = "";
                  double qMark = 0.0;
                  
                  do {
                      System.out.println("Please enter content of question: ");
                      qContent = cin.nextLine();
                      if (qContent.equals("")) {
                          System.out.println("Error: Question content can't be empty!");
                      }
                  }while (qContent.equals(""));
                  
                  do {
                      System.out.println("Please enter mark of question: ");
                      qMark = cin.nextDouble();cin.nextLine();
                      if (qMark < 0 || qMark > 10) {
                          System.out.println("Error: Question mark must be from 0 to 10!");
                      }
                  }while (qMark < 0 || qMark > 10);
                  
                  int qId =qm.addQuestion(qMark, qContent);
                  
                  System.out.println("Your question is created!");
                  System.out.println("+++ [ADD ANSWERS FOR QUESTION] +++");
                  int aNo = 0;
                  do {
                      aNo++;
                      System.out.println("...Answer "+ aNo +"...");
                      String    aContent = "";
                      boolean aStatus = false;
                      
                      
                      do {
                          System.out.println("Please enter content of answer " + aNo +": ");
                          aContent = cin.nextLine();
                          if (aContent.equals("")) {
                              System.out.println("Error: Answer content cann't be empty!");
                          }
                      }while (aContent.equals(""));
                      
                      
                      do {
                          System.out.println("Is this answer True or False? (True/False) ");
                          strUserEnterd = cin.nextLine();
                          if (strUserEnterd.equals("True")) {
                              aStatus = true ;
                              
                          }else if (strUserEnterd.equals("False")) {
                              aStatus = false;
                          }else {
                              
                              System.out.println("Error: You must type 'True' or 'False'!");
                              
                          }
                      }while (!(strUserEnterd.equals("True"))  || strUserEnterd.equals("False"));
                  
                  
                  am.addAnswer(qContent, true, qId);
                  
                  do {
                      System.out.println("Do you want to add more answer? (Yes/No) ");
                      strUserEnterd = cin.nextLine();
                      if ((!(strUserEnterd.equals("Yes") || strUserEnterd.equals("No")))){
                          System.out.println("Error: You must type 'Yes' or 'No'!" );
                      }
                  }while (!(strUserEnterd.equals("Yes") || strUserEnterd.equals("No")));
                  
              }while (strUserEnterd.equals("Yes"));
                  break;
                  
//                  System.out.println("+++ The test is genarating...");
//                  ArrayList<Question> qList = qm.getQuestionBank(qNumbers, isRandom);
//                  
//                          System.out.println("Please enter content of question: ");                 
//                  String qcontent =sc.nextLine();
//                  
//                  
//                  System.out.println(" Please enter mark of question: ");
//                  double mark = sc.nextDouble();
//                  
//                  Question qm = new Question(0, mark, qcontent);
//                
              }
              case 2:{
                  System.out.println("---- QUIZ MANAGEMNET [QUESTION BANK] ("+qm.getSize()+ " questions)-----");
                  qm.showQuestionsBank();
              }break;
              case 3:{
          }     int totalQuestionNumbers = qm.getSize();
                int qNumbers             = 0;
                boolean isRandom    = false;
                double mark     = 0.0;
                double totalMark =0.0;
                int correctCount = 0;
                System.out.println("----QUIZ MANAGEMENT [EXAMINATION] (" + totalQuestionNumbers +" question)----");
                do  {
                    System.out.println("How many question of the test: ");
                    qNumbers = cin.nextInt();
                    cin.nextLine();
                    if (qNumbers <1 || totalQuestionNumbers < qNumbers) {
                        System.out.println("Number of question must be from 1 to "+totalQuestionNumbers);
                    }
                }while (qNumbers < 1 || totalQuestionNumbers < qNumbers);
                
                do {
                    System.out.println("Do you want tgo shuffle the test ? (True/False) ");
                    strUserEnterd = cin.nextLine();
                    if (strUserEnterd.equals("True")) {
                        isRandom = false ;
                        
                    }else {
                        System.out.println("Error : You must type 'True' or 'False'!");
                    }
                }while (!(strUserEnterd.equals("True") || strUserEnterd.equals("False")));
                
                System.out.println("+++ The test is generating...");
                ArrayList<Question> qList = qm.getQuestionBank(qNumbers, isRandom);
                System.out.println("Done! +++");
              
              }
   }
}
   }
}
}
