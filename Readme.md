# Questionnaire
A java web for B/S course provides questionnaire service.

## 如何打包
* 先把前端项目打包
    * 在front文件夹下使用 npm run build
    * 将生成的static文件夹和index.html文件复制到后端项目的src/main/resources/static文件夹下
* 再将后端项目打包
    * 使用idea打成war包
    * 将war包放在tomcat服务器的webapp文件夹下