package com.example.braitrainer_sharedpreferences;

import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class Riddle {
    public static final String TAG = "Riddle";
    ArrayList<Integer> randomOrdAnsArrList;

    private int num1;
    private int num2;
    private int correctAnswer;
    private int wrongAnswer1;
    private int wrongAnswer2;
    private int wrongAnswer3;
    private boolean operation;

    public Riddle(int num1, int num2) {
        this.num1 = num1;
        this.num2 = num2;
        randomOrdAnsArrList = new ArrayList<>();

        correctAnswer =  generateRandomCorrectResponse(num1, num2);
        setWrongAnswers(correctAnswer);
        randomiseAnswerOrder();
    }

    public Riddle() {
        num1 = Riddle.getRandomNumberInDiapason(0,99);
        num2 = Riddle.getRandomNumberInDiapason(0,99);
        randomOrdAnsArrList = new ArrayList<>();

        correctAnswer =  generateRandomCorrectResponse(num1, num2);
        setWrongAnswers(correctAnswer);
        randomiseAnswerOrder();
    }

    public static int getRandomNumberInDiapason(int min, int max){
        int result;
        result = (int) Math.floor(Math.random() *(max - min + 1) + min);
        return result;
    }

    private void setWrongAnswers(int correctAnsw){
        wrongAnswer1 = randomOperation(correctAnsw);
        wrongAnswer2 = randomOperation(correctAnsw);
        wrongAnswer3 = randomOperation(correctAnsw);
    }
    //chooses randomly what the operation is going to be plus or minus
    private int randomOperation(int num){
        int result;

        boolean minus = false;
        boolean factor = new Random().nextBoolean();

        int randNum = Riddle.getRandomNumberInDiapason(1,10);
        if(factor==minus){
            result =num - randNum;
        }else {
            result =num + randNum;
        }
        return result;
    }

    //returns the sum or the remainder of the subtraction
    private int generateRandomCorrectResponse(int num1, int num2){
        int result;
        boolean minus = false;
        operation = new Random().nextBoolean();
        if(operation==minus){
            result =num1 - num2;
        }else {
            result =num1 + num2;
        }
        return result;
    }

    public ArrayList<Integer> randomiseAnswerOrder(){
        Set<Integer> LHS = new LinkedHashSet<>();
        while (LHS.size()<4){
            int index = Riddle.getRandomNumberInDiapason(0,3);
            LHS.add(index);
        }
        Integer[] randomIndexArray =  new Integer[4];
        LHS.toArray(randomIndexArray);

        Integer[] answersInRandomOrderArr = new Integer[4];
        answersInRandomOrderArr[randomIndexArray[0]] = getCorrectAnswer();
        answersInRandomOrderArr[randomIndexArray[1]] = getWrongAnswer1();
        answersInRandomOrderArr[randomIndexArray[2]] = getWrongAnswer2();
        answersInRandomOrderArr[randomIndexArray[3]] = getWrongAnswer3();

        for (Integer answer: answersInRandomOrderArr) {
            randomOrdAnsArrList.add(answer);
        }

        Log.d(TAG, ""+this.toString());
        Log.d(TAG, "randomiseAnswerOrder: "+ randomOrdAnsArrList.toString());
        Log.d(TAG, "randomiseAnswerOrder: "+ randomOrdAnsArrList.size());


        return  randomOrdAnsArrList;
    }
    public int getNum1() {return num1;}
    public int getNum2() {return num2;}
    public int getCorrectAnswer() {return correctAnswer;}
    public int getWrongAnswer1() {return wrongAnswer1;}
    public int getWrongAnswer2() {return wrongAnswer2;}
    public int getWrongAnswer3() {return wrongAnswer3;}
    public boolean getOperation() {return operation;}//returns true if plus
    public ArrayList<Integer> getRandomOrdAnsArrList() {
        return randomOrdAnsArrList;
    }

    @Override
    public String toString() {
        return "Riddle{" +
                "randomisedOrderAnswersArrList=" + randomOrdAnsArrList +
                ", num1=" + num1 +
                ", num2=" + num2 +
                ", correctAnswer=" + correctAnswer +
                ", wrongAnswer1=" + wrongAnswer1 +
                ", wrongAnswer2=" + wrongAnswer2 +
                ", wrongAnswer3=" + wrongAnswer3 +
                ", operation=" + operation +
                '}';
    }
}
