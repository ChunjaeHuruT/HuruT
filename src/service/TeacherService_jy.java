package service;

import dao.TeacherDAO_jy;
import dto.Teacher_jy;
import main.HuruTMain;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TeacherService_jy {
    TeacherDAO_jy teacherDAOJy;
    BufferedReader br;
    Teacher_jy teacherJy; // 로그인된 선생님 객체
    ClassService_jy classServiceJy;

    // 생성자
    public TeacherService_jy(){
        teacherDAOJy = new TeacherDAO_jy();
        br = new BufferedReader(new InputStreamReader(System.in));
        teacherJy = HuruTMain.getTeacherJy();
    }

    // 수업 관리
    public void manageClasses(int inputByTeacherClassManage, ClassService_jy classServiceJy){
        // 1.수업 등록하기 2.수업 수정하기 3.수업 삭제하기 4.질문 보러 가기
        switch (inputByTeacherClassManage){
            // 1. 수업 등록하기
            case 1:
                //classServiceJy.insertClass();
                break;
            // 2.수업 수정하기
            case 2:

                break;
            // 3.수업 삭제하기
            case 3:

                break;
            // 4.질문 보러 가기
            case 4:

                break;
            // 예외 입력
            default:

        }
    }

}
