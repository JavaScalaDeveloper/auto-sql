package com.hc.crud.teacherlist.repository;

import com.hc.crud.teacherlist.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.List;

@EnableJpaRepositories
public interface ModelRepository extends JpaRepository<Model, Integer> {
    @Query(value = "show tables", nativeQuery = true)
    List<String> getAllTablesByDsId(String tableName);

    @Query(value = "select COLUMN_NAME AS name,COLUMN_COMMENT AS alias from information_schema.columns where table_name = :name", nativeQuery = true)
    List<List<Object>> getFieldsByTbName(@Param("name") String name);
}
