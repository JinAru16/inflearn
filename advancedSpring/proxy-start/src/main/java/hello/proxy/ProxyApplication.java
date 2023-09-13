package hello.proxy;

import hello.proxy.config.AppV1Config;
import hello.proxy.config.AppV2Config;
import hello.proxy.config.v1_proxy.ConcreteProxyConfig;
import hello.proxy.config.v1_proxy.InterfaceProxyConfig;
import hello.proxy.trace.logtrace.LogTrace;
import hello.proxy.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

//@Import(AppV1Config.class)
//@Import({AppV1Config.class, AppV2Config.class})
//@Import(InterfaceProxyConfig.class)
@Import(ConcreteProxyConfig.class)
@SpringBootApplication(scanBasePackages = "hello.proxy.app") //주의
/**
 * ScanBasePackages를 별로로 적용해주면 해당 패키지와 그 패키지 하위만 컴포넌트 스캔을 하게 됨.
 * 여기선 app패키지와 그 하위만 스캔되겠네.
 * 원래 지정 안해져문 hello.proxy 패키지와 그 하위를 전체 스캔하게 됨.
 * 근데 이렇게 지정한 이유가 뭐냐? config가 버젼별로 달라지는데 그때마다 모든 config 버젼이 스캔이 되어버리는걸 방지하기 위해서
 * 스캔 대상을 좁혀주고, 필요한 config만 따로 @Import로 등록해주기 위해서 이렇게 진행하는 것이다.
 *
 */
public class ProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}
	@Bean
	public LogTrace logTrace(){
		return new ThreadLocalLogTrace();
	}

}
