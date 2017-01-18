package apk;

import beansoft.util.FileOperate;
import com.meituan.android.walle.ChannelInfo;
import com.meituan.android.walle.ChannelReader;
import com.meituan.android.walle.ChannelWriter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import util.StringUtil;

import java.io.File;

/**
 * 瓦力打包相关操作。
 * Created by beansoft on 2017/1/18.
 */
public class WalleSignUtil {
    public static final String OUT_DIR = "输出渠道包";// 输出路径

    /**
     * 创建渠道包APK文件。
     * @param channels
     */
    public static void createSignChannelAPKFile(String inputAPK, String... channels) {
        try{
            FileOperate fileOperate = new FileOperate();
            fileOperate.newFolder(OUT_DIR);
            String apkFileName = StringUtil.getFileName(inputAPK);

            if(channels != null) {
                for(String ch : channels) {
                    if(!StringUtil.isEmpty(ch)) {
                        generateChannelApk(new File(inputAPK), new File(OUT_DIR), ch);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void generateChannelApk(final File inputFile, final File outputDir, final String channel) {
        final String name = FilenameUtils.getBaseName(inputFile.getName());
        final String extension = FilenameUtils.getExtension(inputFile.getName());
        final String newName = name + "_" + channel + "." + extension;
        final File channelApk = new File(outputDir, newName);
        try {
            FileUtils.copyFile(inputFile, channelApk);
            ChannelWriter.put(channelApk, channel, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readChannel(final File file) {
        final ChannelInfo channelInfo = ChannelReader.get(file);
        if (channelInfo == null) {
            return "<无>";
        }
        return channelInfo.getChannel();
    }

}