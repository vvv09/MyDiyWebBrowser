package com.valunskii.mysuperbrowser.main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Browser extends JFrame{
    private JTextField addressBar;
    private JEditorPane browserPage;

    public Browser() {
        super("Мой Супер Браузер");

        //созание адресной строки
        addressBar = new JTextField("Введи адрес, чувак!!!");
        //слушаем введение адреса и нажатие энтер
        addressBar.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        loadData(e.getActionCommand()); //передача введенных данных методу loadData

                    }
                }
        );
        add(addressBar, BorderLayout.NORTH);// размещаем адресную строку вверху окна


        //создание окна браузера
        browserPage = new JEditorPane();
        browserPage.setEditable(false); // не можем редактировать
        // слушатель нажатия гипперссылки(на отображаемой странице)
        browserPage.addHyperlinkListener(
                new HyperlinkListener() {
                    @Override
                    public void hyperlinkUpdate(HyperlinkEvent e) {
                        if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED){ //если на ссыллку нажали
                            loadData(e.getURL().toString());//передача введенных данных методу loadData
                        }
                    }
                }
        );
        add(new JScrollPane(browserPage), BorderLayout.CENTER);
        setSize(1024, 576);
        setVisible(true);

    }

    //загрузка данных в окно браузера
    private void loadData(String userText){
        try{
            browserPage.setPage(userText);
            addressBar.setText(userText);
        } catch (Exception e) {
            browserPage.setText("О чорт! Ссайтом \n" + "\t" + userText +
                    "\n \t\tчто-то не так :(");
        }

    }
}
