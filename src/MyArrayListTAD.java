package MyArrayList;

public interface MyArrayListTAD {

    void addFirst(int num);
    void addLast(int num);
    void insertAt(int num, int index);
    //Remover em
    void removeFirst();
    void removeLast();
    void removeAt(int index);
    void remove(int item);

    //Busch
    int find(int item);
    int get(int index);
    void set(int index, int num);

    boolean isEmpty();
    boolean isFull();
    void count();
    void display();
    void addSorted(int num);
}
