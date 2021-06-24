package com.hc.crud.teacherlist.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "auto_sql_model")
public class Model {
    /*
     * @GeneratedValue 自增id
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "datasource_id")
    private Long datasourceId;
    private String name;
    private String comment;
    private Integer deleted = 0;
    @Column(name = "gmt_create")
    private Long gmtCreate = 0L;
    @Column(name = "gmt_update")
    private Long gmtUpdate = 0L;
    private Integer creator = 0;
    private Integer modifier;

    @Transient//JPA忽略的字段
    private List<ModelFields> fields;

}
