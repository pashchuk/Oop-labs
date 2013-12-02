/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop_lab4;

/**
 *
 * @author Eduard
 */
public class Main {
    public static void main(String[] args) {
        // TODO code application logic here
        AmmunitionList list = new AmmunitionList();
        
        Ammunition helm1 = new Helm(300,20,50);
        Ammunition sword1 = new Sword(500,8,"Sword1");
        Ammunition sword2 = new Sword(450,5,"Sword2");
        Ammunition armor1= new Armor(800,20,150);
        Ammunition armor2 = new Armor(900,22,350);
        
        list.add(helm1);
        list.add(sword1);
        System.out.println("Lenght: " + list.size());
        list.PrintAmmunition();
        list.remove(0);
        list.PrintAmmunition();
        System.out.println("Cost: " + list.get(list.size() - 1).GetCost());
        list.clear();
        list.PrintAmmunition();
    }
}
