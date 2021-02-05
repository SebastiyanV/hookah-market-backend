package amvisible.hookahmarket.data.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ArticleStatusEnum {
    APPROVED("Одобрена"),
    AWAITING_APPROVAL("Чакаща одобрение"),
    INACTIVE("Неактивна");

    private String name;
}
