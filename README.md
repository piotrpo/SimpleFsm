This is library supporting implementation of FSMs in Java application

All you have to do is to create enums for States and Events and define transitions.
For examples check [unit tests](https://github.com/piotrpo/SimpleFsm/tree/master/simpleFsm/src/test/java/pl/com/digita/simplefsm).

Basic usage

Include library as usual (binaries are available at jcenter repository):
```
compile 'pl.com.digita.simplefsm:simpleFsm:0.1'
```

Define enums containing states and events. There is no special requirements for those objects - you can use here 
virtually any `enum`.

```java
enum States
{
    ON, OFF
}
    
enum Events
{
    SWITCH
}
```

Then declare and instantiate State Machine. Generic parameters are enum with States, enum with Events and StatefulContext.
 
```java
StateMachine<States, Events, DefaultContext<States>> fsm;
        fsm = new StateMachine<>();
```

`StateMachine` holds all data in `StatefulContext` objects, so write your own or just use predefined one. Just take care
about setting generic type - it **must match** first generic parameter of StateMachine. Here you set initial state.
you can use single StateMachine to process different context.

```java
DefaultContext<States> context = new DefaultContext<>();
context.setCurrentState(States.OFF);
fsm.setContext(context);
```

Last step is to define possible transitions (transitions have directions):
```java
fsm
    .addTransition(ON, OFF, SWITCH)
    .addTransition(OFF, ON, SWITCH);
    
```

Then just pass some event to machine
```java
fsm.onEvent(SWITCH);
```

Finally - read current status:

```java
fsm.getContext().getCurrentState()
```

There is also possibility to add listeners and trigger some external processes as well as implement decision
pseudo states - more examples will come soon.