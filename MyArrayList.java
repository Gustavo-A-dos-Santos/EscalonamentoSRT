package MyArrayList;
//addFirst, addLast, insertAt), remoção (removeFirst, removeLast, removeAt, remove),
// busca (find, get) e atualização (set),
public class MyArrayList implements MyArrayListTAD {
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
    @Override
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
    @Override
    public void addLast(int num){
        if(isFull()){
            System.out.println("Erro ao adicionar um novo elemento, lista cheia!!");
            return;
        }
        array[size] = num;
        size++;
    }
    @Override
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
    @Override
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
    @Override
    public void removeLast(){
        array[size-1] = 0;
        size--;
    }
    @Override
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
    @Override
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
    @Override
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
    @Override
    public int get(int index){
        return array[index];
    }
    @Override
    public void set(int index, int num){
        array[index] = num;
    }
    @Override
    public boolean isEmpty(){
        if(size==0){
            return true;
        }
        return false;
    }
    @Override
    public boolean isFull(){
        if(size>=capacity){
            return true;
        }
        return false;
    }
    @Override
    public void count(){

    }
    @Override
    public void display(){
        for (int i=0; i<size; i++ ){
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }
    @Override
    public void addSorted(int num){}
}
