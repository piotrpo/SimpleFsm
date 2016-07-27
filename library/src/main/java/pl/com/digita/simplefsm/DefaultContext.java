package pl.com.digita.simplefsm;

/**
 * Created by piotr on 27.07.2016.
 * Minimum implementation of StatefulContext
 */
public class DefaultContext<S> implements StateMachine.StatefulContext<S>
{

    private S state;

    /**
     * Get current state of FSM
     * @return current state
     */
    @Override
    public S getCurrentState()
    {
        return state;
    }

    /**
     * Set current state of FSM
     * @param state FSM state to be set
     */
    @Override
    public void setCurrentState(S state)
    {
        this.state = state;
    }
}
