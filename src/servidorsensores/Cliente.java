/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorsensores;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author ramyllo
 */
public class Cliente extends JFrame implements ActionListener {

    JTextArea campoMsg = new JTextArea();
    JTextField campoTexto = new JTextField();
    JLabel msg = new JLabel("Menssagem");
    JButton botaoEnviar = new JButton("Enviar");

    public Cliente() {
        super("Cliente");
        Container JanelaCliente = getContentPane();
        JanelaCliente.setLayout(null);
        JanelaCliente.setBackground(java.awt.Color.darkGray);

        JanelaCliente.add(msg);
        msg.setSize(100, 20);
        msg.setLocation(75, 50);
        msg.setForeground(java.awt.Color.WHITE);

        JanelaCliente.add(campoTexto);
        campoTexto.setSize(210, 20);
        campoTexto.setLocation(20, 80);

        JanelaCliente.add(botaoEnviar);
        botaoEnviar.setSize(100, 20);
        botaoEnviar.setLocation(75, 110);
        botaoEnviar.addActionListener(this);

        JanelaCliente.add(campoMsg);
        campoMsg.setSize(230, 140);
        campoMsg.setLocation(10, 130);

        setSize(250, 300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Cliente();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //////
        try {
            Socket soc = new Socket("127.0.0.1", 4000);
            PrintWriter writer = new PrintWriter(soc.getOutputStream());
            writer.write(campoTexto.getText());
            // writer.flush();
            //writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        /*
        try {

            Socket meuSocket = new Socket("127.0.0.1", 4000);

            DataOutputStream fluxo_saida = new DataOutputStream(meuSocket.getOutputStream());
            //String texto = sc.toString();
            fluxo_saida.writeUTF(campoTexto.getText());

            fluxo_saida.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }  */
    }
}
