// proyecto MULTITAREAS

// implemmentacion de una clase tarea con id y nombre
// actividades o  tareas

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.swing.*;

public class Tareas{
    //
    private int id;
    private String nombre;

    public Tarea(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Tarea{id=" + id + ", nombre='" + nombre + "'}";
    }
    public static void main(String[] args) {
        List<Tarea> tareas = new ArrayList<>();

        tareas.add(new Tarea(1, "Comprar pan"));
        tareas.add(new Tarea(2, "Estudiar Java"));
        tareas.add(new Tarea(3, "Llamar a mamá"));

        for (Tarea tarea : tareas) {
            System.out.println(tarea);
        }
    }
}
