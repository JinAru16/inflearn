# 1. 기본적인 쿠키 생성법
```java
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletResponse response){
        if(bindingResult.hasErrors()){
        return "/login/loginForm";
        }
        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        if(loginMember == null){
        bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
        return "login/loginForm";
        }
        //로그인 성공 처리
        // 쿠키에 시간 정보를 주지 않으면 세션 쿠키(브라우저 끌 때 까지 쿠키가 지속됨.
        Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
        response.addCookie(idCookie);


        return "redirect:/";
        }
```
// 여기선 MVC방식이라 HttpServletResponse로 내려줌

## 1-1 쿠키 만료방₩
```java
    private static void expireCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("memberId", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
```
* 쿠키 시간을 0으로 만들면 된다.

# 2. 보안문제

# 3. 해결방법

4

