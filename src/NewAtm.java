import java.util.Scanner;
class LowBalance extends Exception{
    double amt;
    LowBalance(double amt){
        this.amt=amt;
    }
    @Override
    public String toString(){
        return "hold on!low balance" ;
    }
}
class Account{
    double amt;
    long accno;
    static final double minbal=5000;
    double bal=0;
    Account(long accno)
    {
        this.accno=accno;

    }
    void deposit(double amt){
        bal= bal+amt;
    }
    void withdraw(double amt)throws LowBalance{
        if(bal-amt<minbal){
            LowBalance e=new LowBalance(bal-amt);
            try {
                throw e;
            } catch (LowBalance ex) {
                throw new RuntimeException(ex);
            }
        }
        else{
            bal=bal-amt;
        }
    }
    double getBalance(){
        return bal;
    }
}class NewAtm{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter your account number: ");
        long accno = sc.nextLong();
        Account acc = new Account(accno);
        int choice;
        do {
            System.out.println("ATM OPTIONS:");
            System.out.println("1.Deposit:");
            System.out.println("2.Withdraw:");
            System.out.println("3.Check balance:");
            System.out.println("4.Exit");
            choice = sc.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Enter amount to deposit:");
                double depositAmount = sc.nextDouble();
                acc.deposit(depositAmount);
                break;
            case 2:

                System.out.println("Enter amount to withdraw:");
                double withdrawAmount = sc.nextDouble();
                try {
                    acc.withdraw(withdrawAmount);
                }
                catch(LowBalance e){
                    System.out.println(e);
                }
                break;
            case 3:
                System.out.println("Your balance is: " + acc.getBalance());
                break;
            case 4:
                System.out.println("Exiting...");
                System.exit(0);
                break;
            default:System.out.println("Invalid choice. Please try again :)");
        }} while (choice != 4);
    }
}
