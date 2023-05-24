package home.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import home.user.model.UserDTO;
import home.user.model.service.UserService;
import home.user.util.JwtService;
import home.user.util.TokenSet;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/users")
@Api("사용자 컨트롤러  API V1")
public class UserController {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private TokenSet tokens;

	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) UserDTO user) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			UserDTO loginUser = userService.login(user);
			if (loginUser != null) {
				String accessToken = jwtService.createAccessToken("userid", loginUser.id);// key, data
				String refreshToken = jwtService.createRefreshToken("userid", loginUser.id);// key, data
				
				// save refresh Token
				tokens.add(refreshToken);
				
				logger.debug("로그인 accessToken 정보 : {}", accessToken);
				logger.debug("로그인 refreshToken 정보 : {}", refreshToken);
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userid") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userid,
			HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		if (jwtService.checkToken(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			String tokenUserid = (String) jwtService.get("access-token").get("userid");
			
			if (tokenUserid.equals(userid)) {
				try {
//					로그인 사용자 정보.
					UserDTO memberDto = userService.userInfo(userid);
					resultMap.put("userInfo", memberDto);
					resultMap.put("message", SUCCESS);
					status = HttpStatus.ACCEPTED;
				} catch (Exception e) {
					logger.error("정보조회 실패 : {}", e);
					resultMap.put("message", e.getMessage());
					status = HttpStatus.INTERNAL_SERVER_ERROR;
				}
			} else {
				logger.error("토큰과 다른 아이디 조회 : {}", tokenUserid);
				resultMap.put("message", "try diffrent id");
				status = HttpStatus.BAD_REQUEST;
			}
			

		} else {
			logger.error("사용 불가능 토큰!!!");
			logger.error("" + request.getHeader("access-token"));
			resultMap.put("message", FAIL);
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "로그아웃", notes = "회원 정보를 담은 Token을 제거한다.", response = Map.class)
	@GetMapping("/logout")
	public ResponseEntity<?> removeToken(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			String token = request.getHeader("access-token");
			tokens.remove(token);
			
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	@ApiOperation(value = "Access Token 재발급", notes = "만료된 access token을 재발급받는다.", response = Map.class)
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("access-token");
		logger.debug("token : {}", token);
		if (jwtService.checkToken(token)) {
			if (tokens.contains(token)) {
				String tokenUserid = (String) jwtService.get("access-token").get("userid");
				String accessToken = jwtService.createAccessToken("userid", tokenUserid);
				logger.debug("token : {}", accessToken);
				logger.debug("정상적으로 액세스토큰 재발급!!!");
				resultMap.put("access-token", accessToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			}
		} else {
			logger.debug("리프레쉬토큰도 사용불!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	
	@ApiOperation(value = "회원가입", response = Map.class)
	@PostMapping("")
	public ResponseEntity<?> registerUser(@RequestBody @ApiParam(value = "회원가입 데이터", required = true) UserDTO user) throws Exception{
		Map<String, Object> result = new HashMap<>();
		HttpStatus status = null;
		
		int count = userService.addUesr(user);
		if (count > 0) {
			result.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} else {
			result.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		
		return new ResponseEntity<Map<String, Object>>(result, status);
	}
	
	@ApiOperation(value = "회원탈퇴", response = Map.class)
	@DeleteMapping("/{userid}")
	public ResponseEntity<?> deleteUser(@PathVariable("userid") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userid) throws Exception{
		Map<String, Object> result = new HashMap<>();
		HttpStatus status = null;
		
		UserDTO user = new UserDTO();
		user.id = userid;
		
		try {
			userService.deleteUser(user);
			result.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("회원탈퇴 실패 : {}", e);
			result.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		
		return new ResponseEntity<Map<String, Object>>(result, status);
	}

	@ApiOperation(value = "회원정보수정", response = Map.class)
	@PutMapping("/{userid}")
	public ResponseEntity<?> updateUser(@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) UserDTO user) throws Exception{
		Map<String, Object> result = new HashMap<>();
		HttpStatus status = null;
		
		int cnt = userService.updateUser(user);
		
		if (cnt > 0) {
			result.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} else {
			logger.error("회원정보 수정 실패 ");
			result.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(result, status);
	}
}
