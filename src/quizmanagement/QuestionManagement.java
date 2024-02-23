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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
public class QuestionManagement {
    private String Q_FILE ;
    private int numberOfQuestion;
    private ArrayList<Question> questions;
    private AnswerManagement am;
    
    
    
    
    public QuestionManagement (String Q_FILE, AnswerManagement am) throws QuestionException{
        if (Q_FILE.equals("")) {
            throw new QuestionException("The URL of answer data file can't be empty!");
            
        }else{
            this.Q_FILE = Q_FILE;
            
            this.questions = new ArrayList<Question>();
            
            this.numberOfQuestion = 0 ;
            this.am = am;
        }
    }
    public  void loadQuestion() throws IOException, QuestionException{
        File qFile = new File(Q_FILE);
        
        if (!qFile.exists()) {
            qFile.createNewFile();
            System.out.println("The data file question.txt is not exits. "+" Creating new data file questions.txt"+"Done!");
            this.numberOfQuestion = 0;
        }else {
            System.out.println("\n The data file questions.txt is found. "+"Data of questions is loading...");
            BufferedReader br = new BufferedReader(new FileReader(Q_FILE));
            try {
                String qId, qContent, qmark ;
                this.numberOfQuestion = Integer.parseInt(br.readLine());
                for (int i = 0; i < this.numberOfQuestion; i++) {
                    qId      = br.readLine();
                    qContent = br.readLine();
                    qmark    = br.readLine();
                    
                    this.questions.add(new Question( Integer.parseInt(qId), Double.parseDouble(qmark),qContent));
                    
                            
                }
            } finally {
                br.close();
                
            }
            System.out.println("Done! [" + this.numberOfQuestion + " questions]");
        }
    }
    public  int getSize(){
        return this.numberOfQuestion;
    }
    public  int addQuestion( double qMark, String qContent ) throws QuestionException {
        this.questions.add(new Question(++this.numberOfQuestion, qMark, qContent));
        return this.numberOfQuestion;
    }
    
    
    
    
    public  int findQuestion(int qId) {
        for (int i = 0; i < this.questions.size(); i++) {
            Question q = this.questions.get(i);
            if( q.getQId() == qId) {
                return i ;
            }
        }
        return -1;
    }
    
    
    
    public Question getQuestion(int qId) {
        int idx  = this.findQuestion(qId);
        if ( idx == -1 ) {
            return null ;
            
        }else {
            return this.questions.get(idx);
        }
    }
    
    
    
    public void saveQuestions () throws IOException {
        FileWriter fw = new FileWriter(new File(Q_FILE),false) ;
        try {
            System.out.println("\n Question is saving into data file questions.txt...");
            fw.append(String.valueOf(this.numberOfQuestion)+"\n");
            for (int i = 0; i < this.numberOfQuestion; i++) {
                int qId          = this.questions.get(i).getQId();
                String qContent  = this.questions.get(i).getQContent();
                double qMark     = this.questions.get(i).getQMark();
                
                
                fw.append(String.valueOf(qId) + "\n");
                fw.append(qContent + "\n");
                fw.append(String.valueOf(qMark) + "\n");
            }
        }finally {
            fw.close();
            System.out.println("Done! [" + this.numberOfQuestion + " question]");
        }
    }
    
    
    
    public boolean isQuestionsCorrect (int qId, ArrayList<Answer> answers) {
        boolean isCorrect = true ;
        for (int i = 0; i < answers.size(); i++) {
            isCorrect = isCorrect && answers.get(i).isCorrect();
        }
        return isCorrect;
    }
    
    
    public String showQuestion ( int qId, boolean isShuffle) {
        Question q = getQuestion(qId);
        ArrayList<Answer> aList = am.getAnswers(qId, isShuffle);
        
        String str = "";
        
        str += q.toString();
        
        char aNo = 'a';
        for (int i = 0; i < aList.size(); i++) {
            str += "   " + aNo + ".  " +aList.get(i).toString();
        }
        return str;
    }
    
    
    public  void showQuestionsBank() {
        int qNo = 1;
        for (int i = 0; i < this.questions.size(); i++) {
            Question q  = this.questions.get(i);
            System.out.println(qNo + ".   " +showQuestion(q.getQId(), false));
        }
    }
    
    
    
    public ArrayList<Question> getQuestionBank( int qNumber , boolean isShuffle) {
        ArrayList <Question> qList = new ArrayList<Question>();
        
        
        int [] idx = new int [questions.size()];
        for (int i = 0; i < questions.size(); i++) {
            idx[i] = i;
        }
        if (isShuffle) {
            int newIdx, tmp ;
            Random ran = new Random();
            for (int i = 0; i < questions.size(); i++) {
                newIdx = ran.nextInt(questions.size());
                
                
                tmp = idx[i];
                idx[i] = idx[newIdx];
                idx[newIdx] = tmp ;
                
            }
        }
        for (int i = 0; i < qNumber; i++) {
            qList.add(questions.get(idx[i]));
        }
        return qList;
    }
}
