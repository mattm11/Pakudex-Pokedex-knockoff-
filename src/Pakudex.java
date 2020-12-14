import java.util.ArrayList;
import java.util.Collections;

public class Pakudex {

    private int capacity;
    // private Pakuri[] pakudex = new Pakuri[capacity];
    private ArrayList<Pakuri> pakudex = new ArrayList<>();

    public Pakudex() {
        this.capacity = 20;
        ArrayList<Pakuri> pakudex = new ArrayList<>(capacity);
    }

    public Pakudex(int capacity) {
        this.capacity = capacity;
        ArrayList<Pakuri> pakudex = new ArrayList<>(capacity);
    }

    public int getSize() {
        int size = 0;
        for (int i = 0; i < pakudex.size(); i++) {
            if (pakudex.get(i).getAttack() > 0) {
                size++;
            }
        }
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    public String[] getSpeciesArray() {
        String[] speciesArray = new String[pakudex.size()];
        if (pakudex.size() == 0) {
            return null;
        }
        else {
            for (int i = 0; i < pakudex.size(); i++) {
                speciesArray[i] = pakudex.get(i).getSpecies();
            }
        }
        return speciesArray;
    }

    public int[] getStats(String species) {
        int[] stats = new int[3];
        if (pakuriIndex(species)  == -1) {
            return null;
        }
        else {
            for (int i = 0; i < pakudex.size(); i++) {
                if (pakudex.get(i).getSpecies().equals(species)) {
                    stats[0] = pakudex.get(i).getAttack();
                    stats[1] = pakudex.get(i).getDefense();
                    stats[2] = pakudex.get(i).getSpeed();
                }
            }
        }
        return stats;
    }

    public void sortPakuri() {
        for (int i = 0; i < pakudex.size() - 1; i++) {
            for (int j = i + 1; j < pakudex.size(); j++) {
                if (pakudex.get(i).getSpecies().compareTo(pakudex.get(j).getSpecies()) > 0) {
                    Pakuri temp = pakudex.get(i);
                    pakudex.set(i, pakudex.get(j));
                    pakudex.set(j, temp);
                }
            }
        }
    }

    public boolean addPakuri(String species) {
        boolean added = false;
        if (getSize() < getCapacity() && pakuriIndex(species) == -1) {
            Pakuri newPakuri = new Pakuri(species);
            pakudex.add(newPakuri);
            added = true;
        }
        return added;
    }

    public boolean evolveSpecies(String species) {
        boolean evolved = false;
        for (int i = 0; i < pakudex.size(); i++) {
            if (pakudex.get(i).getSpecies().equals(species)) {
                pakudex.get(i).evolve();
                evolved = true;
            }
        }
        return evolved;
    }

    public int pakuriIndex(String species) {
        for (int i = 0; i < pakudex.size(); i++) {
            if (pakudex.get(i).getSpecies().equals(species)) {
                return i;
            }
        }
        return -1;
    }

}
