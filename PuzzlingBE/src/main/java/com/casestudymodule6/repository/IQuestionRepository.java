package com.casestudymodule6.repository;

import com.casestudymodule6.model.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface IQuestionRepository extends JpaRepository<Question, Long> {

    @Query(value = "SELECT * FROM question q JOIN exam e ON q.exam_id = e.id JOIN category c ON e.category_id = c.id WHERE (:name IS NULL OR q.name LIKE CONCAT('%', :name, '%')) AND (:category IS NULL OR c.name = :category) AND (:questionType IS NULL OR q.question_type = :questionType) AND (:level IS NULL OR q.level = :level)", nativeQuery = true)
    List<Question> searchQuestions(@Param("name") String name,
                                   @Param("category") String category,
                                   @Param("questionType") String questionType,
                                   @Param("level") String level);

    @Query(nativeQuery = true, value = "select * from question q join exam e on q.exam_id = e.id where q.exam_id = :examId")
    Set<Question> findQuestionByExamId(@Param("examId") Long examId);

}


