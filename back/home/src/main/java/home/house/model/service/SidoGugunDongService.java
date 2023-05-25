package home.house.model.service;

import home.house.model.SidoGugunDongDto;

import java.util.List;

public interface SidoGugunDongService {

    public List<SidoGugunDongDto> getSido() throws Exception;

    public List<SidoGugunDongDto> getGugun(String sidoName) throws Exception;

    public List<SidoGugunDongDto> getDong(String sidoName, String gugunName) throws Exception;
}
