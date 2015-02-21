package com.winterbe.react;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.databind.ObjectMapper;

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
        ObjectMapper mapper = createMapper();
        String data = mapper.writeValueAsString(comments);
        model.put("content", commentBox);
        model.put("data", data);
        return "index";
    }

    private ObjectMapper createMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.getFactory().setCharacterEscapes(new CustomCharacterEscapes());
        return mapper;
    }

    @SuppressWarnings("serial")
    private static class CustomCharacterEscapes extends CharacterEscapes {

        private final int[] _asciiEscapes;

        public CustomCharacterEscapes() {
            _asciiEscapes = standardAsciiEscapesForJSON();
            _asciiEscapes['/'] = CharacterEscapes.ESCAPE_STANDARD;
            _asciiEscapes['<'] = CharacterEscapes.ESCAPE_STANDARD;
            _asciiEscapes['>'] = CharacterEscapes.ESCAPE_STANDARD;
            _asciiEscapes['+'] = CharacterEscapes.ESCAPE_STANDARD;
        }

        @Override
        public int[] getEscapeCodesForAscii() {
            return _asciiEscapes;
        }

        @Override
        public SerializableString getEscapeSequence(int i) {
            return null;
        }
    }
}
