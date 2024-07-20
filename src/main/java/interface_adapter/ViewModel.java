package interface_adapter;

public abstract class ViewModel {

    private final String modelName;

    public ViewModel(String modelName) {
        this.modelName = modelName;
    }

    public String getModelName() {
        return this.modelName;
    }
}
