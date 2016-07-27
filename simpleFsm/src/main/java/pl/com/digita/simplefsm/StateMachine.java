package pl.com.digita.simplefsm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by piotr on 27.07.2016.
 * FSM engine
 */
public class StateMachine<S extends Enum, E extends Enum, C extends StateMachine.StatefulContext<S>>
{
    private List<Transition<S, E>> transitions = new ArrayList<>();
    private List<StateListener<S, E, C>> stateListeners = new ArrayList<>();
    private C context;

    public C getContext()
    {
        return context;
    }

    public void setContext(C context)
    {
        this.context = context;
    }

    public void addTransition(Transition<S, E> transition)
    {
        transitions.add(transition);
    }

    /**
     * Adds new transition definition to state machine
     *
     * @param from start state
     * @param to end state
     * @param event triggering event
     * @return this instance for more convenient syntax
     */
    public StateMachine<S, E, C> addTransition(S from, S to, E event)
    {
        addTransition(new Transition<>(from, to, event));
        return this;
    }

    public void addStateListener(StateListener<S, E, C> listener)
    {
        stateListeners.add(listener);
    }

    public void onEvent(E event)
    {
        for (Transition<S, E> transition : transitions)
        {
            if (transition.getStateFrom() == context.getCurrentState() && transition.getEvent() == event)
            {
                final Transition<S, E> transitionToExecute = new Transition<>(transition);
                executeTransition(transitionToExecute);
                return;
            }
        }
    }

    /**
     * Execute transition change, going through all lifecycle lifecycle
     *
     * @param transition transition to be executed
     */
    private void executeTransition(Transition<S, E> transition)
    {
        for (StateListener<S, E, C> stateListener : stateListeners)
        {
            if (stateListener.getState() == transition.getStateFrom())
            {
                stateListener.beforeLeave(transition, context);
            }
        }


        for (StateListener<S, E, C> stateListener : stateListeners)
        {
            if (stateListener.getState() == transition.getStateTo())
            {
                stateListener.beforeEnter(transition, context);
            }
        }

        context.setCurrentState(transition.getStateTo());

        for (StateListener<S, E, C> stateListener : stateListeners)
        {
            if (stateListener.getState() == transition.getStateTo())
            {
                stateListener.afterEnter(transition, context);
            }
        }
    }

    public interface StatefulContext<S>
    {
        S getCurrentState();

        void setCurrentState(S state);
    }
}
