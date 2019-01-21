package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        // write your code here
        int answer=0;
        Queue<State> last_states = new LinkedList<State>();
        State start = new State(3, 3, 1, 0, 0);
        last_states.add(start);
        while (true)
        {
            State a_state = (State) last_states.remove();
            if(a_state.isGoal())
            {
                a_state.print();
                break;
            }
            ArrayList<State> n_states=a_state.get_rnd_instance();
            last_states.addAll(n_states);

        }

    }
}
