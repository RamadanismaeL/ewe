package ram.ewe.englishwithexperts.Models;

import ram.ewe.englishwithexperts.Controllers.Alerts.Confirmacao.AlertExitAppController;
import ram.ewe.englishwithexperts.Controllers.Alerts.Confirmacao.AlertExitPaymentRegistrationController;
import ram.ewe.englishwithexperts.Controllers.Alerts.Confirmacao.AlertRestartAppController;
import ram.ewe.englishwithexperts.Controllers.DashboardController;
import ram.ewe.englishwithexperts.Controllers.Registrations.RegistrationsController;
import ram.ewe.englishwithexperts.Controllers.Users.UsersChangePasswordController;
import ram.ewe.englishwithexperts.Controllers.Users.UsersController;

import java.sql.SQLException;

/**
 * @author Ramadan ismaeL
 */
public class ModelControllers {
    private static ModelControllers _instance;
    private final DashboardController _dashboardController;
    private final AlertRestartAppController _alertRestartController;
    private final AlertExitAppController _alertExitController;
    private final UsersChangePasswordController _usersChangePasswordController;
    private final AlertExitPaymentRegistrationController _alertExitPaymentRegistrationController;

    private ModelControllers()
    {
        this._dashboardController = new DashboardController();
        this._alertRestartController = new AlertRestartAppController();
        this._alertExitController = new AlertExitAppController();
        this._usersChangePasswordController = new UsersChangePasswordController();
        this._alertExitPaymentRegistrationController = new AlertExitPaymentRegistrationController();
    }

    public static synchronized ModelControllers getInstance()
    {
        if(_instance == null) return _instance = new ModelControllers();
        return _instance;
    }

    public DashboardController getDashboardController()
    {
        return this._dashboardController;
    }
    public AlertRestartAppController getAlertRestartController()
    {
        return this._alertRestartController;
    }
    public AlertExitAppController getAlertExitController()
    {
        return this._alertExitController;
    }
    public UsersChangePasswordController getUsersChangePasswordController() { return this._usersChangePasswordController; }
    public AlertExitPaymentRegistrationController getAlertExitPaymentRegistrationController() { return this._alertExitPaymentRegistrationController; }
}
