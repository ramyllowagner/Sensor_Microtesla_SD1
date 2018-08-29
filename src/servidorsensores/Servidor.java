package servidorsensores;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ramyllo
 */
public class Servidor {

    static String ip;

    static Scanner s;
    static String c1, c2, c3;

    static Monitor monitor = new Monitor();
    //int valores[] = new int[3];
    static String valores[] = new String[3];

    public static void main(String[] args) throws IOException {

        monitor.setVisible(true);
        //rodaServidor();
        teste();
    }

    public static void teste() throws IOException {
        ServerSocket servidor = new ServerSocket(4000);

        int cont1 = 1;
        int cont2 = 1;
        int cont3 = 1;
        int p = 0, q = 0, r = 0;

        while (true) {

            Socket cliente = new Socket();

            try {

                ip = servidor.accept().getInetAddress().getCanonicalHostName();

                cliente = servidor.accept();
                if (cont1 == 1) {
                    c1 = cliente.getInetAddress().getHostAddress();
                    cont1 = 0;
                } else {
                    if (!c1.equals(cliente.getInetAddress().getHostAddress()) && cont2 == 1) {
                        c2 = cliente.getInetAddress().getHostAddress();
                        cont2 = 0;
                    } else if (!c1.equals(cliente.getInetAddress().getHostAddress()) && !c2.equals(cliente.getInetAddress().getHostAddress()) && cont3 == 1) {
                        c3 = cliente.getInetAddress().getHostAddress();
                        cont3 = 0;
                    }
                }

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

            s = new Scanner(cliente.getInputStream());

            if (cliente.getInetAddress().getHostAddress().equals(c1)) {

                valores[0] = s.nextLine();
                p = 1;
                System.out.println("Dispositivo 1: ");
            }
            if (cliente.getInetAddress().getHostAddress().equals(c2)) {

                valores[1] = s.nextLine();
                q = 1;
                System.out.println("Dispositivo 2: ");
            }

            if (cliente.getInetAddress().getHostAddress().equals(c3)) {

                //valores[2] = s.nextLine();
                valores[2] = "0.0";

                r = 1;
                System.out.println("Dispositivo 3: ");
            }

            valores[2] = "0.0";

            if (p == 1 && q == 1 && r == 1) {
                monitor.monitorSensores(valores);
                p = 0;
                q = 0;
                r = 0;
            }

        }

    }
}

/*
    public static void rodaServidor() {

        try {
            //System.out.println("Estou na Escuta");

            ServerSocket servidor = new ServerSocket(4000);

            while (true) {
                try (Socket meuSocket = servidor.accept()) {
                    DataInputStream fluxo_entrada = new DataInputStream(meuSocket.getInputStream());

                    String sensorNow = fluxo_entrada.readUTF();
                    int media = Integer.parseInt(sensorNow);

                    //campoMsg.setText("\n"+ msg_texto);
                    System.out.println(media);

                    monitor.monitorSensores(media);
                    //media.setText(Integer.toString(media_sensores));
                }
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
 */
