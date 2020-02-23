package com.vasilyevii;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class IoC {

  static TestLoggingInterface createMyClass() {

    InvocationHandler handler = new DemoInvocationHandler(new TestLogging());
    return (TestLoggingInterface) Proxy.newProxyInstance(IoC.class.getClassLoader(),
        new Class<?>[]{TestLoggingInterface.class}, handler);

  }

  static class DemoInvocationHandler implements InvocationHandler {

    private final TestLoggingInterface myClass;
    private final List<Method> loggingMethods = new ArrayList<>();

    DemoInvocationHandler(TestLoggingInterface myClass) {

      this.myClass = myClass;

      for (Method method : myClass.getClass().getDeclaredMethods()) {
        for (Annotation annotation : method.getDeclaredAnnotations()) {
          if (annotation.annotationType() == Log.class) {
            loggingMethods.add(method);
          }
        }
      }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

      for (Method loggingMethod : loggingMethods) {
        if (loggingMethod.getName().equals(method.getName())
                && Arrays.equals(loggingMethod.getParameterTypes(), method.getParameterTypes())) {
          System.out.println("executed method: " + method.getName() + ", param: " + Arrays.toString(args));
          break;
        }
      }
      return method.invoke(myClass, args);
  }

    @Override
    public String toString() {
      return "DemoInvocationHandler{" +
                 "myClass=" + myClass +
                 '}';
    }
  }

}
