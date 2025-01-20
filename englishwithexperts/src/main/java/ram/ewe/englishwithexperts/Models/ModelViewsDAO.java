package ram.ewe.englishwithexperts.Models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import ram.ewe.englishwithexperts.Enums.DashboardEnum;
import ram.ewe.englishwithexperts.Views.ViewsDAOFactory;

public class ModelViewsDAO {
    private static ModelViewsDAO _instance;
    public final ViewsDAOFactory _viewsDAOFactory;
    public final ObjectProperty<DashboardEnum> _dashboardEnum;

    public ModelViewsDAO()
    {
        this._viewsDAOFactory = new ViewsDAOFactory();
        this._dashboardEnum = new SimpleObjectProperty<>();
    }

    public static synchronized ModelViewsDAO getInstance()
    {
        if(_instance == null) return _instance = new ModelViewsDAO();
        return _instance;
    }

    public ViewsDAOFactory getViewsDAOFactory()
    {
        return this._viewsDAOFactory;
    }

    public ObjectProperty<DashboardEnum> getDashboardEnum()
    {
        return this._dashboardEnum;
    }
}
