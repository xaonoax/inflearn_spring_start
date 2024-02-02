package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect  // 해당 어노테이션을 적용시켜야 AOP를 사용할 수 있다.
@Component  // 스프링 빈을 등록하기 위해 해당 어노테이션을 사용해도 되지만 따로 스프링 빈을 등록하는 것을 선호한다.
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))")  // 패키지명.하위 패키지명.클래스명 등등.. AOP를 적용항 타겟을 지정한다.
    public Object execut(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();

        System.out.println("START : " + joinPoint.toString());

        try {
            return joinPoint.proceed(); // proceed() : 다음 메서드로 진행
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;

            System.out.println("END : " + joinPoint.toString() + " " + timeMs + "ms");

        }
    }
}
