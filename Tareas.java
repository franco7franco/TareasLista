
// proyecto TAREAS
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

import javax.swing.JOptionPane;

import java.swing.*;

public class Tareas{
    public static void main(String[] args) {
    	String men="";
    	int op;
    	Solucion sol=new Solucion();
    	Personas p=null,u=null,pm=null,pf=null;
    	do{
    		men=JOptionPane.showInputDialog("1.- ACCERDER \n2.- CREAR CUENTA\n3.-\nElija opcion");
    		op=Integer.parseInt(men);
    		/*switch(op){
    			case 1: p=sol.ingreso(p,u); 
    				    break;
    			case 2: sol.ver(p,"\nLista Original\n");
    				    break;
    			case 3: pm=sol.pasar(p,null,null,"m");
    			        pf=sol.pasar(p,null,null,"f");
    			        sol.ver(p,"\nLista Original\n");
    			        sol.ver(pm,"\nLista Hombres\n");
    			        sol.ver(pf,"\nLista Mujeres\n");
    				    break;
    		}*/
    	}while(op!=4);
    }
}
