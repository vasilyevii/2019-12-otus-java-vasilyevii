package com.vasilyevii;

import com.vasilyevii.command.Command;
import com.vasilyevii.command.PrintATMsBalance;
import com.vasilyevii.factory.ATM;
import com.vasilyevii.observer.ATMEventProducer;

import java.util.ArrayList;
import java.util.List;

public class Department {

    private ArrayList<ATM> atms;
    private final List<Command> commands = new ArrayList<>();

    public Department(ArrayList<ATM> atms) {
        this.atms = atms;
    }

    // commander
    void addCommand(Command command) {
        commands.add(command);
    }

    void executeCommands(Object data) {
        commands.stream().map(cmd -> cmd.execute(data)).forEach(System.out::println);
    }

    public void printATMsBalance() {
        commands.add(new PrintATMsBalance());
        executeCommands(this.atms);
    }

    // observer
    public void restartATMs() {
        ATMEventProducer atmEventProducer = new ATMEventProducer();
        atms.forEach(atm -> atmEventProducer.addListener(atm));
        atmEventProducer.event("restart");
    }

    public void printATMsState() {
        atms.stream().map(atm -> atm.getStateName()).forEach(System.out::println);
    }


}
