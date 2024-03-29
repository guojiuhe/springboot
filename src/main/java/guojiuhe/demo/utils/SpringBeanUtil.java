package guojiuhe.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringBeanUtil implements ApplicationContextAware {

    private static Logger logger = LoggerFactory.getLogger(ApplicationContextAware.class);
    
    protected static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringBeanUtil.applicationContext == null) {
        	SpringBeanUtil.applicationContext = applicationContext;
        }
        logger.info("\r\n----------加载applicationContext成功-----------------");
    }
    
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }
    
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
        return (T) getApplicationContext().getBean(name);
	}
	
    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }
}