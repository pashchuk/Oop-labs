/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package oop_lab4;

import java.util.ArrayList;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 *
 * @author Eduard
 */
public class JUnit {
    Ammunition helm1 = new Helm(300,20,50);
    Ammunition sword1 = new Sword(500,8,"Sword1");
    Ammunition sword2 = new Sword(450,5,"Sword2");
    Ammunition armor1= new Armor(800,20,150);
    Ammunition armor2 = new Armor(900,22,350);

    @Test
    public void testAdd() throws Exception {
        AmmunitionList amun = new AmmunitionList();
        amun.add(helm1);
        assertEquals(amun.size(), 1);
    }

    @Test
    public void testAddAll() throws Exception {
        AmmunitionList amun = new AmmunitionList();

        ArrayList<Ammunition> collection = new ArrayList<Ammunition>();
        collection.add(helm1);
        collection.add(sword1);
        collection.add(sword2);

        amun.addAll(collection);
        assertEquals(amun.size(), 3);
    }

    @Test
    public void testClear() throws Exception {
        AmmunitionList amun = new AmmunitionList();

        ArrayList<Ammunition> collection = new ArrayList<Ammunition>();
        collection.add(helm1);
        collection.add(sword1);
        collection.add(sword2);

        amun.addAll(collection);

        amun.clear();
        assertTrue(amun.isEmpty());
    }

    @Test
    public void testContains() throws Exception {
        AmmunitionList amun = new AmmunitionList(helm1);

        assertTrue(amun.contains(helm1));
        amun.clear();
        assertFalse(amun.contains(helm1));
    }

    @Test
    public void testContainsAll() throws Exception {
        ArrayList<Ammunition> amoArrayList = new ArrayList<Ammunition>();
        amoArrayList.add(helm1);
        amoArrayList.add(sword2);
        amoArrayList.add(sword1);

        AmmunitionList list = new AmmunitionList(amoArrayList);
        assertEquals(list.size(), 3);

        list.add(armor1);

        assertTrue(list.containsAll(amoArrayList));

        amoArrayList.add(armor2);
        assertFalse(list.containsAll(amoArrayList));
    }

    @Test
    public void testEquals() throws Exception {
        ArrayList<Ammunition> amoArrayList = new ArrayList<Ammunition>();
        amoArrayList.add(helm1);
        amoArrayList.add(sword2);
        amoArrayList.add(sword1);

        AmmunitionList list = new AmmunitionList();
        list.add(helm1);
        list.add(sword2);
        list.add(sword1);

        assertTrue(list.equals(amoArrayList));
        amoArrayList.remove(0);

        assertFalse(list.equals(amoArrayList));
    }

    @Test
    public void testGet() throws Exception {
        AmmunitionList list = new AmmunitionList();
        list.add(helm1);
        list.add(sword1);
        list.add(armor1);
        assertTrue(list.get(1).equals(sword1));
    }

    @Test
    public void testSet() throws Exception {
        AmmunitionList list = new AmmunitionList();
        list.add(helm1);
        list.add(sword1);
        list.add(armor1);

        list.set(2, sword2);

        assertTrue(list.get(2).equals(sword2));
    }

    @Test
    public void testIsEmpty() throws Exception {
        AmmunitionList list = new AmmunitionList();
        assertTrue(list.isEmpty());

        list.add(helm1);
        assertFalse(list.isEmpty());
    }

    @Test
    public void testIndexOf() throws Exception {
        AmmunitionList list = new AmmunitionList();
        list.add(helm1);
        list.add(sword1);
        list.add(armor1);
        list.add(sword1);

        assertEquals(list.indexOf(sword1), 1);
    }

    @Test
    public void testLastIndexOf() throws Exception {
        AmmunitionList list = new AmmunitionList();
        list.add(helm1);
        list.add(sword1);
        list.add(armor1);
        list.add(sword1);

        assertEquals(list.lastIndexOf(sword1), 3);
    }

    @Test
    public void testSize() throws Exception {
        AmmunitionList amun = new AmmunitionList();

        ArrayList<Ammunition> collection = new ArrayList<Ammunition>();
        collection.add(helm1);
        collection.add(sword1);
        collection.add(sword2);

        amun.addAll(collection);
        assertEquals(amun.size(), 3);
    }

    @Test
    public void testRemove() throws Exception {
        AmmunitionList amun = new AmmunitionList();

        ArrayList<Ammunition> collection = new ArrayList<Ammunition>();
        collection.add(helm1);
        collection.add(sword1);
        collection.add(sword2);

        amun.addAll(collection);
        amun.remove(2);

        assertEquals(amun.size(),2);

        amun.remove(helm1);
        assertFalse(amun.contains(helm1));
    }

    @Test
    public void testRemoveAll() throws Exception {
        AmmunitionList amun = new AmmunitionList();

        ArrayList<Ammunition> collection = new ArrayList<Ammunition>();
        collection.add(helm1);
        collection.add(sword1);
        collection.add(sword2);

        amun.addAll(collection);

        amun.removeAll(collection);

        assertEquals(amun.size(), 0);
    }

    @Test
    public void testRetainAll() throws Exception {
        AmmunitionList amun = new AmmunitionList();

        ArrayList<Ammunition> collection = new ArrayList<Ammunition>();
        collection.add(helm1);
        collection.add(sword1);
        collection.add(sword2);

        amun.addAll(collection);

        amun.removeAll(collection);

        assertEquals(amun.size(), 0);
    }

    @Test
    public void testSubList() throws Exception {
        AmmunitionList amun = new AmmunitionList();

        ArrayList<Ammunition> collection = new ArrayList<Ammunition>();
        collection.add(helm1);
        collection.add(sword1);
        collection.add(sword2);
        collection.add(armor1);
        collection.add(armor2);

        amun.addAll(collection);

        AmmunitionList subList = amun.subList(1, 3);

        assertTrue(subList.get(2).equals(armor1));
    }

    @Test
    public void testToArray() throws Exception {
        AmmunitionList amun = new AmmunitionList();

        ArrayList<Ammunition> collection = new ArrayList<Ammunition>();
        collection.add(helm1);
        collection.add(sword1);
        collection.add(sword2);
        collection.add(armor1);
        collection.add(armor2);

        amun.addAll(collection);

        for (int i = 0; i < amun.toArray().length; i++) {
            assertTrue(amun.toArray()[i].equals(collection.get(i)));
        }
    }

    private void assertEquals(int size, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
