package com.winterbe.react;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Benjamin Winterberg
 */
@Controller
public class MainController {

    private CommentService service;

    @Autowired
    public MainController(CommentService service) {
        this.service = service;
    }

    @RequestMapping("/")
    public String index(Map<String, Object> model) throws Exception {
        List<Comment> comments = service.getComments();
        React react = new React();
        String commentBox = react.renderCommentBox(comments);
        model.put("content", commentBox);
        model.put("data", comments);
        return "index";
    }

}
