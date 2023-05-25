package home.house.model.service;

import home.house.model.SidoGugunDongDto;
import home.house.model.dao.DongDao;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SidoGugunDongServiceImpl implements SidoGugunDongService {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<SidoGugunDongDto> getSido() throws Exception {
        return sqlSession.getMapper(DongDao.class).getSido();
    }

    @Override
    public List<SidoGugunDongDto> getGugun(String sidoName) throws Exception {
        return sqlSession.getMapper(DongDao.class).getGugun(sidoName);
    }

    @Override
    public List<SidoGugunDongDto> getDong(String sidoName, String gugunName) throws Exception {
        return sqlSession.getMapper(DongDao.class).getDong(sidoName, gugunName);
    }
}
