package view;

import entity.Business;
import java.util.ArrayList;

/**
 * Interface for views that interact with and display business details within a day plan.
 */
public interface IDayplanView {
    void updateBusinessButtons(ArrayList<Business> businesses);
}

