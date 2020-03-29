package com.vasilyevii.state;

import com.vasilyevii.state.ATMState;
import com.vasilyevii.state.InitATMState;
import com.vasilyevii.state.SuspendATMState;
import com.vasilyevii.state.WorkingATMState;

public class ATMStateProvider {

    private static final ATMState initATMState = new InitATMState();
    private static final ATMState workingATMState = new WorkingATMState();
    private static final ATMState suspendATMState = new SuspendATMState();

    public static ATMState getInitATMState() {
        return initATMState;
    }

    public static ATMState getWorkingATMState() {
        return workingATMState;
    }

    public static ATMState getSuspendATMState() {
        return suspendATMState;
    }

}
