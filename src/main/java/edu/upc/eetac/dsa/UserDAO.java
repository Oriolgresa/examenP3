package edu.upc.eetac.dsa;

import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by OriolGresa on 18/11/16.
 */
public class UserDAO {
    static final Logger logger = Logger.getLogger(UserDAO.class);


    public static Connection getConnection() {
        Connection con=null;
        try
        {
            String host = "localhost";
            int port = 3306;
            String database = "examen";
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
            Properties info = new Properties();
            info.setProperty("user", "root");
            info.setProperty("password", "root");
            info.setProperty("useSSL", "false");
            info.setProperty("serverTimezone", "UTC");
            con = DriverManager.getConnection(url, info);
        }
        catch (Exception e)
        {e.printStackTrace();}

        return con;
    }

    public String getValors (Field f) {
        String res=null;
        try {
            Method m = this.getClass().getMethod(getUpper(f.getName()), null);
            res = m.invoke(this, null).toString();
        }
        catch (NoSuchMethodException e1) {
            e1.printStackTrace();
        }
        catch (IllegalAccessException e1) {
            e1.printStackTrace();
        }
        catch (InvocationTargetException e1) {
            e1.printStackTrace();
        }
        return res;
    }

    public void insertarElementos (PreparedStatement preparedStatement) throws NoSuchMethodException, SQLException, InvocationTargetException, IllegalAccessException {
        int i = 1;
        Field[] fields = this.getClass().getFields();

        for (Field f : fields) {
            String res = getValors(f);
            preparedStatement.setObject(i, res);
            i++;
        }
    }

    public String getUpper(String m) {
        String result = Character.toUpperCase(m.charAt(0))+m.substring(1);
        return "get".concat(result);
    }

    public void insertUser() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO UserP3 (dni,nombre,direccion) VALUES (?,?,?);");
        logger.info("QUERY:" +sb.toString()+"\n");

        Connection con = getConnection();
        try{
            PreparedStatement preparedStatement = con.prepareStatement(sb.toString());
            insertarElementos(preparedStatement);
            preparedStatement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*public void select(int id){
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(this.getClass().getSimpleName()).append(" WHERE ID = ").append(id);
        System.out.println("QUERY: "+sb.toString());

        Connection con = getConnection();

        try {

            Statement stmt= con.createStatement();
            ResultSet rs= stmt.executeQuery(sb.toString());
            ResultSetMetaData rsmd=rs.getMetaData();
            rs.next();
            for(int i=1; i<rsmd.getColumnCount()+1;i++){ //lo ejecuto el numero de veces de columnas que tenga en la tabla
                try {
                    if (rsmd.getColumnTypeName(i).equals("INT")) {//para la columna i,si es del tipo int
                        System.out.println(rsmd.getColumnLabel(i) + " = " + rs.getInt(i)); //obtengo la etiqueta de la columna y el entero (id=1...)
                    }
                    if (rsmd.getColumnTypeName(i).equals("VARCHAR")) { //si es del tipovarchar, obtengo lo que es tambien
                        System.out.println(rsmd.getColumnLabel(i) + " = " + rs.getString(i));
                    }
                    if (i == rsmd.getColumnCount()) { //cuando i=numero de columnas, voy al siguiente y salgo del bucle,reiniciando i
                        rs.next();
                        i = 0;
                    }
                }
                catch(Exception e){
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(this.getClass().getSimpleName()).append(" SET ");

        Field[] fields = this.getClass().getDeclaredFields(); //campos--> obtener campos declarados en esta clase:name, address, id

        int numfields = 0;
        for (Field f : fields) {
            if (numfields == fields.length - 1) {
                sb.append(f.getName() + "=?");
            } else {
                sb.append(f.getName() + "=?,");
            }
            numfields++;
        }

        int id=Integer.parseInt(getValors(fields[0]));
        sb.append(" WHERE id=" + id);

        System.out.println("QUERY: " + sb.toString());

        Connection con = getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sb.toString());
            insertarElementos(preparedStatement);
            preparedStatement.execute();

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(){
        StringBuffer sb = new StringBuffer();
        Field[] fields = this.getClass().getDeclaredFields();
        int p=Integer.parseInt(getValors(fields[0]));
        sb.append("DELETE FROM ").append(this.getClass().getSimpleName()).append(" WHERE ID="+p);
        System.out.println("QUERY: "+sb.toString());
        Connection con = getConnection();

        try {
            Statement stmt=con.createStatement();
            stmt.execute(sb.toString());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static List<User> getAllUsers() throws SQLException {
        Connection con = getConnection();
        Statement stmt= null;
        try {
            stmt = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet rs= stmt.executeQuery("SELECT * FROM User");
        List<User> lista = new ArrayList<User>();
        while (rs.next()){
            //User user = new User(rs.getInt("id"),rs.getString("name"),rs.getString("address"));
            //lista.add(user);
        }
        //System.out.println(lista);
        for (User u: lista) {
            System.out.println(u+"\n");
        }
        return lista;
    }*/
}
