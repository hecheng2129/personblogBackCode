package com.hbgc.personblog.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 人与人对话
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Interpersonal")
public class Interpersonal extends BaseEntity implements Serializable,Cloneable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;

    @ManyToOne                 //在哪篇文章发表的评论
    @JoinColumn(name = "blogId",referencedColumnName = "bid")
    private Article article;    //博客文章

    int floor;                 //表示在几楼评论

    @ManyToOne                 //要回复的人
    @JoinColumn(name = "user1Id",referencedColumnName = "uid")
    private Users users1;        //用户

    @ManyToOne                 //回复的人
    @JoinColumn(name = "user2Id",referencedColumnName = "uid")
    private Users users2;        //用户

    private String htmlContent; //评论内容
}
