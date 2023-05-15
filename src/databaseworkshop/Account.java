package databaseworkshop;


public class Account {
    private int accNum;
    private String accName;
    private double balance;
    public Account(int accNum, String accName, double balance){
        this.accNum = accNum;
        this.accName = accName;
        this.balance = balance;
    }
    public void deposit(double money){
        this.balance = this.balance + money;
    }
    
    public void withdraw(double money){
        this.balance = this.balance - money;
    }
    
    public double getBalance(double money){
        return this.balance;
    }
    
    public int getAccNum(){
        return this.accNum;
    }
    
    public String getAccName(){
        return this.accName;
    }
}
