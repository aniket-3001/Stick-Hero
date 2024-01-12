package com.iiitd.stickhero;

import java.util.Comparator;

/*Singleton Design Pattern has been implemented here to ensure that only one comparator object exists
in the system as comparators have no state --> it saves memory by not allowing the creation of more
than 1 comparator object.
Here, the Game Manager will not use the constructor directly but will instead call getInstance to obtain
a HighScoreComparator object that is shared by all classes in the application.
 */
public class HighScoreComparator implements Comparator<Integer> {
    private static HighScoreComparator comparator = null;
    private HighScoreComparator(){}
    public static HighScoreComparator getInstance(){
        if (comparator==null) comparator = new HighScoreComparator();
        return comparator;
    }
    @Override
    public int compare(Integer o1, Integer o2) {
        return o2 - o1; //higher to lower
    }
}
