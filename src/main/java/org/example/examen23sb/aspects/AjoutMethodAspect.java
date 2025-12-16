package org.example.examen23sb.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AjoutMethodAspect {

    @Before("execution(* org.example.examen23sb.services.impl.*ServiceImpl.ajouter*(..))")
    public void logMethodStart(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        log.info("Début méthode « {} »", methodName);
        System.out.println("Début méthode « " + methodName + " »");
    }
}
