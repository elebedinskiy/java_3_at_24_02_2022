package ru.leb.java_3_at_24_03_2022.client;

@FunctionalInterface
public interface Callback {
    void callback(Object... args);
}