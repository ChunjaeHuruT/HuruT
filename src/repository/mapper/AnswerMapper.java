package repository.mapper;

import dao.AnswerDAO;
import dao.StudentDAO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnswerMapper {

    List<AnswerDAO> getAllAnswer();
}
