/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop_lab4;

import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Eduard
 */
public class AmmunitionList {
     /* Elements count */
    private int size = 0;

    private Ammunition[] elements;

    public AmmunitionList() {
        this.elements = new Ammunition[15];
    }

    public AmmunitionList(Ammunition car) {
        this.elements = new Ammunition[15];
        add(car);
    }


    public AmmunitionList(Collection<Ammunition> collection) {
        this.elements = new Ammunition[15];
        addAll(collection);
    }


    /**
     * Growing array capacity on 50 %
     * */
    private void growArrayOfElements() {
        int oldCapacity = elements.length;
        int newCapacity = oldCapacity + Math.round((float)(oldCapacity * 0.3));

        this.elements = Arrays.copyOf(elements, newCapacity);
    }

    /** Appends the specified element to the end of this list */
    public boolean add(Ammunition amun) {
        if (size == elements.length) growArrayOfElements();

        elements[size] = amun;
        size++;

        return true;
    }

    /** Inserts the specified element at the specified position in this list */
    public void add(int index, Ammunition c) {
        if (index >= size) throw new IndexOutOfBoundsException();

        System.arraycopy(elements, index, elements, index + 1,
                size - index);

        elements[index] = c;
        size++;
    }

    /**
     * Appends all of the elements in the specified collection to the end of this list,
     * in the order that they are returned by the specified collection's iterator
     * @param collectionOfAmmunitions The collection who was added to list
     */
    public boolean addAll(Collection<? extends Ammunition> collectionOfAmmunitions) {
        if (collectionOfAmmunitions.size() == 0) return false; // if array is clear

        for (Ammunition c : collectionOfAmmunitions) {
            add(c);
        }

        return true;   //was changed
    }

    /**
     * Inserts all of the elements in the specified collection into this list at the
     * specified position
     * @param index A start added position
     * @param collectionOfAmmunitions The collection who was added to list
     *  */
    public boolean addAll(int index, Collection<? extends Ammunition> collectionOfAmmunitions) {
        if (index >= size) throw new IndexOutOfBoundsException();

        if (collectionOfAmmunitions.size() == 0) return false;

        System.arraycopy(elements, index, elements, index + collectionOfAmmunitions.size(),
                size - index);

        for (Ammunition c : collectionOfAmmunitions) {
            elements[size] = c;
            size++;
        }

        return true;
    }

    /** Removes all of the elements from this list */
    public void clear() {
        for (int i = 0; i < size; i++)
            elements[i] = null;

        size = 0;
    }

    /** Returns true if this list contains the specified element 
     * @param o Object who have to checked on contains in current list
     * @return Return index from contains element
     **/
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    /**
     * Returns true if this list contains all of the
     * elements of the specified collection
     * @param collectionOfAmmunitions The collection who have to checked on contains in current list
     * */
    public boolean containsAll(Collection<?> collectionOfAmmunitions) {
        Ammunition[] cars = collectionOfAmmunitions.toArray(new Ammunition[collectionOfAmmunitions.size()]);

        for (Ammunition c : cars) {
            if (indexOf(c) == -1) return false;
        }

        return true;
    }

    /**
     * Compares the specified object with this list for equality
     * @param collectionOfAmmunitions The collection who have to compare with current list
     * */
    public boolean equals(Collection<? extends Ammunition> collectionOfAmmunitions) {
        if (collectionOfAmmunitions.size() != this.size) return false;

        Ammunition[] cars = collectionOfAmmunitions.toArray(new Ammunition[collectionOfAmmunitions.size()]);

        for (int i = 0; i < cars.length; i++) {
            if (indexOf(cars[i]) == -1) return false;
        }

        return true;
    }

    /**
     * Returns the element at the specified position in this list
     * @param index Index from needed element
     * @return Return element for specified index
     * */
    public Ammunition get(int index) {
        if (index >= size) throw new IndexOutOfBoundsException();
        return elements[index];
    }

    /**
     * Replaces the element at the specified position in this list
     * with the specified element
     * @param index Index of needed element
     * @param c Replaced object
     * @return Return object who was replaced
     * */
    public Ammunition set(int index, Ammunition c) {
        if (index >= size) throw new IndexOutOfBoundsException();

        Ammunition removed = elements[index];
        elements[index] = c;

        return removed;
    }

