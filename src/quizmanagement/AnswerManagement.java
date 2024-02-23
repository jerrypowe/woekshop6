/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizmanagement;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author Tam Pham
 */
public class AnswerManagement {
    private String A_FILE;
    private int numberOfAnswer;
    private ArrayList<Answer> answers;
    
    
    
    public AnswerManagement(String A_FILE ) throws AnswerException{
        if (A_FILE.equals("")){
            throw new AnswerException("The URL of answer data file can't be empty!");
            
        }else{
            this.A_FILE = A_FILE;
            this.answers = new ArrayList<Answer>();
            this.numberOfAnswer = 0;
        }
    }
    public void loadAnswers()throws IOException, AnswerException{
        File aFile = new File(A_FILE);
        
        if (!aFile.exists()){
            aFile.createNewFile();
            System.out.println("The data file answers.txt is not exits."+"Creating new data file answers.txt..."+"Done!");
            this.numberOfAnswer = 0;
        }else{
            System.out.println("\nThe data file answer.txt is found. "+"Data of answer is loading...");
            try (BufferedReader br = new BufferedReader(new FileReader(A_FILE))){
                String qId, aId, aContent, aStatus;
                this.numberOfAnswer =Integer.parseInt(br.readLine());
                for (int i = 0; i < this.numberOfAnswer; i++) {
                    aId = br.readLine();
                    aContent = br.readLine();
                    aStatus  = br.readLine();
                    qId      = br.readLine();
                    this.answers.add(new Answer(Integer.parseInt(aId),aContent, Boolean.parseBoolean(aStatus),Integer.parseInt(qId)));
                
                }
            }
            System.out.println("Done! ["+ this.numberOfAnswer+"answers]");
        }
}
    public int addAnswer(String aContent, boolean aStatus , int qId) throws AnswerException{
        this.answers.add(new Answer(++this.numberOfAnswer,aContent,aStatus,qId));
        return this.numberOfAnswer;
    }
    public  int findAnswer(int aId){
        for (int i = 0; i < this.answers.size(); i++) {
            Answer a = this.answers.get(i);
            if  (a.getAId() == aId) {
                return i;
            }
        }
        return -1;
    }
    public Answer getAnswer(int aId) {
        int idx = this.findAnswer(aId);
        if (idx == -1) {
            return null ;
            
        } else {
            return this.answers.get(idx);
        }
        
      
    }
      public void saveAnswers() throws IOException {
            FileWriter fw = new FileWriter(new File(A_FILE),false);
            
            try {
                System.out.println("\n Answers is saving into data file answers.txt...");
                fw.append(String.valueOf(this.numberOfAnswer)+"\n");
                for (int i = 0; i < this.numberOfAnswer; i++) {
                    int aId         = this.answers.get(i).getAId();
                    String aContent = this.answers.get(i).getAContent();
                    boolean aStatus = this.answers.get(i).getAStatus();
                    int qId         = this.answers.get(i).getQId();
                    
                    
                    fw.append(String.valueOf(aId) + "\n");
                    fw.append(aContent + "\n");
                    fw.append(String.valueOf(aStatus)+"\n");
                    fw.append(String.valueOf(qId)+"\n");
                }
            }finally {
                fw.close();
                System.out.println("Done! ["+ this.numberOfAnswer + " answers]");
            }
        }
      public ArrayList<Answer> getAnswers(int qId, boolean isShuffle) {
          ArrayList<Answer> aList = new ArrayList<Answer>();
          for (int i = 0; i < this.answers.size(); i++) {
               
              Answer a = this.answers.get(i);
              if (a.getQId() == qId) {
                  aList.add(a);
              }
          }
          int [] idx = new int [aList.size()];
          for (int i = 0; i < aList.size(); i++) {
              idx[i] = i ;
          }
          
          if (isShuffle) {
              int newIdx, tmp;
              Random ran = new Random();
              for (int i = 0; i < aList.size(); i++) {
                  newIdx = ran.nextInt(aList.size());
                  tmp = idx[i];
                  idx[i] = idx[newIdx];
                  idx[newIdx] = tmp;
              }
          }
          ArrayList<Answer> result = new ArrayList<Answer>();
          for (int i = 0; i < aList.size(); i++) {
              result.add(aList.get(idx[i]));
          }
          return result;
      }
            
}
