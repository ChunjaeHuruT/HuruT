package main;

import dto.Class_jy;
import dto.Lesson_jy;
import dto.Question_jy;
import dto.Teacher_jy;
import factory.MyBatisMapperFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import service.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class HuruTMain {
    
    // 필요한 서비스를 멤버로 받아오기

    // jy

    // 수업 관련 서비스 객체 (수업 등록/삭제/수정, 질문 보러 가기)
    static ClassService_jy classServiceJy;
    // 질문 관련 서비스 객체 (질문 조회)
    static QuestionService_jy questionServiceJy;
    // 학습 관련 서비스 객체
    static LessonService_jy lessonServiceJY;
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
        System.out.printf("%-5s|%-17s | %10s | %5s | %6s | %-5s\n", "수업 번호", "수업 제목", "총 수업 시간", "학습 개수", "가격", "난이도");

        // 담당 수업 리스트를 불러온다.
        ArrayList<Class_jy> classesList = classServiceJy.getClasses();
        for(Class_jy classJy: classesList){
            // 수업 시간 단위 변경: 초 -> 분
            // DB에는 초 단위로 저장됨. 콘솔 출력은 분, 시간 단위로 출력
            int playtime = classJy.getSeconds() / 60;
            String playtimeStr = Integer.toString(playtime) + "분";

            if(playtime / 60 >= 1){  // 60분 이상 -> 시간 단위로 변경
                playtimeStr = Integer.toString(playtime/60).concat("시간 ");
                if(playtime%60 != 0)    playtimeStr = playtimeStr.concat(Integer.toString(playtime%60) + "분"); // 0분이면 분은 표시하지 않음
            }


            // 난이도: 숫자 -> 한글
            String difficultyStr = (classJy.getDifficulty()==1) ? "쉬움" : ((classJy.getDifficulty()==2) ? "보통" : "어려움");
            System.out.printf("%-8s %-17s %10s %10s %12s %-5s\n", classJy.getClassIdx(), classJy.getClassName(), playtimeStr,
                    classJy.getLectureCnt(), classJy.getPrice(), difficultyStr);
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
            System.out.println("본인이 담당하는 수업 번호를 입력해 주세요.");
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
            System.out.println("본인이 담당하고 있는 수업이 아니기 때문에 삭제하실 수 없습니다.");
            System.out.println("본인이 담당하는 수업 번호를 입력해 주세요.");
            return;
        }

        // 수업 삭제
        classServiceJy.deleteClass(classIdx);

        System.out.println("\n삭제를 완료하였습니다.");
        System.out.println("**************************************************\n");
    }

    // 질문 검색: 담당하고 있는 수업에 관한 질문
    public static void searchQuestions() throws Exception {
        System.out.println("**************************************************\n");
        System.out.println("몇 번째 수업의 질문을 보러 가시겠습니까?\n");
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
            System.out.println("본인이 담당하는 수업 번호를 입력해 주세요.");
            return;
        }

        // 질문 검색
        ArrayList<Question_jy> questionsList = questionServiceJy.getQuestions(classIdx);

        // 질문 리스트 출력
        System.out.println("\n**************************************************\n");
        System.out.println("       질문 제목        |          질문 내용        |    작성자   | 질문 작성일자"); // [리팩토링] 강의 이름이 들어가야 할 것 같음
        for(Question_jy question: questionsList){
            // 질문의 작성자 조회
            String writer = questionServiceJy.getWriter(question.getQuestionIdx());
            System.out.printf("%-20s %-20s %-10s %-20s\n", question.getTitle(), question.getContents(), writer, question.getQuestionDateString());
        }

        System.out.println("\n**************************************************\n");
    }

    // 학습 조회: ALL
    // 특정 class의 모든 lessons을 화면에 출력
    public static void getLessons(int inputByTeacherLessonManage_classIdx) throws Exception {
        // classIdx를 통해 class 객체를 하나 가져옴
        Class_jy aClass = classServiceJy.getClass(inputByTeacherLessonManage_classIdx);
        System.out.println("\n" + teacherJy.getTeacherName()+" 선생님의 ["+aClass.getClassName()+"] 학습 리스트\n");

        // 특정 class의 lessons을 불러옴
        ArrayList<Lesson_jy> lessonsList = lessonServiceJY.getLessons(inputByTeacherLessonManage_classIdx);

        // lessons 출력
        System.out.println("학습번호 |        학습제목        | 학습시간(분)\n");
        for(Lesson_jy lesson: lessonsList){
            int minutes = lesson.getLessonsSeconds()/60;
            System.out.printf("%-7s %-23s %-3s분\n", lesson.getLessonIdx(), lesson.getLessonName(), minutes);
        }
        System.out.println("\n**************************************************\n");
    }

    // 학습 등록
    // 선생님으로부터 lesson 정보 입력 받은 후, classIdx의 class에 등록
    public static void insertLesson(int classIdx) throws Exception{
        System.out.println("**************************************************\n");
        System.out.print("학습 제목 : ");
        String lessonName = br.readLine();
        System.out.print("학습 시간 (분) : ");
        int lessonsMinutes = Integer.parseInt(br.readLine());   // 선생님한테 분 단위로 입력 받음
        int lessonsSeconds = lessonsMinutes * 60;               // DB에 삽입 시, 초 단위로 삽입
        // 학습 등록
        lessonServiceJY.insertLesson(lessonName, classIdx, lessonsSeconds);
        // 수업 객체 불러오기
        Class_jy aClass = classServiceJy.getClass(classIdx);
        // 수업 갱신: 학습 개수, 수업 총 시간
        // 학습 개수 += 1
        // 수업 총 시간 += 학습 시간
        classServiceJy.updateClassByLesson(classIdx, aClass.getLectureCnt() + 1,aClass.getSeconds() + lessonsSeconds);

        System.out.println("\n등록을 완료하였습니다.");
        System.out.println("**************************************************\n");
    }

    // 학습 삭제
    public static void deleteLesson(int classIdx) throws Exception{
        System.out.println("**************************************************\n");
        System.out.println("삭제할 학습 번호를 입력해 주세요.");
        System.out.print("학습 번호 : ");
        int lessonIdx = Integer.parseInt(br.readLine());

        // 해당 수업의 학습이 맞는지 확인
        int validateClassIdx = 0;
        // validateClassIdx = lessonIdx 학습을 가진 수업 인덱스
        validateClassIdx = lessonServiceJY.getClassIdx(lessonIdx);
        // 삭제하려는 학습이 현재 수업에 없을 경우
        // 삭제하려는 학습이 데이터베이스에 존재하지 않을 경우
        if(validateClassIdx != classIdx){
            System.out.println("학습 번호를 잘못 입력하였습니다.");
            return;
        }

        // 수업 객체 불러오기
        Class_jy aClass = classServiceJy.getClass(classIdx);
        // 학습 객체 불러오기
        Lesson_jy lesson = lessonServiceJY.getLesson(lessonIdx);

        // 수업 갱신: 학습 개수, 수업 총 시간
        // 학습 개수 -= 1
        // 수업 총 시간 -= 학습 시간
        classServiceJy.updateClassByLesson(classIdx, aClass.getLectureCnt() - 1,aClass.getSeconds() - lesson.getLessonsSeconds());
        // 학습 삭제
        lessonServiceJY.deleteLesson(lessonIdx);

        System.out.println("\n삭제를 완료하였습니다.");
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
        System.out.println(teacherJy.getTeacherName()+" 선생님 반갑습니다.\n");
        System.out.println("**************************************************\n");

        // 수업 관련 서비스 객체
        classServiceJy = new ClassService_jy();
        // 질문 관련 서비스 객체
        questionServiceJy = new QuestionService_jy();
        // 학습 관련 서비스 객체
        lessonServiceJY = new LessonService_jy();
        // 로그인 상태 여부
        boolean logIn = true;

        while(logIn) {  // 로그아웃 되기 전까지 반복
            // inputByTeacherInWelcome
            System.out.println("**************************************************\n");
            System.out.println("이용할 메뉴를 선택해 주세요.");
            System.out.println("\n1. 수업 관리 2. 학습 관리 3. 로그아웃");
            System.out.print("메뉴 : ");
            int inputByTeacherInWelcome = Integer.parseInt(br.readLine());
            System.out.println("**************************************************\n");

            switch (inputByTeacherInWelcome) {
                // 1. 수업 관리
                case 1:
                    System.out.println("[ 수업 관리 ]");
                    // 담당 수업 목록 출력
                    getClasses();

                    // 수업 관리 서브 메뉴 inputByTeacherClassManage
                    System.out.println("1. 수업 등록하기 2. 수업 수정하기 3. 수업 삭제하기 4. 질문 보러 가기 5. 메인으로 돌아가기");
                    System.out.print("메뉴 : ");
                    int inputByTeacherClassManage = Integer.parseInt(br.readLine());

                    if(inputByTeacherClassManage == 1){ // 1. 수업 등록하기
                        insertClass();
                    }else if(inputByTeacherClassManage == 2){ // 2. 수업 수정하기
                        updateClass();
                    }else if(inputByTeacherClassManage == 3){ // 3. 수업 삭제하기
                        deleteClass();
                    }else if(inputByTeacherClassManage == 4){ // 4. 질문 보러 가기
                        searchQuestions();
                    }else if(inputByTeacherClassManage == 5){ // 5. 메인으로 돌아가기
                        // 메인으로 돌아감
                    }else{  // 예외
                        System.out.println("잘못 입력하셨습니다.");
                    }
                    break;
                // 2. 학습 관리
                case 2:
                    System.out.println("[ 학습 관리 ]");
                    // 담당 수업 목록 출력
                    getClasses();

                    System.out.println("\n몇 번째 수업의 학습을 관리하시겠습니까?");
                    System.out.print("수업 번호 : "); // inputByTeacherLessonManage_classIdx
                    int inputByTeacherLessonManage_classIdx = Integer.parseInt(br.readLine());

                    // 본인 수업 맞는지 확인
                    // 입력받은 classIdx이 해당 선생님의 수업 리스트에 있는지 확인
                    // 선생님의 담당 수업 리스트
                    ArrayList<Class_jy> classesList = classServiceJy.getClasses();
                    boolean inCharge = false;
                    for(Class_jy classJy: classesList){ // [리팩토링] 성능 개선을 위해 map으로 구현
                        if(inputByTeacherLessonManage_classIdx == classJy.getClassIdx()){
                            inCharge = true;
                            break;
                        }
                    }

                    // 수업 번호 잘못 입력 시, 메인 화면으로 돌아간다.
                    if(!inCharge){
                        System.out.println("본인이 담당하는 수업 번호를 입력해 주세요.");
                        break; // 메인 메뉴로 돌아간다. switch (inputByTeacherInWelcome) 탈출
                    }

                    // 특정 수업의 학습 리스트 출력
                    getLessons(inputByTeacherLessonManage_classIdx);

                    // 1.학습 등록하기 2.학습 수정하기 3.학습 삭제하기
                    System.out.println("1.학습 등록 2.학습 삭제 3. 메인으로 돌아가기"); // inputByTeacherLessonManage_menu
                    int inputByTeacherLessonManage_menu = Integer.parseInt(br.readLine());

                    if(inputByTeacherLessonManage_menu == 1){ // 1. 학습 등록
                        insertLesson(inputByTeacherLessonManage_classIdx);
                    }else if(inputByTeacherLessonManage_menu == 2){ // 2. 학습 삭제
                        deleteLesson(inputByTeacherLessonManage_classIdx);
                    }else if(inputByTeacherLessonManage_menu == 3){ // 3. 메인으로 돌아가기
                        // 메인으로 돌아간다.
                    }else{  // 예외
                        System.out.println("잘못 입력하셨습니다.");
                        System.out.println("메인으로 돌아갑니다.");
                    }
                    break;
                // 3. 로그아웃
                case 3:
                    logIn = false;
                    break;
                // 입력 예외
                default:
                    System.out.println("잘못 입력하셨습니다.");
                    System.out.println("메뉴를 다시 입력해 주세요.");
            }
        }
        System.out.println("로그아웃 되었습니다.");

        // jh


        // sz


        // ys


        // he


        
        
    }
    
}
