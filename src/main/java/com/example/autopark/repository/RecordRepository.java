package com.example.autopark.repository;

import com.example.autopark.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {
    @Query("select p from Record p where concat(p.id, '', p.record_name, '', p.author_name, '', p.publish_date, '', p.text) LIKE %?1%")
    List<Record> search(String keyword);

    @Query("SELECT p from Record p where p.record_name LIKE %:keyword%")
    List<Record> findByRecord(@Param("keyword") String keyword);
    @Query("SELECT p from Record p where p.publish_date LIKE %:keyword%")
    List<Record> findByPublishDate(@Param("keyword") String keyword);
    @Query("SELECT c FROM Record c WHERE c.record_name LIKE %:keyword% or c.publish_date LIKE %:keyword%")
    List<Record> findByDateRecord(@Param("keyword") String keyword);
    @Query("SELECT p from Record p where p.author_name LIKE %:keyword%")
    List<Record> findByAuthor(@Param("keyword") String keyword);
    @Query("SELECT p from Record p where p.text LIKE %:keyword%")
    List<Record> findByText(@Param("keyword") String keyword);
}

