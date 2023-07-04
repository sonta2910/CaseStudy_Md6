package com.casestudymodule6.repository;

import com.casestudymodule6.model.question.Category;
import com.casestudymodule6.model.question.Exam;
import com.casestudymodule6.model.record.PermaExam;
import com.casestudymodule6.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IExamRepository extends JpaRepository<Exam, Long>
{
      Iterable<Exam> findExamsByUser(User user);

      Iterable<Exam> findExamsByCategoryAndUser(Category category, User user);



      Iterable<Exam> findExamsByCategory(Category category);


      Optional<Exam> findExamByUserAndName(User user, String name);

      @Query(nativeQuery = true, value = "SELECT e.* from (SELECT ex.*" +
              " FROM exam ex" +
              " JOIN question q ON ex.id = q.exam_id" +
              " GROUP BY ex.id " +
              " HAVING COUNT(q.id) >= 5" +
              " ORDER BY RAND()" +
              " LIMIT 1) e JOIN question q ON e.id = q.exam_id")
      Exam findRandomExam();



}
