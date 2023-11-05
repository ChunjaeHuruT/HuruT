package main;

import dto.Teacher_jy;
import factory.MyBatisMapperFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import service.ClassService_jy;
import service.StudentService;
import service.TeacherService_jy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.Scanner;

public class HuruTMain {
    
    // 필요한 서비스를 멤버로 받아오기

    // jy
    // 선생님 관련 서비스 객체 (수업 관리, 학습 관리, 마이페이지, 로그아웃)
    static TeacherService_jy teacherServiceJy;
    // 수업 관련 서비스 객체 (수업 등록/삭제/수정, 질문 보러 가기)
    static ClassService_jy classServiceJy;
    
    // jh
    
    
    // sz

    
    // ys

    
    // he
    


    // 필요한 기능을 메소드로 만들기

    // jy

    
    // jh
    
    
    // sz

    
    // ys

    
    // he
    


    
    
    public static void main(String[] args) throws Exception {
        // 메인 메소드. 프로그램을 시작하기 위해 이 메인 메소드를 실행시켜야 한다.

        // 사용할 실행 코드를 작성하기

        // jy
        // 입력 스트림
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 테스트용 teacher 객체
        Teacher_jy teacherJy = new Teacher_jy(1, "jy@gmail.com", "이지연", "12345", LocalDateTime.now(), 0, 0);

        // 선생님 로그인 후 welcome 화면
        // 1. 수업 관리 2. 학습 관리 3. 마이페이지 4. 로그아웃
        System.out.println("**************************************************\n");
        System.out.println(teacherJy.getTeacherName()+" 선생님 반갑습니다.");
        System.out.println("**************************************************\n");
        System.out.println("이용할 메뉴를 선택해 주세요.");
        System.out.println("\n1. 수업 관리 2. 학습 관리 3. 마이페이지 4. 로그아웃");

        // 선생님 관련 서비스 객체
        teacherServiceJy = new TeacherService_jy();
        // 수업 관련 서비스 객체
        classServiceJy = new ClassService_jy();
        // 로그인 상태 여부
        boolean logIn = true;

        while(logIn) {  // 로그아웃 되기 전까지 반복
            // 선생님이 welcome 화면에서 입력한 메뉴 번호
            int inputByTeacherInWelcome = Integer.parseInt(br.readLine());

            switch (inputByTeacherInWelcome) {
                case 1:
                    // 1. 수업 관리
                    // 담당 수업 목록 출력

                    // 수업 관리 서브 메뉴
                    // 1. 수업 등록하기 2. 수업 수정하기 3. 수업 삭제하기 4. 질문 보러 가기
                    teacherServiceJy.manageClasses();
                    classServiceJy.insertClass(teacherJy);
                    break;
                case 2:
                    // 2. 학습 관리
                    //classServiceJy.updateClass(teacherJy);
                    break;
                case 3:
                    // 3. 마이페이지
                    break;
                case 4:
                    // 4. 로그아웃
                    // logIn = false;
                    break;
                default:
                    System.out.println("잘못 입력하였습니다.");
                    System.out.println("메뉴를 다시 입력해 주세요.");
            }
        }

        // jh


        // sz


        // ys


        // he


        
        
    }
    
}
