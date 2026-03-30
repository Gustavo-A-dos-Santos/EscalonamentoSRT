

public class MyArrayList  {
    private int[] array;
    private int size;
    private int capacity;

    public MyArrayList(int capacity) {
        this.capacity = capacity;
        array = new int[capacity];
        size = 0;
    }
    public void add(int num){
        if(isFull()){
            System.out.println("Erro, array cheio!");
            return;
        }
        array[size] = num;
        size++;
    }
    //Adicionar em

    public void addFirst(int num) {
        if(isFull()){
            System.out.println("Erro ao adicionar um novo elemento, lista cheia!!");
            return;
        }
        for(int i=size; i>0; i--) {
            if(array[i]==0){
                array[i] = array[i-1];
                array[i-1] = 0;
            }
        }
        array[0] = num;
        size++;

    }

    public void addLast(int num){
        if(isFull()){
            System.out.println("Erro ao adicionar um novo elemento, lista cheia!!");
            return;
        }
        array[size] = num;
        size++;
    }

    public void insertAt(int num, int index){
        if(isFull()){
            System.out.println("Erro ao adicionar um novo elemento, lista cheia!!");
            return;
        }
        for (int i = index; i < capacity-1; i++) {
            array[i+1] = array[i];
        }
        array[index] = num;
        size++;
    }

    public void removeFirst() {
        if(isEmpty()){
            System.out.println("Erro ao remover um elemento, lista vazia!!");
            return;
        }
        for(int i=1;i<size;i++){
            array[i-1] = array[i];
        }
        size--;
    }

    public void removeLast(){
        array[size-1] = 0;
        size--;
    }

    public void removeAt(int index){
        if(isEmpty()){
            System.out.println("Erro ao remover um elemento, lista vazia!!");
            return;
        }
        for(int i=index;i<size;i++){
            array[i] = array[i+1];
        }
        size--;
    }

    public void remove(int item){
        if(isEmpty()){
            System.out.println("Erro ao remover um elemento, lista vazia!!");
            return;
        }
        do{
            if(findBollean(item)){
                removeAt(find(item));
                continue;
            }
            return;

        }while(true);
    }

    public int find(int item){
        for(int i=0;i<size;i++){
            if(array[i]==item){
                return i;
            }
        }
        System.out.println("Item não encontrado.");
        return 0;
    }
    public boolean findBollean(int item){
        for(int i=0;i<size;i++){
            if(array[i]==item){
                return true;
            }
        }
        return false;
    }

    public int get(int index){
        return array[index];
    }

    public void set(int index, int num){
        array[index] = num;
    }

    public boolean isEmpty(){
        if(size==0){
            return true;
        }
        return false;
    }

    public boolean isFull(){
        if(size>=capacity){
            return true;
        }
        return false;
    }

    public void count(){

    }

    public void display(){
        for (int i=0; i<size; i++ ){
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }
    public void addSorted(int num){}
}
