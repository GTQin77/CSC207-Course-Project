package interface_adapter;


/**
 * View model of the program.
 * <p>
 * This implementation referenced the Pualgries' Clean Architecture code for ViewModel on
 * <a href="https://github.com/paulgries/LoginCleanArchitecture/blob/main/src/interface_adapter/ViewModel.java">github.com</a>.
 * </p>
 */
public abstract class ViewModel {

    private final String modelName;

    public ViewModel(String modelName) {
        this.modelName = modelName;
    }

    public String getModelName() {
        return this.modelName;
    }
}
