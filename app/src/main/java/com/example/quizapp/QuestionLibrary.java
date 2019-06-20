package com.example.quizapp;


import android.util.Log;

import com.example.quizapp.Service.CallBackData;
import com.example.quizapp.Service.fetch_data;

import org.json.JSONArray;
import org.json.JSONObject;

public class QuestionLibrary  implements CallBackData  {
    JSONObject data = new JSONObject();
    fetch_data ft ;
     String mQuestions [] = new String[50];
    String mChoices [][] = new String[50][3];
    String mCorrectAnswers[] = new String[50];
//    private String mChoices [][] = {
//            {"Một hệ thống", "System Security", "Flower"},
//            {"Vì sự an toàn", "Kiểm tra hệ thống", "Vi phạm policy"},
//            {"Toàn vẹn", "Sẵn dùng", "A & B"},
//            {"Mất đi", "Lo lắng", "Sẵn dùng"}
//
//    };
//
//
//
//    private String mCorrectAnswers[] = {"Kiểm tra hệ thống", "A & B", "Flower", "Lo lắng"};



    public QuestionLibrary(int idMon) {
        ft = new fetch_data("http://192.168.1.16:3000/cauhoi/" + idMon,null);
    ft.delegate = this;
    ft.execute();

    }

    @Override
    public void onReceiveData(String onData) throws Exception {
        this.data = new JSONObject(onData);
        Log.d("123",onData);
        JSONArray arrQ = new JSONArray(data.get("questions").toString());
        JSONArray arrC = new JSONArray(data.get("answers").toString());
        JSONArray arrA = new JSONArray(data.get("correctAnswers").toString());
        Log.d("123",arrQ.toString());
        mQuestions[0] = arrQ.optString(0);
        for(int i = 1;i < 50; i++)  {
            mQuestions[i] = arrQ.optString(i%(arrQ.length()));
            mCorrectAnswers[i] = arrA.optString(i%(arrA.length()));
            for (int j = 0 ; j<  3; j++) {
                mChoices[i][j] = arrC.optJSONArray(i%(arrQ.length())).optString(j);
            }



        }


    }



    public String getQuestion(int a) {
        String question = mQuestions[a + 1];
        return question;
    }

    public int getLength(){
        return mQuestions.length;
    }

    public String getChoice1(int a) {
        String choice0 = mChoices[a][0];
        return choice0;
    }


    public String getChoice2(int a) {
        String choice1 = mChoices[a][1];
        return choice1;
    }

    public String getChoice3(int a) {
        String choice2 = mChoices[a][2];
        return choice2;
    }

    public String getCorrectAnswer(int a) {
        String answer = mCorrectAnswers[a];
        return answer;
    }


}
