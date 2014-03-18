
import java.util.Arrays;
import java.util.Comparator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eduard
 */
public class OopLab3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Sword a = new Sword(10, 30, "Excalibur");
        Helm b = new Helm(20, 20, 15);
        Knight knight = new Knight();
        knight.TakeSword(a);
        knight.TakeHelm(b);
        knight.TakeHelm(20, 35, 20);
        knight.PrintAmmunition();
        knight.Sort();
        knight.PrintAmmunition();
        System.out.println(knight.GetAmmunitionCost());
        knight.Find(15, 20);
    }
    
}

abstract class Ammunition {
    private int Cost;
    private int Height;
    public Ammunition(int Cost, int Height) {
        this.Cost = Cost;
        this. Height = Height;
    }
    public int GetCost()
    {
        return this.Cost;
    }
    public int GetHeight()
    {
        return this.Height;
    }

    @Override
    public boolean equals(Object obj) {
        Ammunition a = (Ammunition)obj;
        if(a.GetCost()==this.Cost)
            if(a.GetHeight()==this.Height)
                return true;
        return false;
    }
    
}

class Sword extends Ammunition {
    private String name;
    public Sword(int Cost, int Height, String name) {
        super(Cost,Height);
        this.name = name;
    }
    public String GetName() {
        return this.name;
    }
}

class Immortal extends Ammunition {
    private int Diametr;
    public Immortal(int Cost, int Height,int Diametr) {
        super(Cost, Height);
        this.Diametr = Diametr;
    }
    public int GetDiametr() {
        return this.Diametr;
    }
}

class Helm extends Ammunition {
    private int safety;
    public Helm(int Cost, int Height, int Safety) {
        super(Cost, Height);
        safety = Safety;
    }
    public int GetSafety() {
        return this.safety;
    }
}

class Armor extends Ammunition {
    private int safety;
        public Armor(int Cost, int Height, int Safety) {
        super(Cost, Height);
        safety = Safety;
    }
    public int GetSafety() {
        return this.safety;
    }
}

class Knight {
    private int AmunCount = 0;
    private Ammunition[] amunition;
    public Knight() {
        amunition = new Ammunition[20];
    }
    public void TakeSword(int Cost, int Height, String name) {
        amunition[AmunCount++] = new Sword(Cost, Height, name);
    }
    public void TakeSword(Sword sword) {
        amunition[AmunCount++] = new Sword(sword.GetCost(),sword.GetHeight(),sword.GetName());
    }
    public void TakeHelm(int Cost, int Height, int Safety) {
        amunition[AmunCount++] = new Helm(Cost, Height, Safety);
    }
    public void TakeHelm(Helm helm) {
        amunition[AmunCount++] = new Helm(helm.GetCost(), helm.GetHeight(), helm.GetSafety());
    }
    public void TakeImmortal(int Cost, int Height, int Diametr) {
        amunition[AmunCount++] = new Immortal(Cost, Height, Diametr);
    }
    public void TakeImmortal(Immortal immortal) {
        amunition[AmunCount++] = new Immortal(immortal.GetCost(), immortal.GetHeight(), immortal.GetDiametr());
    }
    public void TakeArmor(int Cost, int Height, int Safety) {
        amunition[AmunCount++] = new Armor(Cost, Height, Safety);
    }
    public void TakeArmor(Armor armor) {
        amunition[AmunCount++] = new Armor(armor.GetCost(), armor.GetHeight(), armor.GetSafety());
    }
    public int GetAmmunitionCost() {
        int sum = 0;
        for (int i = 0; i < AmunCount; i++)
            sum += amunition[i].GetCost();
        return sum;
    }
    public void PrintAmmunition() {
        System.out.format("%10s%10s%10s%25s\n", "Type","Cost","Height", "Other Character");
        for (int i = 0; i < AmunCount; i++)
        {
            if(amunition[i] instanceof Sword )
                System.out.format("%10s%10d%10d%25s\n", "Sword",amunition[i].GetCost(),
                        amunition[i].GetHeight(), "Name: " + ((Sword)amunition[i]).GetName());
            if(amunition[i] instanceof Helm )
                System.out.format("%10s%10d%10d%25s\n", "Helm",amunition[i].GetCost(),
                        amunition[i].GetHeight(), "Safety = " + ((Helm)amunition[i]).GetSafety());
            if(amunition[i] instanceof Immortal )
                System.out.format("%10s%10d%10d%25s\n", "Immortal",amunition[i].GetCost(),
                        amunition[i].GetHeight(), "Diametr = " + ((Immortal)amunition[i]).GetDiametr()) ;
            if(amunition[i] instanceof Armor )
                System.out.format("%10s%10d%10d%25s\n", "Armor",amunition[i].GetCost(),
                        amunition[i].GetHeight(), "Safety = " + ((Armor)amunition[i]).GetSafety());
        }
        System.out.print("\n\n");
    }
    public void Sort() {
        Arrays.sort(amunition, 0, AmunCount, new CompareAmmunition());
    }
    public void Find(int startCost, int endCost) {
        boolean IsFinded = false;
        System.out.println("Find value:");
        for (int i = 0; i < AmunCount; i++)
            if(amunition[i].GetCost()>=startCost&&amunition[i].GetCost()<=endCost) {
                IsFinded = true;
                break;
            }
        if (IsFinded) {
            System.out.format("%10s%10s%10s%25s\n", "Type","Cost","Height", "Other Character");
            for (int i = 0; i < AmunCount; i++)
                if(amunition[i].GetCost()>=startCost&&amunition[i].GetCost()<=endCost)
                    System.out.print(PrintAmmo(amunition[i]));
        }
        else
            System.out.println("Value is not finded");
    }
    private String PrintAmmo(Ammunition a) {
        if(a instanceof Sword )
            return String.format("%10s%10d%10d%25s\n", "Sword",a.GetCost(),
                    a.GetHeight(), "Name: " + ((Sword)a).GetName());
        if(a instanceof Helm )
            return String.format("%10s%10d%10d%25s\n", "Helm",a.GetCost(),
                    a.GetHeight(), "Safety = " + ((Helm)a).GetSafety());
        if(a instanceof Immortal )
            return String.format("%10s%10d%10d%25s\n", "Immortal",a.GetCost(),
                    a.GetHeight(), "Diametr = " + ((Immortal)a).GetDiametr()) ;
        if(a instanceof Armor )
            return String.format("%10s%10d%10d%25s\n", "Armor",a.GetCost(),
                    a.GetHeight(), "Safety = " + ((Armor)a).GetSafety());
        return null;
    }
}

class CompareAmmunition implements Comparator<Ammunition> {
    public int compare(Ammunition a1, Ammunition a2) {
        return a1.GetHeight() - a2.GetHeight();
    }
}