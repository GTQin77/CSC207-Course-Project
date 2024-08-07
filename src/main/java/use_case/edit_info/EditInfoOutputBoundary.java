package use_case.edit_info;

public interface EditInfoOutputBoundary {
    /**
     * Output boundary for editing user info.
     * <p>
     *    Implementation referenced from the Paul Gries Clean Architecture code
     * </p>
     * */
    void prepareSuccessView(EditInfoOutputData outputData);

    void prepareFailView(String errorMessage);
}
