package pl.com.digita.simplefsm;

/**
 * Created by piotr on 27.07.2016.
 * Definition of transition between states
 */
public class Transition<S, E>
{
    private S stateFrom;
    private S stateTo;
    private E event;

    public S getStateFrom()
    {
        return stateFrom;
    }

    public void setStateFrom(S stateFrom)
    {
        this.stateFrom = stateFrom;
    }

    public S getStateTo()
    {
        return stateTo;
    }

    public void setStateTo(S stateTo)
    {
        this.stateTo = stateTo;
    }

    public E getEvent()
    {
        return event;
    }

    public void setEvent(E event)
    {
        this.event = event;
    }

    /**
     *
     * @param from source state
     * @param to destination state
     * @param event triggering event
     */
    public Transition(S from, S to, E event)
    {
        stateFrom = from;
        stateTo = to;
        this.event = event;
    }

    /**
     * Copying constructor
     * @param transition transition to be copied
     */
    public Transition(Transition<S, E> transition)
    {
        this(transition.stateFrom, transition.stateTo, transition.event);
    }
}
