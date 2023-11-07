package service;

import dto.Question_jy;
import org.apache.ibatis.session.SqlSession;
import repository.mapper.QuestionMapper_jy;

import java.util.ArrayList;

public class QuestionService_jy {
    // DAO
    SqlSession session;
    QuestionMapper_jy mapper;

    // 생성자
    public QuestionService_jy(){
        this.session = factory.MyBatisMapperFactory.getSqlSession();
        mapper = session.getMapper(QuestionMapper_jy.class);
    }

    // 질문 조회: 특정 수업에(classIdx) 관한 질문만
    public ArrayList<Question_jy> getQuestions(int classIdx){
        // classIdx를 인덱스로 갖는 질문 전부
        ArrayList<Question_jy> questionsList = mapper.getQuestions(classIdx);

        return questionsList;
    }

    // 질문 작성자 닉네임 조회
    public String getWriter(int question_idx){
        String studentNickname = mapper.getWriter(question_idx);
        return studentNickname;
    }
}
