package by.epamtc.ivangavrilovich.shop.controller;

import by.epamtc.ivangavrilovich.shop.controller.impl.*;

import java.util.HashMap;

public class CommandProvider {
    private final HashMap<String, Command> commands = new HashMap<>();
    private final static CommandProvider INSTANCE = new CommandProvider();

    private CommandProvider(){
        commands.put("SIGN_IN", new SignIn());
        commands.put("VIEW_SIGN_IN", new ViewSignIn());
        commands.put("SIGN_OUT", new SignOut());
        commands.put("REGISTER", new Register());
        commands.put("VIEW_REGISTER", new ViewRegister());
        commands.put("VIEW_HOME_PAGE", new ViewHomePage());
        commands.put("VIEW_ADMIN_SCREEN", new ViewAdminScreen());
        commands.put("VIEW_ALL_PRODUCTS", new ViewAllProducts());
        commands.put("VIEW_SINGLE_PRODUCT", new ViewSingleProduct());
        commands.put("VIEW_CART", new ViewCart());
        commands.put("ADD_TO_CART", new AddToCart());
        commands.put("REMOVE_FROM_CART", new RemoveFromCart());
        commands.put("DECREASE_QUANTITY", new DecreaseQuantity());
        commands.put("INCREASE_QUANTITY", new IncreaseQuantity());
        commands.put("EDIT_CURRENT_PRODUCT", new EditCurrentProduct());
        commands.put("SUBMIT_EDITING", new SubmitEditing());
        commands.put("SUBMIT_ORDER", new SubmitOrder());
        commands.put("SUBMIT_USER_CHANGES", new SubmitUserChanges());
        commands.put("SUBMIT_PRODUCT_CHANGES", new SubmitProductChanges());
        commands.put("SUBMIT_ORDER_CHANGES", new SubmitOrderChanges());
        commands.put("CHANGE_LANGUAGE", new ChangeLanguage());
        commands.put("SEARCH", new Search());
        commands.put("WRONG_COMMAND", new WrongCommand());
    }

    public static CommandProvider getInstance() {
        return INSTANCE;
    }

    public Command provideCommand(String name) {
        Command toProvide = commands.get(name);
        return toProvide == null ? commands.get("WRONG_COMMAND") : toProvide;
    }

    public void registerCommand(String name, Command toRegister) {
        commands.put(name, toRegister);
    }
}
