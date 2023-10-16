/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package modul9hanifah;

/**
 *
 * @author RPL SMKN 4 BOGOR
 */
//SITI NUR HANIFAH XI PPLG 2


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Modul9hanifah extends JFrame {
    private JTextField textField;
    private double firstOperand = 0;
    private String operator = "";
    private boolean isNewCalculation = true;
    
    public Modul9hanifah() {
        setTitle("Kalkulator sederhana");
        setSize(300,400);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        textField = new JTextField();
        textField.setFont(new Font("Arial",Font.PLAIN,24));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        
        add(textField,BorderLayout.NORTH);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4,4));
        String[] buttonLabels = {
                "7","8","9","/",
                "4","5","6","*",
                "1","2","3","-",
                "0","C","=","+",
        };
        for (String label : buttonLabels)
        {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial",Font.PLAIN,18));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    onButtonClick(label);
                }
            });
            //Tambahkan MouseListener
            button.addMouseListener(new MouseListener(){
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Handle klik mouse disini (opsional)
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                    // Handle tombol ditekan
                    
                }
                @Override
                public void mouseReleased(MouseEvent e) {
                    //Handle tombol dilepas
                }
                @Override
                public void mouseEntered(MouseEvent e){
                    //Handle ketika mouse masuk ke tombol
                }
                
                @Override
                public void mouseExited(MouseEvent e){
                    //Handle ketika mouse masuk ke tombol
                }
            });
            buttonPanel.add(button);
        }
        add(buttonPanel,BorderLayout.CENTER);
        setVisible(true);
    }
    private void onButtonClick(String label){
        if(isNewCalculation) {
            textField.setText("");
            isNewCalculation = false;
        }
        if (label.matches("[0-9]")){
            textField.setText(textField.getText() + label);
        } else if (label.equals("C")) {
            textField.setText("");
            firstOperand = 0;
            operator = "";
        } else if (label.equals("=")){
            double secondOperand = Double.parseDouble(textField.getText());
            double result = calculate(firstOperand,secondOperand,operator);
            
            textField.setText(String.valueOf(result));
            isNewCalculation = true;
            firstOperand = result;
        } else {
            if (!operator.isEmpty()){
                double secondOperand = Double.parseDouble(textField.getText());
                firstOperand = calculate(firstOperand,secondOperand,operator);
                
                textField.setText(String.valueOf(firstOperand));
                
            } else {
                firstOperand = Double.parseDouble(textField.getText());
            }
            operator = label;
            isNewCalculation = true;
        }
    }
    private double calculate(double num1,double num2, String operator){
        switch (operator){
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 != 0){
                    return num1 / num2;
                } else {
                    
                    JOptionPane.showMessageDialog(this, "Tidak bisa membagi dengan nol!","ERROR",JOptionPane.ERROR_MESSAGE);
                    return num1;
                }
            default:
                return num2;
            
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run(){
                new Modul9hanifah();
            }
        });
    }
    
}
