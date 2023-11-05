package service;

import dao.ClassDAO_jy;
import dto.Teacher_jy;
import main.HuruTMain;

import java.io.BufferedReader;
import java.io.InputStreamReader;

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

    // 수업 조회: 본인이 담당하는 수업으로 제한
    public void getClasses(Teacher_jy teacherJy) throws Exception {

    }

    // 수업 수정
    public void updateClass (Teacher_jy teacherJy) throws Exception {
        /*
        System.out.println("**************************************************\n");
        System.out.println("수정할 수업의 번호를 입력해 주세요.");
        System.out.println("**************************************************\n");
        // 수업 번호 입력
        System.out.print("수업 번호 : ");
        int classIdx = Integer.parseInt(br.readLine());

        // 입력받은 수업 번호가 선생님이 담당하고 있는 수업이 맞는지 확인

        // 수업
        classDAOJy.updateClass();
        */
    }

    // 수업 삭제
}