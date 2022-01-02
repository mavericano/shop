package by.epamtc.ivangavrilovich.shop.bean;

import java.util.HashMap;
import java.util.Map;

public class Role {
    private static final Map<Integer, String> roles = new HashMap<>();

    public static String getRole(Integer id) {
        String role = roles.get(id);
        return role == null ? "Роли не найдено" : role;
    }

    public static void addRole(int id, String role) {
         roles.put(id, role);
    }
}