    /**@return Return true if list is empty*/
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Returns the index of the first occurrence of the specified element in
     * this list, or -1 if this list does not contain the element.
     * @param o Checked object
     * @return index of needed object
     * */
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < elements.length; i++)
                if (elements[i] == null)
                    return i;
        } else {
            for (int i = 0; i < elements.length; i++)
                if (o.equals(elements[i]))
                    return i;
        }
        return -1;
    }

    /**
     * Returns the index of the last occurrence of the specified
     * element in this list, or -1 if this list does not contain the element.
     * @param c Checked object
     * @return Index of needed object
     * */
    public int lastIndexOf(Object c) {
        if (c == null) {
            for (int i = elements.length - 1; i >= 0; i--)
                if (elements[i] == null)
                    return i;
        } else {
            for (int i = elements.length - 1; i >= 0; i--)
                if (c.equals(elements[i]))
                    return i;
        }
        return -1;
    }


    public int size() {
        return this.size;
    }

     /**
     * Remove element from specified index
     * @param index Index of element who have to remove
     * @return Return element who was removed
     * */
    public Ammunition remove(int index) {
        if (index > size - 1) throw new IndexOutOfBoundsException();

        Ammunition removed = elements[index];

        System.arraycopy(elements, index + 1, elements, index,
                   size - index - 1);

        size--;
        elements[size] = null;

        return removed;
    }

    /**Remove object from list if it contains in list*/
    public boolean remove(Object c) {
        int index = indexOf(c);
        if (index == -1) return false;

        System.arraycopy(elements, index + 1, elements, index,
                size - index - 1);

        size--;
        elements[size] = null;
        return true;
    }

    /**
     * Removes from this list all of its elements that are contained in
     * the specified collection
     * @param collectionOfAmmunitions Collection who have to remove
     * */
    public boolean removeAll(Collection<?> collectionOfAmmunitions) {
        boolean status = false;

        Ammunition[] cars = collectionOfAmmunitions.toArray(new Ammunition[collectionOfAmmunitions.size()]);
        for (int i = 0; i < cars.length; i++)
            if (remove(cars[i])) status = true;

        return status;
    }

    /** Retains only the elements in this list that are contained in the specified collection */
    public boolean retainAll(Collection<?> collectionOfAmmunitions) {
        Ammunition[] cars = collectionOfAmmunitions.toArray(new Ammunition[collectionOfAmmunitions.size()]);

        return true;
    }

    /**
     * Returns a view of the portion of this list between the specified fromIndex,
     * inclusive, and toIndex, exclusive.
     * @param fromIndex Start position
     * @param toIndex Finish position
     * @return Return AmmunitionList from specified diapasone
     * */
    public AmmunitionList subList(int fromIndex, int toIndex) {
        AmmunitionList sublist = new AmmunitionList();

        for (int i = fromIndex; i <= toIndex; i++) {
            sublist.add(elements[i]);
        }

        return sublist;
    }

    /**
     * Convert AmmunitionList to array
     * @return Return AmmunitionList in object array
     */
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    public <T> T[] toArray(final T[] a) {
        Object[] result = a;
        if (a.length < size) {
            result = Arrays.copyOf(elements, a.length);
        }

        if (a.length > size) {
            result = Arrays.copyOf(elements, size);
            Arrays.fill(result, a.length, size, null);
        }

        return (T[]) result;
    }

    /**
     * Get iterator in AmmunitionList
     * @return Needed iterator
     */
    public Iterator<Ammunition> iterator() {
        return listIterator();
    }

    /**
     * Get ListIterator in AmmunitionLst
     * @return Needed ListIterator
     */
    public ListIterator<Ammunition> listIterator() {
        return listIterator(0);
    }

    /**
     * Get ListIterator from specified index
     * @param index Index of start iterator's cursor
     * @return Needed ListIterator
     */
    public ListIterator<Ammunition> listIterator(int index) {
        if (index >= size) new IndexOutOfBoundsException();
        return new ListItr(index);
    }

    private class ListItr implements ListIterator<Ammunition> {
        int cursor;       // index of next element to return
        int lastRet = -1; // index of last element returned; -1 if no such


        ListItr(int index) {
            cursor = index;
        }

        public boolean hasPrevious() {
            return cursor != 0;
        }

        public int nextIndex() {
            return cursor + 1;
        }

        public int previousIndex() {
            return cursor - 1;
        }

        public Ammunition previous() {
            int i = cursor - 1;

            if (i < 0)
                throw new NoSuchElementException();

            cursor = i;
            lastRet = i;
            return elements[lastRet];
        }


        public boolean hasNext() {
            return cursor != size;
        }

        public Ammunition next() {
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            cursor = i + 1;
            lastRet = i;
            return elements[lastRet];
        }

        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();

            try {
                AmmunitionList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
              } catch (IndexOutOfBoundsException e) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void set(Ammunition knight) {
           if (lastRet == -1)
               throw new IllegalStateException();
        }

        @Override
        public void add(Ammunition knight) {
        }
    }
    public void PrintAmmunition() {
        if(this.size==0) {
            System.out.println("List is empty");
            return;
        }
        System.out.format("%10s%10s%10s%25s\n", "Type","Cost","Height", "Other Character");
        for (int i = 0; i < size; i++)
        {
            if(elements[i] instanceof Sword )
                System.out.format("%10s%10d%10d%25s\n", "Sword",elements[i].GetCost(),
                        elements[i].GetHeight(), "Name: " + ((Sword)elements[i]).GetName());
            if(elements[i] instanceof Helm )
                System.out.format("%10s%10d%10d%25s\n", "Helm",elements[i].GetCost(),
                        elements[i].GetHeight(), "Safety = " + ((Helm)elements[i]).GetSafety());
            if(elements[i] instanceof Immortal )
                System.out.format("%10s%10d%10d%25s\n", "Immortal",elements[i].GetCost(),
                        elements[i].GetHeight(), "Diametr = " + ((Immortal)elements[i]).GetDiametr()) ;
            if(elements[i] instanceof Armor )
                System.out.format("%10s%10d%10d%25s\n", "Armor",elements[i].GetCost(),
                        elements[i].GetHeight(), "Safety = " + ((Armor)elements[i]).GetSafety());
        }
        System.out.print("\n\n");
    }
}
