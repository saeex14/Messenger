import java.util.*;
//Messenger is main of program
/*
* name : mohammad Hossein
* family : Dehetani
* project of programming
*Osa Messenger , you can make private chat  and chatting with other person had an account in
*this Messenger or want to have,we use any server for online chat,only offline
**/
public class messenger {
    //clear terminal for showing better accuont
    // TODO:should write a methods for clear terminal
    //use the input instead of System.in
    static Scanner input = new Scanner(System.in);
    //show the num and pass client entered is valid?, and find number of client in vector
    public static Vector<String> findAccount(Vector<account>accounts,String num,String pass){
        Vector<String> answer = new Vector<String>();
        for(int i = 0 ; i < accounts.size() ; i++){
            if (accounts.elementAt(i).getNumber().equals(num) &&
                    accounts.elementAt(i).getPassword().equals(pass)){
                answer.add("true");
                answer.add(Integer.toString(i));
                return answer;
            }
        }
        answer.add("false");
        answer.add(Integer.toString(0));
        return answer;
    }
    public static Vector<String> findAccount(Vector<account>accounts,String num){
        Vector<String> answer = new Vector<String>();
        for(int i = 0 ; i < accounts.size() ; i++){
            if (accounts.elementAt(i).getNumber().equals(num)){
                answer.add("true");
                answer.add(Integer.toString(i));
                return answer;
            }
        }
        answer.add("false");
        answer.add(Integer.toString(0));
        return answer;
    }
    //num is valid or not
    public static boolean IsValid(Vector<account>accounts,String num){
        for(int i = 0 ; i < accounts.size() ; i++){
            if (accounts.elementAt(i).getNumber().equals(num))
                return true;
        }
        return false;
    }
    //for show the menu of client
    //change font's color in terminal for show the new message or not
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLACK  = "\u001B[30m";
    //showing the menu and return choice
    public static int Menu(){
        System.out.println("1.sign in");
        System.out.println("2.sign up");
        System.out.println("3.exit");
        return input.nextInt();
    }
    //running the program and make account
    public static void main(String[] args) {
        //all accounts put in this Vector
        Vector<account> accounts = new Vector<account>();
        //this is only for test
        for (int i = 0; i < 10; i++) {
            account temp = new account("a", Integer.toString(i), Integer.toString(i));
            accounts.add(temp);
        }
        System.out.println("Hi , welcome to OSA messenger\nplease enter number of your duty or only enter number");
        while (true) {
            int choose = Menu();
            if (choose == 1) {
                System.out.println("please enter your number:");
                String num = input.nextLine();
                num = input.nextLine();
                System.out.println("please enter your password:");
                String pass = input.nextLine();
                //find account
                Vector<String> find = findAccount(accounts,num,pass);
                if (Boolean.parseBoolean(find.elementAt(0))) {
                    boolean InAccount = true;
                    while(InAccount){
                        accounts.elementAt(Integer.parseInt(find.elementAt(1))).showAccount();
                        int answer = input.nextInt();
                        switch (answer){
                            case 1:
                                //go to pv
                                System.out.println("please enter the number of person:");
                                String person = input.nextLine();
                                person = input.nextLine();
                                Vector<String> PV = findAccount(accounts,person);
                                Vector<String> existPv = accounts.elementAt(Integer.parseInt(find.get(1))).findPv(person);
                                if (Boolean.parseBoolean(find.get(0)) && Boolean.parseBoolean(existPv.get(0))){
                                    //show message
                                    accounts.elementAt(Integer.parseInt(find.elementAt(1))).
                                            pvs.elementAt(Integer.parseInt(existPv.elementAt(1))).ShowMessage();
                                    //ask do you want to send message
                                    System.out.println("Do you want to send message ? (y/n)");
                                    Character ans = input.next().charAt(0);
                                    ans = Character.toLowerCase(ans);
                                    if(ans.equals('y')){
                                        accounts.elementAt(Integer.parseInt(PV.elementAt(1))).
                                                pvs.elementAt(Integer.parseInt(existPv.elementAt(1))).setMessage(
                                                        accounts.elementAt(Integer.parseInt(find.elementAt(1))).
                                                                pvs.elementAt(Integer.parseInt(existPv.elementAt(1))).sendMessage());
                                    }
                                }else
                                    System.out.println("this number is incorrect or this pv is not exist, please test again");
                                break;
                            case 2:
                                //go to group
                                break;
                            case 3:
                                //make group
                                accounts.get(Integer.parseInt(find.get(1))).makeGroup(accounts);
                                break;
                            case 4:
                                //make a private chat
                                accounts.elementAt(Integer.parseInt(find.elementAt(1))).makePv(accounts);
                                break;
                            case 5:
                                //show proifle and you can change your date
                                accounts.elementAt(Integer.parseInt(find.elementAt(1))).showProfile();
                                break;
                            case 6:
                                //close the account
                                InAccount = false;
                                break;
                        }
                    }
                } else System.out.println("your data is incorrect");

                }

            if (choose == 2) {
                account make = new account();
                accounts.add(make);
                choose = Menu();
            }
            if (choose == 3) {
                System.exit(0);
            }
        }
    }
}