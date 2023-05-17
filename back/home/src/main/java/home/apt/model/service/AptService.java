package home.apt.model.service;

import home.apt.model.AptDto;

import java.util.List;

public interface AptService {
    List<AptDto> getAptList(String dong);
}
