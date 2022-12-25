package module;

import dao.NamotDao;
import dao.SorumDao;
import dao.MermoDao;

public class DaoModule {
    private final NamotDao namotDao;
    private final MermoDao mermoDao;
    private final SorumDao sorumDao;

    public DaoModule() {
        this.namotDao = new NamotDao();
        this.mermoDao = new MermoDao();
        this.sorumDao = new SorumDao();
    }

    public NamotDao getNamotDao() {
        return namotDao;
    }

    public MermoDao getMermoDao() {
        return mermoDao;
    }

    public SorumDao getSorumDao() {
        return sorumDao;
    }
}
