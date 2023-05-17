package home.dong.model.service;

import java.util.List;

public interface DongService {

    public List<String> getSido();

    public List<String> getGugun(String sidoName);

    public List<String> getDong(String gugunName);
}
