package com.hc.crud.teacherlist.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "auto_sql_model_fields")
public class ModelFields {
    /*
     * @GeneratedValue 自增id
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "model_id")
    private Integer modelId;
    @Column(name = "name")
    private String name;
    @Column(name = "alias")
    private String alias;
    private Integer deleted = 0;
    @Column(name = "gmt_create")
    private Long gmtCreate = 0L;
    @Column(name = "gmt_update")
    private Long gmtUpdate = 0L;
    private Integer creator = 0;
    private Integer modifier;
}
