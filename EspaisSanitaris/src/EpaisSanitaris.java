import java.sql.*;
import java.util.Scanner;
import  java.util.*;

import static javax.print.attribute.standard.MediaSizeName.C;

public class EpaisSanitaris {
    static Statement sentencia;
    static String dni, nom, cognom, titulacio, hospital;
    static int edat, numPlantes, numHabitacions;

    static  String  adreca, entitat, dataEntrada, dataSortida,DNIsanitari ;


    static int codHabitacio,codEspai;

    static int MetresQuadrats,numLlits,NumBanys;
    static Scanner t = new Scanner(System.in);

    public static void main(String[] args) {


        int opcion = 0;
        do {

            try {
                Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/espaisanitaris", "root", "13245");

                sentencia = conexion.createStatement();

                System.out.println("1.PERSONAL SANITARI");
                System.out.println("2.HABITACIONS");
                System.out.println("3.ESPAIS");
                opcion = t.nextInt();

                switch (opcion) {
                    case 1:

                        MenuSanitari();
                        break;
                    case 2:

                        MenuHabitacions();
                        break;
                    case 3:
                        MenuEspais();
                        break;

                    default:
                        System.out.println("Numero introduït incorrecte, SELECCIONI un numero del 1 AL 3!!");
                }



            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("No ha sido posible las conexion");

            }

        } while (opcion != 3);


    }

