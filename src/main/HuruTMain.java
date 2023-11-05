package main;

import dto.Class_jy;
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
import java.util.ArrayList;
import java.util.Scanner;

public class HuruTMain {
    
    // 필요한 서비스를 멤버로 받아오기

    // jy
    // 선생님 관련 서비스 객체 (수업 관리, 학습 관리, 마이페이지, 로그아웃)
    static TeacherService_jy teacherServiceJy;
    // 수업 관련 서비스 객체 (수업 등록/삭제/수정, 질문 보러 가기)
    static ClassService_jy classServiceJy;
    // 로그인된 선생님 객체. 로그인 전에는 null
    private static Teacher_jy teacherJy = null;
    public static Teacher_jy getTeacherJy(){
        return teacherJy;
    }
    // 입력 스트림
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    // jh
    
    
    // sz

    
    // ys

    
    // he
    


    // 필요한 기능을 메소드로 만들기

    // jy
    // 수업 등록
    public static void insertClass() throws Exception {
        System.out.println("**************************************************\n");
        System.out.println("아래의 정보를 입력하여 수업을 등록해 주세요.\n(난이도는 1=쉬움 / 2=보통 / 3=어려움 입니다.)\n");
        System.out.println("**************************************************\n");
        // 수업 정보 입력
        System.out.print("수업 제목 : ");
        String className = br.readLine();
        System.out.print("가격 : ");  // [리팩토링] 숫자 입력 예외 발생 시, 다시 입력 받기
        int price = Integer.parseInt(br.readLine());
        System.out.print("난이도 : "); // [리팩토링] 1-3 예외 발생 시, 다시 입력 받기
        int difficulty = Integer.parseInt(br.readLine());

        // 수업 등록
        classServiceJy.insertClass(className, price, difficulty);

        System.out.println("\n등록을 완료하였습니다.");
        System.out.println("**************************************************\n");
    }

    // 수업 조회: 본인 담당 수업만
    public static void getClasses() throws Exception {
        System.out.println("**************************************************\n");
        System.out.println("[ "+teacherJy.getTeacherName()+" 선생님의 수업 리스트 ]");
        System.out.println("수업 번호 | 수업 제목 | 총 수업 시간 | 학습 개수 | 가격 | 누적 수강생 | 난이도\n");
        // 담당 수업 리스트를 불러온다.
        ArrayList<Class_jy> classesList = classServiceJy.getClasses();
        for(Class_jy classJy: classesList){
            System.out.println(classJy.getClassIdx() + " | " + classJy.getClassName() + " | "
                    + classJy.getSeconds() + " | " + classJy.getLectureCnt() + " | " + classJy.getPrice() + " | "
                    + classJy.getStudentCnt() + " | " + classJy.getDifficulty());
        }
        System.out.println("\n**************************************************\n");
    }


    // 수업 수정
    public static void updateClass() throws Exception {
        System.out.println("**************************************************\n");
        System.out.println("몇 번째 수업을 수정하시겠습니까?\n");
        System.out.println("**************************************************\n");
        System.out.print("수업 번호 : ");
        int classIdx = Integer.parseInt(br.readLine());

        // 입력받은 classIdx이 해당 선생님의 수업 리스트에 있는지 확인
        // 선생님의 담당 수업 리스트
        ArrayList<Class_jy> classesList = classServiceJy.getClasses();
        boolean inCharge = false;
        for(Class_jy classJy: classesList){ // [리팩토링] 성능 개선을 위해 map으로 구현
            if(classIdx == classJy.getClassIdx()){
                inCharge = true;
                break;
            }
        }

        // 수업 번호 잘못 입력 시, 메인 화면으로 돌아간다.
        if(!inCharge){
            System.out.println("본인이 담당하고 있는 수업이 아니기 때문에 수정하실 수 없습니다.");
            System.out.println("본인이 담당하는 수업 번호를 입력해주세요.");
            return;
        }

        // 수업 수정 정보 입력
        System.out.print("수업 제목 : ");
        String className = br.readLine();
        System.out.print("가격 : ");  // [리팩토링] 숫자 입력 예외 발생 시, 다시 입력 받기
        int price = Integer.parseInt(br.readLine());
        System.out.println("- 난이도는 1=쉬움 / 2=보통 / 3=어려움 입니다. -");
        System.out.print("난이도 : "); // [리팩토링] 1-3 예외 발생 시, 다시 입력 받기
        int difficulty = Integer.parseInt(br.readLine());

        // 수업 수정
        classServiceJy.updateClass(classIdx, className, price, difficulty);

        System.out.println("\n수정을 완료하였습니다.");
        System.out.println("**************************************************\n");
    }

