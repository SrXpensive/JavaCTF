package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Leer {
    private final static BufferedReader entradaConsola =
            new java.io.BufferedReader(new java.io.InputStreamReader(System.in));

    /**
    *Lee un texto del teclado.
    * @param mensaje Texto que se pasa al usuario
    * @return el texto introducido. Blanco en caso de error
     */
    public static String leerTexto(String mensaje){
        String respuesta = null;
        do{
            try{
                System.out.print(mensaje);
                respuesta = entradaConsola.readLine();
            }
            catch (IOException ex){
                return "";
            }
        } while(respuesta == null);
        return respuesta;
    }

    /**
    * Introducción de números enteros
    * @param mensaje Mensaje que se da al usuario
    * @return un entero con el valor
     */
    public static int leerEntero(String mensaje){
        int n= 0;
        boolean conseguido = false;
        while(!conseguido){
            try{
                n= Integer.parseInt(leerTexto(mensaje));
                conseguido=true;
            }
            catch(NumberFormatException ex){
                System.out.println("Debes introducir un número correcto");
            }
        }
        return n;
    }
    /**
    * Introducción de números decimales
    * @param mensaje Mensaje que se da al usuario
    * @return un decimal con el valor
     */
    public static double leerDouble(String mensaje){
        double n=0.0;
        boolean conseguido = false;
        while (!conseguido){
            try{
                n=Double.parseDouble(leerTexto(mensaje));
                conseguido = true;
            }
            catch(NumberFormatException ex){
                System.out.println("Debes introducir un número correcto");
            }
        }
        return n;
    }

    /**
     * Introducción de fecha
     * @param mensaje Mensaje que se le da al usuario
     * @return un objeto fecha
     */
    public static Date leerFecha(String mensaje){
        boolean f = false;
        Date d = new Date();
        SimpleDateFormat fecha = new SimpleDateFormat("dd/M/yyyy");
        do{
            try{
                String dia = leerTexto(mensaje);
                d = fecha.parse(dia);
                f = true;
            }catch(ParseException e){
                System.out.println("Debes introducir la fecha en el formato indicado");
            }
        }while(!f);
        return d;
    }
}

