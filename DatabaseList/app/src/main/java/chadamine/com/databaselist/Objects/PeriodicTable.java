package chadamine.com.databaselist.Objects;

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
        mChemicalElements.add(1, new ChemicalElement("Hydrogen", "H", 1.001));
        mChemicalElements.add(1, new ChemicalElement("Hydrogen", "H", 1.001));
        mChemicalElements.add(1, new ChemicalElement("Hydrogen", "H", 1.001));
        mChemicalElements.add(1, new ChemicalElement("Hydrogen", "H", 1.001));
        mChemicalElements.add(1, new ChemicalElement("Hydrogen", "H", 1.001));
        mChemicalElements.add(1, new ChemicalElement("Hydrogen", "H", 1.001));
        mChemicalElements.add(1, new ChemicalElement("Hydrogen", "H", 1.001));
        mChemicalElements.add(1, new ChemicalElement("Hydrogen", "H", 1.001));
        mChemicalElements.add(1, new ChemicalElement("Hydrogen", "H", 1.001));
        mChemicalElements.add(1, new ChemicalElement("Hydrogen", "H", 1.001));
        mChemicalElements.add(1, new ChemicalElement("Hydrogen", "H", 1.001));
        mChemicalElements.add(1, new ChemicalElement("Hydrogen", "H", 1.001));
        mChemicalElements.add(1, new ChemicalElement("Hydrogen", "H", 1.001));
        mChemicalElements.add(1, new ChemicalElement("Hydrogen", "H", 1.001));
        mChemicalElements.add(1, new ChemicalElement("Hydrogen", "H", 1.001));
        mChemicalElements.add(1, new ChemicalElement("Hydrogen", "H", 1.001));

    }

    public List<ChemicalElement> getElements() {
        return mChemicalElements;
    }

    public ChemicalElement getElement(String name) {
        for(ChemicalElement element : mChemicalElements) {
            if (element.getName() == name) {
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