    // 수업 삭제
    public static void deleteClass() throws Exception {
        System.out.println("**************************************************\n");
        System.out.println("몇 번째 수업을 삭제하시겠습니까?\n");
        System.out.println("**************************************************\n");
        System.out.print("수업 번호 : ");
        int classIdx = Integer.parseInt(br.readLine());

        // 입력받은 classIdx이 해당 선생님의 수업 리스트에 있는지 확인
        // 선생님의 담당 수업 리스트
        ArrayList<Class_jy> classesList = classServiceJy.getClasses();
        boolean inCharge = false;
        for(Class_jy classJy: classesList){ // [리팩토링] 성능 개선을 위해 map으로 구현
            if(classIdx == classJy.getClassIdx()){
                inCharge = true;
                break;
            }
        }

        // 수업 번호 잘못 입력 시, 메인 화면으로 돌아간다.
        if(!inCharge){
            System.out.println("본인이 담당하고 있는 수업이 아니기 때문에 수정하실 수 없습니다.");
            System.out.println("본인이 담당하는 수업 번호를 입력해주세요.");
            return;
        }

        // 수업 수정 정보 입력
        System.out.print("수업 제목 : ");
        String className = br.readLine();
        System.out.print("가격 : ");  // [리팩토링] 숫자 입력 예외 발생 시, 다시 입력 받기
        int price = Integer.parseInt(br.readLine());
        System.out.println("- 난이도는 1=쉬움 / 2=보통 / 3=어려움 입니다. -");
        System.out.print("난이도 : "); // [리팩토링] 1-3 예외 발생 시, 다시 입력 받기
        int difficulty = Integer.parseInt(br.readLine());

        // 수업 수정
        classServiceJy.updateClass(classIdx, className, price, difficulty);

        System.out.println("\n수정을 완료하였습니다.");
        System.out.println("**************************************************\n");
    }

    
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
        // 로그인 된 선생님 객체
        teacherJy = new Teacher_jy(1, "jy@gmail.com", "이지연", "12345", LocalDateTime.now(), 0, 0);

        // 선생님 로그인 후 welcome 화면
        // 1. 수업 관리 2. 학습 관리 3. 마이페이지 4. 로그아웃
        System.out.println("**************************************************\n");
        System.out.println(teacherJy.getTeacherName()+" 선생님 반갑습니다.");
        System.out.println("**************************************************\n");

        // 선생님 관련 서비스 객체
        teacherServiceJy = new TeacherService_jy();
        // 수업 관련 서비스 객체
        classServiceJy = new ClassService_jy();
        // 로그인 상태 여부
        boolean logIn = true;

        while(logIn) {  // 로그아웃 되기 전까지 반복
            // inputByTeacherInWelcome
            System.out.println("이용할 메뉴를 선택해 주세요.");
            System.out.println("\n1. 수업 관리 2. 학습 관리 3. 마이페이지 4. 로그아웃");
            int inputByTeacherInWelcome = Integer.parseInt(br.readLine());
            System.out.println("**************************************************\n");

            switch (inputByTeacherInWelcome) {
                // 1. 수업 관리
                case 1:
                    System.out.println("[ 수업 관리 ]");
                    // 담당 수업 목록 출력
                    getClasses();

                    // 수업 관리 서브 메뉴 inputByTeacherClassManage
                    System.out.println("1. 수업 등록하기 2. 수업 수정하기 3. 수업 삭제하기 4. 질문 보러 가기");
                    int inputByTeacherClassManage = Integer.parseInt(br.readLine());

                    if(inputByTeacherClassManage == 1){ // 1. 수업 등록하기
                        insertClass();
                    }else if(inputByTeacherClassManage == 2){ // 2. 수업 수정하기
                        updateClass();
                    }else if(inputByTeacherClassManage == 3){ // 3. 수업 삭제하기
                        deleteClass();
                    }else if(inputByTeacherClassManage == 4){ // 4. 질문 보러 가기

                    }else{

                    }
                    break;
                // 2. 학습 관리
                case 2:

                    //classServiceJy.updateClass(teacherJy);
                    break;
                // 3. 마이페이지
                case 3:

                    break;
                // 4. 로그아웃
                case 4:
                    // logIn = false;
                    break;
                // 입력 예외
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
