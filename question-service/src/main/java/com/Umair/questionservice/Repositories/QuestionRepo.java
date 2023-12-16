package com.Umair.questionservice.Repositories;

import com.Umair.questionservice.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question,Long> {

    @Query(value = "select * from question q where q.category=:category order by RAND() limit :numOfQues",nativeQuery = true)
    List<Question> getQuesByCategoryAndLimit(@Param("category") String category,@Param("numOfQues") Integer numOfQues);

}
