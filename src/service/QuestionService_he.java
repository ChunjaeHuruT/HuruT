package service;

import factory.MyBatisMapperFactory;
import org.apache.ibatis.session.SqlSession;
import repository.mapper.QuestionMapper_he;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 질문 등록을 위한 서비스 클래스
public class QuestionService_he {

    // 입력을 받는 데 사용할 버퍼드리더
    BufferedReader br;
    // SQL Session
    SqlSession sqlSession;
    // 사용할 매퍼
    QuestionMapper_he questionMapper_he;


    // 생성자
    public QuestionService_he () {
        br = new BufferedReader(new InputStreamReader(System.in));
        sqlSession = MyBatisMapperFactory.getSqlSession();
        questionMapper_he = sqlSession.getMapper(QuestionMapper_he.class);
    }

    // 해당 학생이


    // 질문 등록
    // 받아와야 하는 매개변수: 로그인 중인 학생의 idx, 질문 등록 대상인 수업의 idx
    public void addQuestion (int studentIdx, int classIdx) {
        String
    }

}
