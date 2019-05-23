package com.artech.personal.domain.entity;

import com.artech.domain.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by 程江涛 on 2019/4/24.
 */
@Getter
@Setter
@ToString // 如果不加这个注解，用的就是父类的 toString() 方法，会带 id 和 gmtCreate
@Entity
@Table(name = "book")
public class Book extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String publisher;

    @Column(nullable = false)
    private Double price;

    private String description;

}
