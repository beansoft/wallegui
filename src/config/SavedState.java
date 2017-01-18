package config;

import com.xpec.properties.annotations.Properties;
import com.xpec.properties.container.Container;

/**
 * 上次输入的信息.
 * Created by beansoft on 16/9/21.
 */
@Properties("conf/_savedstate.properties")
public class SavedState {
    @Properties
    public String lastFile;// 最后打开的文件
    @Properties
    public String channelList;// 渠道列表


    private SavedState() {}

    public static SavedState getInstance() {
        SavedState u = new SavedState();

        try {
            Container.injectProperties(u);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public String toString() {
        return "SavedState{" +
                "lastFile='" + lastFile + '\'' +
                ", channelList='" + channelList + '\'' +
                '}';
    }

    public void save() {
        try {
            System.out.println(" +++++++ Save: " + this);
            Container.storeProperties(this, "Last saved app state");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        getInstance();
    }

}