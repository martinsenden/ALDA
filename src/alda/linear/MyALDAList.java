package alda.linear;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Created by martinsenden on 2018-01-16.
 */
public class MyALDAList<E> implements ALDAList {

    private Node<E> first;
    private Node<E> last;


    //Todo, är det verkligen rätt att casta här?
    @Override
    public void add(Object element) throws NullPointerException {

        if (element == null){
            throw new NullPointerException();
        }

        if (first == null){
            first = new Node<E>((E) element);
            last = first;
        }
        else {
            last.next = new Node<E>((E) element);
            last = last.next;
        }
    }

    @Override
    public void add(int index, Object element) throws IndexOutOfBoundsException, NullPointerException{

        if (element == null){
            throw new NullPointerException();
        }

        if (index > size() || index < 0){
            throw new IndexOutOfBoundsException();
        }

        Node<E> newNode = new Node<E>((E) element);

        if(first == null){
            first = newNode;
            last = newNode;
        }



        int counter = 0;
            for (Node<E> temp = first; temp != null; temp = temp.next, counter++) {
                if (counter == index && index == 0) {
                    newNode.next = temp;
                    first = newNode;
                }

                if (counter + 1 == index) {
                    newNode.next = temp.next;
                    temp.next = newNode;
                    if (newNode.next == null) {
                        last = newNode;
                    }

                }
            }
    }

    @Override
    public Object remove(int index) throws IndexOutOfBoundsException {

        if (index > size() || index < 0){
            throw new IndexOutOfBoundsException();
        }

        int counter = 0;
            Node<E> deletedNode;
            if (index == 0){
                deletedNode = first;
                first = first.next;
                return deletedNode.data;

            }

            for (Node<E> temp = first; temp != null; temp = temp.next, counter++){
                if (counter+1 == index && temp.next == last){
                    deletedNode = temp.next;
                    temp.next = null;
                    last = temp;
                    return deletedNode.data;
                }
                if (counter+1 == index){
                    deletedNode = temp.next;
                    temp.next = temp.next.next;
                    return deletedNode.data;
                }
            }
            return null;
    }

    @Override
    public boolean remove(Object element) {
        if(!contains(element)){
            return false;
        }
        if (first.data == element){
            first = first.next;
            return true;

        }

        for (Node<E> temp = first; temp != null; temp = temp.next) {
            if(temp.next.data == element && temp.next == last){
                temp.next = null;
                last = temp;
                return true;
            }
            if(temp.next.data == element){
                temp.next = temp.next.next;
                return true;

            }
        }
        return false;
    }

    @Override
    public Object get(int index) throws IndexOutOfBoundsException {
            int counter = 0;

            for (Node<E> temp = first; temp != null; temp = temp.next, counter++) {
                if (counter == index) {
                    return temp.data;
                }
            }

            throw new IndexOutOfBoundsException();
    }

    @Override
    public boolean contains(Object element) {

        for(Node<E> temp=first; temp!=null; temp=temp.next){
            if(temp.data == element || temp.data.equals(element)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Object element) {
        int counter = 0;

        for(Node<E> temp=first; temp!=null; temp=temp.next, counter++){
            if(temp.data == element || temp.data.equals(element)){
                return counter;
            }
        }

        return -1;
    }

    @Override
    public void clear() {

    }

    @Override
    public int size() {
        int counter = 0;

        for(Node<E> temp=first; temp!=null; temp=temp.next, counter++);
        return counter;
    }

    @Override
    public ALDAIterator iterator() {
        return new ALDAIterator();
    }

    @Override
    public String toString(){
        String tmpString = "[";

        for(Node<E> temp=first; temp!=null; temp=temp.next){
            if (temp.next != null) {
                tmpString += temp.data.toString() + ", ";
            }
            else {
                tmpString += temp.data.toString();
            }
        }

        return tmpString + "]";
    }


    private static class Node<E>{
        E data;
        Node next;

        public Node(E data){
            this.data = data;
        }
    }

    private class ALDAIterator implements Iterator{

        private Node<E> currentNode = first;


        @Override
        public boolean hasNext() {
            return currentNode != null;
            //Hur fan göra det här? Borde vara .next Om vi tar bort .next fungerar BasicIteration
        }

        @Override
        public Object next() throws NoSuchElementException{

            if (!hasNext()){
                throw new NoSuchElementException();
            }

            E nextData = currentNode.data;
            currentNode = currentNode.next;

            return nextData;
        }

        @Override
        public void remove(){

        }
    }
}


