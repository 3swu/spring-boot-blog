package com.wulei.blog.util;

import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.ParserEmulationProfile;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;
import com.vladsch.flexmark.util.misc.Extension;

import java.util.Arrays;

public class MarkdownParser {
    public static String mdToHTML(String mdStr) {
        MutableDataSet options = new MutableDataSet();
        options.setFrom(ParserEmulationProfile.MARKDOWN);
        options.set(Parser.EXTENSIONS, Arrays.asList(
                new Extension[] {TablesExtension.create()}));
        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();

        Node document = parser.parse(mdStr);
        String html = renderer.render(document);
        return html;
    }

    public static String addCss(String html, String css) {
        StringBuilder sb = new StringBuilder();
        sb.append(css)
                .append('\n')
                .append("<div>")
                .append('\n')
                .append(html)
                .append('\n')
                .append("</div>");
        return sb.toString();
    }
}