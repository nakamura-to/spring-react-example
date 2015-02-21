package com.winterbe.react;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import jdk.nashorn.api.scripting.NashornScriptEngine;

public class React {

    private NashornScriptEngine nashorn;

    public React() {
        this("static/bundle.js");
    }

    protected React(String bundleJs) {
        try {
            nashorn = (NashornScriptEngine) new ScriptEngineManager()
                    .getEngineByName("nashorn");
            nashorn.eval(read("nashorn-polyfill.js"));
            nashorn.eval(read(bundleJs));
        } catch (ScriptException e) {
            throw new IllegalStateException("could not init nashorn", e);
        }
    }

    public String renderCommentBox(List<Comment> comments) {
        try {
            Object html = nashorn.invokeFunction("renderServer", comments);
            return String.valueOf(html);
        } catch (Exception e) {
            throw new IllegalStateException("failed to render react component",
                    e);
        }
    }

    private Reader read(String path) {
        InputStream in = getClass().getClassLoader().getResourceAsStream(path);
        if (in == null) {
            throw new IllegalArgumentException(path);
        }
        return new InputStreamReader(in);
    }
}
