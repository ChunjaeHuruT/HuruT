package service;

import dao.QuestionDAO;
import dao.QuestionDAO_he;
import factory.MyBatisMapperFactory;
import org.apache.ibatis.reflection.ArrayUtil;
import org.apache.ibatis.session.SqlSession;
import repository.mapper.QuestionMapper_he;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

// 질문 등록을 위한 서비스 클래스
// 사용법: 본 클래스의 인스턴스를 생성(매개변수로 수업 아이디 넘겨주기)한 후, questionMain() 메소드를 호출한다.
public class QuestionService_he {

    // 사용할 변수. 코드 합칠 때 studentIdx는 로그인 시 제공하는 전역변수로 바꾸어야 함.
    private int studentIdx;
    private int classIdx;
    // 질문 리스트 출력 SQL문 생성에 사용할 변수를 담은 맵
    private Map<String, Integer> idxMap;

    // 입력을 받는 데 사용할 버퍼드리더
    BufferedReader br;
    // SQL Session
    SqlSession sqlSession;
    // 사용할 매퍼
    QuestionMapper_he questionMapper_he;


    // 생성자. classIdx_he를 매개변수로 주어야 함. 코드 합칠 때 studentIdx는 전역변수로 바꾸어야 함.
    public QuestionService_he (int studentIdx, int classIdx) {
        br = new BufferedReader(new InputStreamReader(System.in));
        sqlSession = MyBatisMapperFactory.getSqlSession();
        questionMapper_he = sqlSession.getMapper(QuestionMapper_he.class);

        this.studentIdx = studentIdx;
        this.classIdx = classIdx;
        idxMap = new HashMap<>();
        idxMap.put("classIdx", classIdx);
        idxMap.put("studentIdx", studentIdx);
    }

    // 기본 실행 메소드
    public void questionMain () {
        while (true) {
            try {
                // 본인이 해당 수업에 등록한 모든 질문 리스트 출력
                printMyQuestions();

                // 어떤 동작을 할지 선택
                System.out.println("1.질문등록 2.질문수정 3.질문삭제 4.나가기");
                System.out.print("원하는 작업의 번호를 입력해주세요(1~4): ");
                int menuNum = Integer.parseInt(br.readLine());

                // 1~3 외의 정수를 입력했을 경우를 처리
                if (menuNum<1 || menuNum>4) {
                    System.out.println("*********************************************");
                    System.out.println("입력값이 올바르지 않습니다. 1~4 사이의 정수를 입력해주세요.");
                    continue;   // while문을 다시 실행
                }

                // switch-case문
                switch(menuNum) {
                    case 1:
                        addQuestion();
                        break;
                    case 2:
                        System.out.println("다음의 목록에서 수정할 질문의 번호를 골라 입력하세요.");
                        printMyQuestions();
                        updateQuestion();
                        break;
                    case 3:
                        System.out.println("다음의 목록에서 삭제할 질문의 번호를 골라 입력하세요.");
                        printMyQuestions();
                        deleteQuestion();
                        break;
                    case 4:
                        return;
                }

            }
            catch (Exception e) {
                System.out.println("오류 발생. 이전 화면으로 돌아갑니다.");
                System.out.println(e.getMessage());
                return;
            }
        }
    }

    // 모든 수업의 리스트를 출력하는 메소드
    private void printMyQuestions () {
        ArrayList<QuestionDAO_he> myQuestionList = questionMapper_he.getQuestions(idxMap);
        System.out.println("*********************************************");
        System.out.println("본인이 해당 수업에 등록한 질문은 다음과 같습니다.");
        for (QuestionDAO_he q : myQuestionList) {
            System.out.println();
            System.out.println("질문번호: " + q.getQuestionIdx() + ", 제목: " + q.getTitle());
            System.out.println(q.getContents());
            System.out.println("작성일자 " + q.getQuestionDate());
        }
        System.out.println("*********************************************");
    }


    // 질문 등록 메소드
    private void addQuestion () throws IOException {
        // 등록될 값을 담을 HashMap
        Map<String, Object> param = new HashMap<>();
        System.out.println("*********************************************");
        System.out.println("질문 등록을 시작합니다.");
        System.out.println("아래 항목을 입력해주세요.");
        System.out.print("질문 제목: ");
        param.put("title", br.readLine());
        System.out.print("질문 내용: ");
        param.put("content", br.readLine());
        param.put("studentIdx", studentIdx);
        param.put("classIdx", classIdx);
        sqlSession.insert("repository.mapper.QuestionMapper_he.insertQuestion", param);
        sqlSession.commit();
        System.out.println("등록이 완료되었습니다.");
        System.out.println("*********************************************");
    }

    // 질문 수정 메소드
    private void updateQuestion () throws IOException {
        System.out.print("수정할 질문의 번호: ");
        int questionIdx = Integer.parseInt(br.readLine());
        System.out.println("*********************************************");
        Map<String, Object> param = new HashMap<>();
        System.out.println("질문 수정을 시작합니다.");
        System.out.println("아래 항목을 입력해주세요.");
        System.out.print("질문 제목: ");
        param.put("title", br.readLine());
        System.out.print("질문 내용: ");
        param.put("content", br.readLine());
        param.put("questionIdx", questionIdx);
        sqlSession.update("repository.mapper.QuestionMapper_he.updateQuestion", param);
        sqlSession.commit();
        System.out.println("수정이 완료되었습니다.");
        System.out.println("*********************************************");

    }

    // 질문 삭제 메소드
    private void deleteQuestion () throws IOException {
        System.out.print("삭제할 질문의 번호: ");
        int questionIdx = Integer.parseInt(br.readLine());
        questionMapper_he.deleteQuestion(questionIdx);
        sqlSession.commit();
        System.out.println("삭제가 완료되었습니다.");
    }


}
