package ru.leb.java_3_at_24_03_2022.server;

public interface AuthService {
    void start();

    String getNickByLoginPass(String login, String pass);

    void stop();
}