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
public class Answer {
    private int aId;
    private String aContent;
    private boolean aStatus;
    private boolean aSelected;
    private int qId;
    
    
    public int getAId(){
        return aId;
        
    }
    public void setAId(int aId) throws AnswerException{
        if (aId <= 0){
            throw new AnswerException("Answer ID must be a positive integer");
        }else {
            this.aId = aId;
        }
    }
    public String getAContent(){
        return aContent;
    }
    public void setAcontent(String aContent ) throws AnswerException {
        if(aContent.equals("")){
            throw new AnswerException("Answer content can't be empty");
        }else 
            this.aContent = aContent;
    }
    public boolean getAStatus(){
        return aStatus;
    }
    public  void setAStatus(boolean aStatus){
        this.aStatus = aStatus;
    }
    public  boolean isASeclected(){
        return aSelected;
    }
    public void setASelected(boolean aSelected){
        this.aSelected = aSelected;
        
    }
    public int getQId(){
        return qId;
    }
    public void setQId(int qId) throws AnswerException{
        if (qId <= 0){
            throw new AnswerException("Question ID must be a postive integer");
            
        }else{
            this.qId = qId;
        }
    }
    public Answer (int aId, String aContent, boolean aStatus, int qId) throws AnswerException{
        this.setAId(aId);
        this.setAcontent(aContent);
        this.setAStatus(aStatus);
        this.setASelected(false);
        this.setQId(qId);
    }
    public  boolean isCorrect(){
        return this.aStatus == this.aSelected;
    }
    @Override 
    public String toString(){
        return this.aContent + "\n";
    }
}
