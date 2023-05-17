package home.apt.model.service;

import home.apt.model.AptDto;
import home.apt.model.dao.AptDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AptServiceImpl implements AptService {

    @Autowired
    private AptDao aptDao;

    @Override
    public List<AptDto> getAptList(String dong) {
        return aptDao.getAptList(dong);
    }
}
