package amvisible.hookahmarket.service.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BrandServiceModel extends BaseServiceModel {

    private String name;
    private List<ArticleServiceModel> articles;

    public BrandServiceModel(String name) {
        this.name = name;
    }
}
