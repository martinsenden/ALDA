package alda.linear;

import java.lang.reflect.Array;
import java.util.Iterator;


/**
 * Created by martinsenden on 2018-01-16.
 */
public class MyALDAList<E> implements ALDAList {

    private Node<E> first;
    private Node<E> last;


    //Todo, är det verkligen rätt att casta här?
    @Override
    public void add(Object element) {

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
    public void add(int index, Object element) throws IndexOutOfBoundsException{
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
    public Object remove(int index) {
        int counter = 0;

        for(Node<E> temp=first; temp!=null; temp=temp.next, counter++){
            if(counter == index){
                return temp.data;
            }
        }

        return null;
    }

    @Override
    public boolean remove(Object element) {
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

        for(Node<E> temp=first; temp!=null; temp=temp.next, counter++){

        }
        return counter;
    }

    @Override
    public Iterator iterator() {
        return null;
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
}


