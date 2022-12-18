package module;

import dao.NamotDao;
import dao.SorumDao;
import dao.MerekDao;

public class DaoModule {
    private final NamotDao jenisHewanDao;
    private final MerekDao habitatDao;
    private final SorumDao hewanDao;

    public DaoModule() {
        this.jenisHewanDao = new NamotDao();
        this.habitatDao = new MerekDao();
        this.hewanDao = new SorumDao();
    }

    public NamotDao getJenisHewanDao() {
        return jenisHewanDao;
    }

    public MerekDao getHabitatDao() {
        return habitatDao;
    }

    public SorumDao getHewanDao() {
        return hewanDao;
    }
}
