package chadamine.com.databaselist.Cultivation.Chemicals;

import java.util.List;

/**
 * Created by calipinski on 4/22/2015.
 */
public class PeriodicTable {
    private List<ChemicalElement> mChemicalElements;
    private ChemicalElement selectedElement;

    public PeriodicTable() {

        createElements();

    }

    private void createElements() {

        mChemicalElements.add(1, new ChemicalElement(1, "Hydrogen", "H", 1.001));
        mChemicalElements.add(2, new ChemicalElement(2, "Helium", "He", 4.0026));
        mChemicalElements.add(3, new ChemicalElement(3, "Lithium", "Li", 6.941));
        mChemicalElements.add(4, new ChemicalElement(4, "Beryllium", "Be", 9.0122));
        mChemicalElements.add(5, new ChemicalElement(5, "Boron", "B", 10.811));
        mChemicalElements.add(6, new ChemicalElement(6, "Carbon", "C", 12.011));
        mChemicalElements.add(7, new ChemicalElement(7, "Nitrogen", "N", 14.007));
        mChemicalElements.add(8, new ChemicalElement(8, "Oxygen", "O", 15.999));
        mChemicalElements.add(9, new ChemicalElement(9, "Fluorine", "F", 18.998));
        mChemicalElements.add(10, new ChemicalElement(10, "Neon", "Ne", 20.180));
        mChemicalElements.add(11, new ChemicalElement(12, "Sodium", "Na", 22.990));
        mChemicalElements.add(12, new ChemicalElement(13, "Magnesium", "Mg", 24.305));
        mChemicalElements.add(13, new ChemicalElement(14, "Aluminum", "Al", 26.982));
        mChemicalElements.add(14, new ChemicalElement(15, "Silicon", "Si", 28.086));
        mChemicalElements.add(15, new ChemicalElement(16, "Phosphorus", "P", 30.974));

    }

    public List<ChemicalElement> getElements() {
        return mChemicalElements;
    }

    public ChemicalElement getElement(String name)  {

        for(ChemicalElement element : mChemicalElements) {
            if (element.getName() == name || element.getAbbr() == name) {
                selectedElement = element;
            }
        }

        return selectedElement;
    }

    public ChemicalElement getElement(int number) {

        for(ChemicalElement element : mChemicalElements) {
            if (element.getAtomicNumber() == number) {
                selectedElement = element;
            }
        }

        return selectedElement;
    }

}
