package com.keke.spring;

import java.beans.Introspector;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author k 2023/1/9 15:24
 */
public class KApplicationContext {

    private Class configClass;

    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();

    private ArrayList<BeanPostProcessor> beanPostProcessorsList = new ArrayList<>();

    public KApplicationContext(Class configClass) {
        this.configClass = configClass;
        // 扫描路径

        if (configClass.isAnnotationPresent(ComponentScan.class)) {
            ComponentScan annotation = (ComponentScan) configClass.getAnnotation(ComponentScan.class);
            String path = annotation.value(); // 扫描路径
            path = path.replace(".", "/");

            ClassLoader classLoader = KApplicationContext.class.getClassLoader();
            URL resource = classLoader.getResource(path);

            File file = new File(resource.getFile());
//            System.out.println(file);
            // 扫描
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File f : files) {
                    String filePath = f.getAbsolutePath();
                    if (filePath.endsWith(".class")) {

                        String className = filePath.substring(filePath.indexOf("com"), filePath.indexOf(".class"));
                        className = className.replace("\\", ".");
//                        System.out.println(className);
                        Class<?> aClass = null;
                        try {
                            aClass = classLoader.loadClass(className);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        if (aClass.isAnnotationPresent(Component.class)) {

                            //
                            if (BeanPostProcessor.class.isAssignableFrom(aClass)) {
                                BeanPostProcessor instance = null;
                                try {
                                    instance = (BeanPostProcessor) aClass.newInstance();
                                } catch (InstantiationException e) {
                                    e.printStackTrace();
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                                beanPostProcessorsList.add(instance);
                            }


                            // BeanDefinition
                            Component annotation2 = aClass.getAnnotation(Component.class);
                            String beanName = annotation2.value();


                            if (beanName.equals("")) {
                                beanName = Introspector.decapitalize(aClass.getSimpleName());
                            }

                            BeanDefinition beanDefinition = new BeanDefinition();
                            beanDefinition.setType(aClass);

                            if (aClass.isAnnotationPresent(Scope.class)) {
                                Scope annotation1 = aClass.getAnnotation(Scope.class);
                                beanDefinition.setScope(annotation1.value());
                            } else {
                                beanDefinition.setScope("singleton");
                            }

                            beanDefinitionMap.put(beanName, beanDefinition);
                        }
                    }


                }
            }
        }
        // 实例化单例 bean
        for (String s : beanDefinitionMap.keySet()) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(s);
            if (beanDefinition.getScope().equals("singleton")) {
                Object bean = createBean(s, beanDefinition);
                singletonObjects.put(s, bean);
            }
        }
    }

    private Object createBean(String beanName, BeanDefinition beanDefinition) {
        Class clazz = beanDefinition.getType();
        try {

            Object instance = clazz.getConstructor().newInstance();

            // autowire
            for (Field f : clazz.getDeclaredFields()) {
                if (f.isAnnotationPresent(Autowired.class)) {
                    f.setAccessible(true);
                    f.set(instance, getBean(f.getName()));
                }
            }

            // Aware 回调
            if (instance instanceof BeanNameAware) {
                ((BeanNameAware) instance).setBeanName(beanName);
            }

            //  bean 初始化前 前置方法
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorsList) {
                instance = beanPostProcessor.postProcessorBeforeInitialization(beanName, instance);
            }


            // 初始化对象的属性
            if (instance instanceof InitializingBean) {
                ((InitializingBean) instance).afterPropertitySet();
            }

            // BeanPostProcessor 初始化后 AOP
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorsList) {
                instance = beanPostProcessor.postProcessorAfterInitialization(beanName, instance);
            }

            return instance;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Object getBean(String beanName) {

        // bean
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new NullPointerException();
        } else {
            String scope = beanDefinition.getScope();
            if (scope.equals("singleton")) {
                Object o = singletonObjects.get(beanName);
                if (o == null) {
                    Object bean = createBean(beanName, beanDefinition);
                    singletonObjects.put(beanName, bean);
                }
                return o;
            } else {
                return createBean(beanName, beanDefinition);
            }
        }
//        return null;
    }
}
