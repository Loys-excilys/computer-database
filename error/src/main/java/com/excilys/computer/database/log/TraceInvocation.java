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

@Aspect
@Component
public class TraceInvocation implements Ordered{
	private transient Logger LOGGER = LoggerFactory.getLogger(TraceInvocation.class);
	private int order;
	@Around("traceInvocationPointcut()")
	public Object afficherTrace(ProceedingJoinPoint joinpoint) throws Throwable {

		String nomMethode = joinpoint.getTarget().getClass().getSimpleName() + "." + joinpoint.getSignature().getName();
		final Object[] args = joinpoint.getArgs();
		final StringBuffer sb = new StringBuffer();

		sb.append(joinpoint.getSignature().toString());
		sb.append(" avec les parametres : (");
		for (int i = 0; i < args.length; i++) {
			sb.append(args[i]);
			if (i < args.length - 1) {
				sb.append(", ");
			}
		}
		sb.append(")");
		LOGGER.info("Debut methode : " + sb);
		Object obj = null;
		try {
			obj = joinpoint.proceed();
		} finally {
			LOGGER.info("Fin methode :  " + nomMethode + " retour=" + obj);
		}
		return obj;
	}
	  @Override
	  public int getOrder() {
	    return order;
	  }
	  
	  @Value("2")
	  public void setOrder(final int order) {
	    this.order = order;
	  }
	  @Pointcut("execution(* com.excilys.computer.database.service.*.*(..))")
	  public void traceInvocationPointcut() {
	  }
}