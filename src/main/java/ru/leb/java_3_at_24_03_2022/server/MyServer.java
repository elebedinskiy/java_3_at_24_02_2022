package ru.leb.java_3_at_24_03_2022.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class MyServer {
    private final int PORT = 8189;

    private Map<String, ClientHandler> clients;
    private String nicklist;
    private AuthService authService;

    public AuthService getAuthService() {
        return authService;
    }

    public String getNicklist() {
        return nicklist;
    }

    public void updateNickList() {
        try {
            nicklist = "";
            for (String key : clients.keySet()) {
                nicklist += key + " ";
            }
        } catch (NullPointerException e) {
            //e.printStackTrace();
        }
    }

    public MyServer() {
        try (ServerSocket server = new ServerSocket(PORT)) {
            authService = new BaseAuthService();
            authService.start();
            clients = new HashMap<>();

            while (true) {
                System.out.println("Сервер ожидает подключения");
                Socket socket = server.accept();
                System.out.println("Клиент подключился");
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            System.out.println("Ошибка в работе сервера");
        } finally {
            if (authService != null) {
                authService.stop();
            }
        }
    }

    public synchronized boolean isNickBusy(String nick) {
        return clients.containsKey(nick);
    }

    public synchronized void broadcastMsg(String msg) {
        for (ClientHandler o : clients.values()) {
            o.sendMsg(msg);
        }
    }

    // метод разошлёт обновлённый список пользователей;
    public void sendUserList() {
        broadcastMsg("/userlist " + getNicklist());
    }

    public synchronized void broadcastMsg(String from, String msg) {
        broadcastMsg(formatMessage(from, msg));
    }

    public synchronized void broadcastMsgPrivate(String from, String to, String msg) {
        String str = "private from " + from + ": " + msg;
        clients.get(to).sendMsg(str);
    }

    public synchronized void unsubscribe(ClientHandler o) {
        clients.remove(o.getName());
        updateNickList();
    }

    public synchronized void subscribe(ClientHandler o) {
        clients.put(o.getName(), o);
        updateNickList();
    }

    private String formatMessage(String from, String msg) {
        return from + ": " + msg;
    }
}