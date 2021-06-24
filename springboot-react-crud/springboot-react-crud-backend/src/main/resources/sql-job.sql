create database auto_sql;
use auto_sql;
drop table if exists model;
CREATE TABLE `model`
(
    `id`            bigint(20) unsigned NOT NULL auto_increment COMMENT '主键',
    `datasource_id` bigint(20)          NOT NULL COMMENT '数据源id',
    `name`          varchar(64)                  DEFAULT NULL COMMENT '模型名称',
    `comment`       varchar(255)                 DEFAULT NULL COMMENT '备注',
    `deleted`       tinyint(1)          NOT NULL DEFAULT '0' COMMENT '0 - 正常数据; 1 - 已归档(删除)',
    `gmt_create`    bigint(20)          NOT NULL COMMENT '创建时间',
    `creator`       bigint(20)          NOT NULL COMMENT '创建人',
    `gmt_update`    bigint(20)          NOT NULL COMMENT '最后一次更新时间',
    `modifier`      bigint(20)                   DEFAULT NULL COMMENT '最后一次修改人',
    PRIMARY KEY (`id`),
    index (id, name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='模型基本信息表';

drop table if exists fields;
CREATE TABLE `fields`
(
    `id`         bigint(20) unsigned NOT NULL auto_increment COMMENT '主键',
    `model_id`   bigint(20)          NOT NULL COMMENT '模型id',
    `name`       varchar(64)                  DEFAULT NULL COMMENT '字段名称',
    `alias`      varchar(64)                  DEFAULT NULL COMMENT '别名',
    `type`       varchar(64)                  DEFAULT NULL COMMENT '数据类型',
    `display`    tinyint(1)          NOT NULL DEFAULT '0' COMMENT '0 - 显示（可用）; 1 - 隐藏(不可用)',
    `deleted`    tinyint(1)          NOT NULL DEFAULT '0' COMMENT '0 - 正常数据; 1 - 已归档(删除)',
    `gmt_create` bigint(20)          NOT NULL COMMENT '创建时间',
    `creator`    bigint(20)          NOT NULL COMMENT '创建人',
    `gmt_update` bigint(20)          NOT NULL COMMENT '最后一次更新时间',
    `modifier`   bigint(20)                   DEFAULT NULL COMMENT '最后一次修改人',
    PRIMARY KEY (`id`),
    index (model_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='模型字段信息表';

drop table if exists detail;
CREATE TABLE `detail`
(
    `id`         bigint(20) unsigned NOT NULL COMMENT '主键',
    `name`       varchar(64)         NOT NULL COMMENT '名称',
    `sql`        text                         DEFAULT NULL COMMENT '生成的SQL',
    `deleted`    tinyint(1)          NOT NULL DEFAULT '0' COMMENT '0 - 正常数据; 1 - 已归档(删除)',
    `gmt_create` bigint(20)          NOT NULL COMMENT '创建时间',
    `creator`    bigint(20)          NOT NULL COMMENT '创建人',
    `gmt_update` bigint(20)          NOT NULL COMMENT '最后一次更新时间',
    `modifier`   bigint(20)                   DEFAULT NULL COMMENT '最后一次修改人',
    PRIMARY KEY (`id`),
    index (id, name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='sql基本信息表';

drop table if exists rules;
CREATE TABLE `rules`
(
    `id`          bigint(20) unsigned NOT NULL COMMENT '主键',
    `field_id`    bigint(20)          NOT NULL COMMENT '字段信息id',
    `sortable`    tinyint(1)          NOT NULL DEFAULT '0' COMMENT '0 - 默认升序; 1 - 降序',
    `agg_type`    varchar(64)         NOT NULL COMMENT '聚合类型',
    `field_alias` varchar(64)         NOT NULL COMMENT '字段别名',
    `rule`        varchar(64)         NOT NULL COMMENT '过滤规则',
    `rule_value`  text                NOT NULL COMMENT '过滤规则代入值',
    `deleted`     tinyint(1)          NOT NULL DEFAULT '0' COMMENT '0 - 正常数据; 1 - 已归档(删除)',
    `gmt_create`  bigint(20)          NOT NULL COMMENT '创建时间',
    `creator`     bigint(20)          NOT NULL COMMENT '创建人',
    `gmt_update`  bigint(20)          NOT NULL COMMENT '最后一次更新时间',
    `modifier`    bigint(20)                   DEFAULT NULL COMMENT '最后一次修改人',
    PRIMARY KEY (`id`),
    index (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='sql字段规则详细信息表';
