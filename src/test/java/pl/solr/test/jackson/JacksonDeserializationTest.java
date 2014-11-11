package pl.solr.test.jackson;

import javassist.ClassPool;
import javassist.CtClass;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;


public class JacksonDeserializationTest {

    @Test
    public void test() throws Exception {
        final String src = "{}";
        ObjectMapper mapper = new ObjectMapper();

        final ClassPool pool = ClassPool.getDefault();
        final CtClass clazz = pool.makeClass(User.class.getName() + "#proxy" + System.nanoTime());
        clazz.addInterface(pool.get(User.class.getName()));
        clazz.setSuperclass(pool.get(UserImpl.class.getName()));

        final Class<?> cl = clazz.toClass();
        final UserImpl userImpl = (UserImpl) cl.newInstance(); //it works
        final User user = (User) cl.newInstance();

        if (cl.isAssignableFrom(UserImpl.class)) {
            System.err.println("Is assignable!");
        } else {
            System.err.println("NOT assignable!");
        }
        final User u = (User) mapper.readValue(src, cl);

//        com.fasterxml.jackson.databind.JsonMappingException: Failed to narrow type [simple type, class pl.solr.test.jackson.User#proxy1415664016178743000] with concrete-type annotation (value pl.solr.test.jackson.UserImpl), method 'pl.solr.test.jackson.User#proxy1415664016178743000': Class pl.solr.test.jackson.UserImpl is not assignable to pl.solr.test.jackson.User#proxy1415664016178743000
//        at com.fasterxml.jackson.databind.deser.DeserializerCache.modifyTypeByAnnotation(DeserializerCache.java:475)
//        at com.fasterxml.jackson.databind.deser.DeserializerCache._createDeserializer(DeserializerCache.java:332)
//        at com.fasterxml.jackson.databind.deser.DeserializerCache._createAndCache2(DeserializerCache.java:261)
//        at com.fasterxml.jackson.databind.deser.DeserializerCache._createAndCacheValueDeserializer(DeserializerCache.java:241)
//        at com.fasterxml.jackson.databind.deser.DeserializerCache.findValueDeserializer(DeserializerCache.java:142)
//        at com.fasterxml.jackson.databind.DeserializationContext.findRootValueDeserializer(DeserializationContext.java:381)
//        at com.fasterxml.jackson.databind.ObjectMapper._findRootDeserializer(ObjectMapper.java:3154)
//        at com.fasterxml.jackson.databind.ObjectMapper._readMapAndClose(ObjectMapper.java:3047)
//        at com.fasterxml.jackson.databind.ObjectMapper.readValue(ObjectMapper.java:2146)
//        at pl.solr.test.jackson.JacksonDeserialization.test(JacksonDeserialization.java:26)
//        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
//        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
//        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
//        at java.lang.reflect.Method.invoke(Method.java:483)
//        at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:47)
//        at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
//        at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:44)
//        at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
//        at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:271)
//        at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:70)
//        at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:50)
//        at org.junit.runners.ParentRunner$3.run(ParentRunner.java:238)
//        at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:63)
//        at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:236)
//        at org.junit.runners.ParentRunner.access$000(ParentRunner.java:53)
//        at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:229)
//        at org.junit.runners.ParentRunner.run(ParentRunner.java:309)
//        at org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:50)
//        at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)
//        at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:459)
//        at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:675)
//        at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:382)
//        at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:192)
//    Caused by: java.lang.IllegalArgumentException: Class pl.solr.test.jackson.UserImpl is not assignable to pl.solr.test.jackson.User#proxy1415664016178743000
//        at com.fasterxml.jackson.databind.JavaType._assertSubclass(JavaType.java:408)
//        at com.fasterxml.jackson.databind.JavaType.narrowBy(JavaType.java:148)
//        at com.fasterxml.jackson.databind.deser.DeserializerCache.modifyTypeByAnnotation(DeserializerCache.java:473)
//        ... 32 more


    }

}
