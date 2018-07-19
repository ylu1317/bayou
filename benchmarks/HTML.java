package edu.rice.pliny.apitrans.examples;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class HTML {
    String filename = "code_completion/src/test/resources/apitrans/html/jsoup.html";

    public HTML() {}

    @Test
    public void read_text() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String         line = null;
        StringBuilder  stringBuilder = new StringBuilder();
        String         ls = System.getProperty("line.separator");
        while((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        String content = stringBuilder.toString();
        String selector = "a[href]";
        String attr = "href";

        Document doc = Jsoup.parse(content);
        Elements links = doc.select(selector);
        Element link = links.first();
        String href = link.attr(attr);
        assertEquals("/", href);
    }

    @Test
    public void read_text2() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String         line = null;
        StringBuilder  stringBuilder = new StringBuilder();
        String         ls = System.getProperty("line.separator");
        while((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        String content = stringBuilder.toString();
        String attr = "href";

        HtmlCleaner cleaner = new HtmlCleaner();
        TagNode node = cleaner.clean(content);
        TagNode[] links = node.getElementsHavingAttribute(attr, true);
        TagNode link = links[0];
        String href = link.getAttributeByName(attr);
        assertEquals("/", href);
    }

}
