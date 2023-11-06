package dao;

import dto.Question_jy;
import org.apache.ibatis.session.SqlSession;
import repository.mapper.QuestionMapper_jy;

import java.util.ArrayList;

public class QuestionDAO_jy {
    /*
    private int questionIdx;
    private String title;
    private String contents;
    private int studentId;
    private int classId;
    private LocalDateTime questionDate;
    */

    // DB 연결
    SqlSession session;
    QuestionMapper_jy mapper;

    // 생성자
    public QuestionDAO_jy(){
        this.session = factory.MyBatisMapperFactory.getSqlSession();
        mapper = session.getMapper(QuestionMapper_jy.class);
    }

    // 질문 조회: 특정 수업에(classIdx) 관련한 질문 전부
    public ArrayList<Question_jy> getQuestions(int classIdx){
        // classIdx를 인덱스로 갖는 질문 전부
        ArrayList<Question_jy> questionsList = mapper.getQuestions(classIdx);

        return questionsList;
    }

}
