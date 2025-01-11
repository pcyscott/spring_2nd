package com.mysite.lect.Util;

import org.springframework.stereotype.Component;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

@Component
public class CommonUtil {
    public String markdown(String markdown){
        Parser paser = Parser.builder().build();
        Node document = paser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }
}
