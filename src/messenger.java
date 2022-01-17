import java.util.*;
/*
* name : mohammad Hossein
* family : Dehetani
* project of programming
*Messenger , you can make private chat  and chatting with other person had an account in
*this Messenger or want to have,we use any server for online chat,only offline
**/
public class messenger {
    //clear terminal for showing better accuont
    // TODO:should write a methods for clear terminal
    //change color in terminal
    public static final String ANSI_RESET  = "\u001B[0m";
    public static final String ANSI_GREEN  = "\u001B[32m";
    public static final String ANSI_RED    = "\u001B[31m";
    //use the input instead of System.in
    static Scanner input = new Scanner(System.in);
    //show the num and pass client entered is valid?, and find number of client in vector
    public static Vector<String> findAccount(String num,String pass){
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
    public static Vector<String> findAccount(String num){
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
    public static void dutyInGruop(account account,int indexGb){
        account.gb.get(indexGb).ShowMessage();
        System.out.println("1.send Messege");
        System.out.println("2.change name of group");
        System.out.println("3.add new member");
        System.out.println("4.remove member");
        System.out.println("5.close");
        int duty = input.nextInt();
        switch (duty){
            case 1 -> {
                //update messege for all members
                account.gb.get(indexGb).sendMessage();
                for (String num:account.gb.get(indexGb).getNumbers()){
                    for (account member:accounts){
                        if (member.getNumber().equals(num)){
                            for (groupChat group:member.gb){
                                if (group.getNameGroup().equals(account.gb.get(indexGb).getNameGroup())){
                                    group.UpdateMesseger(account.gb.get(indexGb).getMessege());
                                }
                            }
                        }
                    }
                }
            }
            case 2 -> {
                System.out.println("enter new name:");
                //set the new name for Group Chat
                String NewName = input.nextLine();
                for (String num:account.gb.get(indexGb).getNumbers()){
                    for (account member:accounts){
                        if (member.getNumber().equals(num)){
                            for (groupChat group:member.gb){
                                if (group.getNameGroup().equals(account.gb.get(indexGb).getNameGroup())){
                                    group.setNameGroup(NewName);
                                }
                            }
                        }
                    }
                }
            }
            case 3 ->{
                //set new member
                System.out.println("enter new number:");
                String NewNum = input.next();
                for (String num:account.gb.get(indexGb).getNumbers()){
                    for (account member:accounts){
                        if (member.getNumber().equals(num)){
                            for (groupChat group:member.gb){
                                if (group.getNameGroup().equals(account.gb.get(indexGb).getNameGroup())){
                                    group.setNewNumber(NewNum);
                                }
                            }
                        }
                    }
                }
            }
            case 4 -> {
                //remove the person
                System.out.print("enter number you want remove:");
                String Rnum = input.next();
                for (String num:account.gb.get(indexGb).getNumbers()){
                    for (account member:accounts){
                        if (member.getNumber().equals(num)){
                            for (String RemoveMember:member.gb.get(indexGb).getNumbers()){
                                if (RemoveMember.equals(Rnum)){
                                    RemoveMember = Rnum;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public static void dutyInPv(account Inaccount,account PrivateChatPerson, int indexPv) {
        //show all Messege
        Inaccount.pvs.get(indexPv).ShowMessage();
        //ask do you want to send message
        System.out.println("1.send Messege");
        System.out.println("2.close");
        int duty = input.nextInt();
        if (duty == 1)
            PrivateChatPerson.pvs.get(indexPv).setMessage(Inaccount.pvs.get(indexPv).sendMessage());
    }
    public static boolean IsValid(String num){
        for(account account: accounts){
            if (account.getNumber().equals(num))
                return true;
        }
        return false;
    }
    //all accounts put in this Vector
    public static Vector<account> accounts = new Vector<account>();
    //showing the menu and return choice
    public static int Menu(){
        System.out.println("1.sign in");
        System.out.println("2.sign up");
        System.out.println("3.exit");
        return input.nextInt();
    }
    //running the program and make account
    public static void main(String[] args) {
        //this is only for test
        for (int i = 0; i < 10; i++) {
            account temp = new account("a", Integer.toString(i), Integer.toString(i));
            accounts.add(temp);
        }
        System.out.println("Hi , welcome to messenger\nplease enter number of your duty or only enter number");
        while (true) {
            int choose = Menu();
            if (choose == 1) {
                System.out.println("please enter your number:");
                String num = input.next();
                System.out.println("please enter your password:");
                String password = input.next();
                //find account want to go its account
                Vector<String> find = findAccount(num,password);
                if (Boolean.parseBoolean(find.get(0))) {
                    boolean InAccount = true;
                    while(InAccount){
                        //showing the accout and all duty can do
                        accounts.elementAt(Integer.parseInt(find.elementAt(1))).showAccount();
                        int answer = input.nextInt();
                        //do duty choose and it must be integar Because input is only for integar
                        switch (answer) {
                            case 1 -> {
                                //go to pv
                                System.out.println("please enter the number of person:");
                                String personNumber = input.next();
                                //Pv is person you want send the messege
                                Vector<String> PV = findAccount(personNumber);
                                //existPv means already pv with him/her made or not and find which index had
                                Vector<String> existPv = accounts.get(Integer.parseInt(find.get(1))).findPv(personNumber);
                                if (Boolean.parseBoolean(find.get(0)) && Boolean.parseBoolean(existPv.get(0))) {
                                    //show message
                                    dutyInPv(accounts.get(Integer.parseInt(find.get(1))),accounts.get(Integer.parseInt(PV.get(1))),Integer.parseInt(existPv.get(1)));
                                } else
                                    System.out.println(ANSI_RED + "this number is incorrect or this pv is not exist, please test again" + ANSI_RESET);
                            }
                            case 2 -> {
                                //go to group
                                System.out.println("enter name of group:");
                                String nameGb = input.next();
                                Vector<String> IsValid = accounts.get(Integer.parseInt(find.get(1))).findGb(nameGb);
                                if (Boolean.parseBoolean(IsValid.get(0))) {
                                    dutyInGruop(accounts.get(Integer.parseInt(find.get(1))), Integer.parseInt(IsValid.get(1)));
                                } else
                                    System.out.println(ANSI_RED + "group not valid !!,choose other" + ANSI_RESET);
                            }
                            case 3 ->
                                    //make group
                                    accounts.get(Integer.parseInt(find.get(1))).makeGroup(accounts);
                            case 4 ->
                                    //make a private chat
                                    accounts.elementAt(Integer.parseInt(find.get(1))).makePv(accounts);
                            case 5 ->
                                    //show proifle and you can change your date
                                    accounts.elementAt(Integer.parseInt(find.get(1))).showProfile();
                            case 6 ->
                                    //close the account
                                    InAccount = false;
                        }
                    }
                } else
                    System.out.println(ANSI_RED + "your data is incorrect" + ANSI_RESET);
                }

            if (choose == 2) {
                //make new account
                account make = new account();
                if(!IsValid(make.getNumber()))
                     accounts.add(make);
                else
                    System.out.println(ANSI_RED + "this number exised" + ANSI_RESET);
            }
            if (choose == 3) {
                System.exit(0);
            }
        }
    }
}