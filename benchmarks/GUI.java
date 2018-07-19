package edu.rice.pliny.apitrans.examples;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.junit.Test;

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import javax.swing.JButton;


public class GUI {

    @Test
    public void window() {
        String frame_title = "frame";
        String label = "label";
        String btn_label = "btn";

        // Create the window
        JFrame f = new JFrame(frame_title);
        // Sets the behavior for when the window is closed
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // Add a layout manager so that the button is not placed on top of the label
        f.setLayout(new FlowLayout());
        // Add a label and a button
        JLabel jlabel = new JLabel(label);
        f.add(jlabel);

        JButton jbtn = new JButton(btn_label);
        f.add(jbtn);

        // Arrange the components inside the window
        f.pack();
        // By default, the window is not visible. Make it visible.
        f.setVisible(true);
    }

    @Test
    public void window2() {
        String frame_title = "frame";
        String label = "label";
        String btn_label = "btn";

        Stage stg = new Stage();
        stg.setTitle(frame_title);

        StackPane root = new StackPane();
        ObservableList<Node> children = root.getChildren();

        Button btn = new Button(btn_label);
        children.add(btn);
        Label lbl = new Label(label);
        children.add(lbl);

        Scene scene = new Scene(root, 300, 250);
        stg.setScene(scene);
        stg.show();



    }
}
