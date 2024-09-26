import com.mysql.cj.result.RowList;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/demo";
        String username="root";
        String password="root";
        Connection connection
                = DriverManager.getConnection(url,username,password);

        Statement statement= connection.createStatement();
        String sql="select * from discipline";
        ResultSet resultSet=statement.executeQuery(sql);
        while (resultSet.next()){
            System.out.println(resultSet.getString("name")+"                "+resultSet.getString("description"));
        }
        String updatedsql1="update discipline set name=? where id=?";
        PreparedStatement preparedStatement1=connection.prepareStatement(updatedsql1);
        preparedStatement1.setString(1,"Sunil");
        preparedStatement1.setInt(2,2);

        String updatedsql2="update discipline set description=? where id=?";
        PreparedStatement preparedStatement2=connection.prepareStatement(updatedsql2);
       preparedStatement2.setString(1,"Bye");
        preparedStatement2.setInt(2,1);

        boolean autocommit= connection.getAutoCommit();
        try{
            connection.setAutoCommit(false);
            preparedStatement1.executeUpdate();
            System.out.println("here1");
            preparedStatement2.executeUpdate();
            System.out.println("here2");
            connection.commit();
        }
        catch (SQLException e){
            System.out.println("here3");
            System.out.println(e.getMessage());
            connection.rollback();

        }
        finally {
            System.out.println("here4");
            connection.setAutoCommit(autocommit);
        }
        connection.close();
    }
}