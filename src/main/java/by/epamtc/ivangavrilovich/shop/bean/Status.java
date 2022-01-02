package by.epamtc.ivangavrilovich.shop.bean;

import java.util.HashMap;
import java.util.Map;

public class Status {
    private static final Map<Integer, String> statuses = new HashMap<>();

    public static String getStatus(Integer id) {
        String status = statuses.get(id);
        return status == null ? "Статуса не найдено" : status;
    }

    public static void addStatus(int id, String status) {
        statuses.put(id, status);
    }
}
