package main;

import dao.StudentDAO;
import dao.TeacherDAO;
import service.LoginService_ys;
import service.SignUpService_ys;

import javax.swing.text.Style;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class HuruTMain_ys {
    
    // 필요한 서비스를 멤버로 받아오기

    // jy

    
    // jh
    
    
    // sz

    
    // ys
    // 회원가입 서비스 객체
    static SignUpService_ys signUpService_ys = new SignUpService_ys();
    // 로그인 서비스 객체
    static LoginService_ys loginService_ys = new LoginService_ys();
    // 입력 스트림
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // he
    


    // 필요한 기능을 메소드로 만들기

    // jy

    
    // jh
    
    
    // sz

    
    // ys

    // 로그인한 회원 정보 값의 변수
    public static int loginStudentIdx; // 로그인한 학생의 idx값
    public static String loginStudentNickName; // 로그인한 학생의 닉네임
    public static int loginTeacherIdx; // 로그인한 선생님의 idx값
    public static String loginTeacherName; // 로그인한 선생님의 이름
    

    // 학생/선생님 회원종류 선택화면
    public static void checkUserView() {
        System.out.println();
        System.out.println("*****************************************");
        System.out.println("          회원 종류를 입력 해주세요.");
        System.out.println("*****************************************");
        System.out.println("1.학생 2.선생님");

    }

    // 첫 시작화면
    public static void startView() {
        System.out.println();
        System.out.println("*****************************************");
        System.out.println("        후루티에 오신 것을 환영합니다");
        System.out.println("*****************************************");
        System.out.println("1.로그인 2.회원가입 3.프로그램 종료");
    }

    // 로그인 입력화면
    public static void loginView() {
        System.out.println();
        System.out.println("*****************************************");
        System.out.println("        아이디와 비밀번호를 입력해주세요");
        System.out.println("*****************************************");
    }

    // 회원가입 입력화면
    public static void signUpView() {
        System.out.println();
        System.out.println("*****************************************");
        System.out.println("            회원가입을 시작합니다.");
        System.out.println("           아래 항목을 입력해주세요.");
        System.out.println("*****************************************");
    }

    // 학생 로그인 기능
    public static void studentLogin() throws Exception {
        // 로그인 화면
        loginView();
        // 사용자 입력값
        System.out.print("아이디: ");
        String studentEmail = br.readLine();
        System.out.print("비밀번호: ");
        String studentPassword = br.readLine();

        // DB에 id/pwd 정보가 있는지 확인 기능
        int studentLogin = loginService_ys.studentLogin(studentEmail, studentPassword);

        // DB에 존재하면 로그인 성공
        if (studentLogin == 1) {
            System.out.println("로그인 성공");

            // 로그인 한 학생 정보를 loginStudentValue에 저장
            StudentDAO loginStudentValue = loginService_ys.getLoginStudent(studentEmail);
            // 로그인 정보를 넘겨줄 변수에 저장
            loginStudentIdx = loginStudentValue.getStudentIdx();
            loginStudentNickName = loginStudentValue.getStudentNickname();


            /*
             * 학생 메인 페이지로 이동 메소드 추가
             * */

            // DB에 존재하지 않으면 로그인 실패
        } else {
            System.out.println("로그인 실패");
            studentLogin();
        }
    }

    public static void teacherLogin() throws Exception {
        // 로그인 화면 출력
        loginView();
        // 사용자 입력값
        System.out.print("아이디: ");
        String teacherEmail = br.readLine();
        System.out.print("비밀번호: ");
        String teacherPassword = br.readLine();

        // DB에 id/pwd 정보가 있는지 확인 기능
        int teacherLogin = loginService_ys.teacherLogin(teacherEmail, teacherPassword);

        // DB에 존재하면 로그인 성공
        if (teacherLogin == 1) {
        System.out.println("로그인 성공");

            // 로그인 한 선생님 정보를 loginStudentValue에 저장
            TeacherDAO loginTeacherValue = loginService_ys.getLoginTeacher(teacherEmail);
            // 로그인 정보를 넘겨줄 변수에 저장
            loginTeacherIdx = loginTeacherValue.getTeacherIdx();
            loginTeacherName = loginTeacherValue.getTeacherName();


        /*
        *  선생님 메인 페이지로 이동 메소드 추가
        * */

        // DB에 존재하지 않으면 로그인 실패
        } else {
        System.out.println("로그인 실패");
        teacherLogin();
        }
    }

    // 학생 회원가입 기능
    public static void studentSignUp() throws Exception {
        // 회원가입 화면 출력
        signUpView();
        // 사용자 입력값
        System.out.print("아이디: ");
        String studentEmail = br.readLine();
        // 아이디 중복 확인. 중복 시 회원가입 재실행
        if(signUpService_ys.studentEmailDuplicationCheck(studentEmail) == 1) {
            System.out.println("동일한 아이디가 존재합니다.");
            studentSignUp(); // 회원가입 재실행
        }
        System.out.print("비밀번호: ");
        String studentPassword = br.readLine();
        System.out.print("비밀번호확인: ");
        String studentPassword2 = br.readLine();
        // 비밀번호 확인 검사. 틀렸을 때 회원가입 재실행
        if(!Objects.equals(studentPassword, studentPassword2)) {
            System.out.println("비밀번호 확인이 틀렸습니다.");
            studentSignUp(); // 회원가입 재실행
        }
        System.out.print("닉네임: ");
        String studentNickname = br.readLine();
        if(signUpService_ys.studentNicknameDuplicationCheck(studentNickname) == 1) {
            System.out.println("동일한 닉네임이 존재합니다.");
            studentSignUp(); // 회원가입 재실행
        }

        // DB에 회원정보 등록
        signUpService_ys.studentSignUp(studentEmail, studentPassword, studentNickname);
        System.out.println("회원가입에 성공하였습니다.");
    }

    // 선생님 회원가입 기능
    public static void teacherSignUp() throws Exception {
        // 회원가입 화면 출력
        signUpView();
        // 사용자 입력값
        System.out.print("아이디: ");
        String teacherEmail = br.readLine();
        // 아이디 중복 확인. 중복 시 회원가입 재실행
        if(signUpService_ys.teacherEmailDuplicationCheck(teacherEmail) == 1) {
            System.out.println("동일한 아이디가 존재합니다.");
            teacherSignUp(); // 회원가입 재실행
        }
        System.out.print("비밀번호: ");
        String teacherPassword = br.readLine();
        System.out.print("비밀번호확인: ");
        String teacherPassword2 = br.readLine();
        // 비밀번호 확인 검사. 틀렸을 때 회원가입 재실행
        if(!Objects.equals(teacherPassword, teacherPassword2)) {
            System.out.println("비밀번호 확인이 틀렸습니다.");
            teacherSignUp(); // 회원가입 재실행
        }
        System.out.print("성함: ");
        String teacherName = br.readLine();

        // DB에 회원정보 등록
        signUpService_ys.studentSignUp(teacherEmail, teacherPassword, teacherName);
        System.out.println("회원가입에 성공하였습니다.");
    }

    
    // he
    


    
    
    public static void main(String[] args) throws Exception {
        // 메인 메소드. 프로그램을 시작하기 위해 이 메인 메소드를 실행시켜야 한다.

        // 사용할 실행 코드를 작성하기

        // jy


        // jh


        // sz


        // ys
        // 입력 스트림
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 프로그램 시작 변수
        boolean start = true;

        // 프로그램 종료 전까지 반복
        while(start) {
            // 시작 화면 출력
            startView();
            // 사용자 입력 변수 선언
            int inputByStartView = Integer.parseInt(br.readLine());

            switch (inputByStartView) {
                // 1.로그인
                case 1:
                    // 회원 종류 선택 화면
                    checkUserView();
                    // 사용자 입력 변수 선언
                    int inputByCheckUserView = Integer.parseInt(br.readLine());

                    // 1.학생 로그인
                    if (inputByCheckUserView == 1) {
                        studentLogin();
                        // 2.선생님 로그인
                    } else if (inputByCheckUserView == 2) {
                        teacherLogin();
                        // 잘못된 입력
                    } else {
                        System.out.println("잘못 입력하셨습니다.");
                    }
                    break; // 수정필요: while()문 이동 -> case1 으로 이동

                // 2. 회원가입
                case 2:
                    // 회원 종류 선택 화면
                    checkUserView();
                    // 사용자 입력 변수 선언
                    int inputByCheckUserView2 = Integer.parseInt(br.readLine());

                    // 1.학생 회원가입
                    if (inputByCheckUserView2 == 1) {
                        studentSignUp();
                    // 2.선생님 회원가입
                    } else if (inputByCheckUserView2 == 2) {
                        teacherSignUp();
                    // 잘못된 입력
                    } else {
                        System.out.println("잘못 입력하셨습니다.");
                    }
                    break; // 수정필요: while()문 이동 -> case1 으로 이동

                    // 3.프로그램 종료
                case 3:
                    System.out.println("프로그램을 종료합니다.");
                    start = false;
                    break;
                // 잘못된 입력
                default:
                    System.out.println("잘못 입력하셨습니다.");
                    System.out.println("메뉴를 다시 입력해 주세요.");
            }
        }

        // he


        
        
    }
    
}
