# 1. UserInfo를 가져오는 법

-> UserDetailsService를 상속받아서 UserDetailsServiceImpl룰 구현하다.
```java
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AuthMapper authMapper;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        // 로그인한 유저 정보 조회
        AuthDomain.UserResponse user = authMapper.retrieveUserInfo(userId);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + userId);
        }

        // 로그인한 유저의 권한 정보 조회
        List<AuthDomain.UserRoleInfoResponse> userRoleInfo = authMapper.userRoleInfo(userId);
        String[] roles = userRoleInfo.stream()
                .map(UserRoleInfoResponse::getRoleCd)
                .toArray(String[]::new);

        return new UserDetailsImpl(userId, user.getUserPw(), user.getUserNm(), user.getLangCd(), user.getCompCd(), roles);
    }

    public UserDetailsImpl getUserInfo() {
        return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
```

# 1.2 DI를 이용한 방법

```java
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TermApi {
    private final TermService termService;
    private final UserDetailsServiceImpl userDetailsService;

    UserDetailsImpl user = userDetailsService.getUserInfo();
}
```


# 1.3 @AuthenticationPrincipal을 이용한 방법.

```java
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TermApi {
    private final TermService termService;

    @GetMapping("/term/list")
    public GcResponse getTermData(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String langCd = userDetails.getLangCd();

        TermListResponse response = termService.getTermList(langCd);

        return GcResponse.builder()
                .code(GcResult.OK.code())
                .result(GcResult.OK.result())
                .build()
                .addData("response", response);
    }
}
```