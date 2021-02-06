package amvisible.hookahmarket.service.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ArticleViewServiceModel extends BaseServiceModel {

    private ArticleServiceModel article;
    private String ipAddress;
    private Date date;
}
