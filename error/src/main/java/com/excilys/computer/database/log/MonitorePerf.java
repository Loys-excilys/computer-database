package com.excilys.computer.database.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class MonitorePerf implements Ordered {
  private static Logger LOGGER = LoggerFactory.getLogger(MonitorePerf.class);
  private int order;
 
  @Around("monitorePerfPointcut()")
  public Object executer(final ProceedingJoinPoint joinpoint) throws Throwable {
    Object returnValue;
    StopWatch clock = new StopWatch(getClass().getName());
    try {
      clock.start(joinpoint.toString());
      returnValue = joinpoint.proceed();
    } finally {
      clock.stop();
      LOGGER.info("temps d'execution : "+ clock.getTotalTimeMillis() + " ms\n" + clock.prettyPrint());
    }
    return returnValue;
  }
  
  @Override
  public int getOrder() {
    return order;
  }
 
  @Pointcut("execution(* com.excilys.computer.database.service.*.*(..))")
  public void monitorePerfPointcut() {
  }
 
  @Value("1")
  public void setOrder(final int order) {
    this.order = order;
  }
}