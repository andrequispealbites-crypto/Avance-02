package main;

import controllers.HomeController;
import core.Controller;

public class Main {

    public static void main(
            String[] args) {

        Controller controller =
                new HomeController();

        controller.run();
    }
}