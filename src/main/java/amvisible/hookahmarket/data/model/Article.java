package amvisible.hookahmarket.data.model;

import amvisible.hookahmarket.data.enumerate.ArticleStatusEnum;
import amvisible.hookahmarket.data.enumerate.ArticleTypeEnum;
import amvisible.hookahmarket.data.enumerate.ConditionEnum;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "articles")
@ToString
public class Article extends BaseModel {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "article_condition", nullable = false)
    @Enumerated(EnumType.STRING)
    private ConditionEnum articleCondition;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ArticleTypeEnum type;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ArticleStatusEnum status;

    @Column(name = "negotiable")
    private boolean negotiable;

    @Column(name = "price")
    private double price;

    @Column(name = "chat_conversation")
    private boolean chatConversation;

    @JsonManagedReference
    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ArticleImage> images;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "published_on")
    private Date publishedOn;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_modify")
    private Date lastModify;

    @ManyToOne(fetch = FetchType.EAGER)
    private User author;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Brand brand;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Category category;

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ArticleView> views;

    public Article() {
        this.images = new ArrayList<>();
        this.type = ArticleTypeEnum.NORMAL;
        this.status = ArticleStatusEnum.AWAITING_APPROVAL;
    }

    public void addImage(ArticleImage articleImage) {
        this.getImages().add(articleImage);
    }
}
