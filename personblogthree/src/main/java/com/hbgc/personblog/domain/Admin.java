package com.hbgc.personblog.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ADMIN")
public class Admin extends BaseEntity implements Serializable,Cloneable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int aid;

    private String username;        //用户名
    private String password;        //密码
}
