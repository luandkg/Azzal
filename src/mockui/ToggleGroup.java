package mockui;

import java.util.ArrayList;

public class ToggleGroup {

    private ArrayList<Toggle> mToggles;

    public ToggleGroup() {

        mToggles = new ArrayList<Toggle>();

    }

    public void adicionar(Toggle eToggle) {
        mToggles.add(eToggle);
    }

    public void marcar(Toggle eToggle) {

        for (Toggle mToggle : mToggles) {
            mToggle.setStatus(false);
        }

        eToggle.setStatus(true);

    }

}
