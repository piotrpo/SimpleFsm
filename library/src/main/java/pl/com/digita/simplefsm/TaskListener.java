package pl.com.digita.simplefsm;

/**
 * Created by piotr on 27.07.2016.
 * Interface for listening to tasks
 */
public interface TaskListener<S, E, C>
{
    /**
     * Method called before exiting bound state
     * @param transition copy of transition definition
     * @param context FSM context
     */
    void beforeLeave(Transition<S, E> transition, C context);

    /**
     * Method called after entering bound state
     * @param transition copy of transition definition
     * @param context FSM context
     */
    void afterEnter(Transition<S, E> transition, C context);

    /**
     * Method called before entering bound state
     * @param transition copy of transition definition
     * @param context FSM context
     */
    void beforeEnter(Transition<S, E> transition, C context);

    /**
     * Gets state to witch this listener is bound to
     * @return enum value of bound state
     */
    S getState();
}
