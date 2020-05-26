package com.zhengxin.tank;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.util.Properties;

/**
 * @Auther: zhengxin
 * @Date: 2020/5/24 - 05 - 24 - 17:47
 * @Description: 配置文件工具类
 * @version: 1.0
 */
public class PropertyMgr {
    private static volatile PropertyMgr propertyMgr;
    private static volatile Properties props;
    private PropertyMgr(){}
    public static PropertyMgr getInstance(){
        if (propertyMgr == null) {
            synchronized (PropertyMgr.class) {
                if (propertyMgr == null) {
                    propertyMgr = new PropertyMgr();
                    props = new Properties();
                    try {
                        props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
        return propertyMgr;
    }
    public  Object getProp(String key) {
            return props.get(key);
    }
    public int getIntProp(String key) {
        return Integer.valueOf(getStringProp(key));
    }
    public String getStringProp (String key) {
        return getProp(key).toString();
    }
}
