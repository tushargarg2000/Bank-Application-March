package bank.application;

import java.util.UUID;

public class SBI implements BankInterface{

    private int balance;
    private String accountNo;
    private String password;
    public String name;

    public static double rateOfInterest;

    public SBI(int balance, String password, String name) {

        this.accountNo = UUID.randomUUID().toString();
        this.balance = balance;
        this.password = password;
        this.name = name;
    }

    @Override
    public int checkBalance(String password) {

        if(password==this.password){
            return balance;
        }
        return -1;
    }

    @Override
    public String addMoney(int money) {

        balance = balance + money;

        String message = money + " has been added to the account "+accountNo + "" +
                "the total balance is now "+balance;

        return message;
    }

    @Override
    public String withdrawMoney(int drawMoney, String password) {

        if(password==this.password){

            if(drawMoney>balance){
                return "Insufficient balance";
            }
            else{
                balance = balance - drawMoney;
                return "Money withdrawn successfully. Remaining balance is "+balance;
            }
        }else{
            return "Wrong password";
        }

    }

    @Override
    public String changePassword(String oldPassword, String newPassword) {

        //this.password means the current password for the account
        if(this.password.equals(oldPassword)){
            this.password = newPassword;
            return "Password has been updated successfully";
        }else{
            return "Wrong password";
        }

    }

    @Override
    public double calculateTotalInterest(int years) {

        double interest = balance*rateOfInterest*years/100;

        return interest;
    }
}
