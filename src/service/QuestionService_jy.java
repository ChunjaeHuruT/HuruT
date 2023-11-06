package service;

import dao.ClassDAO_jy;
import dao.QuestionDAO_jy;
import dto.Question_jy;
import dto.Teacher_jy;
import main.HuruTMain;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class QuestionService_jy {
    ClassDAO_jy classDAOJy;
    BufferedReader br;
    Teacher_jy teacherJy;
    QuestionDAO_jy questionDAOJy;

    // 생성자
    public QuestionService_jy(){
        questionDAOJy = new QuestionDAO_jy();
        br = new BufferedReader(new InputStreamReader(System.in));
        teacherJy = HuruTMain.getTeacherJy();
    }

    // 질문 검색: 특정 수업에(classIdx) 관한 질문만
    public ArrayList<Question_jy> getQuestions(int classIdx){
        ArrayList<Question_jy> questionsList = questionDAOJy.getQuestions(classIdx);

        return questionsList;
    }
}
