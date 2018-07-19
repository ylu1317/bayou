package edu.rice.pliny.apitrans.examples;

import com.hp.gagawa.java.elements.Html;
import com.hp.gagawa.java.elements.Head;
import com.hp.gagawa.java.elements.Title;
import com.hp.gagawa.java.elements.Body;
import com.hp.gagawa.java.elements.H1;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.P;
import j2html.tags.ContainerTag;
import org.junit.Test;

import java.io.*;

import static j2html.TagCreator.*;

public class HTML3 {
    String content = "foobar";
    String header = "Header";
    String title = "Title";

    String true_html = "<html><head><title>Title</title></head><body><h1>Header</h1><div><p>foobar</p></div></body></html>";
    String true_html2 = "<html xmlns=\"http://www.w3.org/1999/xhtml\"><head><title>Title</title></head><body><h1>Header</h1><div><p>foobar</p></div></body></html>";

    @Test
    public void create_html() throws IOException {
        ContainerTag title = title(this.title);
        ContainerTag head = head(title);

        ContainerTag h1 = h1(this.header);
        ContainerTag p = p(this.content);
        ContainerTag div = div(p);
        ContainerTag body = body(h1, div);

        ContainerTag html = html(head, body);
        String str = html.render();
        System.out.println(str);
    }

    @Test
    public void create_html2() throws IOException {
        Html html = new Html();

        Head head = new Head();
        Title title = new Title();
        title.appendText(this.title);
        head.appendChild(title);
        html.appendChild(head);

        Body body = new Body();
        H1 h1 = new H1();
        h1.appendText(this.header);
        Div div = new Div();
        P p = new P();
        p.appendText(this.content);
        div.appendChild(p);
        body.appendChild(h1);
        body.appendChild(div);

        html.appendChild(body);

        String str = html.write();
        System.out.println(str);
    }

}
