package amvisible.hookahmarket.data.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ArticleTypeEnum {
    NORMAL("Обикновена"),
    PROMO("Промо");

    private String name;
}
