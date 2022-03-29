package ru.leb.java_3_at_24_03_2022.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Network implements Closeable {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private Callback callOnMsgReceived;
    private Callback callOnAuthenticated;
    private Callback callOnException;
    private Callback callOnCloseConnection;
    private ObservableList<String> userlist;

    public void setCallOnMsgReceived(Callback callOnMsgReceived) {
        this.callOnMsgReceived = callOnMsgReceived;
    }

    public void setCallOnAuthenticated(Callback callOnAuthenticated) {
        this.callOnAuthenticated = callOnAuthenticated;
    }

    public void setCallOnException(Callback callOnException) {
        this.callOnException = callOnException;
    }

    public void setCallOnCloseConnection(Callback callOnCloseConnection) {
        this.callOnCloseConnection = callOnCloseConnection;
    }

    public void sendAuth(String login, String password) {
        try {
            connect();
            out.writeUTF("/auth " + login + " " + password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<String> getUserList () {
        return userlist;
    }

    public void setUserlist (String msg) {
        userlist = null;
        String[] list = msg.split(" ");
        ArrayList<String> nicklist = new ArrayList<>();
        for (int i = 1; i < list.length; i++){
            nicklist.add(list[i] + " ");
        }
        userlist = FXCollections.observableArrayList(nicklist);
    }

    public void connect() {
        if (socket != null && !socket.isClosed()) {
            return;
        }

        try {
            socket = new Socket("localhost", 8189);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            Thread clientListenerThread = new Thread(() -> {
                try {
                    while (true) {
                        String msg = in.readUTF();
                        if (msg.startsWith("/authok ")) {
                            callOnAuthenticated.callback(msg.split("\\s")[1]);
                            break;
                        } else {
                            callOnException.callback(msg);
                        }
                    }
                    while (true) {
                        String msg = in.readUTF();
                        // в этом месте будем читать список пользователей
                        if (msg.startsWith("/userlist ")) {
                            setUserlist(msg);
                            msg = "";
                        }
                        if (msg.equals("/end")) {
                            break;
                        }
                        if (msg != ""){
                            callOnMsgReceived.callback(msg);
                        }
                    }
                } catch (IOException e) {
                    callOnException.callback("Соединение с сервером разорвано");
                } finally {
                    close();
                }
            });
            clientListenerThread.setDaemon(true);
            clientListenerThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean sendMsg(String msg) {
        if (out == null) {
            callOnException.callback("Соединение с сервером не установлено");
        }

        try {
            out.writeUTF(msg);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void close() {
        callOnCloseConnection.callback();
        close(in, out, socket);
    }

    private void close(Closeable... objects) {
        for (Closeable o : objects) {
            try {
                o.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}