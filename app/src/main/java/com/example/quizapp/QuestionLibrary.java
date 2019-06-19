package com.example.quizapp;


import android.util.Log;

import com.example.quizapp.Service.CallBackData;
import com.example.quizapp.Service.fetch_data;

import org.json.JSONArray;
import org.json.JSONObject;

public class QuestionLibrary  implements CallBackData  {
    JSONObject data ;
    JSONArray arrj;
    fetch_data ft = new fetch_data("http://192.168.1.16:3000/cauhoi",null);
    public QuestionLibrary() {
    ft.delegate = this;
    ft.execute();
    }
    @Override
    public void onReceiveData(String data) {



    }
    private String mQuestions1 [] = new String[4];
    private String mQuestions [] = {
            "An ninh mạng là gì?",
             "Tại sao lại phải dùng giám sát mạng",
             "Đâu là một phần của an toàn mạng",
                "Vật chất không ____"


    };


    private String mChoices [][] = {
            {"Một hệ thống", "System Security", "Flower"},
                        {"Vì sự an toàn", "Kiểm tra hệ thống", "Vi phạm policy"},
                      {"Toàn vẹn", "Sẵn dùng", "A & B"},
                       {"Mất đi", "Lo lắng", "Sẵn dùng"}

    };



    private String mCorrectAnswers[] = {"Kiểm tra hệ thống", "A & B", "Flower", "Lo lắng"};




    public String getQuestion(int a) {
        String question = mQuestions[a];
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
