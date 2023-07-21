package com.Toby.Config;

import com.Toby.Config.autoconfig.DispatcherServletConfig;
import com.Toby.Config.autoconfig.MyAutoConfigImportSelector;
import com.Toby.Config.autoconfig.TomcatWebServerConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(MyAutoConfigImportSelector.class)
public @interface EnableMyAutoConfiguration {
}
