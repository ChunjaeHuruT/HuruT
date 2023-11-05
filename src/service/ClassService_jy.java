package service;

import dao.ClassDAO_jy;
import dto.Class_jy;
import dto.Teacher_jy;
import main.HuruTMain;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ClassService_jy {
    ClassDAO_jy classDAOJy;
    BufferedReader br;
    Teacher_jy teacherJy;

    // 생성자
    public ClassService_jy(){
        classDAOJy = new ClassDAO_jy();
        br = new BufferedReader(new InputStreamReader(System.in));
        teacherJy = HuruTMain.getTeacherJy();
    }

    public void init (){}

    // 수업 등록
    public void insertClass (String className, int price, int difficulty) {
        // 수업 등록
        classDAOJy.insertClass(className, teacherJy.getTeacherIdx(), price, difficulty);
    }

    // 담당 수업 모두 조회
    public ArrayList<Class_jy> getClasses() throws Exception {
        ArrayList<Class_jy> classesList = classDAOJy.getClasses(teacherJy.getTeacherIdx());
        return classesList;
    }

    // 수업 수정
    public void updateClass (int classIdx, String className, int price, int difficulty) throws Exception {
        // 수업 수정
        classDAOJy.updateClass(classIdx, className, price, difficulty, teacherJy.getTeacherIdx());
    }

    // 수업 삭제
    public void deleteClass (int classIdx){
        // 수업 삭제
        classDAOJy.deleteClass(classIdx);
    }
}
