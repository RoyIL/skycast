package use_case.setsettings;

public interface SetSettingsOutputBoundary {
    void prepareSuccessView(SetSettingsOutputData user);
    void prepareFailView(String error);
}
