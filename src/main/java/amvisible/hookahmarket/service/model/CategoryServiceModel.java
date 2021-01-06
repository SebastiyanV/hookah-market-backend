package amvisible.hookahmarket.service.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CategoryServiceModel extends BaseServiceModel {

    private String name;
    private List<ArticleServiceModel> articles;

    public CategoryServiceModel(String name) {
        this.name = name;
    }
}
