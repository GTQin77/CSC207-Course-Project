package interface_adapter;

public abstract class ViewModel {

    private String modelName;

    public ViewModel(String modelName) {
        this.modelName = modelName;
    }

    public String getModelName() {
        return this.modelName;
    }
}
