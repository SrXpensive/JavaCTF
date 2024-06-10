package ctf;

import entidades.Equipo;
import entidades.Junior;
import entidades.Participante;
import exception.CTFException;
import io.Leer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

public class Ctf {
    static ArrayList<Participante> participantes = new ArrayList<>();
    static ArrayList<Equipo> equipos = new ArrayList<>();
    static int intentos = 40;
    static int puntosBajo = 5;
    static int puntosMedio = 15;
    static int puntosAlto = 30;
    public static void main(String[] args) {
        int opcion;
       do{
           System.out.println("MENU");
           System.out.println("________");
           System.out.println("1. Configurar");
           System.out.println("2. COMPETIR");
           System.out.println("0. Salir");
           opcion = Leer.leerEntero("Introduce una opción: ");
           switch(opcion){
               case 1:
                   try{
                       configurar();
                   }catch(CTFException e){
                        System.out.println(e.getMessage());
                   }
                   break;
               case 2:

           }
       }while(opcion!=0);
    }
    public static void configurar() throws CTFException {
        int opcion2;
        do{
            System.out.println("1. Parámetros");
            System.out.println("2. Gestionar Participantes");
            System.out.println("3. Gestionar equipos");
            System.out.println("4. Importar datos");
            System.out.println("5. Exportar datos");
            System.out.println("0. Salir");
            opcion2 = Leer.leerEntero("Introduce una opción: ");
            switch(opcion2){
                case 1:
                    intentos = Leer.leerEntero("Introduce el número de intentos: ");
                    puntosBajo = Leer.leerEntero("Introduce los puntos obtenidos por resolver un reto de nivel bajo: ");
                    puntosMedio = Leer.leerEntero("Introduce los puntos obtenidos por resolver un reto de nivel medio: ");
                    puntosAlto = Leer.leerEntero("Introduce los puntos obtenidos por resolver un reto de nivel alto: ");
                    break;
                case 2:
                    int opcion3;
                    System.out.println("1. Listar participantes");
                    System.out.println("2. Introducir participantes");
                    opcion3 = Leer.leerEntero("Introduce una opción: ");
                    switch(opcion3){
                        case 1:
                            Collections.sort(participantes);
                            for(Participante p: participantes){
                                System.out.println("Nombre: "+p.getNombre()+", Equipo: "+p.getEquipo());
                            }
                        break;
                        case 2:
                            Equipo e = null;
                            String nombre = Leer.leerTexto("Introduce el nombre del participante: ");
                            for(Participante p: participantes){
                                if(p.getNombre().equals(nombre)){
                                    throw new CTFException("El participante ya existe");
                                }
                            }
                            String equipo = Leer.leerTexto("Introduce el nombre del equipo del participante: ");
                            for(Equipo eq:equipos){
                                if(eq.getNombre().equals(equipo)){
                                    e = eq;
                                    break;
                                }else{
                                    throw new CTFException("El equipo no existe");
                                }
                            }
                            System.out.println("1. Junior");
                            System.out.println("2. Especialista");
                            int rango = Leer.leerEntero("Introduce el rango del participante: ");
                            if(rango == 1){
                                int bon = Leer.leerEntero("Introduce la bonificación del participante Junior: ");
                                Participante p = new Junior(nombre,e,bon);
                                participantes.add(p);
                            }

                            break;

                    }
            }
        }while(opcion2!=0);
    }
    public static void conectar(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CTF","root","root");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}