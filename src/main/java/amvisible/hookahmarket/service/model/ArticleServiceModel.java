package amvisible.hookahmarket.service.model;

import amvisible.hookahmarket.data.enumerate.ConditionEnum;
import amvisible.hookahmarket.data.model.Article;
import amvisible.hookahmarket.data.model.Brand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ArticleServiceModel extends BaseServiceModel{

    private String title;
    private String description;
    private ConditionEnum articleCondition;
    private boolean negotiable;
    private double price;
    private Date publishedOn;
    private Date lastModify;
    private UserServiceModel author;
    private Brand brand;
    private Article article;
}
