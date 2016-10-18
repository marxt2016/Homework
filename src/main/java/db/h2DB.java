package db;

import model.Aviacompany;
import model.Plane;
import org.h2.tools.DeleteDbFiles;

import java.sql.*;
import java.util.List;

/**
 * Created by Olga_Melnikova on 10/18/2016.
 */
public class h2DB {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:file:D:/workspace/Homework/aviacompany";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    public static void main (String args[]) throws Exception {
        try{
            DeleteDbFiles.execute("D:/workspace/Homework/", "aviacompany", true);
            insertWithPreparedStatement(getConnection());
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void insertWithPreparedStatement(Connection con) throws SQLException{

        PreparedStatement insertPreparedStatement = null;


        String InsertQuery = "INSERT INTO Planes" +
                "(id, model, cargoVolume,numberOfSeats,averageRidingSpeed,averageFlyingSpeed, averageFlyingDistance) values" +
                "(?,?,?,?,?,?,?)";

        try{
            Aviacompany aviacompany = new Aviacompany().loadPlanesFromXMLSource();
            List<Plane> planesList = aviacompany.getPlanes();
            con.setAutoCommit(false);

            for (int i=0; i<= planesList.size()-1; i++){
                insertPreparedStatement = con.prepareStatement(InsertQuery);
                insertPreparedStatement.setInt(1,i);
                insertPreparedStatement.setString(2, planesList.get(i).getModel());
                insertPreparedStatement.setInt(3, planesList.get(i).getCargoVolume());
                insertPreparedStatement.setInt(4, planesList.get(i).getNumberOfSeats());
                insertPreparedStatement.setFloat(5, planesList.get(i).getAverageRidingSpeed());
                insertPreparedStatement.setFloat(6, planesList.get(i).getAverageFlyingSpeed());
                insertPreparedStatement.setFloat(7, planesList.get(i).getAverageFlyingDistance());
                insertPreparedStatement.executeUpdate();
            }

            insertPreparedStatement.close();


            con.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // con.close();
        }

    }

    public static void createWithPreparedStatement(Connection con) throws SQLException{



        PreparedStatement createPreparedStatement = null;

        String CreateQuery = "CREATE TABLE Planes" +
                "(id int primary key," +
                "model varchar(255)," +
                "cargoVolume int," +
                "numberOfSeats int," +
                "averageRidingSpeed float," +
                "averageFlyingSpeed float," +
                "averageFlyingDistance float )";



        try{
            con.setAutoCommit(false);
            createPreparedStatement = con.prepareStatement(CreateQuery);
            createPreparedStatement.executeUpdate();
            createPreparedStatement.close();


            con.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //con.close();
        }

    }

    public static ResultSet selectPlanes(Connection con){

        PreparedStatement selectPreparedStatement = null;
        ResultSet rs = null;
        String SelectQuery = "SELECT * FROM Planes";
        try {
            selectPreparedStatement = con.prepareStatement(SelectQuery);
            rs = selectPreparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;

    }
    public static Connection getConnection(){

        Connection dbConnection = null;
        try{
            Class.forName(DB_DRIVER);
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        }catch (SQLException se){
            System.out.println(se.getMessage());
        }
        return dbConnection;

    }



//    private static void insertWithStatement() throws SQLException {
//        Connection connection = getConnection();
//        Statement stmt = null;
//        try {
//            connection.setAutoCommit(false);
//            stmt = connection.createStatement();
//            stmt.execute("CREATE TABLE TEST(id int primary key, name varchar(255))");
//            stmt.execute("INSERT INTO TEST(id, name) VALUES(1, 'Anju')");
//
//            ResultSet rs = stmt.executeQuery("select * from TEST");
//            System.out.println("H2 Database inserted through Statement");
//            while (rs.next()) {
//                System.out.println("Id "+rs.getInt("id")+" Name "+rs.getString("name"));
//            }
//            stmt.close();
//            connection.commit();
//        } catch (SQLException e) {
//            System.out.println("Exception Message " + e.getLocalizedMessage());
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            connection.close();
//        }
//    }

}
