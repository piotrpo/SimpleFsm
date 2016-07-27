package pl.com.digita.simplefsm;


/**
 * Created by piotr on 27.07.2016.
 * Default implementation of TaskListener simplifies syntax of FSM definition
 */
public class DefaultTaskListener<S, E, C> implements TaskListener<S, E, C>
{
    S boundState;

    public DefaultTaskListener(S boundState)
    {
        this.boundState = boundState;
    }

    /**
     * Method called before exiting bound state
     *
     * @param transition copy of transition definition
     * @param context    FSM context
     */
    @Override
    public void beforeLeave(Transition<S, E> transition, C context)
    {

    }

    /**
     * Method called after entering bound state
     *
     * @param transition copy of transition definition
     * @param context    FSM context
     */
    @Override
    public void afterEnter(Transition<S, E> transition, C context)
    {

    }

    /**
     * Method called before entering bound state
     *
     * @param transition copy of transition definition
     * @param context    FSM context
     */
    @Override
    public void beforeEnter(Transition<S, E> transition, C context)
    {

    }

    /**
     * Gets state to witch this listener is bound to
     *
     * @return enum value of bound state
     */
    @Override
    public S getState()
    {
        return null;
    }
}
