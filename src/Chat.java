
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Scanner;

/**
 *
 * @author lewismcdonald
 */
public class Chat extends javax.swing.JFrame {

    /**
     * Creates new form Chat
     */
    static ServerSocket serverSock;
    static Socket clientSock;
    ArrayList ClientOutput;
    ArrayList<String> users;

    public class HandleClient implements Runnable {

        BufferedReader reader;
        Socket sock;
        PrintWriter client;

        public HandleClient(Socket clientSocket, PrintWriter user) {
            client = user;
            try {
                sock = clientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
            } catch (Exception ex) {
                msg_area.append("Something went wrong... \n");
            }

        }

        @Override
        public void run() {
            String message, connect = "Connect", disconnect = "Disconnect", chat = "Chat";
            String[] data;

            try {
                while ((message = reader.readLine()) != null) {
                    msg_area.append("Received: " + message + "\n");
                    data = message.split(":");

                    for (String token : data) {
                        msg_area.append(token + "\n");
                    }
                    if (data[2].equals(connect)) {
                        Announce((data[0] + ":" + data[1] + ":" + chat));
                        addUser(data[0]);
                    } else if (data[2].equals(disconnect)) {
                        Announce((data[0] + ":has disconnected." + ":" + chat));
                        removeUser(data[0]);
                    } else if (data[2].equals(chat)) {
                        Announce(message);
                    } else {
                        msg_area.append("No Conditions were met. \n");
                    }
                }
            } catch (Exception ex) {
                msg_area.append("Lost a connection. \n");
                ex.printStackTrace();
                ClientOutput.remove(client);
            }
        }
    }

    public Chat() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        msg_area = new javax.swing.JTextArea();
        startButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        endButton = new javax.swing.JButton();
        online_users = new javax.swing.JButton();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        msg_area.setEditable(false);
        msg_area.setBackground(new java.awt.Color(222, 222, 222));
        msg_area.setColumns(20);
        msg_area.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        msg_area.setRows(5);
        msg_area.setText("Server Panel");
        jScrollPane2.setViewportView(msg_area);

        startButton.setText("Start Server");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        clearButton.setText("Clear");
        clearButton.setName(""); // NOI18N
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        endButton.setText("End Server");
        endButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endButtonActionPerformed(evt);
            }
        });

        online_users.setText("Current Users");
        online_users.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                online_usersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(online_users, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(clearButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(96, 96, 96)
                        .addComponent(endButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(endButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(startButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(online_users, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed

        Thread Starter = new Thread(new ServerStart());
        Starter.start();
        msg_area.append("\n Server has started... \n");

    }//GEN-LAST:event_startButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed

        msg_area.setText("");
    }//GEN-LAST:event_clearButtonActionPerformed

    private void endButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endButtonActionPerformed
        try {
            Thread.sleep(4000);
        } catch (Exception ex) {

        }
        msg_area.setText("");
        msg_area.append("Server has stopped.");
    }//GEN-LAST:event_endButtonActionPerformed

    private void online_usersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_online_usersActionPerformed
        // TODO add your handling code here:
        msg_area.append("\n Online: \n");
        for (String current_user : users) {
            msg_area.append(current_user);
            msg_area.append("\n");
        }
    }//GEN-LAST:event_online_usersActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Chat().setVisible(true);
            }
        });
    }

    public class ServerStart implements Runnable {

        @Override
        public void run() {
            ClientOutput = new ArrayList();
            users = new ArrayList();

            try {
                serverSock = new ServerSocket(1201);

                while (true) {
                    clientSock = serverSock.accept();
                    PrintWriter writer = new PrintWriter(clientSock.getOutputStream());
                    ClientOutput.add(writer);

                    Thread listener = new Thread(new HandleClient(clientSock, writer));
                    listener.start();
                    msg_area.append("Connection Recieved. \n");
                }
            } catch (Exception ex) {
            }
        }
    }

    public void addUser(String data) {
        String message, add = ": :Connect", done = "Server: :Done", name = data;
        msg_area.append("Before " + name + " added. \n");
        users.add(name);
        msg_area.append("After " + name + " added. \n");
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);

        for (String token : tempList) {
            message = (token + add);
            Announce(message);
        }
        Announce(done);
    }

    public void removeUser(String data) {
        String message, add = ": :Connect", done = "Server: :Done", name = data;
        users.remove(name);
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);

        for (String token : tempList) {
            message = (token + add);
            Announce(message);
        }
        Announce(done);
    }

    public void Announce(String message) {
        Iterator it = ClientOutput.iterator();
        while (it.hasNext()) {
            try {
                PrintWriter writer = (PrintWriter) it.next();
                writer.println(message);
                msg_area.append("Sending: " + message + "\n");
                writer.flush();
                msg_area.setCaretPosition(msg_area.getDocument().getLength());

            } catch (Exception ex) {
                msg_area.append("Error echoeing the data. \n");
            }
        }
    }

    /*String msgin = "";
        try {
            serverSocket = new ServerSocket(1201); //Server starts with port nuber 1201
            socket = serverSocket.accept(); //Server accepts connections.

            datain = new DataInputStream(socket.getInputStream());
            dataout = new DataOutputStream(socket.getOutputStream());

            while (!msgin.equals("exit")) {
                msgin = datain.readUTF();
                msg_area.setText(msg_area.getText().trim() + "\n Client: \t" + msgin); //display the message from client
            }

        } catch (Exception e) {

        }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearButton;
    private javax.swing.JButton endButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private static javax.swing.JTextArea msg_area;
    private javax.swing.JButton online_users;
    private javax.swing.JButton startButton;
    // End of variables declaration//GEN-END:variables
}
