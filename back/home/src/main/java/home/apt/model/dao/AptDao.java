package home.apt.model.dao;

import home.apt.model.AptDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AptDao {
    List<AptDto> getAptList(String dong);
}
