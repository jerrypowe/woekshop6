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
public class QuestionException extends Exception {
    public QuestionException(String message){
        super(message);
    }
    
    @Override 
    public String getMessage(){
        return "QuestionException: "+ super.getMessage();
    }
}
