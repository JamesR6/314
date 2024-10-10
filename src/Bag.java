public class Bag<E> {
    private int size;
    private E[] con;


    public Bag (int s, E[] c) {
        size = s;
        con = c;
    }

    public int removeExcess(E value, int maxAllowed) {
        int ct = 0;
        for (int a = this.size - 1; a >= 0; a--) {
            if (this.con[a].equals(value)) {
                E temp = this.con[a];
                this.con[a] = this.con[size-1-ct];
                this.con[size-1-ct] = temp;
                ct++;
            }
        }

        int rem = ct - maxAllowed;
        if (rem <= 0) {
            return 0;
        }
        for (int b = 0; b < rem; b++) {
            this.size--;
            this.con[this.size] = null;
        }
        return rem;
    }    

    public String toString() {
        StringBuilder result = new StringBuilder("");
        for (int a = 0; a < this.size; a++) {
            result.append(this.con[a] + " ");
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Object[] input = {"A", "A", "B", "A", "C"};
        Bag<String> bag = new Bag(5, input);
        System.out.println(bag.removeExcess("A", 1));
        System.out.println(bag.toString());
        
        
    }
}
