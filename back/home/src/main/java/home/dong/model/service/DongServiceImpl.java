package home.dong.model.service;

import home.dong.model.dao.DongDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DongServiceImpl implements DongService {

    @Autowired
    private DongDao dongDao;

    @Override
    public List<String> getSido() {
        return dongDao.getSido();
    }

    @Override
    public List<String> getGugun(String sidoName) {
        return dongDao.getGugun(sidoName);
    }

    @Override
    public List<String> getDong(String gugunName) {
        return dongDao.getDong(gugunName);
    }
}
