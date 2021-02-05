package amvisible.hookahmarket.web.model.article;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArticleCreateRequestBody {

    private ArticleCreateServiceModel articleCreateServiceModel;
    private MultipartFile image1;
}
