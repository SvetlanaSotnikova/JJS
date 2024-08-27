package org.Sem1.Task2;

public class DumplingsMeat implements SemiFinishedFood{
    @Override
    public boolean getProteins() {
        return true;
    }

    @Override
    public boolean getFats() {
        return true;
    }

    @Override
    public boolean getCarbohydrates() {
        return true;
    }

    @Override
    public String getName() {
        return "DumplingsMeat";
    }
}
