/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizmanagement;

/**
 *
 * @author Tam Pham
 */
public class Question {
    private int qId;
    private double qMark;
    private String qContent;
    
    public  void setQId(int qId) throws QuestionException {
        if ( qId <= 0){
            throw new QuestionException("Question ID must be a positive integer");
        }else{
            this.qId = qId;
        }
   
    }
     public  void setQMark (double qMark) throws QuestionException{
        if (qMark  < 0 || qMark > 10 ){
            throw new QuestionException("Question mark must be from 0 to 10");
            
        }else {
            this.qMark = qMark;
        }
    }
     public void setQContent (String qContent ) throws QuestionException{
         if (qContent.equals("")){
             throw new QuestionException("Question content can't be empty");
             
         }else {
             this.qContent = qContent;
         }
     }
     public int getQId(){
         return qId;
         
     }
     public double getQMark(){
         return qMark;
     }
     public String getQContent(){
         return qContent;
     }
     public Question (int qId, double qMark, String qContent) throws QuestionException{
         this.setQId(qId);
         this.setQId(qId);
         this.setQContent(qContent);
}
     @Override
     public String toString(){
         return this.qContent + "\n";
     }

}
