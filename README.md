# wallegui
Walle（瓦力）新一代Android渠道包打包神器的界面版本, 高度Mac集成, Dock图标支持打包进度显示, 支持安卓7, 闪电一般的打包速度, 100个渠道包只需要几秒钟, 只依赖Java, 运营妹子也可以用! 懒汉福利: 自动保存输入文件路径和渠道列表.

作者: 刘长炯 BeanSoft@126.com (微信号 weblogic ). 

本项目打渠道包代码基于美团点评团队的瓦力: https://github.com/Meituan-Dianping/walle. 如何在Android代码读取渠道包信息请参考瓦力项目的说明. 更多信息请移步: 
[美团Android新一代渠道包生成工具](http://tech.meituan.com/android-apk-v2-signature-scheme.html) . 目前已用于河狸家新版Android APP的打包, 流量妹子表示打包速度太快太方便了!

老规矩上图:
<img src="https://raw.githubusercontent.com/beansoftapp/wallegui/master/screenshot/wallegui.png">

<img src="https://raw.githubusercontent.com/beansoftapp/wallegui/master/screenshot/walle_dock.png">

如果您有任何建议, 欢迎联系作者!

开发工具: IntelliJ IDEA 社区版

用法:
1. 如果没有安装JRE, 请访问 http://www.java.com/ 下载安装.
2. 双击 out/wallegui.jar, 然后选择APK文件, 输入渠道列表(以换行隔开), 点击按钮"生成渠道包", 即可在"输出渠道包"目录下看到所有的产生的渠道包. 最终打包结果可以用"渠道包集成检测..."来查看是否成功.

备注: wallegui.jar 也可复制到其它目录下面单独执行.

广告: 传统的友盟渠道包打包签名工具开源地址: https://github.com/beansoftapp/umeng-muti-channel-build-tool-java 优势: 不用改任何代码! 弱点: 打包速度略慢, 一分钟约60个渠道.

Build on top of following projects:<br>
https://github.com/rampi/properties-util<br>
https://github.com/bulenkov/Darcula<br>



