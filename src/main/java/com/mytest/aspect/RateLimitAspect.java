package com.mytest.aspect;

import com.alibaba.fastjson.JSONObject;
import com.mytest.annotation.RateLimit;
import com.mytest.model.vos.SchoolVO;
import com.mytest.service.ratelimit.PricingPlanService;
import io.github.bucket4j.*;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.Duration;

@Aspect
@Component
public class RateLimitAspect {
    private static final Logger LOG = LoggerFactory.getLogger(RateLimitAspect.class);

    @Pointcut("@annotation(com.mytest.annotation.RateLimit)")
    public void addAdvice(){}

    @Autowired
    private PricingPlanService pricingPlanService;



    @Around("addAdvice()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        HttpServletRequest requests = (HttpServletRequest) args[0];
        LOG.info("============限流开始  around============");
        LOG.info("URL: " + requests.getRequestURL().toString());
        MethodSignature joinPointObject = (MethodSignature) joinPoint.getSignature();
        Method method = joinPointObject.getMethod();
        boolean flag = method.isAnnotationPresent(RateLimit.class);
        if (flag) {
            RateLimit rateLimit = method.getAnnotation(RateLimit.class);
            LOG.info("============capacity============" + rateLimit.capacity());
            LOG.info("============time============" + rateLimit.time());
            String apiKey = requests.getHeader("X-api-key");


            Bucket bucket = pricingPlanService.resolveBucket(apiKey);
            ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);
            if (probe.isConsumed()) {
                /*return ResponseEntity.ok()
                        .header("X-Rate-Limit-Remaining", Long.toString(probe.getRemainingTokens()))
                        .body(new SchoolVO().setSchoolName("jianpianxiaoxue"));*/
                LOG.info("============未溢出============" + JSONObject.toJSONString(bucket));

            }

            else{
                long waitForRefill = probe.getNanosToWaitForRefill() / 1_000_000_000;
                ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                        .header("X-Rate-Limit-Retry-After-Seconds", String.valueOf(waitForRefill))
                        .build();

                throw new RuntimeException("too many requests");
            }



            //贪婪模式
            //
//            Refill refill = Refill.greedy(rateLimit.capacity(), Duration.ofSeconds(1));
//            Bandwidth limit = Bandwidth.classic(rateLimit.capacity(), refill);
//            Bucket bucket = Bucket.builder()
//                    .addLimit(limit)
//                    .build();

        } else {
            // 如果方法上没有注解，则搜索类上是否有注解
/*            DataSource classDataSource = AnnotationUtils.findAnnotation(joinPointObject.getMethod().getDeclaringClass(), DataSource.class);
            if (classDataSource != null) {
                setDataSource(classDataSource,method);
            } else {
                DynamicDataSourceContextHolder.clearDataSourceKey();
                logger.info("没有注解使用默认的");
            }*/
        }






        Object result = joinPoint.proceed();
        LOG.info("============打印日志结束around============");
//        LOG.info("before....");
        return result;
    }




}