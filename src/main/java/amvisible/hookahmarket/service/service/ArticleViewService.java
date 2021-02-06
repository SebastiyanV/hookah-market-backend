package amvisible.hookahmarket.service.service;

import javax.servlet.http.HttpServletRequest;

public interface ArticleViewService {

    void registerView(String articleId, HttpServletRequest request);
}
