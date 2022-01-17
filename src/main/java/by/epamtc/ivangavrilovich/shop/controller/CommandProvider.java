package by.epamtc.ivangavrilovich.shop.controller;

import by.epamtc.ivangavrilovich.shop.controller.impl.*;

import java.util.HashMap;

public class CommandProvider {
    private final HashMap<String, Command> commands = new HashMap<>();
    private final static CommandProvider instance = new CommandProvider();

    private CommandProvider(){
        commands.put("SIGN_IN", new SignIn());
        commands.put("REGISTER", new Register());
        commands.put("VIEW_HOME_PAGE", new ViewHomePage());
        commands.put("VIEW_ALL_PRODUCTS", new ViewAllProducts());
        commands.put("CHANGE_LANGUAGE", new ChangeLanguage());
        commands.put("WRONG_COMMAND", new WrongCommand());
    }

    public static CommandProvider getInstance() {
        return instance;
    }

    public Command provideCommand(String name) {
        Command toProvide = commands.get(name);
        return toProvide == null ? commands.get("WRONG_COMMAND") : toProvide;
    }

    public void registerCommand(String name, Command toRegister) {
        commands.put(name, toRegister);
    }
}
