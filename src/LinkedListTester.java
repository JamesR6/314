/*  Student information for assignment:
 *
 *  On my honor, JP Reeves, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  Name: JP Reeves
 *  email address: jpascualsr06@gmail.com
 *  UTEID: jsr3699
 *  Number of slip days used on this assignment: 0
 */

/* Experiment results. CS314 students, place your experiment
 *  results here:
 * 
 *   Methods faster with LL314:
 * Adding at end
 * Adding at front
 * Removing from front
 * 
 *   Methods faster with java ArrayList:
 * Getting random
 * Getting all using iterator
 * Getting all using get method
 * 
 *                        | LL314  | ArrayLists
 * Adding at end          |  O(N)  |   O(N)
 * Adding at front        |  O(N)  |   O(N^2)
 * Removing from front    |  O(N)  |   O(N^2)
 * Getting random         | O(N^2) |   O(N)
 * Getting all using it   |  O(N)  |   O(N)
 * Getting all using get  | O(N^2) |   O(N)
 * 
 * I decided these were the most likely Big Os of each
 * method because all of them followed a pattern of
 * doubling time when N is doubled (indicating O(N)) or
 * quadrupling time when N is doubled (indicating O(N^2))
 */

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;
import java.util.HashSet;

public class LinkedListTester {

    public static void main(String[] args) {

        // CS314 students. Add your tests here:

    }

    private static ArrayList<String> linkedListToArrayList(LL314<String> testList) {
        ArrayList<String> result = new ArrayList<>();
        Iterator<String> s = testList.iterator();
        while (s.hasNext()) {
            result.add(s.next());
        }
        return result;
    }

    private static LL314<String> arrayListToLinkedList(ArrayList<String> toCompare) {
        LL314<String> result = new LL314<>();
        Iterator<String> s = toCompare.iterator();
        while (s.hasNext()) {
            result.add(s.next());
        }
        return result;
    }

    // Convert elements of list to an array. Uses the list
    // size method and iterator.
    private static Object[] toArray(LL314<String> list) {
        Object[] result = new Object[list.size()];
        Iterator<String> it = list.iterator();
        int index = 0;
        while (it.hasNext()) {
            result[index] = it.next();
            index++;
        }
        return result;
    }

    // pre: none
    // post: return true if the
    private static boolean arraysSame(Object[] one, Object[] two) {
        return Arrays.equals(one, two);
    }
}