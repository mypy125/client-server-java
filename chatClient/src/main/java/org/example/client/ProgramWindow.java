package org.example.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProgramWindow extends JFrame implements ActionListener {
    private  Network network;
    private static final int WIDTH = 550;
    private static final int HEIGHT = 400;
    private JFrame frame = new JFrame();
    private JLabel label = new JLabel();
    private JPanel panel = new JPanel();
    private JTextArea textArea = new JTextArea();
    private JTextField textField = new JTextField();
    private JTextField nik = new JTextField("Gor");

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ProgramWindow();
            }
        });

    }

    private ProgramWindow(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIDTH,HEIGHT);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);

        textArea.setEnabled(false);
        textArea.setLineWrap(true);
        textField.addActionListener(this);
        add(textArea, BorderLayout.CENTER);
        add(textField, BorderLayout.SOUTH);
        add(nik, BorderLayout.NORTH);

        setVisible(true);
        try {
            network = new Network(args -> {
                textArea.append((String) args[0]);
            });
        } catch (Exception e) {
           e.printStackTrace();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = textField.getText();
        if(msg.equals(""))
            return;
        textField.setText(null);
        network.sendMassage(nik.getText() + ":" + msg);
    }
}