package home.house.model.dao;

import home.house.model.SidoGugunDongDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface DongDao {
    public List<SidoGugunDongDto> getSido() throws SQLException;

    public List<SidoGugunDongDto> getGugun(String sidoName) throws SQLException;

    public List<SidoGugunDongDto> getDong(String sidoName, String gugunName) throws SQLException;
}
