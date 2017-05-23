package ecompim.SQL;

import java.sql.Connection;
import java.lang.String;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by JV on 15-02-2017.
 */
public class SQL {

    private Statement myStatement;
    private Connection con = null;
    private ResultSet myResultSet;
    private StringBuilder query;

    /**
     * @param connection
     * @param user
     * @param password
     */
    public SQL(String connection, String user, String password){
        try {
            con = DriverManager.getConnection(connection,user,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.myStatement = null;
        this.query = new StringBuilder();

    }


    /**
     * Add a SQL statement or just a part of one to the SQL Script
     * @param value The statement
     */
    public void add(String value){
        query.append(value);
    }

    /**
     * Clear the SQL script
     */
    public void clear(){
        query = new StringBuilder();
    }

    /**
     * Executes a query
     * This method is not used to excute an update-statements
     */
    public void open() {
        try {
            myStatement = con.createStatement();
            myResultSet = myStatement.executeQuery(query.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Executes the update-statement defined in the SQL script
     */
    public void execute() {
        try {
            myStatement = con.createStatement();
            myStatement.executeUpdate(query.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Close the current resultset
     */
    public void close(){
        try {
            if (this.myResultSet != null) {
                this.myResultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Return the resultset generated from the Open()-method.
     * @return A resultset
     */
    public ResultSet getResultSet() {
        return this.myResultSet;
    }
}
