package com.hbgc.personblog.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 评论文章（人对文章评论）
 */
//spring boot 查询对象报错No serializer found for class org.hibernate.proxy.pojo.javassist.JavassistLazyInit
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PostComment")
public class PostComment extends BaseEntity implements Serializable,Cloneable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cid;

    @ManyToOne                 //一篇文章，多个评论
    @JoinColumn(name = "blogId",referencedColumnName = "bid")
    private Article article;    //博客文章

    @ManyToOne                 //一个用户，多个评论
    @JoinColumn(name = "userId",referencedColumnName = "uid")
    private Users users;        //用户

    private String htmlContent; //评论内容
}
