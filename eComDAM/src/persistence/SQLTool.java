package persistence;

import java.sql.*;

public class SQLTool {

    private PreparedStatement myStatement;
    private Connection con = null;
    private ResultSet myResultSet;
    private StringBuilder query;

    /**
     * @param connection
     * @param user
     * @param password
     */
    public SQLTool(String connection, String user, String password) {
        try {
            con = DriverManager.getConnection(connection, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.myStatement = null;
        this.query = new StringBuilder();

    }


    /**
     * Add a SQLTool statement or just a part of one to the SQLTool Script
     *
     * @param value The statement
     */
    public void add(String value) {
        query.append(value);
    }

    /**
     * Prepare the statement for parameters.
     */
    public void prepareStatement() {
        try {
            this.myStatement = con.prepareStatement(query.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add a parameter to the prepared statement
     * Important: call prepareStatement() before use
     *
     * @param index paramter index
     * @param value an int
     */
    public void addParameter(int index, int value) {
        try {
            if (myStatement != null) {
                myStatement.setInt(index, value);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add a parameter to the prepared statement
     * Important: call prepareStatement() before use
     *
     * @param index paramter index
     * @param value a Boolean
     */
    public void addParameter(int index, Boolean value) {
        try {
            if (myStatement != null) {
                myStatement.setBoolean(index, value);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add a parameter to the prepared statement
     * Important: call prepareStatement() before use
     *
     * @param index parameter index
     * @param value a String
     */
    public void addParameter(int index, String value) {
        try {
            if (myStatement != null) {
                myStatement.setString(index, value);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Clear the SQLTool script
     */
    public void clear() {
        query = new StringBuilder();
    }

    /**
     * Executes a query
     * This method is not used to execute an update-statements
     * Important: call prepareStatement before open()
     */
    public void open() {
        try {
            myResultSet = myStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Executes the update-statement defined in the SQLTool script
     * mportant: call prepareStatement before execute()
     */
    public void execute() {
        try {
            myStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Close the current resultset
     */
    public void close() {
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
     *
     * @return A resultset
     */
    public ResultSet getResultSet() {
        return this.myResultSet;
    }
}
