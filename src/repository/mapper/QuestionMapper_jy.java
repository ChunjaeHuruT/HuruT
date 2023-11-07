package repository.mapper;

import dto.Question_jy;

import java.util.ArrayList;

public interface QuestionMapper_jy {
    public ArrayList<Question_jy> getQuestions(int classIdx);
    public String getWriter(int questionIdx);
}
