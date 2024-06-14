package ctf;

import entidades.*;
import exception.CTFException;
import io.Leer;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

public class Ctf {
    static ArrayList<Participante> participantes = new ArrayList<>();
    static ArrayList<Equipo> equipos = new ArrayList<>();
    static ArrayList<Equipo> ranking = new ArrayList<>();
    static ArrayList<Participante> miembros = new ArrayList<>();

    static int intentos = 20;
    static int puntosBajo = 5;
    static int puntosMedio = 15;
    static int puntosAlto = 30;
    static int rondas = 3;
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
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
                   try{
                       competir();
                   }catch(CTFException e){
                       System.out.println(e.getMessage());
                   }

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
                    rondas = Leer.leerEntero("Introduce el número de rondas máximo: ");
                    break;
                case 2:
                    int opcion3;
                    System.out.println("1. Listar participantes");
                    System.out.println("2. Introducir participantes");
                    System.out.println("3. Dar de baja participante");
                    System.out.println("4. Asignar participantes a un equipo");
                    System.out.println("0. Atrás");
                    opcion3 = Leer.leerEntero("Introduce una opción: ");
                    switch(opcion3){
                        case 1:
                            if(participantes.isEmpty()){
                                System.out.println("No hay participantes para mostrar");
                            }else{
                                Collections.sort(participantes);
                                for(Participante p: participantes){
                                    System.out.println("Nombre: "+p.getNombre()+", Equipo: "+p.getEquipo().getNombre());
                                }
                            }
                            break;
                        case 2:
                            Equipo e = null;
                            int cont=1;
                            if(equipos.isEmpty()){
                                System.out.println("Cada jugador debe ir en un equipo y todavía no hay ningún equipo, añade primero alguno");
                                break;
                            }
                            String nombre = Leer.leerTexto("Introduce el nombre del participante: ");
                            for(Participante p: participantes){
                                if(p.getNombre().equals(nombre)){
                                    throw new CTFException("El participante ya existe");
                                }
                            }
                            for(Equipo eq : equipos){
                                System.out.println(cont+" "+eq.getNombre());
                                cont++;
                            }
                            int indice = Leer.leerEntero("Introduce el equipo (número): ");
                            if(indice-1 > equipos.size()){
                                throw new CTFException("El equipo no existe");
                            }
                            e = equipos.get(indice-1);
                            System.out.println("1. Junior");
                            System.out.println("2. Especialista");
                            int rango = Leer.leerEntero("Introduce el rango del participante: ");
                            if(rango == 1){
                                int bon = Leer.leerEntero("Introduce la bonificación del participante Junior: ");
                                Participante junior = new Junior(nombre,e,bon);
                                participantes.add(junior);
                                e.asignarMiembro(junior);
                            }
                            if(rango == 2){
                                String especializacion = "";
                                int pen;
                                System.out.println("1. Web");
                                System.out.println("2. Reversing");
                                System.out.println("3. Stegano");
                                System.out.println("4. Networking");
                                System.out.println("5. Crypto");
                                System.out.println("6. Osint");
                                System.out.println("7. Scripting");
                                int esp = Leer.leerEntero("Introduce la especialización del participante Especialista: ");
                                switch(esp){
                                    case 1:
                                        especializacion = "Web";
                                        break;
                                    case 2:
                                        especializacion = "Reversing";
                                        break;
                                    case 3:
                                        especializacion = "Stegano";
                                        break;
                                    case 4:
                                        especializacion = "Networking";
                                        break;
                                    case 5:
                                        especializacion = "Crypto";
                                        break;
                                    case 6:
                                        especializacion = "Osint";
                                        break;
                                    case 7:
                                        especializacion = "Scripting";
                                        break;
                                }
                                pen = Leer.leerEntero("Introduce la penalización del especialista si falla (número de puntos): ");
                                Participante especialista = new Especialista(nombre,e,especializacion,pen);
                                participantes.add(especialista);
                                especialista.setnIntentos(intentos);
                                e.asignarMiembro(especialista);
                            }
                            break;
                        case 3:
                            int contador=1;
                            int opcion4;
                            for(Participante p: participantes){
                                System.out.println(contador+" "+p.getNombre());
                                contador++;
                            }
                            opcion4 = Leer.leerEntero("¿Qué participante quieres dar de baja? (escribir su número): ");
                            if(participantes.get(opcion4-1).getEquipo() != null){
                                System.out.println("Procediendo a eliminar al participante de su equipo...");
                                participantes.get(opcion4-1).desasignarEquipo(participantes.get(opcion4-1).getEquipo());
                                System.out.println("Participante sin equipo");
                            }else{
                                participantes.remove(participantes.get(opcion4-1));
                                System.out.println("Participante eliminado satisfactoriamente");
                            }
                            break;
                        case 4:
                            int contj = 1;
                            int conteq = 1;
                            int jugador;
                            int eq;
                            for(Participante p: participantes){
                                System.out.println(contj+" "+p.getNombre());
                                contj++;
                            }
                            jugador = Leer.leerEntero("¿A qué jugador quieres asignarle un equipo?(indica el número): ");
                            for (Equipo equi:equipos){
                                System.out.println(conteq+" "+equi.getNombre());
                                conteq++;
                            }
                            eq = Leer.leerEntero("¿Qué equipo quieres asignarle? (indica el número): ");
                            participantes.get(jugador-1).asignarEquipo(equipos.get(eq-1));
                            System.out.println("Equipo asignado exitosamente");
                            break;
                    }
                    break;
                case 3:
                    int opcion;
                    System.out.println("1. Listar equipos");
                    System.out.println("2. Dar de alta equipo");
                    System.out.println("3. Dar de baja equipo");
                    opcion = Leer.leerEntero("Introduce una opción: ");
                    switch(opcion){
                        case 1:
                            if(equipos.isEmpty()){
                                System.out.println("No hay equipos para mostrar");
                            }else{
                                Collections.sort(equipos);
                                for(Equipo e: equipos){
                                    System.out.println(e);
                                }
                            }
                            break;
                        case 2:
                            String nombre = Leer.leerTexto("Introduce el nombre del equipo: ");
                            Equipo e = new Equipo(nombre);
                            equipos.add(e);
                            System.out.println("Equipo añadido exitosamente");
                            break;
                        case 3:
                            if(equipos.isEmpty()){
                                System.out.println("No hay equipos para borrar");
                            }else{
                                int cont = 1;
                                int num;
                                for(Equipo eq: equipos){
                                    System.out.println(cont+" "+eq.getNombre());
                                }
                                num = Leer.leerEntero("¿Qué equipo quieres borrar? (introduce el número): ");
                                equipos.remove(equipos.get(num-1));
                                System.out.println("Equipo eliminado exitosamente");
                            }
                            break;
                    }
                    break;
                case 4:
                    try{
                        File f = new File("participantes.dat");
                        if(!f.exists()){f.createNewFile();}
                        FileInputStream fis = new FileInputStream(f);
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        while(fis.available() !=0){
                            participantes = (ArrayList<Participante>) ois.readObject();
                        }
                        ois.close();
                        fis.close();
                        for(Participante p : participantes){
                            if(p instanceof Especialista){
                                p.setnIntentos(intentos);
                            }
                        }
                    }catch(ClassNotFoundException | IOException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    try{
                        FileOutputStream fos = new FileOutputStream("participantes.dat");
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        for(Participante p : participantes){
                            p.setnIntentos(0);
                            p.setPuntosGanados(0);
                            if(p instanceof Especialista){
                                ((Especialista) p).setPenalizacion(0);
                            }
                            if(p instanceof Junior){
                                ((Junior)p).setBonificacion(0);
                            }
                        }
                        oos.writeObject(participantes);
                        oos.close();
                        fos.close();
                    }catch(IOException e){
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        }while(opcion2!=0);
    }
    public static void competir() throws CTFException{
        int inicial = (int)(Math.random()*equipos.size());
        int indice;
        File f = new File("ranking.dat");
        if(f.exists()){
            try{
                FileInputStream fis = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fis);
                while(fis.available() !=0){
                    ranking = (ArrayList<Equipo>) ois.readObject();
                }
                ois.close();
                fis.close();
            }catch(IOException | ClassNotFoundException e){
                System.out.println(e.getMessage());
            }
            System.out.println("RANKING");
            for(Equipo equipo: ranking){
                System.out.println(equipo);
            }
        }
        System.out.println("--------------");
        System.out.println("COMIENZA EL JUEGO");
        if(participantes.isEmpty()){
            System.out.println("No hay participantes, añade algunos antes de comenzar");
        }else{
            for(int i = 1; i<=rondas; i++){
                System.out.println("Ronda "+i);
                for(int j = 0; j<equipos.size(); j++ ){
                    indice = (inicial+j) % equipos.size();
                    System.out.println("Le toca al equipo "+equipos.get(indice).getNombre());
                    miembros = equipos.get(indice).getParticipantes();
                    int cont=1;
                    for(Participante p: miembros){
                        System.out.println(cont+" "+p.getNombre());
                        cont++;
                    }
                    int miembro = Leer.leerEntero("¿Quién va a retar? (introduce el número): ");
                    Participante atacante = equipos.get(indice).getParticipantes().get(miembro-1);
                    if(atacante.fueraDeLaCompeticion()){
                        throw new CTFException("Este participante está fuera de la competición");
                    }else{
                        int rival;
                        int cont3 = 1;
                        ArrayList<Equipo> rivales = new ArrayList<>();
                        for(Equipo e: equipos){
                            if(e.getNombre().equals(equipos.get(indice).getNombre())){
                                continue;
                            }
                            System.out.println(cont3+" "+e.getNombre());
                            rivales.add(e);
                            cont3++;
                        }
                        rival = Leer.leerEntero("¿Contra qué equipo quieres enfrentarte? (introduce el número): ");
                        miembros = rivales.get(rival-1).getParticipantes();
                        int cont2 = 1;
                        for(Participante p: miembros){
                            System.out.println(cont2+" "+p.getNombre());
                            cont2++;
                        }
                        int miembro2 = Leer.leerEntero("¿A qué miembro del equipo quieres desafiar? (introduce el número): ");
                        Participante defensor = rivales.get(rival-1).getParticipantes().get(miembro2-1);
                        if(defensor.fueraDeLaCompeticion()){
                            throw new CTFException("Este participante está fuera de la competición");
                        }else{
                            mostrarRetos();
                            int reto = Leer.leerEntero("¿Que reto quieres enviarle? (introduce su ID): ");
                            atacante.competirCon(defensor,reto);
                            String respuesta = Leer.leerTexto("¿Cuál es la solución a este reto?: ");
                            try(ResultSet res = reto(reto)){
                                if(res != null){
                                    if(respuesta.equals(res.getString(5))){
                                        System.out.println("Has acertado!");
                                        puntosYFlags(defensor,res);
                                        defensor.setnIntentos(defensor.getnIntentos()-1);
                                        atacante.setPuntosGanados(atacante.getPuntosGanados()-5);
                                        System.out.println("Ahora "+defensor.getNombre()+" retará a "+atacante.getNombre());
                                        mostrarRetos();
                                        reto = Leer.leerEntero("¿Qué reto quieres enviarle? (introduce su ID): ");
                                        defensor.competirCon(atacante,reto);
                                        respuesta = Leer.leerTexto("¿Cuál es la solución a este reto?: ");
                                        try(ResultSet rebote = reto(reto)){
                                            if(rebote != null){
                                                if(respuesta.equals(rebote.getString(5))){
                                                    System.out.println("Has acertado!");
                                                    puntosYFlags(atacante,rebote);
                                                    atacante.setnIntentos(atacante.getnIntentos()-1);
                                                    atacante.setPuntosGanados(atacante.getPuntosGanados()+20);
                                                    defensor.setPuntosGanados(defensor.getPuntosGanados()-5);
                                                }else{
                                                    System.out.println("Has fallado");
                                                    atacante.setnIntentos(atacante.getnIntentos()-3);
                                                    if(atacante instanceof Especialista){
                                                        atacante.setPuntosGanados(atacante.getPuntosGanados()-((Especialista)atacante).getPenalizacion());
                                                    }
                                                }
                                            }
                                        }catch(SQLException e){
                                            System.out.println(e.getMessage());
                                        }
                                    }else{
                                        System.out.println("Has fallado");
                                        defensor.setnIntentos(defensor.getnIntentos()-3);
                                        if(defensor instanceof Especialista){
                                            defensor.setPuntosGanados(defensor.getPuntosGanados()-((Especialista)defensor).getPenalizacion());
                                        }
                                    }
                                }
                            }catch(SQLException e){
                                System.out.println(e.getMessage());
                            }
                        }
                    }
                }
            }
            System.out.println("FINAL DE LA PARTIDA");
            System.out.println("-------------------");
            System.out.println("RESULTADOS");
            for(Participante p: participantes){
                System.out.println(p);
            }
        }

    }
    public static void mostrarRetos(){
        ResultSet rs = null;
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.1.108:3306/CTF","edu","alumne");
            Statement s = conn.createStatement();
            rs = s.executeQuery("select * from retos");
            while(rs.next()){
                System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static ResultSet reto(int id){
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.1.108:3306/CTF","edu","alumne");
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("select * from retos where id ="+id);
            rs.next();
            return rs;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static void puntosYFlags(Participante p,ResultSet rs) throws SQLException {
        switch(rs.getString(4)){
            case "bajo":
                p.setPuntosGanados(p.getPuntosGanados()+puntosBajo);
                p.getFlags()[0] += 1;
                break;
            case "medio":
                if(p instanceof Junior){
                    p.setPuntosGanados(p.getPuntosGanados()+puntosMedio+((Junior)p).getBonificacion());
                }
                p.getFlags()[1] += 1;
                break;
            case "alto":
                if(p instanceof Junior){
                    p.setPuntosGanados(p.getPuntosGanados()+puntosAlto+((Junior)p).getBonificacion());
                }
                p.getFlags()[2] += 1;
                break;
        }
    }
}