package com.casestudymodule6.repository;


import com.casestudymodule6.model.dto.LeaderDTO;
import com.casestudymodule6.model.record.Record;
import com.casestudymodule6.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRecordRepository extends JpaRepository<Record, Long>
{

//    @Query(nativeQuery = true, value = "select account.username as 'username',Max((record.user_point / record.exam_point) * 100) as 'score' from quiz.record record join quiz.user u on u.id = record.user_id" +" "+
//            "join quiz.account account on account.user_id = u.id where record.exam_id = :examId group by account.username order by score desc")
//    List<LeaderDTO> findAllUserByExam(@Param("examId") Long examId);
@Query(nativeQuery = true, value = "select account.username as 'username',(record.user_point / record.exam_point) * 100 as 'score' from record record join user u on u.id = record.user_id" +" "+
        "join account account on account.user_id = u.id where record.exam_id = :examId order by score desc")
List<LeaderDTO> findAllRecordByExam(@Param("examId") Long examId);



    @Query(nativeQuery = true, value = "SELECT r.username, r.score, r.picture, r.record_id as 'recordID' " +
            "FROM " +
            "(SELECT account.username AS 'username', " +
            "MAX((record.user_point / record.exam_point) * 100) AS 'score', " +
            "u.avatar AS 'picture', " +
            "MAX(record.id) AS 'record_id' " +
            "FROM record record " +
            "JOIN user u ON u.id = record.user_id " +
            "JOIN account account ON account.user_id = u.id " +
            "JOIN perma_exam pe ON pe.id = record.exam_id " +
            "WHERE pe.name = :permaExamName AND pe.user_id = :userId " +
            "GROUP BY account.username) r " +
            "JOIN record ON record.id = r.record_id " +
            "ORDER BY r.score DESC")
    List<LeaderDTO> findAllRecordByPermaExam(@Param("permaExamName") String permaExamName, @Param("userId") Long userId);

Iterable<Record> findRecordByUser(User user);


}
