package home.house.controller;

import home.house.model.SidoGugunDongDto;
import home.house.model.service.SidoGugunDongService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/map")
public class HouseController {

    @Autowired
    private SidoGugunDongService sidoGugunDongService;

    @ApiOperation(value = "시도 정보", notes = "전국의 시도 정보를 반환", response = List.class)
    @GetMapping("/sido")
    public ResponseEntity<List<SidoGugunDongDto>> getSido() throws Exception {
        return new ResponseEntity<>(sidoGugunDongService.getSido(), HttpStatus.OK);
    }

    @ApiOperation(value = "구군 정보", notes = "전국의 구군 정보를 반환", response = List.class)
    @GetMapping("/gugun")
    public ResponseEntity<List<SidoGugunDongDto>> getGugun(@ApiParam(value = "구군 정보를 얻기 위한 시도 정보", required = true)String sido) throws Exception {
        return new ResponseEntity<>(sidoGugunDongService.getGugun(sido), HttpStatus.OK);
    }

    @ApiOperation(value = "동 정보", notes = "전국의 동 정보를 반환", response = List.class)
    @GetMapping("/dong")
    public ResponseEntity<List<SidoGugunDongDto>> getDong(@ApiParam(value = "동 정보를 얻기 위한 시도 정보", required = true)String sido, @ApiParam(value = "동 정보를 얻기 위한 구군 정보", required = true)String gugun) throws Exception {
        return new ResponseEntity<>(sidoGugunDongService.getDong(sido, gugun), HttpStatus.OK);
    }
}
