package org.Sem1.Task2;

public class DumplingsBerries  implements SemiFinishedFood{
    @Override
    public boolean getCarbohydrates() {
        return true;
    }

    @Override
    public boolean getProteins() {
        return false;
    }

    @Override
    public boolean getFats() {
        return false;
    }

    @Override
    public String getName() {
        return "Dumplings Berries";
    }
}
