package ram.ewe.englishwithexperts.Models;

import ram.ewe.englishwithexperts.Data.ConnectionDAO;
import ram.ewe.englishwithexperts.Views.AlertsFactory;
import ram.ewe.englishwithexperts.Views.ProgressFactory;
import ram.ewe.englishwithexperts.Views.ViewsFactory;

import java.sql.SQLException;

/**
 * @author Ramadan ismaeL
 */
public class Model {
    private static Model _instance;
    private final AlertsFactory _alertsFactory;
    private final ViewsFactory _viewsFactory;
    private final ProgressFactory _progressFactory;

    private Model()
    {
        this._alertsFactory = new AlertsFactory();
        this._viewsFactory = new ViewsFactory();
        this._progressFactory = new ProgressFactory();
    }

    public static synchronized Model getInstance()
    {
        if(_instance == null) return _instance = new Model();
        return _instance;
    }

    public AlertsFactory getAlertsFactory() { return this._alertsFactory; }
        public ViewsFactory getViewsFactory() { return this._viewsFactory; }
            public ProgressFactory getProgressFactory() { return this._progressFactory; }
}
