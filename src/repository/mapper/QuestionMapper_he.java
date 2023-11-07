package repository.mapper;

import dao.QuestionDAO;
import dao.QuestionDAO_he;

import java.util.ArrayList;
import java.util.Map;

public interface QuestionMapper_he {

    // 해당 학생이 해당 수업에 달았던 질문 리스트를 불러오는 메소드
    public ArrayList<QuestionDAO_he> getQuestions (Map<String, Integer> map);

    // 질문 하나를 테이블에 추가하는 메소드
    public void insertQuestion (QuestionDAO_he questionDAO_he);

    // 질문 하나를 수정하는 메소드
    public void updateQuestion (Map<String, Object> map);

    // 질문 하나를 삭제하는 메소드
    public void deleteQuestion (int questionIdx);

}
