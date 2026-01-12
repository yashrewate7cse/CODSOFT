import java.util.Scanner;
public class Atminterface {
    public class Useraccount{
        int accountnumber;
        int accountbalance;
        int accountpin;
        String holdername;
        String secureanswer;
        public Useraccount(int accountnumber,int accountbalance,int accountpin,String holdername,String secureanswer){
            this.accountnumber = accountnumber;
            this.accountbalance = accountbalance;
            this.accountpin = accountpin;
            this.holdername = holdername;
            this.secureanswer= secureanswer;
        }
        public int getaccountnumber(){
            return accountnumber;
        }
        public int getaccountbalance(){
            return accountbalance;
        }
        public int getaccountpin(){
            return accountpin;
        }
        public String getholdername(){
            return holdername; 
        }
        public String getsecureanswer(){
            return secureanswer; 
        }
        public void createnewaccount(){
            System.out.println("Enter your full name (as First Middle Last) : ");
            String holdername=scaninfo.nextLine();
            System.out.println("Enter secure pin for your account : ");
            int accountpin=scaninfo.nextInt();
            scaninfo.nextLine();
            int accountbalance=0;
            int amax=99999999;
            int amin=11111111;
            int accountnumber=(int)(Math.random()*(amax-amin+1)+amin);
            System.out.println("Enter name of your favourite place as secure answer:");
            String secureanswer=scaninfo.nextLine();
            System.out.println("your account is created successfully ");
            useraccounts.add(new Atminterface.Useraccount(accountnumber, accountbalance, accountpin, holdername, secureanswer));
            System.out.println("Account Details (Remember your account number & secure pin for future use):");
            for (Atminterface.Useraccount account : useraccounts) {
                if(account.getaccountnumber() == accountnumber) {
                    System.out.println("Account Number: " + account.getaccountnumber());
                    System.out.println("Account Balance: " + account.getaccountbalance());
                    System.out.println("Secure PIN: " + account.getaccountpin());
                    System.out.println("Holder Name: " + account.getholdername());
                    System.out.println("Secure Answer: " + account.getsecureanswer());
                }
            }
        }
        public int exixtingacc(int accnum){
            boolean accountExists = false;
            for (Atminterface.Useraccount account : useraccounts) {
                if(account.getaccountnumber() == accnum) {
                    accountExists = true;
                    break;
                }
            }
            if (!accountExists) {
                System.out.println("Account ("+ accnum+") not found or might be blocked. please try again.");
                startatm();
            }
            int securepin=0;
            for (Atminterface.Useraccount account : useraccounts) {
                if(account.getaccountnumber() == accnum) {
                    securepin=account.getaccountpin();
                }
            }
            int attempts=0;
            while(attempts<3){
                System.out.println("Enter account secure pin associated to this account :");
                accountpin=scaninfo.nextInt();
                scaninfo.nextLine();
                if(accountpin==securepin){
                    System.out.println("PIN verified successfully");
                    for (Atminterface.Useraccount account : useraccounts) {
                        if(account.getaccountnumber() == accnum) {
                            return account.getaccountbalance();
                        }
                    }
                }else{
                    attempts++;
                    System.out.println("Incorrect PIN. Please try again.");
                    if(attempts==3){
                        System.out.println("Maximum attempts reached.");
                        security(accountpin, securepin, accnum);
                    }
                }
            }
            return -4;
        }  
        public void security(int accountpin, int securepin, int accnum){
            System.out.println("Enter 1 to reset/forgetten your PIN or 2 to return to main menu:");
            int valuein=scaninfo.nextInt();
            scaninfo.nextLine();
            switch (valuein) {
                case 1:
                    for (Atminterface.Useraccount account : useraccounts) {
                        if(account.getaccountnumber() == accnum ) {
                            System.out.println("To reset your PIN, answer the following security question:");
                            System.out.println("What is the name of your favourite place?");
                            String answer=scaninfo.nextLine();
                            System.out.println("Enter your full name.");
                            String fullname=scaninfo.nextLine();
                            if(answer.equals(account.getsecureanswer()) && fullname.equals(account.getholdername())){
                                System.out.println("Security answer & full name verified. You can now set a new PIN.");
                                System.out.println("Enter new secure pin for your account :");
                                int newpin=scaninfo.nextInt();
                                scaninfo.nextLine();
                                account.accountpin=newpin;
                                System.out.println("Your PIN has been reset successfully. Please login again.");
                            }else{
                                System.out.println("Secure answer or Full name does not match. Account added to blocked accounts list.");
                                System.out.println("Your account has been blocked due to multiple incorrect PIN attempts.");
                                System.out.println("Please contact customer support to unblock your account.");
                                blockedaccounts.add(account);
                                useraccounts.remove(account);
                            }
                            startatm();
                        }
                    }
                    break;
                case 2:
                    System.out.println("Returning to main menu.");
                    startatm();
                    break;
                default:
                    System.out.println("Invalid input. Re-type your choice.");
                    security(accountpin, securepin, accnum);
                    break;
            }
        }
    }
    public class Transaction{
        int amount;
        int accnum;
        String transactiontype;
        public Transaction(int amount, int accnum, String transactiontype){
            this.amount = amount;
            this.accnum = accnum;
            this.transactiontype = transactiontype;
        }
        public String gettransactiondetails(int accnum){
            if(this.accnum == accnum){
                return "Transaction Type: " + this.transactiontype + ", Amount: " + this.amount;
            }
            return "";
        }
    }
    public class ATMachine {
        public void userinputatm(int accountbalance, int accnum){
            System.out.println("ATM Menu:");
            String[] infolist={
                "withwrawal",
                "deposit",
                "check balance",
                "transaction history",
                "exit"
            };
            for(int i=0;i<infolist.length;i++){
                System.out.println((i+1)+"."+infolist[i]);
            }
            if(scaninfo.hasNextInt()){
                System.out.println("Enter your choice of action from ATM menu:");
                int info =scaninfo.nextInt();
                scaninfo.nextLine();
                switch (info) {
                    case 1:
                        System.out.println("Enter amount (between 1 to 50,000) to withdraw:");
                        int amount=scaninfo.nextInt();
                        scaninfo.nextLine();
                        if (accountbalance>=amount && amount<=50000){
                            accountbalance=accountbalance-amount;
                            System.out.println("please collect your cash "+amount);
                            for (Atminterface.Useraccount account : useraccounts) {
                                if(account.getaccountnumber() == accnum) {
                                    account.accountbalance=accountbalance;
                                }
                            }
                            transactionhistory.add(new Transaction(amount,accnum,"Withdrawal"));
                        }else{
                            System.out.println("your account balance is not sufficient for withdrawing "+amount+" amount or amount exceeds withdrawal limit of 50,000");
                            userinputatm(accountbalance, accnum);
                        }
                        startatm();
                        break;
                    case 2:
                        System.out.println("Enter amount (between 1 to 1,00,000) to deposit:");
                        int ammount=scaninfo.nextInt();
                        scaninfo.nextLine();
                        if (ammount<1 || ammount>100000){
                            System.out.println("Deposit amount should be between 1 to 1,00,000");
                            userinputatm(accountbalance, accnum);
                            break;
                        }
                        accountbalance=accountbalance+ammount;
                        System.out.println("you have successfully deposited "+ammount+" amount");
                        for (Atminterface.Useraccount account : useraccounts) {
                            if(account.getaccountnumber() ==  accnum) {
                                account.accountbalance=accountbalance;
                            }
                        }
                        transactionhistory.add(new Transaction(ammount,accnum,"Deposit"));
                        startatm();
                        break;
                    case 3:
                        System.out.println("your account balance is "+accountbalance);
                        startatm();
                        break;
                    case 4:
                        System.out.println("Transaction History:");
                        boolean hasTransactions = false;
                        for (Atminterface.Transaction transaction : transactionhistory) {
                            String details = transaction.gettransactiondetails(accnum);
                            if (!details.isEmpty()) {
                                System.out.println(details);
                                hasTransactions = true;
                            }
                        }
                        if (!hasTransactions) {
                            System.out.println("No transactions found.");
                        }
                        startatm();
                        break;
                    case 5:
                        System.out.println("Thanks for using ATM interface");
                        System.out.println("Exiting ATM menu");
                        startatm();
                        break;
                    default:
                        System.out.println("Invalid input. Re-type your choice.");
                        userinputatm(accountbalance, accnum);
                        break;
                }
            }else{
                System.out.println("Invalid input. Re-type your choice.");
                scaninfo.nextLine();
                userinputatm(accountbalance, accnum);
            }
        }
    }
    public void startatm(){
        System.out.println("Main Menu:");
        String[] infolist={
            "Create new account",
            "Access existing account",
            "Exit"
        };
        for(int i=0;i<infolist.length;i++){
            System.out.println((i+1)+"."+infolist[i]);
        }
        System.out.println("Enter your choice of action from main menu:");
        if (scaninfo.hasNextInt()){
            int info =scaninfo.nextInt();
            scaninfo.nextLine();
            switch (info) {
                case 1:
                    Useraccount newaccount = new Useraccount(0,0,0,"","");
                    newaccount.createnewaccount();
                    startatm();
                    break;
                case 2:
                    System.out.println("enter account number:");
                    int accnum=scaninfo.nextInt();
                    scaninfo.nextLine();
                    Useraccount account = new Useraccount(0,0,0,"","");
                    int balance = account.exixtingacc(accnum);
                    if(balance==-4){
                        System.out.println("PIN verification failed. Returning to main menu.");
                        startatm();
                    }
                    ATMachine ATMachine = new ATMachine();
                    ATMachine.userinputatm(balance,accnum);
                    break;
                case 3:
                    System.out.println("Thanks for using ATM interface");
                    System.out.println("Exiting ATM Main Menu");
                    break;
                default:
                    System.out.println("Invalid input. Re-type your choice for main menu.");
                    startatm();
                    break; 
            }
        }else{
            System.out.println("Invalid input. Re-type your choice for main menu.");
            scaninfo.nextLine();
            startatm();
        }
    }
    public java.util.ArrayList<Atminterface.Useraccount> useraccounts = new java.util.ArrayList<>();
    public java.util.ArrayList<Atminterface.Transaction> transactionhistory = new java.util.ArrayList<>();
    public java.util.ArrayList<Atminterface.Useraccount> blockedaccounts = new java.util.ArrayList<>();
    private final Scanner scaninfo= new Scanner(System.in);
    public void main(String[] args) {
        System.out.println("i am yash welcome to ATM(Automated Teller Machine) interface");
        startatm();
        scaninfo.close();
    }
}
