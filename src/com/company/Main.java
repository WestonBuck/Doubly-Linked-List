package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        DoublyLinkedList list = new DoublyLinkedList();
        String[] arrayList = new String[list.size()];
        String[] arrayList2 = new String[list.size()];

        list.addFirst("USA");
        list.addLast("Germany");
        list.addFirst("France");
        list.addLast("England");
        list.addFirst("Belgium");

        DoublyLinkedList.Node current;
        arrayList = toArrayFromFirst(list);
        //arrayList2 = toArrayFromLast(list);


        for (int i = 0; i < list.size();i++)
        {
            System.out.println(arrayList[i]);
        }

        for (int i = 0; i < list.size();i++)
        {
            System.out.println(arrayList2[i]);
        }
    }

    public static String[] toArrayFromFirst(DoublyLinkedList list)
    {

        String[] stringArray = new String[list.size()];
        stringArray = list.getAllElements();
//
//        for (int i = 0; i<list.size(); i++)
//        {
//            stringArray = list.getAllElements();
//        }
        return stringArray;
    }

    /*public static String[] toArrayFromLast(DoublyLinkedList list)
    {
        String[] stringArray = new String[list.size()];
        for (int i = list.size(); i>0; i--)
        {
            stringArray[i] = list.getElement().toString();
        }
        return stringArray;
    }*/




    public static class DoublyLinkedList<E>
    {

        private static class Node<E>
        {
            private E element;
            private Node<E> prev;
            private Node<E> next;
            public Node (E e, Node<E> p , Node<E> n)
            {
                element = e;
                prev = p;
                next = n;
            }

            public E getElement()
            {
                return element;
            }

            public Node<E> getPrev()
            {
                return prev;
            }
            public Node<E> getNext()
            {
                return next;
            }
            public void setPrev(Node<E> p)
            {
                prev = p;
            }
            public void setNext(Node<E> n)
            {
                next = n;
            }
        }

        private Node<E> header;
        private Node<E> trailer;
        private int size = 0;

        public DoublyLinkedList()
        {
            header = new Node<>(null,null,null);
            trailer = new Node<>(null,null,null);
            header.setNext(trailer);
        }


        public int size()
        {
            return size;
        }

        public boolean isEmpty ()
        {
            return size == 0;
        }

        public E first ()
        {
            if (isEmpty())
            {
                return null;
            }
            return  header.getNext().getElement();
        }

        public E last ()
        {
            if (isEmpty())
            {
                return null;
            }
            return  header.getPrev().getElement();
        }

        public void addFirst (E e)
        {
            addBetween(e,header,header.getNext());
        }

        public void addLast(E e)
        {
            addBetween(e,trailer.getPrev(),trailer);
        }

        public E removeFirst()
        {
            if (isEmpty()) {
                return null;
            }
            return remove(header.getNext());
        }

        public E removeLast()
        {
            if (isEmpty()) {
                return null;
            }
            return remove(trailer.getPrev());
        }

        private void addBetween(E e, Node predecessor, Node successor)
        {
             Node<E> newest = new Node<>(e,predecessor,successor);
             predecessor.setNext(newest);
             successor.setPrev(predecessor);
             size++;
        }

        private E remove (Node<E> node)
        {
            Node<E> predessor = node.getPrev();
            Node<E> succcessor = node.getNext();
            predessor.setNext(succcessor);
            succcessor.setPrev(predessor);
            size--;
            return node.getElement();
        }

        public String[] getAllElements()
        {
            Node current = header;
            String[] temp = new String[size()];
            for(int i=0; i<size();i++)
            {
                current = current.getNext();
                temp[i]= current.element.toString();
            }
            return temp;
        }
    }
}
