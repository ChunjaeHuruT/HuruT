package service;

import dao.TeacherDAO_jy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TeacherService_jy {
    TeacherDAO_jy teacherDAOJy;
    BufferedReader br;

    // 생성자
    public TeacherService_jy(){
        teacherDAOJy = new TeacherDAO_jy();
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    // 수업 관리
    public void manageClasses(){
        // 담당 수업 리스트 출력

        // 1.수업 등록하기 2.수업 수정하기 3.수업 삭제하기 4.질문 보러 가기

    }

}
