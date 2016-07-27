package pl.com.digita.simplefsm;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static pl.com.digita.simplefsm.StateMachineCalcTest.Events.*;
import static pl.com.digita.simplefsm.StateMachineCalcTest.States.*;


/**
 * Created by piotr on 27.07.2016.
 * Test with simple FSM with 5 states and events allowing to
 * go upward and backward through them.
 */
public class StateMachineCalcTest
{
    @Test
    public void simpleDefinition()
    {
        //states and events are passed as generic types of FSM
        StateMachine<States, Events, DefaultContext<States>> fsm;
        fsm = new StateMachine<>();

        DefaultContext<States> context = new DefaultContext<>();

        //set beginning state
        context.setCurrentState(ONE);
        fsm.setContext(context);

        //define transitions
        fsm
                .addTransition(ONE, TWO, INCREASE)
                .addTransition(TWO, THREE, INCREASE)
                .addTransition(THREE, FOUR, INCREASE)
                .addTransition(FOUR, FIVE, INCREASE)

                .addTransition(TWO, ONE, DECREASE)
                .addTransition(THREE, TWO, DECREASE)
                .addTransition(FOUR, THREE, DECREASE)
                .addTransition(FIVE, FOUR, DECREASE);

        fsm.onEvent(INCREASE);
        assertEquals(TWO, context.getCurrentState());

        fsm.onEvent(INCREASE);
        assertEquals(THREE, context.getCurrentState());
        fsm.onEvent(INCREASE);
        assertEquals(FOUR, context.getCurrentState());
        fsm.onEvent(INCREASE);
        assertEquals(FIVE, context.getCurrentState());

        //check behaviour when no matching transition for event
        for (int i = 0; i < 5; i++)
        {
            fsm.onEvent(INCREASE);
        }
        assertEquals("state unexpectedly changed", FIVE, context.getCurrentState());

        fsm.onEvent(DECREASE);
        assertEquals(FOUR, context.getCurrentState());

        fsm.onEvent(DECREASE);
        assertEquals(THREE, context.getCurrentState());

        fsm.onEvent(DECREASE);
        assertEquals(TWO, context.getCurrentState());

        fsm.onEvent(DECREASE);
        assertEquals(ONE, context.getCurrentState());
    }

    enum States
    {
        ONE, TWO, THREE, FOUR, FIVE
    }

    enum Events
    {
        INCREASE, DECREASE
    }
}