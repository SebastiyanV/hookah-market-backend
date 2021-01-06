package amvisible.hookahmarket.data.model;

import amvisible.hookahmarket.data.enumerate.ConditionEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
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

    @Column(name = "negotiable")
    private boolean negotiable;

    @Column(name = "price")
    private double price;

    @Column(name = "chat_conversation")
    private boolean chatConversation;

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
}
