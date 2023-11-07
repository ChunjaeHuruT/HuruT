package service;

import factory.MyBatisMapperFactory;
import org.apache.ibatis.session.SqlSession;
import repository.mapper.ReviewMapper_he;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

// 강의평 등록을 위한 서비스 클래스
public class ReviewService_he {

    // 입력을 받는 데 사용할 버퍼드리더
    BufferedReader br;
    // SQL Session
    SqlSession sqlSession;
    // 사용할 매퍼
    ReviewMapper_he reviewMapper_he;


    // 생성자
    public ReviewService_he () {
        br = new BufferedReader(new InputStreamReader(System.in));
        sqlSession = MyBatisMapperFactory.getSqlSession();
        reviewMapper_he = sqlSession.getMapper(ReviewMapper_he.class);
    }

    // 강의평 등록
    // 받아와야 하는 매개변수: 로그인 중인 학생의 idx, 강의평 등록 대상인 수업의 idx
    public void addReview(int studentIdx, int classIdx) {

        while(true) {
            String contents = "";
            int rating = -1;

            try {
                // 강의평 입력받기
                System.out.println("*********************************************");
                System.out.println("강의평 등록을 시작합니다.");
                System.out.println("아래 항목을 입력해주세요");
                System.out.print("강의평(최대 255자): ");
                contents = br.readLine();
                System.out.print("평점(0~5 사이의 정수): ");
                rating = Integer.parseInt(br.readLine());

            } catch (Exception e) {
                System.out.println("오류가 발생했습니다. 다시 입력해주세요.");
                continue;
            }

            // 강의평이 너무 길 경우
            if(contents.length()>255) {
                System.out.println("강의평의 길이가 너무 깁니다. 다시 입력해주세요.");
                continue;
            }
            // 평점 값이 정수이긴 한데 0~5 사이가 아닐 경우
            if(rating < 0 || rating > 5) {
                System.out.println("평점이 0~5 사이의 정수가 아닙니다. 다시 입력해주세요.");
                continue;
            }

            // 입력받은 데이터를 데이터베이스에 저장
            Map<String, Object> param = new HashMap<>();
            param.put("classId", classIdx);
            param.put("studentId", studentIdx);
            param.put("contents", contents);
            param.put("rating", rating);
            sqlSession.insert("repository.mapper.ReviewMapper_he.insertReview", param);
            sqlSession.commit();

            System.out.println("등록이 완료되었습니다.");
            System.out.println("*********************************************");

            // 성공적으로 등록이 완료되었으면 break
            break;

        }



    }



}
