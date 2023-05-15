package databaseworkshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Bank {

    private String name;

    public Bank(String name) {
        this.name = name;
    }

    public void listAccount() {
        Connection connection = BankingConnection.connect();
        try {
            Statement statement = connection.createStatement();
            String sql = "select * from account";
            ResultSet results;
            results = statement.executeQuery(sql);
            while (results.next()) {
                System.out.println(results.getString(1) + " " + results.getString(2) + " "
                        + results.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void openAcc(int accNum, String name) {
        try {
            Connection connection = BankingConnection.connect();
            PreparedStatement insertion = connection.prepareStatement("INSERT into account values"
                    + "(?,?,?)");
            insertion.setInt(1, accNum);
            insertion.setString(2, name);
            insertion.setDouble(3, 0.00d);
            insertion.execute();
            System.out.println("Account created successfully");
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void closeAcc(int accNum) {
        Connection connection = BankingConnection.connect();
        try {
            PreparedStatement deletion = connection.prepareStatement("DELETE from account where accNumber = ?");
            deletion.setInt(1, accNum);
            deletion.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deposit(int accountNumber, double money) {
        Account account = getAccount(accountNumber);
        account.deposit(money);
        Connection connection = BankingConnection.connect();
        try {
            PreparedStatement depo = connection.prepareStatement("UPDATE account SET accBalance = accBalance + ? where accNumber = ?");
            depo.setDouble(1, money);
            depo.setInt(2, accountNumber);
            depo.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void withdraw(int accountNumber, double money) {
        Account account = getAccount(accountNumber);
        account.withdraw(money);
        
        Connection connection = BankingConnection.connect();
        try {
            PreparedStatement depo = connection.prepareStatement("UPDATE account SET accBalance = accBalance - ? where accNumber = ?");
            depo.setDouble(1, money);
            depo.setInt(2, accountNumber);
            depo.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Account getAccount(int accountNumber) {
        Account account = null;
        Connection connection = BankingConnection.connect();
        String sql = "select * from account where accNumber = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountNumber);
            ResultSet results = preparedStatement.executeQuery();
            results.next();
            
            account = new Account(results.getInt(1),results.getString(2),results.getDouble(3));
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
        return account;
    }

}
