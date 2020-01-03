package com.hbgc.personblog.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 *这是文章表
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ARTICLE")
public class Article extends BaseEntity implements Serializable,Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bid;

    private String title;       //主题
    private String mdContent;     //内容
    private String htmlContent;     //内容
    private int likeNumber;     //点赞数
    private int readNumber;     //阅读数
    private int replyNumber;    //回复数
    private String image;       //图片

    //多篇文章对应一个用户
    @ManyToOne
    @JoinColumn(name = "userid",referencedColumnName = "uid")
    private Users users;
}
