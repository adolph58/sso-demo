package com.artech.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by 程江涛 on 2019/4/13.
 */
@Getter
@Setter
@Entity
@Table(name = "role")
public class Role extends IdEntity {

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 100)
    private String description;

}
