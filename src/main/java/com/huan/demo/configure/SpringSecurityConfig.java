//package com.huan.demo.configure;
//
//import com.huan.demo.auth.JwtAuthenticationTokenFilter;
//import com.huan.demo.auth.JwtUserDetailsService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.BeanIds;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
///**
// * <p>
// *     @EnableGlobalMethodSecurity(prePostEnabled = true) :  开启 SpEL（Spring Expression Language）表达式
// *     1. 权限表达式可以在 SpringSecurityConfig 中统一配置
// *     2. 可以使用注解在方法上使用
// * </p>
// *
// *
// *
// * @author 牟欢
// * @Classname SpringSecurityConfig
// * @Description TODO
// * @Date 2020-12-15 15:35
// */
//@Slf4j
//@EnableWebSecurity
//@RequiredArgsConstructor
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final JwtUserDetailsService userDetailsService;
//    private final JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
//
//    /**
//     * 将 BCryptPasswordEncoder 加密对象注入 ICO 容器
//     * @return
//     */
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    /**
//     * 配置 Spring Security 加密方法
//     *
//     * @param auth
//     * @throws Exception
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        log.info("执行顺序 0， 程序启动时执行");
//        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
//    }
//
//    /**
//     * <p>
//     *     常用 SpEL 表达式
//     *     hasRole([role])                       拥有指定角色时返回 true
//     *     hasAnyRole([role1],[role2])           拥有任意一个角色时返回 true
//     *     hasAuthority([authority])             拥有某资源的访问权限时返回 true
//     *     hasAnyAuthority([auth1],[auth2])      拥有部分资源的访问权限时返回 true
//     *     permitAll                             永远返回 true
//     *     denyAll                               永远返回 false
//     * </p>
//     *
//     * 配置 Spring Security 过滤规则
//     * @param http
//     * @throws Exception
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and().csrf().disable()
//                // 添加自定义 JWT 过滤器
//                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
//                // 配置全局过滤规则
//                .authorizeRequests()
//
//                // antMatchers后面添加路径  .access后面添加路径的过滤规则
//                .antMatchers("/tasks/**").access("hasRole('ADMIN')")
//                // 自定义认证逻辑和参数
//
//                // 其他都放行了
//                .anyRequest().permitAll()
//                .and()
//                // 不需要session
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    }
//
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
//        return source;
//    }
//
//    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//}
