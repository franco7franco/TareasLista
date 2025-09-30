package app;

import app.dao.TareaDAO;
import app.dao.TareaSQL;
import app.service.Servicios;
import app.ui.Interfaz;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class TareaGit {
    public static void main(String[] args) throws Exception {
        var conn = TareaSQL.connect();
        var dao = new TareaDAO(conn);
        dao.createTable();

        var servicio = new Servicios(dao);

        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Screen screen = new com.googlecode.lanterna.screen.TerminalScreen(terminal);
        screen.startScreen();

        try {
            new Interfaz(servicio, screen).showMainMenu();
        } finally {
            screen.stopScreen();
        }
    }
}