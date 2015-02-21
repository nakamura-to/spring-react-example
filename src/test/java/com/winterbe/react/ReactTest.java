package com.winterbe.react;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ReactTest {

    @Test
    public void testRenderCommentBox() throws Exception {
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment("Peter Parker", "This is a comment."));
        comments.add(new Comment("John Doe", "This is *another* comment."));

        React react = new React("dummy-bundle.js");
        String html = react.renderCommentBox(comments);

        assertNotNull(html);
    }

}