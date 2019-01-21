package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class State {

    String move="";
    int[] cur_state=new int[5];
    int[] goal_state={0,0,0,3,3};
    ArrayList<State> prev_states=new ArrayList<State>();

    public State(int left_m,int left_c,int boat,int right_m,int right_c)
    {
        this.cur_state[0]= left_m;
        this.cur_state[1]=left_c;
        this.cur_state[2]=boat;
        this.cur_state[3]=right_m;
        this.cur_state[4]=right_c;


    }

    public ArrayList<State> get_rnd_instance()
    {

        for (int i =0;i<prev_states.size();i++)
        {
            if (prev_states.get(i).cur_state[0]==cur_state[0]&& prev_states.get(i).cur_state[1]==cur_state[1] && prev_states.get(i).cur_state[2]==cur_state[2] && prev_states.get(i).cur_state[3]==cur_state[3]&&prev_states.get(i).cur_state[4]==cur_state[4])
            {
                return new ArrayList<State>();
            }

        }



        int boat=this.cur_state[2];
        int left_m=this.cur_state[0];
        int left_c=this.cur_state[1];
        int right_m=this.cur_state[3];
        int right_c=this.cur_state[4];
        ArrayList<State> next_states= new ArrayList<State>();
        if (boat==1)
        {
            if (left_m>1 ) // send 2 m
            {
                State n_state=new State(left_m-2,left_c,0,right_m+2,right_c);

                if(n_state.isAvailable()) {
                    n_state.move="moved 2m to right >>";
                    next_states.add(n_state);
                }
            }

            if (left_c>0) // Send 1 c
            {
                State n_state=new State(left_m,left_c-1,0,right_m,right_c+1);
                if(n_state.isAvailable()) {
                    n_state.move="moved 1c to right >>";
                    next_states.add(n_state);
                }
            }

            if (left_m>0) // Send 1 m
            {
                State n_state=new State(left_m-1,left_c,0,right_m+1,right_c);
                if(n_state.isAvailable()) {
                    n_state.move="moved 1m to right >>";
                    next_states.add(n_state);
                }
            }

            if (left_m>0 && left_c>0) // Send 1 c and 1 m
            {
                State n_state=new State(left_m-1,left_c-1,0,right_m+1,right_c+1);
                if(n_state.isAvailable()) {
                    n_state.move="moved 1m and 1c to right >>";
                    next_states.add(n_state);
                }
            }
            if (left_c>1 ) // send 2 c
            {
                State n_state=new State(left_m,left_c-2,0,right_m,right_c+2);
                if(n_state.isAvailable()) {
                    n_state.move="moved 2c to right >>";
                    next_states.add(n_state);
                }
            }

        }
        else
        {
            if (right_m>1)
            {
                State n_state=new State(left_m+2,left_c,1,right_m-2,right_c);
                if(n_state.isAvailable()) {
                    n_state.move="<< moved 2m to left";
                    next_states.add(n_state);
                }
            }

            if (right_c>0) // Send 1 c
            {
                State n_state=new State(left_m,left_c+1,1,right_m,right_c-1);
                if(n_state.isAvailable()) {
                    n_state.move="<< moved 1c to left";
                    next_states.add(n_state);
                }
            }

            if (right_m>0) // Send 1 m
            {
                State n_state=new State(left_m+1,left_c,1,right_m-1,right_c);
                if(n_state.isAvailable()) {

                    n_state.move="<< moved 1m to left";
                    next_states.add(n_state);
                }
            }

            if (right_m>0 && right_c>0)
            {
                State n_state=new State(left_m+1,left_c+1,1,right_m-1,right_c-1);
                if(n_state.isAvailable()) {
                    n_state.move="<< moved 1m and 1c to left";
                    next_states.add(n_state);
                }
            }
            if (right_c>1 )
            {
                State n_state=new State(left_m,left_c+2,1,right_m,right_c-2);
                if(n_state.isAvailable()) {
                    n_state.move="<< moved 2c to left";
                    next_states.add(n_state);
                }
            }

        }

        for (int i =0;i<next_states.size();i++)
        {
            next_states.get(i).prev_states.addAll(this.prev_states);
            next_states.get(i).prev_states.add(this);
        }


        return next_states;
    }

    public boolean isAvailable()
    {
        int boat=this.cur_state[2];
        int left_m=this.cur_state[0];
        int left_c=this.cur_state[1];
        int right_m=this.cur_state[3];
        int right_c=this.cur_state[4];

        if(left_m<left_c & left_m!=0)
            return false;
        if(right_m<right_c & right_m!=0)
            return false;
        return true;
    }

    public boolean isGoal()
    {
        if (cur_state[0]==goal_state[0] && cur_state[1]==goal_state[1] && cur_state[2]==goal_state[2] && cur_state[3]==goal_state[3] && cur_state[4]==goal_state[4])
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    public void print()
    {
        for (int i =0;i<prev_states.size();i++)
        {
            int[] a_state=prev_states.get(i).cur_state;
            int boat=a_state[2];
            int left_m=a_state[0];
            int left_c=a_state[1];
            int right_m=a_state[3];
            int right_c=a_state[4];
            String boat_txt="|  b|";
            if (boat==1)
            {
                boat_txt="|b  |";
            }
            System.out.println(prev_states.get(i).move);
            System.out.println(left_c+"c "+left_m+"m "+boat_txt+" "+right_c+"c "+right_m+"m ");

        }

        int boat=this.cur_state[2];
        int left_m=this.cur_state[0];
        int left_c=this.cur_state[1];
        int right_m=this.cur_state[3];
        int right_c=this.cur_state[4];
        String boat_txt="|  b|";
        if (boat==1)
        {
            boat_txt="|b  |";
        }
        System.out.println(move);
        System.out.println(left_c+"c "+left_m+"m "+boat_txt+" "+right_c+"c "+right_m+"m ");


    }

}
