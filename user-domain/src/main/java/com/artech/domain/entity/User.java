package com.artech.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by 程江涛 on 2019/4/13.
 */
@Getter
@Setter
@Entity
@Table(name = "user")
public class User extends BaseEntity{

    @Column(nullable = false, length = 20)
    private String loginName;

    @Column(nullable = false)
    private String password;

    private String email;

    @Column(nullable = false)
    private String mobile;

    /**
     * 多对多 用户 -> 角色 中间表
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

}
