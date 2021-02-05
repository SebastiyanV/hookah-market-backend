package amvisible.hookahmarket.service.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArticleImageServiceModel extends BaseServiceModel {

    private String imageName;
    private ArticleServiceModel articleServiceModel;
    private boolean coverImage;
}
