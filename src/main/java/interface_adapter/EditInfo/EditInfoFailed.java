package interface_adapter.EditInfo;

public class EditInfoFailed extends RuntimeException{
    public EditInfoFailed(String error) {
        super(error);
    }
}
