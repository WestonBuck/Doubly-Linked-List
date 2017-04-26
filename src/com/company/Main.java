package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        DoublyLinkedList list = new DoublyLinkedList();
        DoublyLinkedList copyList = new DoublyLinkedList();
        String[] arrayList = new String[list.size()];
        String[] arrayList2 = new String[list.size()];
        String[] arrayList3 = new String[list.size()];


        list.addFirst("USA");
        list.addLast("Germany");
        list.addFirst("France");
        list.addLast("England");
        list.addFirst("Belgium");

        arrayList = toArrayFromFirst(list);
        arrayList2 = toArrayFromLast(list);
        arrayList3 = deepCopy(list);

        System.out.println("Going Forwards:");
        for (int i = 0; i < list.size()-1;i++)
        {
            System.out.println(arrayList[i]);
        }
        System.out.println();
        System.out.println("Now Backwards:");
        for (int i = 0; i < list.size()-1;i++)
        {
           System.out.println(arrayList2[i]);
        }

        System.out.println();
        System.out.println("Now for the Copied List");
        for (int i = 0; i < list.size()-1;i++)
        {
            System.out.println(arrayList3[i]);
        }
    }

    public static String[] toArrayFromFirst(DoublyLinkedList list)
    {

        String[] stringArray = new String[list.size()];
        stringArray = list.getAllElements();
        return stringArray;
    }

    public static String[] toArrayFromLast(DoublyLinkedList list)
    {
        String[] stringArray2 = new String[list.size];
        stringArray2 = list.getAllElementsB();
        return stringArray2;
    }

    public static String[] deepCopy(DoublyLinkedList list)
    {
        DoublyLinkedList newList = new DoublyLinkedList();
        String[] stringArray = new String[list.size];
        newList.deepCopyList(list);

        stringArray = newList.getAllElements();
        return stringArray;
    }




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
            if (size < 1)
            {
                Node<E> newest = new Node<>(e,header,header.next);
                header.next = newest;
                newest.next = trailer;
                trailer.prev = newest;
                size++;
            }
            else
            {
                addBetween(e, header, header.getNext());
            }
        }

        public void addLast(E e)
        {
            if (size < 1)
            {
                Node<E> newest = new Node<>(e,header,trailer.prev);
                header.next = newest;
                trailer.prev = newest;
                size++;
            }
            else
            {
                addBetween(e,trailer.getPrev(),trailer);
            }
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

        private void addBetween(E e, Node<E> predecessor, Node<E> successor)
        {
             Node<E> newest = new Node<>(e,predecessor,successor);
             predecessor.setNext(newest);
             successor.setPrev(newest);
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
            String[] temp = new String[size];
            for(int i=0; i<size;i++)
            {
                current = current.getNext();
                temp[i]= current.element.toString();
            }
            return temp;
        }

        public String[] getAllElementsB()
        {
            Node current = trailer;
            String[] temp = new String[size];
            int j = 0;
            for(int i=size-1; i>=0;i--)
            {
                current = current.getPrev();
                temp[j]= current.element.toString();
                j++;
            }
            return temp;
        }

        public DoublyLinkedList deepCopyList(DoublyLinkedList OGList)
        {
            DoublyLinkedList newList = new DoublyLinkedList();
            Node<E> newNode; Node<E> oldHead;
            oldHead = OGList.header;
            
            oldHead = oldHead.next;
            while(oldHead.getNext() != null)
            {

                newList.addLast(oldHead.getElement());
                oldHead = oldHead.getNext();
            }

            String[] temp = new String[size];
            return newList;
        }
    }
}
