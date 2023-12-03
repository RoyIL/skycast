package use_case.location_lookup;

public interface LocationLookupOutputBoundary {
    void prepareSuccessView(LocationLookupOutputData user);
    void prepareFailView(String error);
}
