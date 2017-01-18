package beansoft.util;

import java.io.*;

/**
 * FileOperate.java
 * 文件的各种操作
 * @author 杨彩 http://blog.sina.com.cn/m/yangcai
 * 文件操作 1.0
 */

public class FileOperate
{

    public FileOperate()
    {
    }
    /**
     * 新建目录
     */
    public void newFolder(String folderPath)
    {
        try
        {
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            if(!myFilePath.exists())
            {
                myFilePath.mkdir();
            }
            System.out.println("新建目录操作 成功执行");
        }
        catch(Exception e)
        {
            System.out.println("新建目录操作出错");
            e.printStackTrace();
        }
    }
    /**
     * 新建文件
     */
    public void newFile(String filePathAndName, String fileContent)
    {
        try
        {
            String filePath = filePathAndName;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            if (!myFilePath.exists())
            {
                myFilePath.createNewFile();
            }
            FileWriter resultFile = new FileWriter(myFilePath);
            PrintWriter myFile = new PrintWriter(resultFile);
            String strContent = fileContent;
            myFile.println(strContent);
            resultFile.close();
            System.out.println("新建文件操作 成功执行");
        }
        catch (Exception e)
        {
            System.out.println("新建目录操作出错");
            e.printStackTrace();
        }
    }
    /**
     * 删除文件
     */
    public void delFile(String filePathAndName)
    {
        try
        {
            String filePath = filePathAndName;
            filePath = filePath.toString();
            File myDelFile = new File(filePath);
            myDelFile.delete();
            System.out.println("删除文件操作 成功执行");
        }
        catch (Exception e)
        {
            System.out.println("删除文件操作出错");
            e.printStackTrace();
        }
    }
    /**
     * 删除文件夹
     */
    public void delFolder(String folderPath)
    {
        try
        {
            delAllFile(folderPath); //删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            if(myFilePath.delete()) { //删除空文件夹
                System.out.println("删除文件夹" + folderPath + "操作 成功执行");
            } else {
                System.out.println("删除文件夹" + folderPath + "操作 执行失败");
            }
        }
        catch (Exception e)
        {
            System.out.println("删除文件夹操作出错");
            e.printStackTrace();
        }
    }
    /**
     * 删除文件夹里面的所有文件
     * @param path String 文件夹路径 如 c:/fqf
     */
    public void delAllFile(String path)
    {
        File file = new File(path);
        if(!file.exists())
        {
            return;
        }
        if(!file.isDirectory())
        {
            return;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++)
        {
            if(path.endsWith(File.separator))
            {
                temp = new File(path + tempList[i]);
            }
            else
            {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile())
            {
                temp.delete();
            }
            if (temp.isDirectory())
            {
                //delAllFile(path+"/"+ tempList[i]);//先删除文件夹里面的文件
                delFolder(path+ File.separatorChar + tempList[i]);//再删除空文件夹
            }
        }
        System.out.println("删除文件操作 成功执行");
    }
    /**
     * 复制单个文件
     * @param oldPath String 原文件路径 如：c:/fqf.txt
     * @param newPath String 复制后路径 如：f:/fqf.txt
     */
    public void copyFile(String oldPath, String newPath)
    {
        try
        {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists())
            {
                //文件存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                int length;
                while ( (byteread = inStream.read(buffer)) != -1)
                {
                    bytesum += byteread; //字节数 文件大小
//                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
            System.out.println("复制单个文件 成功执行");
        }
        catch (Exception e)
        {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();
        }
    }
    /**
     * 复制整个文件夹内容
     * @param oldPath String 原文件路径 如：c:/fqf
     * @param newPath String 复制后路径 如：f:/fqf/ff
     */
    public void copyFolder(String oldPath, String newPath)
    {
        try
        {
            (new File(newPath)).mkdirs(); //如果文件夹不存在 则建立新文件夹
            File a=new File(oldPath);
            String[] file=a.list();
            File temp=null;
            for (int i = 0; i < file.length; i++)
            {
                if(oldPath.endsWith(File.separator))
                {
                    temp=new File(oldPath+file[i]);
                }
                else
                {
                    temp=new File(oldPath+File.separator+file[i]);
                }
                if(temp.isFile())
                {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(newPath + "/" +
                            (temp.getName()).toString());
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ( (len = input.read(b)) != -1)
                    {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                if(temp.isDirectory())
                {
                    //如果是子文件夹
                    copyFolder(oldPath+"/"+file[i],newPath+"/"+file[i]);
                }
            }
            System.out.println("复制文件夹操作 成功执行");
        }
        catch (Exception e)
        {
            System.out.println("复制整个文件夹内容操作出错");
            e.printStackTrace();
        }
    }
    /**
     * 移动文件到指定目录
     * @param oldPath String 如：c:/fqf.txt
     * @param newPath String 如：d:/fqf.txt
     */
    public void moveFile(String oldPath, String newPath)
    {
        copyFile(oldPath, newPath);
        delFile(oldPath);
    }
    /**
     * 移动文件到指定目录
     * @param oldPath String 如：c:/fqf.txt
     * @param newPath String 如：d:/fqf.txt
     */
    public void moveFolder(String oldPath, String newPath)
    {
        copyFolder(oldPath, newPath);
        delFolder(oldPath);
    }
    public static void main(String args[])
    {
        String aa,bb;
        boolean exitnow=false;
        System.out.println("使用此功能请按[1]  功能一：新建目录");
        System.out.println("使用此功能请按[2]  功能二：新建文件");
        System.out.println("使用此功能请按[3]  功能三：删除文件");
        System.out.println("使用此功能请按[4]  功能四：删除文件夹");
        System.out.println("使用此功能请按[5]  功能五：删除文件夹里面的所有文件");
        System.out.println("使用此功能请按[6]  功能六：复制文件");
        System.out.println("使用此功能请按[7]  功能七：复制文件夹的所有内容");
        System.out.println("使用此功能请按[8]  功能八：移动文件到指定目录");
        System.out.println("使用此功能请按[9]  功能九：移动文件夹到指定目录");
        System.out.println("使用此功能请按[10] 退出程序");
        while(!exitnow)
        {
            FileOperate fo=new FileOperate();
            try
            {
                BufferedReader Bin=new BufferedReader(new InputStreamReader(System.in));
                String a=Bin.readLine();
                int b=Integer.parseInt(a);
                switch(b)
                {
                    case 1:System.out.println("你选择了功能一  请输入目录名");
                        aa=Bin.readLine();
                        fo.newFolder(aa);
                        break;
                    case 2:System.out.println("你选择了功能二  请输入文件名");
                        aa=Bin.readLine();
                        System.out.println("请输入在"+aa+"中的内容");
                        bb=Bin.readLine();
                        fo.newFile(aa,bb);
                        break;
                    case 3:System.out.println("你选择了功能三  请输入文件名");
                        aa=Bin.readLine();
                        fo.delFile(aa);
                        break;
                    case 4:System.out.println("你选择了功能四  请输入文件名");
                        aa=Bin.readLine();
                        fo.delFolder(aa);
                        break;
                    case 5:System.out.println("你选择了功能五  请输入文件名");
                        aa=Bin.readLine();
                        fo.delAllFile(aa);
                        break;
                    case 6:System.out.println("你选择了功能六  请输入文件名");
                        aa=Bin.readLine();
                        System.out.println("请输入目标文件名");
                        bb=Bin.readLine();
                        fo.copyFile(aa,bb);
                        break;
                    case 7:System.out.println("你选择了功能七  请输入源文件名");
                        aa=Bin.readLine();
                        System.out.println("请输入目标文件名");
                        bb=Bin.readLine();
                        fo.copyFolder(aa,bb);
                        break;
                    case 8:System.out.println("你选择了功能八  请输入源文件名");
                        aa=Bin.readLine();
                        System.out.println("请输入目标文件名");
                        bb=Bin.readLine();
                        fo.moveFile(aa,bb);
                        break;
                    case 9:System.out.println("你选择了功能九  请输入源文件名");
                        aa=Bin.readLine();
                        System.out.println("请输入目标文件名");
                        bb=Bin.readLine();
                        fo.moveFolder(aa,bb);
                        break;
                    case 10:exitnow=true;
                        System.out.println("程序结束，请退出");
                        break;
                    default:System.out.println("输入错误.请输入1-10之间的数");
                }
                System.out.println("请重新选择功能");
            }
            catch(Exception e)
            {
                System.out.println("输入错误字符或程序出错");
            }
        }
    }
}

