package by.epamtc.ivangavrilovich.shop.controller;

import by.epamtc.ivangavrilovich.shop.controller.impl.RedirectFromWelcomePage;
import by.epamtc.ivangavrilovich.shop.controller.impl.Register;
import by.epamtc.ivangavrilovich.shop.controller.impl.SignIn;
import by.epamtc.ivangavrilovich.shop.controller.impl.WrongCommand;

import java.util.HashMap;

public class CommandProvider {
    private final HashMap<String, Command> commands = new HashMap<>();
    private final static CommandProvider instance = new CommandProvider();

    private CommandProvider(){
        commands.put("SIGN_IN", new SignIn());
        commands.put("REGISTER", new Register());
        commands.put("REDIRECT_FROM_WELCOME_PAGE", new RedirectFromWelcomePage());
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
