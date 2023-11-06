package service;

import dao.ClassDAO_jy;
import dao.LessonDAO_jy;
import dto.Lesson_jy;
import dto.Teacher_jy;
import main.HuruTMain;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LessonService_jy {
    LessonDAO_jy lessonDAOJy;
    BufferedReader br;

    // 생성자
    public LessonService_jy(){
        lessonDAOJy = new LessonDAO_jy();
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    // 학습 생성
    public void insertLesson (String lessonName, int classId, int lessonsSeconds){
        lessonDAOJy.insertLesson(lessonName, classId, lessonsSeconds);
    }

    // 학습 조회
    // classIdx 수업의 학습 전체 리턴
    public ArrayList<Lesson_jy> getLessons(int classId) {
        ArrayList<Lesson_jy> lessonsList = lessonDAOJy.getLessons(classId);
        return lessonsList;
    }

    // 학습 삭제
    public void deleteLesson(int lessonIdx){
        lessonDAOJy.deleteLesson(lessonIdx);
    }

    // 학습의 수업 idx 조회
    // lessonIdx를 가진 classIdx 리턴
    public int getClassIdx(int lessonIdx){
        int classIdx = lessonDAOJy.getClassIdx(lessonIdx);
        return classIdx;
    }
}
