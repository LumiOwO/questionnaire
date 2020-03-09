# Questionnaire
A java web for B/S course provides questionnaire service.

## 如何打包
* 先把前端项目打包
    * 部署到服务器上时，将main.js的baseURL中主机ip地址改为服务器ip地址
    * 在front文件夹下使用 npm run build
    * 将生成的static文件夹和index.html文件复制到后端项目的src/main/resources/static文件夹下
* 再将后端项目打包
    * 使用idea打成war包
        * 先clean再package
    * 将war包放在tomcat服务器的webapp文件夹下

## 一些问题
* 手机与电脑在局域网中但无法访问页面
    * 关闭电脑防火墙
    * 前端config/index.js中host改为0.0.0.0
    * 前端src/main.js中baseURL的localhost改为电脑ip地址
* CSS样式不生效
    * https://blog.csdn.net/danruWang/article/details/88999024
    * 在main.js中，router模块最后import，防止样式被覆盖