    public static void MenuSanitari() throws SQLException {

        byte opcionSanitari;
        System.out.println("PERSONAL SANITARI");
        System.out.println("-----------");
        System.out.println("1.Alta Personal Sanitari");
        System.out.println("2.Modificar Personal Sanitari");
        System.out.println("3.Eliminar Personal Sanitari");
        System.out.println("4.Llistar Personal Sanitari");

        opcionSanitari = t.nextByte();

        switch (opcionSanitari) {
            case 1:
                AfegirSanitari();
                break;
            case 2:
                ModificarSanitari();

                break;
            case 3:

                EliminarSanitari();
                break;
            case 4:
                LlistarSanitaris();

                break;

            default:
                System.out.println("Ingrese un numero del 1 al 4!!");
                break;
        }
    }
    public static void MenuHabitacions() throws SQLException{

        System.out.println("HABITACIONS");
        System.out.println("-----------");
        System.out.println("1.Alta habitació");
        System.out.println("2.Modificar habitació");
        System.out.println("3.Eliminar habitació");
        System.out.println("4.Llistar habitació");
        byte opcion = t.nextByte();

        switch (opcion) {
            case 1:

                AfegirHabitacio();
                break;
            case 2:

                ModificarHabitacio();
                break;
            case 3:

              EliminarHabitacio();
                break;
            case 4:
               LlistarHabitacio();

                break;

            default:
                System.out.println("Ingrese un numero del 1 al 4!!");
                break;
        }

    }
    public static void MenuEspais() throws SQLException {

        byte opcionEspais;
        System.out.println("ESPAIS");
        System.out.println("-------------");
        System.out.println("1.Alta Espai");
        System.out.println("2.Modificar Espai");
        System.out.println("3.Eliminar Espai");
        System.out.println("4.Llistar Espais");

        opcionEspais= t.nextByte();

        switch (opcionEspais) {
            case 1:
             AfegirEspai();
                break;
            case 2:
               ModificarEspai();

                break;
            case 3:

            EliminarEspai();
                break;
            case 4:

                LlistarEspai();
                break;

            default:
                System.out.println("Ingrese un numero del 1 al 4!!!");
        }
    }
    public static void AfegirSanitari() throws SQLException {
        System.out.println("Introdueix DNI:");
        dni = t.next();
        System.out.println("Nom");
        nom = t.next();
        System.out.println("Cognom");
        cognom = t.next();
        System.out.println("Edat");
        edat = t.nextInt();
        System.out.println("Titulació");
        titulacio = t.next();
        System.out.println("Hospital on treballa");
        hospital = t.next();

        String SQL = "INSERT INTO PersonalSanitari (DNI,nom,Cognom,Edat,titulacio,Hospital) VALUES ('" + dni + "','" + nom + "','" + cognom + "', '" + edat + "','" + titulacio + "', '" + hospital + "')";
        sentencia.executeUpdate(SQL);
    }
    public static void ModificarSanitari() throws SQLException {

        System.out.println("Importante introducir el nombre del hospital i introducir los dastos de manera correcta.");
        System.out.println("Introdueix el DNI del Sanitari que vol modificar:");
        dni = t.next();
        System.out.println("Nom nou:");
        nom = t.next();
        System.out.println("Nou cognom");
        cognom = t.next();
        System.out.println("Nova edat");
        edat = t.nextInt();
        System.out.println("Nova titulacio");
        titulacio = t.next();
        System.out.println("Nou hospital");
        hospital = t.next();
        String SQL = "UPDATE PersonalSanitari SET nom = '" + nom + "', Cognom= '" + cognom + "', edat=" + edat + " ,titulacio='" + titulacio + "', Hospital= '" + hospital + "' WHERE DNI = '" + dni + "';";
        sentencia.executeUpdate(SQL);


    }
    public static void EliminarSanitari() throws SQLException {
        System.out.println("Introduceix DNI del sanitari que vol eliminar");
        dni = t.next();

        String SQL = " DELETE FROM PersonalSanitari WHERE DNI='" + dni + "';";
        sentencia.executeUpdate(SQL);
    }
    public static void LlistarSanitaris() throws SQLException {
        ResultSet resul = sentencia.executeQuery("SELECT * FROM PersonalSanitari");

        while (resul.next()) {
            System.out.print(" DNI " + resul.getString("DNI"));
            System.out.print(" Nom: " + resul.getString("nom"));
            System.out.print(" Cognom: " + resul.getString("Cognom"));
            System.out.print(" Edat: " + resul.getString("Edat"));
            System.out.print(" Titulacio: " + resul.getString("titulacio"));
            System.out.print(" Hospital: " + resul.getString("Hospital"));

            System.out.println();

        }
    }
    public static void AfegirHabitacio() throws SQLException {
        System.out.println("Codi de la habitacio");
        codHabitacio= t.nextInt();
        System.out.println("Metres Quadrats de la habitació");
        MetresQuadrats= t.nextInt();

        System.out.println("Numero de llits: ");
        numLlits= t.nextInt();
        System.out.println("Numero de banys");
        NumBanys= t.nextInt();

        String SQL = "INSERT INTO Habitacions (CodHabitacio,MetresQuadrats,NumLlits,NumBanys) VALUES ('" + codHabitacio + "','" +MetresQuadrats+ "','" + numLlits +"',"+NumBanys+")";
        sentencia.executeUpdate(SQL);


    }
    public static void ModificarHabitacio() throws SQLException {
        System.out.println("Introdueix el Código de la habitacio a modificar ");
        codHabitacio= t.nextInt();
        System.out.println("Metres Quadrats de la habitació");
        MetresQuadrats= t.nextInt();

        System.out.println("Numero de llits: ");
        numLlits= t.nextInt();
        System.out.println("Numero de banys");
        NumBanys= t.nextInt();
        String SQL = "UPDATE Habitacions SET  MetresQuadrats= '" + MetresQuadrats + "', NumLLits=" + numLlits + " ,NumBanys='" + NumBanys + "' WHERE CodHabitacio = '" +  codHabitacio + "';";
        sentencia.executeUpdate(SQL);

    }
    public static void EliminarHabitacio () throws  SQLException {
        System.out.println("Ingressi el codi de l'habitació que vol eliminar: ");
        codHabitacio = t.nextInt();
        String SQL = " DELETE FROM Habitacions WHERE CodHabitacio='" + codHabitacio + "';";
        sentencia.executeUpdate(SQL);

    }
    public static void LlistarHabitacio () throws SQLException {
        ResultSet resul = sentencia.executeQuery("SELECT * FROM Habitacions");

        while (resul.next()) {
            System.out.print(" Codi Habitació: " + resul.getString("CodHabitacio"));
            System.out.print(" Metres Quadrats: " + resul.getString("MetresQuadrats"));
            System.out.print(" Numero de llits: " + resul.getString("NumLlits"));
            System.out.print(" Numero de Banys: " + resul.getString("NumBanys"));
            System.out.println();
        }
    }
    public static void AfegirEspai() throws SQLException {
        System.out.println("Introdueix Codi d'Habitació: ");
        codHabitacio = t.nextInt();
        System.out.println("DNI del Sanitari: ");
        DNIsanitari = t.next();
        System.out.println("Codi d'Espai: ");
        codEspai = t.nextInt();
        System.out.println("Adreça: ");
        adreca = t.next();
        System.out.println("Entitat del pis: Les dates han de ser format: Any-Mes-Dia ");
        entitat = t.next();
        System.out.println("Data d'entrada: ");
        dataEntrada = t.next();
        System.out.println("Data de sortida: ");
        dataSortida = t.next();
        System.out.println("Numero de plantes: ");
        numPlantes = t.nextInt();
        System.out.println("Numero d'Habitacions: ");
        numHabitacions = t.nextInt();
        System.out.println("Metres Quadrats: ");
        MetresQuadrats = t.nextInt();


        String SQL = "INSERT INTO Espais (CodHabitacio, DNIsanitari, CodEspai, Adreça, Entitat, FechaEntrada, FechaSalida, NumPlantes, NumHabitaciones, MetresCuadrats) VALUES (" + codHabitacio + ",'" + DNIsanitari + "'," + codEspai + ", '" + adreca + "','" + entitat + "', '" + dataEntrada + "', '"+dataSortida+"', "+numPlantes+", "+numHabitacions+", "+MetresQuadrats+")";
        sentencia.executeUpdate(SQL);
    }
    public static void ModificarEspai() throws SQLException {

        System.out.println("Introdueix el Codi del espai a modificar");
        codEspai= t.nextInt();
        System.out.println("Introdueix la vova adreça");
        adreca= t.next();
        System.out.println("Introdueix la nova entitat ");
        entitat= t.next();
        System.out.println("Introdueix un nou numero de plantes");
        numPlantes= t.nextInt();
        System.out.println("Introdueix el nou numero d'Habitacions ");
        numHabitacions= t.nextInt();
        System.out.println("Introdueix els nous metres quadrats");
        MetresQuadrats= t.nextInt();
        String SQL = "UPDATE Espais SET  Adreça= '" + adreca + "', Entitat=" + entitat + " ,NumPlantes='" + numPlantes + "',NumHabitaciones= '"+numHabitacions+"', MetresCuadrats= '"+MetresQuadrats+"' WHERE CodEspai = '" +  codEspai + "';";
        sentencia.executeUpdate(SQL);


    }
    public static void EliminarEspai() throws SQLException{
        System.out.println("Ingressi el codi de l'espai que vol eliminar: ");
        codEspai = t.nextInt();
        String SQL = " DELETE FROM Espais WHERE CodEspai='" + codEspai + "';";
        sentencia.executeUpdate(SQL);
    }
    public static void LlistarEspai () throws SQLException{
        ResultSet resul = sentencia.executeQuery("SELECT * FROM Espais");

        while (resul.next()) {
            System.out.print(" Codi Habitació: " + resul.getString("CodHabitacio"));
            System.out.print(" DNI Sanitari: " + resul.getString("DNIsanitari"));
            System.out.print(" Codi Espai: " + resul.getString("CodEspai"));
            System.out.print(" Adreça: " + resul.getString("Adreça"));
            System.out.print(" Entitat: " + resul.getString("Entitat"));
            System.out.print(" Data Entrada: " + resul.getString("FechaEntrada"));
            System.out.print(" Data sortida: " + resul.getString("FechaSalida"));
            System.out.print(" Numero de Plantes: " + resul.getString("NumPlantes"));
            System.out.print(" Numero d'habitacions: " + resul.getString("NumHabitaciones"));
            System.out.print(" Metres Quadrats: " + resul.getString("MetresCuadrats"));
            System.out.println();
        }
    }

}
