package amvisible.hookahmarket.web.model.article;

import amvisible.hookahmarket.data.enumerate.ConditionEnum;
import amvisible.hookahmarket.data.model.User;
import amvisible.hookahmarket.service.model.BrandServiceModel;
import amvisible.hookahmarket.service.model.CategoryServiceModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ArticleCreateServiceModel {

    private String title;
    private String description;
    private ConditionEnum articleCondition;
    private boolean negotiable;
    private double price;
    private boolean chatConversation;
    private Date publishedOn;
    private Date lastModify;
    private User author;
    private BrandServiceModel brand;
    private CategoryServiceModel category;
}
