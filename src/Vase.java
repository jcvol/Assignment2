//  I picked option 2 because, considering all the guests are wanting to look at one thing, having them wait
//  and observe the sign seemed to be the best choice.
//  The advantages of this include the simplicity of implementation, and, in the simulation, each waiting guest
//  has the same chances of entering the room. The simulation reduces threads naturally, as once the guest has
//  seen the vase, it can end its execution. In terms of threads, the threads are able to work on another task
//  (in this case, wandering around) while waiting.
//  The disadvantages of this is that each guest might get "unlucky" or "cut off" by another guest, even if they have
//  been wandering around the showcase for a while. In terms of time for each guest, it may be less efficient than
//  using something like a queue (if the queue is finite).

public class Vase {
    // False means room is not busy (i.e. "available").
    public static boolean busySign = false;

    public static void main(String[] args) throws InterruptedException {
        // Static number of guests
        int numGuests = 10;
        // Create each guest as its own thread.
        var guests = new Thread[numGuests];

        for (int i=0; i < numGuests; i++){
            guests[i] = new Thread(new Guest("Guest " + (i + 1)));
            guests[i].start();
        }

        // Wait for every guest to have entered the vase room.
        for (int i=0; i < numGuests; i++){
            guests[i].join();
        }

        // Once all threads have finished execution, every guest has seen the vase in the vase room.
        System.out.println("All guests have seen the minotaur's precious vase.");
    }
}
