package pl.com.digita.simplefsm;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static pl.com.digita.simplefsm.StateMachineSimpleTest.Events.SWITCH;
import static pl.com.digita.simplefsm.StateMachineSimpleTest.States.OFF;
import static pl.com.digita.simplefsm.StateMachineSimpleTest.States.ON;

/**
 * Created by piotr on 27.07.2016.
 *
 * Test of FSM emulating stateful electrical switch
 */
public class StateMachineSimpleTest
{
    @Test
    public void simpleDefinition()
    {
        //states and events are passed as generic types of FSM
        StateMachine<States, Events, DefaultContext<States>> fsm;
        fsm = new StateMachine<>();

        DefaultContext<States> context = new DefaultContext<>();

        //set beginning state
        context.setCurrentState(States.OFF);
        fsm.setContext(context);

        //define transitions
        fsm
                .addTransition(ON, OFF, SWITCH)
                .addTransition(OFF, ON, SWITCH);

        assertEquals(OFF, context.getCurrentState());

        fsm.onEvent(SWITCH);
        assertEquals(ON, context.getCurrentState());

        fsm.onEvent(SWITCH);
        assertEquals(OFF, context.getCurrentState());
    }

    enum States
    {
        ON, OFF
    }

    enum Events
    {
        SWITCH
    }
}