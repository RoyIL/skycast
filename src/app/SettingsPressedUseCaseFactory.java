package app;

import interface_adapter.loggedin.settings.SettingsViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.loggedin.settings.SettingsButtonController;
import interface_adapter.loggedin.settings.SettingsButtonPresenter;
import use_case.settings_pressed.SettingsPressedInteractor;
import use_case.settings_pressed.SettingsPressedInputBoundary;
import use_case.settings_pressed.SettingsPressedOutputBoundary;

public class SettingsPressedUseCaseFactory {


    private SettingsPressedUseCaseFactory() {}

    //private static create(SettingsViewModel viewModel, ViewManagerModel managerModel) {


    public static SettingsButtonController createSettingsButtonController(ViewManagerModel viewManager,
                                                                          SettingsViewModel viewModel) {
    //SettingsPressedInputBoundary interactor,
    //SettingsPressedOutputBoundary presenter)
        SettingsPressedOutputBoundary presenter = new SettingsButtonPresenter(viewManager, viewModel);
        SettingsPressedInputBoundary interactor = new SettingsPressedInteractor(presenter);
        return new SettingsButtonController(interactor);
    }
}
